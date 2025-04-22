package org.example.omnibesponsor.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.omnibesponsor.client.CardClient;
import org.example.omnibesponsor.dto.CardBenefitReqDto;
import org.example.omnibesponsor.service.BenefitService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchedulerConfig {

    private final BenefitService benefitService;

    @Scheduled(cron = "0 0 0 * * *")
    public void updateAllBenefitStatuses() {
        try {
            benefitService.updateAllAndSyncBenefits();
        } catch (Exception e) {
            log.error("[스케줄러] 혜택 동기화 실패", e);
        }
    }

}
