package br.com.zup.ecommerce.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class User {

    @NotBlank
    @Email
    private String login;

    @NotBlank
    private String password;

    private LocalDateTime createdAt = LocalDateTime.now();

    public User(String login, String encryptedPassword) {
        this.login = login;
        this.password = encryptedPassword;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
