package br.com.zup.ecommerce.provider.gateway;

import br.com.zup.ecommerce.model.buy.FinalizingPurchase;

import java.net.URI;
import java.text.MessageFormat;

public class PaypalGateway implements Gateway {

    @Override
    public GatewayType getType() {
        return GatewayType.PAYPAL;
    }

    @Override
    public URI getRedirectUri(FinalizingPurchase purchase, URI actualUri) {
        return URI.create(MessageFormat.format("http://paypal.com/{0}?redirectUrl={1}"
                , purchase.getId(), actualUri.getPath()));
    }


}
