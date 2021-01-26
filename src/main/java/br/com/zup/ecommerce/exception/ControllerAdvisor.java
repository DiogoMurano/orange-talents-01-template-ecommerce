package br.com.zup.ecommerce.exception;

import br.com.zup.ecommerce.controller.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        ObjectError objectError = errors.stream().findFirst().orElse(null);

        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, objectError != null && objectError
                .getDefaultMessage() != null ? objectError.getDefaultMessage() :
                "Some of the parameters sent were rejected.");

        return ResponseEntity.badRequest().body(response);
    }

}