package org.example.omnibesponsor.service;

import org.example.omnibesponsor.dto.ProductCategoryReqDto;
import org.example.omnibesponsor.dto.ProductCategoryResDto;

import java.util.List;

public interface ProductCategoryService {

    ProductCategoryResDto.CreateProductCategory createProductCategory(ProductCategoryReqDto.CreateProductCategory productCategoryReqDto);
    List<ProductCategoryResDto.GetProductCategory> getAllProductCategories();

}
