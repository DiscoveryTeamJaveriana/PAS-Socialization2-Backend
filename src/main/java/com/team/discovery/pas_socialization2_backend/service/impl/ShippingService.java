package com.team.discovery.pas_socialization2_backend.service.impl;

import java.util.List;

import com.team.discovery.pas_socialization2_backend.model.despachos_db.Shipping;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.State;
import com.team.discovery.pas_socialization2_backend.repository.ShippingRepository;
import com.team.discovery.pas_socialization2_backend.service.IShippingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ShippingService implements IShippingService {

    private final ShippingRepository shippingRepository;

    @Override
    public List<Shipping> getShippingByStatus(State state) {
        return shippingRepository.findShippingsByState(state);
    }

    @Override
    public void processPendingShipping(List<Shipping> shippingList) {
        //Call MOCKS
        log.info("Calling mocks...");
    }
}
