package br.com.zup.ecommerce.provider.gateway;

import br.com.zup.ecommerce.model.buy.PaymentStatus;
import br.com.zup.ecommerce.model.buy.Purchase;

import java.net.URI;

public interface Gateway {

    GatewayType getType();

    URI getRedirectUri(Purchase purchase, URI currentUri);

    PaymentStatus getPaymentStatus(Object status);

}
