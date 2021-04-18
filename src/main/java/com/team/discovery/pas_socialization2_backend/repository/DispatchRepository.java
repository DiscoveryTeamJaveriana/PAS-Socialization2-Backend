package com.team.discovery.pas_socialization2_backend.repository;

import com.team.discovery.pas_socialization2_backend.model.despachos_db.Shipping;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DispatchRepository extends JpaRepository<Shipping, Long> {
    List<Shipping> findShippingByState(State state);
}
