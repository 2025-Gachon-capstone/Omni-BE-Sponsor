package org.example.omnibesponsor.service;

import org.example.omnibesponsor.dto.ProductReqDto;
import org.example.omnibesponsor.dto.ProductResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface ProductService {

    ProductResDto.CreateProduct createProduct(ProductReqDto.CreateProduct createProduct);
    ProductResDto.GetProductPage getProducts(Long categoryId,Pageable pageable);
    ProductResDto.GetProduct getDetailProduct(@PathVariable Long productId);
    List<ProductResDto.GetProductList> getProductList(ProductReqDto.GetProductList getProductList);
}
