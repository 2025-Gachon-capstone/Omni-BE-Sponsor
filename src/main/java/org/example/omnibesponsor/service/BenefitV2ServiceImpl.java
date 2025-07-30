package org.example.omnibesponsor.service;

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

import java.time.LocalDate;

@Service
public class BenefitV2ServiceImpl implements BenefitV2Service {

    private final SponsorRepository sponsorRepository;
    private final BenefitRepository benefitRepository;

    public BenefitV2ServiceImpl(SponsorRepository sponsorRepository, BenefitRepository benefitRepository) {
        this.sponsorRepository = sponsorRepository;
        this.benefitRepository = benefitRepository;
    }

    @Override
    public BenefitResDto.UpdateBenefit createBenefit(Long sponsorId, BenefitReqDto.CreateBenefit createBenefitDto) {

        Sponsor sponsor = sponsorRepository.findById(sponsorId)
                .orElseThrow(()-> new GeneralException(ErrorStatus._NOT_FOUND_SPONSOR));

        Benefit benefit = Benefit.builder()
                .sponsor(sponsor)
                .title(createBenefitDto.getTitle())
                .startDate(createBenefitDto.getStartDate())
                .endDate(createBenefitDto.getEndDate())
                .discountRate(createBenefitDto.getDiscountRate())
                .targetProduct(createBenefitDto.getTargetProduct())
                .amount(createBenefitDto.getAmount())
                .build();

        String requestedStatus = createBenefitDto.getStatus();

        if ("PENDING".equals(requestedStatus)) {
            benefit.setStatus(BenefitStatus.PENDING);
        } else {
            LocalDate startDate = createBenefitDto.getStartDate() != null ? createBenefitDto.getStartDate() : benefit.getStartDate();

            if (startDate == null || LocalDate.now().isBefore(startDate)) {
                benefit.setStatus(BenefitStatus.BEFORE);
            } else {
                benefit.setStatus(BenefitStatus.ONGOING);
            }
        }

        Benefit savedBenefit = benefitRepository.save(benefit);

        return BenefitConverter.toUpdateBenefit(benefit);
    }
}


