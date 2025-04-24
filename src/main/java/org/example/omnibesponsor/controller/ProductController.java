package org.example.omnibesponsor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "COMMON200-성공",content = @Content(schema = @Schema(implementation = ApiResult.class))),
            @ApiResponse(responseCode = "4002", description = "SPONSOR4002-없는 스폰서입니다.",content = @Content(schema = @Schema(implementation = ApiResult.class))),
    })
    public ApiResult<ProductResDto.CreateProduct> createProduct(@RequestBody ProductReqDto.CreateProduct createProduct) {

        return ApiResult.onSuccess(productService.createProduct(createProduct));

    }

    @GetMapping
    @Operation(summary = "전체 상품 가져오기 API",
            description = " 페이징 필요 - ( page, size 만 적어도 됨, 스웨거에서 sort 는 배열이 아닌 빈문자열로 넣어주세요, 카테고리별 요청시 카테고리 id도 입력해주세요. ) - ( 토큰 필요 없음 )",
            tags = "Product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "COMMON200-성공",content = @Content(schema = @Schema(implementation = ApiResult.class))),
    })
    public ApiResult<ProductResDto.GetProductPage> getProduct(@RequestParam(required = false) Long categoryId,
                                   @PageableDefault(size = 8, sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable) {

        return ApiResult.onSuccess(productService.getProducts(categoryId, pageable));

    }

    @GetMapping("/{productId}")
    @Operation(summary = "상세 상품 가져오기 API",
            description = " ( 토큰 필요 없음 )",
            tags = "Product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "COMMON200-성공",content = @Content(schema = @Schema(implementation = ApiResult.class))),
            @ApiResponse(responseCode = "4001", description = "PRODUCT4001-상품이 없습니다.",content = @Content(schema = @Schema(implementation = ApiResult.class))),
    })
    public ApiResult<ProductResDto.GetProduct> getDetailProduct(@PathVariable Long productId) {

        return ApiResult.onSuccess(productService.getDetailProduct(productId));

    }

}
