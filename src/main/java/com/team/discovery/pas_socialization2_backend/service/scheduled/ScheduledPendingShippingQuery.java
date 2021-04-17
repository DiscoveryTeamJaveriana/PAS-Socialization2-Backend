package com.team.discovery.pas_socialization2_backend.service.scheduled;

import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@EnableScheduling
@Component
public class ScheduledPendingShippingQuery {

    @Transactional
    @Scheduled(cron = "${cron.pending_shipping}")
    public void getPendingShipping(){
        log.info("Starting query");
        log.info("Finishing query");
    }

}
