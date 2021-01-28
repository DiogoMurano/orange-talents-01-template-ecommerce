package br.com.zup.ecommerce.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse {

    @JsonProperty
    private final HttpStatus status;

    @JsonProperty
    @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
    private final LocalDateTime dateTime;

    @JsonProperty
    private final String message;

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;

        this.dateTime = LocalDateTime.now();
    }
}
