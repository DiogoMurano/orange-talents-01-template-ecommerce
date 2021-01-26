package br.com.zup.ecommerce.controller;

import br.com.zup.ecommerce.controller.request.UserRequest;
import br.com.zup.ecommerce.model.User;
import br.com.zup.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody @Valid UserRequest request) {
        User user = new User(request.getLogin(), passwordEncoder.encode(request.getPassword()));

        repository.save(user);

        return ResponseEntity.ok().build();
    }

}
