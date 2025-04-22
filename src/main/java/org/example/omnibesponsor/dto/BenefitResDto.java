package org.example.omnibesponsor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class BenefitResDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateBenefit{

        private Long sponsorId;
        private Long benefitId;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetBenefit{

        private Long benefitId;
        private int amount;
        private String benefitStatus;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetBatchBenefit{

        private Long benefitId;
        private String title;
        private String sponsorName;
        private Float discountRate;
        private LocalDate endDate;
        private int amount;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateBenefit{

        private Long benefitId;
        private String title;
        private String benefitStatus;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteBenefit{

        private Long benefitId;
        private String benefitStatus;

    }


}
