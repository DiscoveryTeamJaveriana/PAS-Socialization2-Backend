package com.team.discovery.pas_socialization2_backend.service;

import com.team.discovery.pas_socialization2_backend.model.despachos_db.Shipping;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.User;

public interface IOfferService {

    void postOffer(final Integer value, final Shipping shipping, final User usuarioTransporte);
}
