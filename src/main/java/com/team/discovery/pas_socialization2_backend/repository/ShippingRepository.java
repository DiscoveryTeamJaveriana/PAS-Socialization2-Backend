package com.team.discovery.pas_socialization2_backend.repository;

import com.team.discovery.pas_socialization2_backend.model.despachos_db.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
}
