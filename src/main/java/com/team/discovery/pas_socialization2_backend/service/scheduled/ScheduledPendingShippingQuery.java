package com.team.discovery.pas_socialization2_backend.service.scheduled;

import java.util.List;
import javax.transaction.Transactional;

import com.team.discovery.pas_socialization2_backend.model.despachos_db.Shipping;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.State;
import com.team.discovery.pas_socialization2_backend.service.IShippingService;
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

    private IShippingService shippingService;

    @Transactional
    @Scheduled(cron = "${cron.pending_shipping}")
    public void getPendingShipping(){
        log.info("Starting cron pending_shipping");
        List<Shipping> shippingList = shippingService.getShippingByStatus(State.DESPACHO_CARGADO);
        if (!shippingList.isEmpty() && shippingList.size()>0) {
            shippingService.processPendingShipping(shippingList);
        }
        log.info("Finishing cron pending_shipping");
    }

}
