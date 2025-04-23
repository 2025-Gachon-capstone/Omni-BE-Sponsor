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
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final SponsorRepository sponsorRepository;
    private final ProductRepository productRepository;

    public ProductServiceImpl(SponsorRepository sponsorRepository, ProductRepository productRepository) {
        this.sponsorRepository = sponsorRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ProductResDto.CreateProduct createProduct(ProductReqDto.CreateProduct createProduct) {

        Sponsor sponsor = sponsorRepository.findById(createProduct.getSponsorId())
                .orElseThrow(()-> new GeneralException(ErrorStatus._NOT_FOUND_SPONSOR));

        Product savedProduct = productRepository.save(ProductConverter.CreatProduct(createProduct));

        return ProductConverter.ToCreateProduct(savedProduct);
    }
}
