package org.example.omnibesponsor.service;

import org.example.omnibesponsor.dto.ProductReqDto;
import org.example.omnibesponsor.dto.ProductResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {

    ProductResDto.CreateProduct createProduct(ProductReqDto.CreateProduct createProduct);
    ProductResDto.GetProductPage getProducts(Long categoryId,Pageable pageable);

}
