package org.example.omnibesponsor.service;

import lombok.extern.slf4j.Slf4j;
import org.example.omnibesponsor.client.CardClient;
import org.example.omnibesponsor.common.apiPayload.code.status.ErrorStatus;
import org.example.omnibesponsor.common.apiPayload.exception.GeneralException;
import org.example.omnibesponsor.converter.BenefitConverter;
import org.example.omnibesponsor.dto.BenefitReqDto;
import org.example.omnibesponsor.dto.BenefitResDto;
import org.example.omnibesponsor.dto.CardBenefitReqDto;
import org.example.omnibesponsor.entity.Benefit;
import org.example.omnibesponsor.entity.Sponsor;
import org.example.omnibesponsor.entity.type.BenefitStatus;
import org.example.omnibesponsor.repository.BenefitRepository;
import org.example.omnibesponsor.repository.SponsorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BenefitServiceImpl implements BenefitService {

    private final SponsorRepository sponsorRepository;
    private final BenefitRepository benefitRepository;
    private final CardClient cardClient;

    public BenefitServiceImpl(SponsorRepository sponsorRepository, BenefitRepository benefitRepository,
                              CardClient cardClient) {
        this.sponsorRepository = sponsorRepository;
        this.benefitRepository = benefitRepository;
        this.cardClient = cardClient;
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
                .discountRate(0f)
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
    public List<BenefitResDto.GetBatchBenefit> getBatchBenefit(List<Long> benefitIds) {
        List<Benefit> benefits = benefitRepository.findAllById(benefitIds);

        return benefits.stream()
                .map(BenefitConverter::toGetBatchBenefit)
                .toList();
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
    public List<CardBenefitReqDto.SyncCardBenefit> updateOngoingBenefits() {

        LocalDate today = LocalDate.now();
        List<CardBenefitReqDto.SyncCardBenefit> syncList = new ArrayList<>();
        try {
            List<Benefit> targetBenefits = benefitRepository.findOngoingTargets(today);

            for (Benefit benefit : targetBenefits) {
                benefit.setStatus(BenefitStatus.ONGOING);
                syncList.add(new CardBenefitReqDto.SyncCardBenefit(
                        benefit.getBenefitId(),
                        BenefitStatus.ONGOING.name()
                ));
            }

            benefitRepository.saveAll(targetBenefits);
            log.info("[스케줄러] ONGOING 상태로 {}건 변경", targetBenefits.size());

        } catch (Exception e) {
            log.error("[스케줄러] ONGOING 상태 업데이트 실패: {}", e.getMessage(), e);
        }
        return syncList;
    }

    @Override
    @Transactional
    public List<CardBenefitReqDto.SyncCardBenefit> updateExpiredBenefits() {

        LocalDate today = LocalDate.now();
        List<CardBenefitReqDto.SyncCardBenefit> syncList = new ArrayList<>();
        try {
            List<Benefit> targetBenefits = benefitRepository.findExpiredTargets(today);

            for (Benefit benefit : targetBenefits) {
                benefit.setStatus(BenefitStatus.EXPIRED);
                syncList.add(new CardBenefitReqDto.SyncCardBenefit(
                        benefit.getBenefitId(),
                        BenefitStatus.EXPIRED.name()
                ));
            }

            benefitRepository.saveAll(targetBenefits);
            log.info("[스케줄러] EXPIRED 상태로 {}건 변경", targetBenefits.size());

        } catch (Exception e) {
            log.error("[스케줄러] EXPIRED 상태 업데이트 실패: {}", e.getMessage(), e);
        }
        return syncList;
    }

    @Override
    public void updateAllAndSyncBenefits() {
        log.info("[트랜잭션] 혜택 + 카드혜택 상태 동기화 시작");

        List<CardBenefitReqDto.SyncCardBenefit> syncList = new ArrayList<>();

        syncList.addAll(updateOngoingBenefits());
        syncList.addAll(updateExpiredBenefits());

        try {
            cardClient.syncCardBenefits(syncList);
            log.info("[트랜잭션] 카드 서비스 상태 동기화 성공 ({}건)", syncList.size());
        } catch (Exception e) {
            log.error("[트랜잭션] 카드 서비스 동기화 실패 - 롤백 진행", e);
            throw new GeneralException(ErrorStatus._CARD_SERVICE_ERROR); // 전체 롤백 유도
        }

        log.info("[트랜잭션] 전체 혜택 동기화 완료");
    }

}
