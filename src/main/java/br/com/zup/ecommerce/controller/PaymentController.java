package br.com.zup.ecommerce.controller;

import br.com.zup.ecommerce.controller.request.PaymentRequest;
import br.com.zup.ecommerce.model.buy.Payment;
import br.com.zup.ecommerce.model.buy.PaymentStatus;
import br.com.zup.ecommerce.model.buy.Purchase;
import br.com.zup.ecommerce.provider.gateway.Gateway;
import br.com.zup.ecommerce.repository.PaymentRepository;
import br.com.zup.ecommerce.repository.PurchaseRepository;
import br.com.zup.ecommerce.service.GatewayService;
import br.com.zup.ecommerce.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PurchaseRepository purchaseRepository;
    private final PaymentRepository paymentRepository;

    private final PaymentService paymentService;
    private final GatewayService gatewayService;

    @Autowired
    public PaymentController(PurchaseRepository purchaseRepository, PaymentRepository paymentRepository,
                             PaymentService paymentService, GatewayService gatewayService) {
        this.purchaseRepository = purchaseRepository;
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
        this.gatewayService = gatewayService;
    }

    @PostMapping
    public ResponseEntity<?> sendNewPayment(@RequestBody @Valid PaymentRequest request, UriComponentsBuilder builder) {
        Purchase purchase = purchaseRepository.findById(request.getPurchaseId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchase not found."));

        Gateway gateway = gatewayService.findByType(purchase.getGatewayType());
        PaymentStatus status = gateway.getPaymentStatus(request.getStatus());

        Payment payment = request.toModel(purchase, status);

        paymentService.checkPayment(payment, gateway, builder.build().toUri());
        paymentRepository.save(payment);

        return ResponseEntity.ok().build();
    }

}
