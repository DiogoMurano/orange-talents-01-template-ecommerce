package br.com.zup.ecommerce.provider.gateway;

import br.com.zup.ecommerce.model.buy.PaymentStatus;
import br.com.zup.ecommerce.model.buy.Purchase;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.text.MessageFormat;

public class PagseguroGateway implements Gateway {

    @Override
    public GatewayType getType() {
        return GatewayType.PAGSEGURO;
    }

    @Override
    public URI getRedirectUri(Purchase purchase, URI currentUri) {
        return URI.create(MessageFormat.format("http://pagseguro.com?returnId={0}&redirectUrl={1}",
                purchase.getId(), currentUri));
    }

    @Override
    public PaymentStatus getPaymentStatus(Object status) {
        if (!(status instanceof String)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status format.");
        }

        String response = (String) status;
        return response.equals("SUCESSO") ? PaymentStatus.SUCCESS : PaymentStatus.FAIL;
    }
}
