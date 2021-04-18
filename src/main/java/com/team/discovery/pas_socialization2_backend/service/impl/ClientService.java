package com.team.discovery.pas_socialization2_backend.service.impl;

import com.team.discovery.pas_socialization2_backend.controller.model.Aprobar;
import com.team.discovery.pas_socialization2_backend.controller.model.Despacho;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.Shipping;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.State;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.User;
import com.team.discovery.pas_socialization2_backend.repository.ShippingRepository;
import com.team.discovery.pas_socialization2_backend.service.IClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class ClientService implements IClientService {

    private ShippingRepository shippingRepository;

    public ClientService(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    @Override
    public List<Despacho> searchDispatchClient(int idUsuarioDestino) {

        User user = User.builder().id((long) idUsuarioDestino).build();

        List<Shipping> shippings = shippingRepository.findShippingsByUserAndState(user,State.DESPACHO_POR_APROBAR);
        List<Despacho> despachos = new ArrayList<>();
        if (despachos.isEmpty()) {

            for (Shipping shipping : shippings) {
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

        }else {
            log.info("Usuario no tiene despachos pendientes por aprobar");
        }

        return despachos;
    }

    @Override
    public Void approveDispatch(Aprobar requestAprobar) {
        return null;
    }

    @Override
    public ArrayList<Despacho> searchHistoricalDispatch(int id) {
        return null;
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
