package org.example.omnibesponsor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.omnibesponsor.common.apiPayload.ApiResult;
import org.example.omnibesponsor.dto.BenefitReqDto;
import org.example.omnibesponsor.dto.BenefitResDto;
import org.example.omnibesponsor.service.BenefitV2Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sponsor/v2/benefits")
public class BenefitV2Controller {

    private final BenefitV2Service benefitV2Service;

    public BenefitV2Controller(BenefitV2Service benefitV2Service) {
        this.benefitV2Service = benefitV2Service;
    }

    @PostMapping
    @Operation(summary = "혜택 생성 API V2",
            description = "서비스 통신입니다.",
            tags = "Service-Benefit")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "COMMON200-성공",content = @Content(schema = @Schema(implementation = ApiResult.class))),
            @ApiResponse(responseCode = "4002", description = "SPONSOR4002-없는 스폰서입니다.",content = @Content(schema = @Schema(implementation = ApiResult.class))),
    })
    public ApiResult<BenefitResDto.UpdateBenefit> createBenefit(@RequestParam Long sponsorId,
                                                                @RequestBody BenefitReqDto.CreateBenefit createBenefitDto){

        return ApiResult.onSuccess(benefitV2Service.createBenefit(sponsorId, createBenefitDto));

    }
}
