package org.example.omnibesponsor.converter;

import org.example.omnibesponsor.dto.BenefitReqDto;
import org.example.omnibesponsor.dto.BenefitResDto;
import org.example.omnibesponsor.entity.Benefit;
import org.example.omnibesponsor.entity.type.BenefitStatus;

import java.util.List;
import java.util.stream.Collectors;

public class BenefitConverter {

    public static BenefitResDto.GetBenefit toGetBenefit(Benefit benefit) {

        return BenefitResDto.GetBenefit.builder()
                .benefitId(benefit.getBenefitId())
                .amount(benefit.getAmount())
                .benefitStatus(String.valueOf(benefit.getStatus()))
                .build();
    }

    public static BenefitResDto.GetBatchBenefit toGetBatchBenefit(Benefit benefit){

        return BenefitResDto.GetBatchBenefit.builder()
                .benefitId(benefit.getBenefitId())
                .title(benefit.getTitle())
                .sponsorName(benefit.getSponsor().getSponsorName())
                .discountRate(benefit.getDiscountRate())
                .endDate(benefit.getEndDate())
                .amount(benefit.getAmount())
                .targetProduct(benefit.getTargetProduct())
                .build();
    }

    public static Benefit updateBenefit(Benefit benefit, BenefitReqDto.UpdateBenefit dto){

        if (dto.getTitle() != null) benefit.setTitle(dto.getTitle());
        if (dto.getStartDate() != null) benefit.setStartDate(dto.getStartDate());
        if (dto.getEndDate() != null) benefit.setEndDate(dto.getEndDate());
        if (dto.getDiscountRate() != null) benefit.setDiscountRate(dto.getDiscountRate());
        if (dto.getTargetProduct() != null) benefit.setTargetProduct(dto.getTargetProduct());
        if (dto.getAmount() != null) benefit.setAmount(dto.getAmount());
        if (dto.getTargetMember() != null) benefit.setTargetMember(dto.getTargetMember());
        if (!"PENDING".equals(dto.getStatus())) benefit.setStatus(BenefitStatus.BEFORE);

        return benefit;

    }

    public static BenefitResDto.UpdateBenefit toUpdateBenefit(Benefit benefit){

        return BenefitResDto.UpdateBenefit.builder()
                .benefitId(benefit.getBenefitId())
                .title(benefit.getTitle())
                .benefitStatus(String.valueOf(benefit.getStatus()))
                .build();

    }

    public static BenefitResDto.DeleteBenefit toDeleteBenefit(Benefit benefit){

        return BenefitResDto.DeleteBenefit.builder()
                .benefitId(benefit.getBenefitId())
                .benefitStatus(String.valueOf(benefit.getStatus()))
                .build();

    }

}
