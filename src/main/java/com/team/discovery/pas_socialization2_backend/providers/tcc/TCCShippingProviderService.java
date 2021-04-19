package com.team.discovery.pas_socialization2_backend.providers.tcc;

import java.io.IOException;

import com.team.discovery.pas_socialization2_backend.model.despachos_db.Shipping;
import com.team.discovery.pas_socialization2_backend.providers.tcc.model.ShippingPriceRequest;
import com.team.discovery.pas_socialization2_backend.providers.tcc.model.ShippingPriceResponse;
import com.team.discovery.pas_socialization2_backend.providers.tcc.service.TCCRestService;
import com.team.discovery.pas_socialization2_backend.service.IShippingProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class TCCShippingProviderService implements IShippingProvider {

    @Value("${providers.tcc.url}")
    String url;

    @Override
    public Integer getOffer(Shipping shipping) {

        try {
            Response<ShippingPriceResponse> response = getService()
                    .consultShippingPrice(adaptShippingToRequest(shipping)).execute();
            if(response.body() != null){
                return response.body().getValueShipping();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private TCCRestService getService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(TCCRestService.class);
    }

    private ShippingPriceRequest adaptShippingToRequest(Shipping shipping) {
        return ShippingPriceRequest.builder()
                .address(shipping.getUser().getAddress())
                .boxes(shipping.getBoxesAmount())
                .weight(shipping.getTotalWeight())
                .build();
    }
}
