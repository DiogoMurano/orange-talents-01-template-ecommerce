package br.com.zup.ecommerce.controller.response;

public class JwtResponse {

    private String token;
    private String type;

    public JwtResponse(String token) {
        this.token = token;
        this.type = "Bearer";
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }
}
