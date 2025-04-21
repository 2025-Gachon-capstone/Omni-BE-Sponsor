package org.example.omnibesponsor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.example.omnibesponsor.common.apiPayload.ApiResult;
import org.example.omnibesponsor.dto.BenefitReqDto;
import org.example.omnibesponsor.dto.BenefitResDto;
import org.example.omnibesponsor.dto.CategoryReqDto;
import org.example.omnibesponsor.dto.CategoryResDto;
import org.example.omnibesponsor.entity.Category;
import org.example.omnibesponsor.service.BenefitService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sponsor/v1/benefits")
public class BenefitController {

    private final BenefitService benefitService;

    public BenefitController(BenefitService benefitService) {
        this.benefitService = benefitService;
    }

    @PostMapping()
    @Operation(summary = "혜택 생성 API",
            description = "채팅방 생성시 자동으로 생성됩니다. ReqeustParam을 이용해 sponsorId 입력해주세요. - ( 엑세스 토큰 필요 )",
            tags = "Benefit")
    public ApiResult<BenefitResDto.CreateBenefit> createBenefit(@Parameter(hidden = true) @RequestHeader("X-Authorization-Id") Long memberId,
                                                   @RequestParam Long sponsorId){

        return ApiResult.onSuccess(benefitService.createBenefit(memberId,sponsorId));

    }

    @PatchMapping("/{benefitId}")
    @Operation(summary = "혜택 갱신 API",
            description = " status에 PENDING이 아닌 값이 올 경우 최종 제출입니다. - ( 엑세스 토큰 필요 )",
            tags = "Benefit")
    public ApiResult<BenefitResDto.UpdateBenefit> updateBenefit(@Parameter(hidden = true) @RequestHeader("X-Authorization-Id") Long memberId,
                                      @PathVariable Long benefitId,
                                      @RequestBody BenefitReqDto.UpdateBenefit updateBenefitDto){

        return ApiResult.onSuccess(benefitService.updateBenefit(memberId,benefitId,updateBenefitDto));

    }

    @DeleteMapping("/{benefitId}")
    @Operation(summary = "혜택 삭제 API",
            description = " 삭제시 상태가 DELETE로 변경됩니다. - ( 엑세스 토큰 필요 )",
            tags = "Benefit")
    public ApiResult<BenefitResDto.DeleteBenefit> deleteBenefit(@Parameter(hidden = true) @RequestHeader("X-Authorization-Id") Long memberId,
                                                                @PathVariable Long benefitId){

        return ApiResult.onSuccess(benefitService.deleteBenefit(memberId,benefitId));

    }

}
