package org.example.omnibesponsor.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDate;

public class BenefitReqDto {


    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class UpdateBenefit{

        private String title;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate startDate;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate endDate;

        private Float discountRate;
        private String targetProduct;
        private Integer amount;
        private String targetMember;
        private String status;

    }

}
