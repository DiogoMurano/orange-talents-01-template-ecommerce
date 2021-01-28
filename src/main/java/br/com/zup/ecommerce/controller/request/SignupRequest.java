package br.com.zup.ecommerce.controller.request;

import br.com.zup.ecommerce.model.user.User;
import br.com.zup.ecommerce.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupRequest {

    @JsonProperty
    @NotBlank
    @Email
    @UniqueValue(domainClass = User.class, fieldName = "login")
    private final String login;

    @JsonProperty
    @NotBlank
    @Size(min = 6)
    private final String password;

    public SignupRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
