package com.team.discovery.pas_socialization2_backend.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.team.discovery.pas_socialization2_backend.model.despachos_db.Offer;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.Shipping;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.State;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.User;
import com.team.discovery.pas_socialization2_backend.model.providers.ShippingProviderEnum;
import com.team.discovery.pas_socialization2_backend.repository.OfferRepository;
import com.team.discovery.pas_socialization2_backend.repository.ShippingRepository;
import com.team.discovery.pas_socialization2_backend.repository.UserRepository;
import com.team.discovery.pas_socialization2_backend.service.IOfferService;
import com.team.discovery.pas_socialization2_backend.service.IShippingProvider;
import com.team.discovery.pas_socialization2_backend.service.IShippingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ShippingService implements IShippingService {

    private final ShippingRepository shippingRepository;

    private final UserRepository userRepository;

    private final IOfferService offerService;

    @Override
    public List<Shipping> getShippingByStatus(State state) {
        return shippingRepository.findShippingsByState(state);
    }

    @Override
    public void processPendingShipping(List<Shipping> shippingList) {
        final Map<IShippingProvider, User> providers = Arrays.stream(ShippingProviderEnum.values())
                                                             .collect(Collectors.toMap(ShippingProviderEnum::getShippingProvider, shippingProviderEnum -> userRepository.findUsersByUserName(shippingProviderEnum.getName())));
        log.info("Got Providers: {}", providers);
        shippingList.forEach(shipping -> {
                providers.forEach((provider, user) ->
                        offerService.postOffer(provider.getOffer(shipping),shipping, user));
                shipping.setState(State.DESPACHO_OFERTADO);
                log.info("Shipping [{}] with providers offers", shipping);
                shippingRepository.save(shipping);
            }
        );

    }
}
