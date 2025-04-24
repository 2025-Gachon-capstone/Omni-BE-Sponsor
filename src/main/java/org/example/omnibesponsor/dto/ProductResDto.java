package org.example.omnibesponsor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

public class ProductResDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateProduct {

        private Long productId;
        private String productName;
        private BigDecimal productPrice;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetProduct {

        private Long productId;
        private String productName;
        private BigDecimal productPrice;
        private String categoryTitle;
        private String sponsorName;
        private String imageUrl;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetProductPage{

        List<ProductResDto.GetProduct> products;
        boolean isFirst;
        boolean isLast;
        int pageSize;
        long totalElements;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetProductList{

        private Long productId;
        private String productName;
        private BigDecimal productPrice;

    }

}
