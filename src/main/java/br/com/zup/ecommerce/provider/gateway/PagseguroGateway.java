package br.com.zup.ecommerce.provider.gateway;

import br.com.zup.ecommerce.model.buy.FinalizingPurchase;

import java.net.URI;
import java.text.MessageFormat;

public class PagseguroGateway implements Gateway {

    @Override
    public GatewayType getType() {
        return GatewayType.PAGSEGURO;
    }

    @Override
    public URI getRedirectUri(FinalizingPurchase purchase, URI currentUri) {
        return URI.create(MessageFormat.format("http://pagseguro.com?returnId={0}&redirectUrl={1}",
                purchase.getId(), currentUri.getPath()));
    }
}
