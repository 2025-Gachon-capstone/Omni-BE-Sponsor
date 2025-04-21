package org.example.omnibesponsor.service;

import org.example.omnibesponsor.dto.BenefitReqDto;
import org.example.omnibesponsor.dto.BenefitResDto;

public interface BenefitService {
    BenefitResDto.CreateBenefit createBenefit(Long memberId, Long sponsorId);
    BenefitResDto.GetBenefit getBenefit(Long benefitId);
    BenefitResDto.UpdateBenefit updateBenefit(Long memeberId, Long benefitId, BenefitReqDto.UpdateBenefit updateBenefitDto);
    BenefitResDto.DeleteBenefit deleteBenefit(Long memeberId, Long benefitId);
    void updateOngoingBenefits();
    void updateExpiredBenefits();
}
