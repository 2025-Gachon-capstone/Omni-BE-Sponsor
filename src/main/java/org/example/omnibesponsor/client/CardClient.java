package org.example.omnibesponsor.client;

import org.example.omnibesponsor.common.apiPayload.ApiResult;
import org.example.omnibesponsor.dto.CardBenefitReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "cardClient", url = "${CARD_SERVER_ADDRESS}")
public interface CardClient {

    @PostMapping("/card/v1/cardBenefits/sync-status")
    void syncCardBenefits(@RequestBody List<CardBenefitReqDto.SyncCardBenefit> syncList);

    @GetMapping("/card/v1/cardBenefits/exist")
    ApiResult<Boolean> existsByBenefitId(@RequestParam("benefitId") Long benefitId);

}
