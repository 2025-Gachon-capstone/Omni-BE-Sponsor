package org.example.omnibesponsor.converter;

import org.example.omnibesponsor.dto.ProductCategoryReqDto;
import org.example.omnibesponsor.entity.ProductCategory;

public class ProductCategoryConverter {

    public static ProductCategory toProductCategory(ProductCategoryReqDto.CreateProductCategory productCategoryReqDto) {

        return ProductCategory.builder()
                .name(productCategoryReqDto.getName())
                .build();

    }

}
