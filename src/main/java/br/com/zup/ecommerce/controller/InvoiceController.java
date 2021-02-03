package br.com.zup.ecommerce.controller;

import br.com.zup.ecommerce.controller.request.InvoiceRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @PostMapping
    public ResponseEntity<?> receivePayment(@RequestBody @Valid InvoiceRequest request) {
        return ResponseEntity.ok().build();
    }

}
