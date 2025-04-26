package org.example.omnibesponsor.service;

import org.example.omnibesponsor.dto.ProductCategoryReqDto;
import org.example.omnibesponsor.dto.ProductCategoryResDto;

public interface ProductCategoryService {

    ProductCategoryResDto.CreateProductCategory createProductCategory(ProductCategoryReqDto.CreateProductCategory productCategoryReqDto);

}
