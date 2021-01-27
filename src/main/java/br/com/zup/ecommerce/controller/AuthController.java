package br.com.zup.ecommerce.controller;

import br.com.zup.ecommerce.controller.request.LoginRequest;
import br.com.zup.ecommerce.controller.request.SignupRequest;
import br.com.zup.ecommerce.controller.response.JwtResponse;
import br.com.zup.ecommerce.model.User;
import br.com.zup.ecommerce.repository.UserRepository;
import br.com.zup.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createNewUser(@RequestBody @Valid SignupRequest request) {
        authService.signupUser(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody @Valid LoginRequest request) {
        JwtResponse response = authService.signinUser(request);
        return ResponseEntity.ok(response);
    }

}
