package com.team.discovery.pas_socialization2_backend.service;

import com.team.discovery.pas_socialization2_backend.model.despachos_db.Shipping;

public interface IShippingProvider {

    Integer getOffer(final Shipping shipping);
}
