package org.example.omnibesponsor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CardBenefitReqDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SyncCardBenefit{

        private Long benefitId;
        private String newStatus;

    }

}
