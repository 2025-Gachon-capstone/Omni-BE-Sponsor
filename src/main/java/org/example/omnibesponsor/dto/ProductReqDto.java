package org.example.omnibesponsor.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

public class ProductReqDto {

    @Getter
    public static class CreateProduct{

        private Long sponsorId;
        private String productName;
        private BigDecimal productPrice;

    }

    @Getter
    public static class GetProductList{

        List<Long> productIds;

    }

}
