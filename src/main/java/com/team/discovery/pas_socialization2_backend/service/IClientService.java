package com.team.discovery.pas_socialization2_backend.service;

import com.team.discovery.pas_socialization2_backend.controller.model.Aprobar;
import com.team.discovery.pas_socialization2_backend.controller.model.Despacho;
import java.util.List;


public interface IClientService {

    List<Despacho> searchDispatchClient(int idUsuarioDestino);
    Void approveDispatch(Aprobar requestAprobar);
    List<Despacho> searchHistoricalDispatch(int id);
}
