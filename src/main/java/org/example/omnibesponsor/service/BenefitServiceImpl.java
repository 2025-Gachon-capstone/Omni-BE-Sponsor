package org.example.omnibesponsor.service;

import lombok.extern.slf4j.Slf4j;
import org.example.omnibesponsor.common.apiPayload.code.status.ErrorStatus;
import org.example.omnibesponsor.common.apiPayload.exception.GeneralException;
import org.example.omnibesponsor.converter.BenefitConverter;
import org.example.omnibesponsor.dto.BenefitReqDto;
import org.example.omnibesponsor.dto.BenefitResDto;
import org.example.omnibesponsor.entity.Benefit;
import org.example.omnibesponsor.entity.Sponsor;
import org.example.omnibesponsor.entity.type.BenefitStatus;
import org.example.omnibesponsor.repository.BenefitRepository;
import org.example.omnibesponsor.repository.SponsorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class BenefitServiceImpl implements BenefitService {

    private final SponsorRepository sponsorRepository;
    private final BenefitRepository benefitRepository;

    public BenefitServiceImpl(SponsorRepository sponsorRepository, BenefitRepository benefitRepository) {
        this.sponsorRepository = sponsorRepository;
        this.benefitRepository = benefitRepository;
    }

    @Override
    @Transactional
    public BenefitResDto.CreateBenefit createBenefit(Long memberId, Long sponsorId) {

        Sponsor sponsor = sponsorRepository.findById(sponsorId)
                .orElseThrow(()-> new GeneralException(ErrorStatus._NOT_FOUND_SPONSOR));

        if (!sponsor.getMemberId().equals(memberId)) {
            throw new GeneralException(ErrorStatus._SPONSOR_FORBIDDEN);
        }

        Benefit benefit = Benefit.builder()
                .sponsor(sponsor)
                .status(BenefitStatus.PENDING)
                .build();

        Benefit savedBenefit = benefitRepository.save(benefit);
        sponsor.getBenefits().add(savedBenefit);

        return new BenefitResDto.CreateBenefit(sponsor.getSponsorId(),savedBenefit.getBenefitId());
    }

    @Override
    public BenefitResDto.GetBenefit getBenefit(Long benefitId) {

        Benefit benefit = benefitRepository.findById(benefitId)
                .orElseThrow(()-> new GeneralException(ErrorStatus._NOT_FOUND_BENEFIT));

        return BenefitConverter.toGetBenefit(benefit);
    }

    @Override
    @Transactional
    public BenefitResDto.UpdateBenefit updateBenefit(Long memeberId, Long benefitId, BenefitReqDto.UpdateBenefit updateBenefitDto) {

        Benefit benefit = benefitRepository.findById(benefitId)
                .orElseThrow(()-> new GeneralException(ErrorStatus._NOT_FOUND_BENEFIT));

        if(!benefit.getSponsor().getMemberId().equals(memeberId)){
            throw new GeneralException(ErrorStatus._SPONSOR_FORBIDDEN);
        }

        Benefit updatedBenefit = BenefitConverter.updateBenefit(benefit, updateBenefitDto);

        Benefit savedBenefit = benefitRepository.save(updatedBenefit);

        return BenefitConverter.toUpdateBenefit(savedBenefit);
    }

    @Override
    @Transactional
    public BenefitResDto.DeleteBenefit deleteBenefit(Long memeberId, Long benefitId) {

        Benefit benefit = benefitRepository.findById(benefitId)
                .orElseThrow(()-> new GeneralException(ErrorStatus._NOT_FOUND_BENEFIT));

        if(!benefit.getSponsor().getMemberId().equals(memeberId)){
            throw new GeneralException(ErrorStatus._SPONSOR_FORBIDDEN);
        }

        if(benefit.getStatus() == BenefitStatus.DELETED){
            throw new GeneralException(ErrorStatus._ALREADY_DELETED_BENEFIT);
        }

        benefit.setStatus(BenefitStatus.DELETED);

        Benefit deletedBenefit = benefitRepository.save(benefit);

        return BenefitConverter.toDeleteBenefit(deletedBenefit);
    }

    @Override
    @Transactional
    public void updateOngoingBenefits() {

        LocalDate today = LocalDate.now();
        try {
            List<Benefit> targetBenefits = benefitRepository.findOngoingTargets(today);

            for (Benefit benefit : targetBenefits) {
                benefit.setStatus(BenefitStatus.ONGOING);
            }

            benefitRepository.saveAll(targetBenefits);
            log.info("[스케줄러] ONGOING 상태로 {}건 변경", targetBenefits.size());

        } catch (Exception e) {
            log.error("[스케줄러] ONGOING 상태 업데이트 실패: {}", e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void updateExpiredBenefits() {

        LocalDate today = LocalDate.now();
        try {
            List<Benefit> targetBenefits = benefitRepository.findExpiredTargets(today);

            for (Benefit benefit : targetBenefits) {
                benefit.setStatus(BenefitStatus.EXPIRED);
            }

            benefitRepository.saveAll(targetBenefits);
            log.info("[스케줄러] EXPIRED 상태로 {}건 변경", targetBenefits.size());

        } catch (Exception e) {
            log.error("[스케줄러] EXPIRED 상태 업데이트 실패: {}", e.getMessage(), e);
        }

    }

}
