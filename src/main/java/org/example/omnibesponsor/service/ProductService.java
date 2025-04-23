package org.example.omnibesponsor.service;

import org.example.omnibesponsor.dto.ProductReqDto;
import org.example.omnibesponsor.dto.ProductResDto;


public interface ProductService {

    ProductResDto.CreateProduct createProduct(ProductReqDto.CreateProduct createProduct);

}
