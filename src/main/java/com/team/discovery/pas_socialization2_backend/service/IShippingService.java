package com.team.discovery.pas_socialization2_backend.service;

import java.util.List;

import com.team.discovery.pas_socialization2_backend.model.despachos_db.Shipping;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.State;

public interface IShippingService {

    List<Shipping> getShippingByStatus(final State state);

    void processPendingShipping(final List<Shipping> shippingList);
}
