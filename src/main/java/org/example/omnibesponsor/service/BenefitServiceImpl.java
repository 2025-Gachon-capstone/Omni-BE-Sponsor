package org.example.omnibesponsor.service;

import org.example.omnibesponsor.common.apiPayload.code.status.ErrorStatus;
import org.example.omnibesponsor.common.apiPayload.exception.GeneralException;
import org.example.omnibesponsor.dto.BenefitResDto;
import org.example.omnibesponsor.entity.Benefit;
import org.example.omnibesponsor.entity.Sponsor;
import org.example.omnibesponsor.entity.type.BenefitStatus;
import org.example.omnibesponsor.repository.BenefitRepository;
import org.example.omnibesponsor.repository.SponsorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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

}
