package org.example.omnibesponsor.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.omnibesponsor.common.apiPayload.ApiResult;
import org.example.omnibesponsor.dto.SponsorReqDto;
import org.example.omnibesponsor.dto.SponsorResDto;
import org.example.omnibesponsor.entity.Sponsor;
import org.example.omnibesponsor.repository.SponsorRepository;
import org.example.omnibesponsor.service.SponsorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sponsor/v1")
public class SponsorController {

    private final SponsorService sponsorService;

    public SponsorController(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    @PostMapping("/sponsors")
    @Operation(summary = "스폰서 생성 API",description = "스폰서 생성 API입니다. 서비스 끼리 통신 입니다.",tags = "Service-Sponsor")
    public ApiResult<?> createSponsor(@RequestBody SponsorReqDto.CreateSponsor CreateSponsorDto){

        Sponsor savedSponsor = sponsorService.createSponsor(CreateSponsorDto);
        return ApiResult.onSuccess(SponsorResDto.CreateSponsor.from(savedSponsor));

    }

    @GetMapping("/sponsors/{memberId}")
    @Operation(summary = "스폰서 Id 가져오기 API",description = "서비스 끼리 통신입니다.",tags = "Service-Sponsor")
    public ApiResult<SponsorResDto.GetSponsorId> getSponsorId(@PathVariable Long memberId){

        return ApiResult.onSuccess(sponsorService.getSponsorId(memberId));
    }


}
