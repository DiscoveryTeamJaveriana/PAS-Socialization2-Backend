package com.team.discovery.pas_socialization2_backend.providers.coordinadora;

import com.team.discovery.pas_socialization2_backend.model.despachos_db.Shipping;
import com.team.discovery.pas_socialization2_backend.service.IShippingProvider;
import org.springframework.stereotype.Service;

@Service
public class CoordinadoraShippingProviderService implements IShippingProvider {
    @Override
    public Integer getOffer(Shipping shipping) {
        return 10;
    }
}
