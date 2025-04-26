package org.example.omnibesponsor.service;

import org.example.omnibesponsor.common.apiPayload.code.status.ErrorStatus;
import org.example.omnibesponsor.common.apiPayload.exception.GeneralException;
import org.example.omnibesponsor.converter.ProductCategoryConverter;
import org.example.omnibesponsor.dto.ProductCategoryReqDto;
import org.example.omnibesponsor.dto.ProductCategoryResDto;
import org.example.omnibesponsor.entity.ProductCategory;
import org.example.omnibesponsor.repository.ProductCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    @Transactional
    public ProductCategoryResDto.CreateProductCategory createProductCategory(ProductCategoryReqDto.CreateProductCategory productCategoryReqDto) {

        if (productCategoryRepository.findByName(productCategoryReqDto.getName()).isPresent()) {
            throw new GeneralException(ErrorStatus._ALREADY_EXIST_PRODUCTCATEGORY);
        }

        ProductCategory savedProductCategory = productCategoryRepository.save(
                ProductCategoryConverter.toProductCategory(productCategoryReqDto));

        return new ProductCategoryResDto.CreateProductCategory(savedProductCategory.getProductCategoryId(), savedProductCategory.getName());

    }
}
