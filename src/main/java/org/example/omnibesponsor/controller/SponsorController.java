package org.example.omnibesponsor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "COMMON200-성공",content = @Content(schema = @Schema(implementation = ApiResult.class))),
            @ApiResponse(responseCode = "4001", description = "SPONSOR4001-이미 존재하는 스폰서입니다.",content = @Content(schema = @Schema(implementation = ApiResult.class))),
            @ApiResponse(responseCode = "4002", description = "CATEGORY4002-카테고리가 없습니다.",content = @Content(schema = @Schema(implementation = ApiResult.class))),
    })
    public ApiResult<SponsorResDto.CreateSponsor> createSponsor(@RequestBody SponsorReqDto.CreateSponsor CreateSponsorDto){

        return ApiResult.onSuccess(sponsorService.createSponsor(CreateSponsorDto));

    }

    @GetMapping("/sponsors/{memberId}")
    @Operation(summary = "스폰서 Id 가져오기 API",description = "서비스 끼리 통신입니다.",tags = "Service-Sponsor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "COMMON200-성공",content = @Content(schema = @Schema(implementation = ApiResult.class))),
            @ApiResponse(responseCode = "4002", description = "SPONSOR4002-없는 스폰서입니다.",content = @Content(schema = @Schema(implementation = ApiResult.class))),
    })
    public ApiResult<SponsorResDto.GetSponsorId> getSponsorId(@PathVariable Long memberId){

        return ApiResult.onSuccess(sponsorService.getSponsorId(memberId));
    }

}
