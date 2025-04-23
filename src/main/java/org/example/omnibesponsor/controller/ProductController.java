package org.example.omnibesponsor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.example.omnibesponsor.common.apiPayload.ApiResult;
import org.example.omnibesponsor.dto.BenefitResDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sponsor/v1/products")
public class ProductController {

    @PostMapping
    @Operation(summary = "상품 생성 API",
            description = " 더미데이터 사용 예정 - ( 엑세스 토큰 필요 )",
            tags = "Product")
    public ApiResult<?> createBenefit(){

        return ApiResult.onSuccess();

    }

}
