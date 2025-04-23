package org.example.omnibesponsor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.example.omnibesponsor.common.apiPayload.ApiResult;
import org.example.omnibesponsor.dto.BenefitResDto;
import org.example.omnibesponsor.dto.ProductReqDto;
import org.example.omnibesponsor.dto.ProductResDto;
import org.example.omnibesponsor.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sponsor/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Operation(summary = "상품 생성 API",
            description = " 더미데이터 사용 예정 - ( 엑세스 토큰 필요 )",
            tags = "Product")
    public ApiResult<ProductResDto.CreateProduct> createBenefit(@RequestBody ProductReqDto.CreateProduct createProduct) {

        return ApiResult.onSuccess(productService.createProduct(createProduct));

    }

    @GetMapping
    @Operation(summary = "전체 상품 가져오기 API",
            description = " 페이징 필요 - ( page, size 만 적어도 됨, 스웨거에서 sort 는 배열이 아닌 빈문자열로 넣어주세요, 카테고리별 요청시 카테고리 id도 입력해주세요. ) - ( 토큰 필요 없음 )",
            tags = "Product")
    public ApiResult<ProductResDto.GetProductPage> getBenefit(@RequestParam(required = false) Long categoryId,
                                   @PageableDefault(size = 8, sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable) {

        return ApiResult.onSuccess(productService.getProducts(categoryId, pageable));

    }

}
