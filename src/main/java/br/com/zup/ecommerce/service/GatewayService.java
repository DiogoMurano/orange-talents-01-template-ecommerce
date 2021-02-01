package br.com.zup.ecommerce.service;

import br.com.zup.ecommerce.model.buy.FinalizingPurchase;
import br.com.zup.ecommerce.provider.gateway.Gateway;
import br.com.zup.ecommerce.provider.gateway.GatewayType;
import br.com.zup.ecommerce.provider.gateway.PaymentGatewayProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@Service
public class GatewayService {

    private final PaymentGatewayProvider gatewayProvider;

    @Autowired
    public GatewayService(PaymentGatewayProvider gatewayProvider) {
        this.gatewayProvider = gatewayProvider;
    }

    public Gateway findByName(String name) {
        GatewayType type = GatewayType.findByName(name).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment Gateway not found."));

        return gatewayProvider.findByType(type).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment Gateway not available."));
    }

    public ResponseEntity<?> createGatewayRedirect(Gateway gateway, FinalizingPurchase purchase, URI currentUri) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(gateway.getRedirectUri(purchase, currentUri))
                .build();
    }

}
