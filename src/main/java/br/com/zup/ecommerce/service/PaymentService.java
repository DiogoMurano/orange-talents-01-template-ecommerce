package br.com.zup.ecommerce.service;

import br.com.zup.ecommerce.controller.request.InvoiceRequest;
import br.com.zup.ecommerce.controller.request.SellerRequest;
import br.com.zup.ecommerce.model.buy.Payment;
import br.com.zup.ecommerce.model.buy.PaymentStatus;
import br.com.zup.ecommerce.model.buy.Purchase;
import br.com.zup.ecommerce.provider.gateway.Gateway;
import br.com.zup.ecommerce.provider.mail.Mail;
import br.com.zup.ecommerce.provider.mail.MailProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;

@Service
public class PaymentService {

    private final MailProvider mailProvider;
    private final GatewayService gatewayService;

    private final RestTemplate restTemplate;

    @Autowired
    public PaymentService(MailProvider mailProvider, GatewayService gatewayService, RestTemplate restTemplate) {
        this.mailProvider = mailProvider;
        this.gatewayService = gatewayService;
        this.restTemplate = restTemplate;
    }

    public void checkPayment(Payment payment, Gateway gateway, URI currentUri) {
        if (payment.getPurchase().getPayments().stream().anyMatch(p -> p.getStatus() == PaymentStatus.SUCCESS)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This purchase already has the payment made.");
        }

        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            successfullyPayment(payment);
            return;
        }

        failedPayment(payment, gateway, currentUri);
    }

    private void successfullyPayment(Payment payment) {
        Purchase purchase = payment.getPurchase();
        restTemplate.exchange("http://localhost:8080/invoice",
                HttpMethod.POST,
                getEntity(new InvoiceRequest(purchase.getId(), purchase.getAuthor().getId())),
                InvoiceRequest.class);

        restTemplate.exchange("http://localhost:8080/seller",
                HttpMethod.POST,
                getEntity(new SellerRequest(purchase.getId(), purchase.getProduct().getUser().getId())),
                InvoiceRequest.class);

        String buyerEmail = payment.getPurchase().getAuthor().getLogin();

        mailProvider.sendMail(new Mail(
                "Compra realizada com sucesso!",
                MessageFormat.format("A compra foi realizada com sucesso! \n{0}", payment.getPurchase().toString()),
                buyerEmail));
    }

    private void failedPayment(Payment payment, Gateway gateway, URI currentUri) {
        String buyerEmail = payment.getPurchase().getAuthor().getLogin();
        mailProvider.sendMail(new Mail(
                "Tentativa de pagamento falhou!",
                "Por favor, tente novamente através do link abaixo: " + gatewayService
                        .getGatewayLink(gateway, payment.getPurchase(), currentUri), buyerEmail));
    }

    private <T> HttpEntity<T> getEntity(T t) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return new HttpEntity<>(t, headers);
    }

}
