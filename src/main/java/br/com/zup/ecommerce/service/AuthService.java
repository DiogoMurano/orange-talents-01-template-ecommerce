package br.com.zup.ecommerce.service;

import br.com.zup.ecommerce.controller.request.LoginRequest;
import br.com.zup.ecommerce.controller.request.SignupRequest;
import br.com.zup.ecommerce.controller.response.JwtResponse;
import br.com.zup.ecommerce.model.User;
import br.com.zup.ecommerce.repository.UserRepository;
import br.com.zup.ecommerce.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthService(UserRepository repository, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager, TokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public void signupUser(SignupRequest request) {
        User user = new User(request.getLogin(), passwordEncoder.encode(request.getPassword()));
        repository.save(user);
    }

    public JwtResponse signinUser(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenService.generateToken(authentication);

        return new JwtResponse(token);
    }

}
