package com.team.discovery.pas_socialization2_backend.providers.tcc.service;

import com.team.discovery.pas_socialization2_backend.providers.tcc.model.ShippingPriceRequest;
import com.team.discovery.pas_socialization2_backend.providers.tcc.model.ShippingPriceResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TCCRestService {
    @POST("shipping/price")
    Call<ShippingPriceResponse> consultShippingPrice(@Body ShippingPriceRequest request);
}
