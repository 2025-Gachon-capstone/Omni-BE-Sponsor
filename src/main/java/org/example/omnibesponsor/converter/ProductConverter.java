package org.example.omnibesponsor.converter;

import org.example.omnibesponsor.dto.ProductReqDto;
import org.example.omnibesponsor.dto.ProductResDto;
import org.example.omnibesponsor.entity.Product;
import org.example.omnibesponsor.entity.Sponsor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConverter {

    public static Product creatProduct(ProductReqDto.CreateProduct createProduct, Sponsor sponsor) {

        return Product.builder()
                .productName(createProduct.getProductName())
                .productPrice(createProduct.getProductPrice())
                .imageUrl("defaultImageUrl")
                .sponsor(sponsor)
                .build();
    }

    public static ProductResDto.CreateProduct toCreateProduct(Product product) {

        return ProductResDto.CreateProduct.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .build();
    }

    public static ProductResDto.GetProduct toGetProduct(Product product) {

        return ProductResDto.GetProduct.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .categoryTitle(product.getSponsor().getCategory().getTitle())
                .sponsorName(product.getSponsor().getSponsorName())
                .imageUrl(product.getImageUrl())
                .build();
    }

    public static ProductResDto.GetProductPage toGetProductPage(Page<Product> productPage) {

        List<ProductResDto.GetProduct> productList = productPage.stream()
                .map(ProductConverter::toGetProduct)
                .collect(Collectors.toList());

        return ProductResDto.GetProductPage.builder()
                .products(productList)
                .isFirst(productPage.isFirst())
                .isLast(productPage.isLast())
                .pageSize(productPage.getTotalPages())
                .totalElements(productPage.getTotalElements())
                .build();
    }

    public static ProductResDto.GetProductList toGetProductList(Product product) {

        return ProductResDto.GetProductList.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .build();
    }

    public static List<ProductResDto.GetProductList> toGetProductList(List<Product> products){

        return products.stream()
                .map(ProductConverter::toGetProductList)
                .collect(Collectors.toList());
    }

}
