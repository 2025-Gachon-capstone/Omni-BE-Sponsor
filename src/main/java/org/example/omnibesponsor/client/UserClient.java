package org.example.omnibesponsor.client;

import org.example.omnibesponsor.common.apiPayload.ApiResult;
import org.example.omnibesponsor.dto.MemberBenefitReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "userClient", url = "${USER_SERVER_ADDRESS}")
public interface UserClient {

    @PostMapping("/user/v1/memberBenefits/sync-status")
    void syncMemberBenefits(@RequestBody List<MemberBenefitReqDto.SyncMemberBenefit> syncList);

    @GetMapping("/user/v1/memberBenefits/exist")
    ApiResult<Boolean> existsByBenefitId(@RequestParam("benefitId") Long benefitId);


}
