package com.team.discovery.pas_socialization2_backend.repository;

import java.util.List;

import com.team.discovery.pas_socialization2_backend.model.despachos_db.Shipping;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {

    List<Shipping> findShippingsByState(final State state);

}
