package org.example.omnibesponsor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.omnibesponsor.common.apiPayload.ApiResult;
import org.example.omnibesponsor.dto.ProductCategoryReqDto;
import org.example.omnibesponsor.dto.ProductCategoryResDto;
import org.example.omnibesponsor.service.ProductCategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sponsor/v1/productCategories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @PostMapping
    @Operation(summary = "상품 목록 생성 API", description = " - 상품별 카테고리 생성입니다. ( 액세스 토큰 필요 ) ", tags = "ProductCategory")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "COMMON200-성공",content = @Content(schema = @Schema(implementation = ApiResult.class))),
    })
    public ApiResult<ProductCategoryResDto.CreateProductCategory> createProductCategory(@Valid @RequestBody ProductCategoryReqDto.CreateProductCategory reqDto){

        return ApiResult.onSuccess(productCategoryService.createProductCategory(reqDto));

    }

}
