package com.team.discovery.pas_socialization2_backend.providers.tcc.model;

import lombok.Builder;

@Builder
public class ShippingPriceRequest {
    Long boxes;
    Double weight;
    String address;
}
