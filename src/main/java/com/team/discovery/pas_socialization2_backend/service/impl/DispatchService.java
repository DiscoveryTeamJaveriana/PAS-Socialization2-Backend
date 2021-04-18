package com.team.discovery.pas_socialization2_backend.service.impl;

import com.team.discovery.pas_socialization2_backend.controller.model.Cotizar;
import com.team.discovery.pas_socialization2_backend.controller.model.Despacho;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.Offer;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.Shipping;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.State;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.User;
import com.team.discovery.pas_socialization2_backend.repository.DispatchRepository;
import com.team.discovery.pas_socialization2_backend.repository.OfferRepository;
import com.team.discovery.pas_socialization2_backend.repository.ShippingRepository;
import com.team.discovery.pas_socialization2_backend.service.IDispatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DispatchService implements IDispatchService {
    private DispatchRepository dispatchRepository;
    private OfferRepository offerRepository;
    private ShippingRepository shippingRepository;

    public DispatchService(DispatchRepository dispatchRepository, OfferRepository offerRepository, ShippingRepository shippingRepository) {
        this.dispatchRepository = dispatchRepository;
        this.offerRepository = offerRepository;
        this.shippingRepository = shippingRepository;
    }

    @Override
    public List<Despacho> searchDispatches(State state) {
        List<Shipping> shippings = dispatchRepository.findShippingByState(state);
        List<Despacho> despachos = new ArrayList<>();

        if (shippings != null){
            for (Shipping shipping : shippings){
                Despacho despacho = new Despacho();
                despacho.setId(shipping.getId().intValue());
                despacho.setCantidadCajas(shipping.getBoxesAmount().intValue());
                despacho.setPesoTotal(shipping.getTotalWeight().intValue());
                despacho.setIdEstado(getStateID(shipping.getState()));
                despacho.setIdUsuarioDestino(shipping.getUser().getId().intValue());
                despacho.setMejorOferta(shipping.getBestOffer().intValue());
                despacho.setTransportadora(shipping.getDispatcher());
                despachos.add(despacho);
            }
        }

        return despachos;
    }

    @Override
    public void createOffer(long id, Cotizar cotizar) {
        Shipping shipping = new Shipping();
        shipping.setId(id);
        User usuarioTransporte = new User();
        usuarioTransporte.setId(cotizar.getIdUsuarioTransporte().longValue());
        Offer offerSave = Offer.builder().shipping(shipping).userTransport(usuarioTransporte).value(cotizar.getOferta()).build();
        offerRepository.save(offerSave);
        log.info("Successful Offer creation");

        List<Offer> offers = offerRepository.findAll().stream().filter(offer -> offer.getShipping().getId().intValue() == id).collect(Collectors.toList());
        shipping.setBestOffer(0L);
        for (Offer offer : offers){
            if(offer.getValue() > shipping.getBestOffer()){
                shipping = offer.getShipping();
                shipping.setBestOffer(offer.getValue().longValue());
                shipping.setDispatcher(offer.getUserTransport().getName());
            }
        }
        shippingRepository.save(shipping);
        log.info("Successful Shipping update");
    }


    public State getStateEnum(int id) {
        switch (id) {
            case 1:
                return State.DESPACHO_CARGADO;
            case 2:
                return State.DESPACHO_OFERTADO;
            case 3:
                return State.DESPACHO_POR_APROBAR;
            case 4:
                return State.DESPACHO_APROBADO;
            case 5:
                return State.DESPACHO_RECHAZADO;
            default:
                return State.DESPACHO_ENVIADO;
        }

    }

    public int getStateID(State stateEnum) {
        switch (stateEnum) {
            case DESPACHO_CARGADO:
                return 1;
            case DESPACHO_OFERTADO:
                return 2;
            case DESPACHO_POR_APROBAR:
                return 3;
            case DESPACHO_APROBADO:
                return 4;
            case DESPACHO_RECHAZADO:
                return 5;
            default:
                return 6;
        }

    }
}
