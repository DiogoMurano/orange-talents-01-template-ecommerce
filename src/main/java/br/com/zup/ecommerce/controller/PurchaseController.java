package br.com.zup.ecommerce.controller;

import br.com.zup.ecommerce.controller.request.PurchaseRequest;
import br.com.zup.ecommerce.model.buy.Purchase;
import br.com.zup.ecommerce.model.product.Product;
import br.com.zup.ecommerce.provider.gateway.Gateway;
import br.com.zup.ecommerce.provider.mail.Mail;
import br.com.zup.ecommerce.provider.mail.MailProvider;
import br.com.zup.ecommerce.repository.PurchaseRepository;
import br.com.zup.ecommerce.service.GatewayService;
import br.com.zup.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final ProductService productService;
    private final GatewayService gatewayService;

    private final PurchaseRepository purchaseRepository;

    private final MailProvider mailProvider;

    @Autowired
    public PurchaseController(
            ProductService productService,
            GatewayService gatewayService,
            PurchaseRepository purchaseRepository,
            MailProvider mailProvider) {

        this.productService = productService;
        this.gatewayService = gatewayService;
        this.purchaseRepository = purchaseRepository;
        this.mailProvider = mailProvider;
    }

    @PostMapping
    public ResponseEntity<?> createNewPurchase(@RequestBody @Valid PurchaseRequest request, UriComponentsBuilder builder) {
        Product product = productService.findById(request.getProductId());
        productService.checkAvailable(product, request.getQuantity());

        Gateway gateway = gatewayService.findByName(request.getPaymentGateway());

        Purchase purchase = request.toModel(gateway.getType(), product);
        purchaseRepository.save(purchase);

        mailProvider.sendMail(new Mail("Um usuário iniciou uma compra!",
                "O usuário iniciou o processo de compra", product.getUser().getLogin()));

        productService.updateStock(product, request.getQuantity());

        return gatewayService.createGatewayRedirect(gateway, purchase, builder.build().toUri());
    }

}
