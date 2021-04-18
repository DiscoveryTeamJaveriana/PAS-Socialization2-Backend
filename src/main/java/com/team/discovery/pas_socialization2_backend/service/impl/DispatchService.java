package com.team.discovery.pas_socialization2_backend.service.impl;

import com.team.discovery.pas_socialization2_backend.controller.model.Despacho;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.Shipping;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.State;
import com.team.discovery.pas_socialization2_backend.repository.DispatchRepository;
import com.team.discovery.pas_socialization2_backend.service.IDispatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DispatchService implements IDispatchService {
    private DispatchRepository dispatchRepository;

    public DispatchService (DispatchRepository dispatchRepository) {
        this.dispatchRepository = dispatchRepository;
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
                despachos.add(despacho);
            }
        }

        return despachos;
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
