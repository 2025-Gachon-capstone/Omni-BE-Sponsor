package org.example.omnibesponsor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberBenefitReqDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SyncMemberBenefit{

        private Long benefitId;
        private String newStatus;

    }

}
