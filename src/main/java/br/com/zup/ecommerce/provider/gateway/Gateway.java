package br.com.zup.ecommerce.provider.gateway;

import br.com.zup.ecommerce.model.buy.FinalizingPurchase;

import java.net.URI;

public interface Gateway {

    GatewayType getType();

    URI getRedirectUri(FinalizingPurchase purchase, URI currentUri);

}
