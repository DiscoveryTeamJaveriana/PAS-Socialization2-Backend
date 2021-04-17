package com.team.discovery.pas_socialization2_backend.service;

import com.team.discovery.pas_socialization2_backend.controller.model.Aprobar;
import com.team.discovery.pas_socialization2_backend.controller.model.Despacho;

import java.util.ArrayList;


public interface IClientService {

    Despacho searchDispatchClient(int idUsuarioDestino);
    Void approveDispatch(Aprobar requestAprobar);
    ArrayList<Despacho> searchHistoricalDispatch(int id);
}
