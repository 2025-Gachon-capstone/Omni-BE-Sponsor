package org.example.omnibesponsor.service;

import org.example.omnibesponsor.common.apiPayload.code.status.ErrorStatus;
import org.example.omnibesponsor.common.apiPayload.exception.GeneralException;
import org.example.omnibesponsor.converter.ProductConverter;
import org.example.omnibesponsor.dto.ProductReqDto;
import org.example.omnibesponsor.dto.ProductResDto;
import org.example.omnibesponsor.entity.Product;
import org.example.omnibesponsor.entity.ProductCategory;
import org.example.omnibesponsor.entity.Sponsor;
import org.example.omnibesponsor.repository.ProductCategoryRepository;
import org.example.omnibesponsor.repository.ProductRepository;
import org.example.omnibesponsor.repository.SponsorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final SponsorRepository sponsorRepository;
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    public ProductServiceImpl(SponsorRepository sponsorRepository, ProductRepository productRepository,
                              ProductCategoryRepository productCategoryRepository) {
        this.sponsorRepository = sponsorRepository;
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    @Transactional
    public ProductResDto.CreateProduct createProduct(ProductReqDto.CreateProduct createProduct) {

        Sponsor sponsor = sponsorRepository.findById(createProduct.getSponsorId())
                .orElseThrow(()-> new GeneralException(ErrorStatus._NOT_FOUND_SPONSOR));

        ProductCategory productCategory = productCategoryRepository.findById(createProduct.getProductCategoryId())
                .orElseThrow(()-> new GeneralException(ErrorStatus._NOT_FOUND_PRODUCTCATEGORY));

        Product savedProduct = productRepository.save(ProductConverter.createProduct(createProduct,sponsor,productCategory));

        productCategory.getProducts().add(savedProduct);

        return ProductConverter.toCreateProduct(savedProduct);
    }

    @Override
    public ProductResDto.GetProductPage getProducts(Long productCategoryId, Pageable pageable) {

        Page<Product> products;

        if (productCategoryId != null) {
            products = productRepository.findByProductCategory_ProductCategoryId(productCategoryId, pageable);
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

    @Override
    public List<ProductResDto.GetProductList> getProductList(ProductReqDto.GetProductList getProductList) {
        List<Product> products = productRepository.findAllById(getProductList.getProductIds());

        if (products.isEmpty()) {
            throw new GeneralException(ErrorStatus._NOT_FOUND_PRODUCT);
        }

        return ProductConverter.toGetProductList(products);
    }
}
