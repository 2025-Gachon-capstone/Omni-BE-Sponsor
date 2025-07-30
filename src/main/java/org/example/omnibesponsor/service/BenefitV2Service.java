package org.example.omnibesponsor.service;



import org.example.omnibesponsor.dto.BenefitReqDto;
import org.example.omnibesponsor.dto.BenefitResDto;

public interface BenefitV2Service {
    BenefitResDto.UpdateBenefit createBenefit(Long sponsorId, BenefitReqDto.CreateBenefit createBenefitDto);
}
