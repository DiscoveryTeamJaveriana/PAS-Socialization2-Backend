package com.team.discovery.pas_socialization2_backend.providers.coordinadora;

import java.rmi.RemoteException;

import com.team.discovery.pas_socialization2_backend.model.despachos_db.Shipping;
import com.team.discovery.pas_socialization2_backend.providers.coordinadora.model.ShippingInput;
import com.team.discovery.pas_socialization2_backend.providers.coordinadora.model.ShippingOutput;
import com.team.discovery.pas_socialization2_backend.providers.coordinadora.service.Shipping_ws;
import com.team.discovery.pas_socialization2_backend.providers.coordinadora.service.Shipping_wsStub;
import com.team.discovery.pas_socialization2_backend.service.IShippingProvider;
import org.apache.axis2.AxisFault;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CoordinadoraShippingProviderService implements IShippingProvider {

    Shipping_ws shippingWs;

    @Value("${providers.coordinadora.url}")
    String url;

    @Override
    public Integer getOffer(Shipping shipping) {
        try {
            buildConnection();
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }

        ShippingInput shippingInput = new ShippingInput();
        shippingInput.setAddress(shipping.getUser().getAddress());
        shippingInput.setBoxesAmount(shipping.getBoxesAmount());
        shippingInput.setTotalWeight(shippingInput.getTotalWeight());

        try {
            ShippingOutput shippingOutput = shippingWs.calculate(shippingInput);
            return shippingOutput.getValue();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void buildConnection() throws AxisFault {
        shippingWs = new Shipping_wsStub(url);
    }
}
