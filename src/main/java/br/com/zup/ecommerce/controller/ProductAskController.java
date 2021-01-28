package br.com.zup.ecommerce.controller;

import br.com.zup.ecommerce.controller.request.AskRequest;
import br.com.zup.ecommerce.controller.response.AskResponse;
import br.com.zup.ecommerce.controller.response.ListAskResponse;
import br.com.zup.ecommerce.model.product.Ask;
import br.com.zup.ecommerce.model.product.Product;
import br.com.zup.ecommerce.model.user.User;
import br.com.zup.ecommerce.provider.mail.Mail;
import br.com.zup.ecommerce.provider.mail.MailProvider;
import br.com.zup.ecommerce.repository.AskRepository;
import br.com.zup.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/product/ask")
public class ProductAskController {

    private final ProductRepository productRepository;
    private final AskRepository askRepository;
    private final MailProvider mailProvider;

    @Autowired
    public ProductAskController(ProductRepository productRepository, AskRepository askRepository, MailProvider mailProvider) {
        this.productRepository = productRepository;
        this.askRepository = askRepository;
        this.mailProvider = mailProvider;
    }

    @PostMapping
    public ResponseEntity<ListAskResponse> sendNewAsk(@RequestBody @Valid AskRequest request) {
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Ask ask = request.toModel(user, product);
        askRepository.save(ask);

        mailProvider.sendMail(new Mail(
                "Nova pergunta recebida",
                MessageFormat.format("Pergunta de {0}\nLink: {1}", user.getLogin(), ask.getLink()),
                product.getUser().getLogin()));

        Spliterator<Ask> spliterator = askRepository.findAll().spliterator();
        List<AskResponse> responses = StreamSupport.stream(spliterator, false)
                .map(AskResponse::new).collect(Collectors.toList());

        return ResponseEntity.ok(new ListAskResponse(responses));
    }

}
