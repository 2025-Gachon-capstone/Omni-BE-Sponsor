package org.example.omnibesponsor.dto;

import lombok.Getter;

import java.math.BigDecimal;

public class ProductReqDto {

    @Getter
    public static class CreateProduct{

        private Long sponsorId;
        private String productName;
        private BigDecimal productPrice;

    }

}
