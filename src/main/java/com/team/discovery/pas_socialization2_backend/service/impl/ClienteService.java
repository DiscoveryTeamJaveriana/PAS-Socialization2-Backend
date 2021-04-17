package com.team.discovery.pas_socialization2_backend.service.impl;

import com.team.discovery.pas_socialization2_backend.controller.model.Aprobar;
import com.team.discovery.pas_socialization2_backend.controller.model.Despacho;
import com.team.discovery.pas_socialization2_backend.service.IClientService;

import java.util.ArrayList;

public class ClienteService implements IClientService {
    @Override
    public Despacho searchDispatchClient(int idUsuarioDestino) {
        return null;
    }

    @Override
    public Void approveDispatch(Aprobar requestAprobar) {
        return null;
    }

    @Override
    public ArrayList<Despacho> searchHistoricalDispatch(int id) {
        return null;
    }
}
