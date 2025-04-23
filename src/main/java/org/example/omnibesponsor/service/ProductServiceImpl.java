package org.example.omnibesponsor.service;

import org.example.omnibesponsor.common.apiPayload.code.status.ErrorStatus;
import org.example.omnibesponsor.common.apiPayload.exception.GeneralException;
import org.example.omnibesponsor.converter.ProductConverter;
import org.example.omnibesponsor.dto.ProductReqDto;
import org.example.omnibesponsor.dto.ProductResDto;
import org.example.omnibesponsor.entity.Product;
import org.example.omnibesponsor.entity.Sponsor;
import org.example.omnibesponsor.repository.ProductRepository;
import org.example.omnibesponsor.repository.SponsorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final SponsorRepository sponsorRepository;
    private final ProductRepository productRepository;

    public ProductServiceImpl(SponsorRepository sponsorRepository, ProductRepository productRepository) {
        this.sponsorRepository = sponsorRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public ProductResDto.CreateProduct createProduct(ProductReqDto.CreateProduct createProduct) {

        Sponsor sponsor = sponsorRepository.findById(createProduct.getSponsorId())
                .orElseThrow(()-> new GeneralException(ErrorStatus._NOT_FOUND_SPONSOR));

        Product savedProduct = productRepository.save(ProductConverter.creatProduct(createProduct,sponsor));

        return ProductConverter.toCreateProduct(savedProduct);
    }

    @Override
    public ProductResDto.GetProductPage getProducts(Long categoryId, Pageable pageable) {

        Page<Product> products;

        if (categoryId != null) {
            products = productRepository.findBySponsor_Category_CategoryId(categoryId, pageable);
        } else {
            products = productRepository.findAll(pageable);
        }

        return ProductConverter.toGetProductPage(products);
    }

    @Override
    public ProductResDto.GetProduct getDetailProduct(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(()->new GeneralException(ErrorStatus._NOT_FOUND_PRODUCT));

        return ProductConverter.toGetProduct(product);

    }
}
