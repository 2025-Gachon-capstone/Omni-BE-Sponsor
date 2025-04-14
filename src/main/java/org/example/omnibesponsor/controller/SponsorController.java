package org.example.omnibesponsor.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.omnibesponsor.common.apiPayload.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sponsor")
public class SponsorController {

    @GetMapping("/sponsor/test")
    @Operation(summary = "스폰서 테스트 API",description = "스폰서 테스트입니다.",tags = "Sponsor")
    public ApiResult<?> test(){
        return ApiResult.onSuccess("sponsor test");
    }
}
