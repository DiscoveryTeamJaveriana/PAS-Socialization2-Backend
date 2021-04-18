package com.team.discovery.pas_socialization2_backend.service.impl;

import com.team.discovery.pas_socialization2_backend.model.despachos_db.Offer;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.Shipping;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.User;
import com.team.discovery.pas_socialization2_backend.repository.OfferRepository;
import com.team.discovery.pas_socialization2_backend.service.IOfferService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OfferService implements IOfferService {

    private final OfferRepository offerRepository;

    @Override
    public void postOffer(Integer value, Shipping shipping, User usuarioTransporte) {
        Offer offer = Offer.builder().shipping(shipping).userTransport(usuarioTransporte).value(value).build();
        offerRepository.save(offer);
    }
}
