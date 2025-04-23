package org.example.omnibesponsor.converter;

import org.example.omnibesponsor.dto.ProductReqDto;
import org.example.omnibesponsor.dto.ProductResDto;
import org.example.omnibesponsor.entity.Product;
import org.example.omnibesponsor.entity.Sponsor;

public class ProductConverter {

    public static Product CreatProduct(ProductReqDto.CreateProduct createProduct, Sponsor sponsor) {

        return Product.builder()
                .productName(createProduct.getProductName())
                .productPrice(createProduct.getProductPrice())
                .imageUrl("defaultImageUrl")
                .sponsor(sponsor)
                .build();
    }

    public static ProductResDto.CreateProduct ToCreateProduct(Product product) {

        return ProductResDto.CreateProduct.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .build();
    }

}
