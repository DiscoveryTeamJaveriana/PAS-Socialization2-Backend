package com.team.discovery.pas_socialization2_backend.service;

import com.team.discovery.pas_socialization2_backend.controller.model.Despacho;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.State;

import java.util.List;

public interface IDispatchService {
    List<Despacho> searchDispatches(State state);
}
