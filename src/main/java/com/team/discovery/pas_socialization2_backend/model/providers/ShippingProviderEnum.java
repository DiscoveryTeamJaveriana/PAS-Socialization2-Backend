package com.team.discovery.pas_socialization2_backend.model.providers;

import com.team.discovery.pas_socialization2_backend.configuration.ContextWrapper;
import com.team.discovery.pas_socialization2_backend.providers.coordinadora.CoordinadoraShippingProviderService;
import com.team.discovery.pas_socialization2_backend.providers.tcc.TCCShippingProviderService;
import com.team.discovery.pas_socialization2_backend.service.IShippingProvider;
import org.springframework.context.ApplicationContext;

public enum ShippingProviderEnum {
    TCC("TCC", TCCShippingProviderService.class),
    COORDINADORA("Coordinadora", CoordinadoraShippingProviderService.class);

    private final String name;

    private final Class<? extends IShippingProvider> shippingProvider;


    ShippingProviderEnum(final String name, final Class<? extends IShippingProvider> shippingProvider) {
        this.name = name;
        this.shippingProvider = shippingProvider;
    }

    public String getName() {
        return name;
    }

    public IShippingProvider getShippingProvider() {
        return ContextWrapper.getContext().getBean(shippingProvider);
    }
}
