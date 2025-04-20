package org.example.omnibesponsor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class BenefitResDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateBenefit{

        private Long sponsorId;
        private Long benefitId;

    }
}
