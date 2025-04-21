package org.example.omnibesponsor.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.omnibesponsor.service.BenefitService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchedulerConfig {

    private final BenefitService benefitService;

    @Scheduled(cron = "0 0 0 * * *")
    public void updateAllBenefitStatuses() {
        log.info("[스케줄러] 혜택 상태 갱신 시작");

        try {
            benefitService.updateOngoingBenefits();
            benefitService.updateExpiredBenefits();
            log.info("[스케줄러] 혜택 상태 갱신 완료");
        } catch (Exception e) {
            log.error("[스케줄러] 혜택 상태 갱신 실패", e);
        }
    }

}
