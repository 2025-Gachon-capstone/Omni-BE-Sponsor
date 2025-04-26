package org.example.omnibesponsor.converter;

import org.example.omnibesponsor.dto.ProductCategoryReqDto;
import org.example.omnibesponsor.dto.ProductCategoryResDto;
import org.example.omnibesponsor.entity.ProductCategory;

import java.util.List;
import java.util.stream.Collectors;

public class ProductCategoryConverter {

    public static ProductCategory toProductCategory(ProductCategoryReqDto.CreateProductCategory productCategoryReqDto) {

        return ProductCategory.builder()
                .name(productCategoryReqDto.getName())
                .build();

    }

    public static ProductCategoryResDto.GetProductCategory getProductCategory(ProductCategory productCategory) {
        return ProductCategoryResDto.GetProductCategory.builder()
                .productCategoryId(productCategory.getProductCategoryId())
                .name(productCategory.getName())
                .build();
    }

    public static List<ProductCategoryResDto.GetProductCategory> getProductCategory(List<ProductCategory> productCategories) {
        return productCategories.stream()
                .map(ProductCategoryConverter::getProductCategory)
                .collect(Collectors.toList());
    }

}
