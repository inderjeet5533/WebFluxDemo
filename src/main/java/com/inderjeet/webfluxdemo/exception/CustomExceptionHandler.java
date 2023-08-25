package com.inderjeet.webfluxdemo.exception;

import com.inderjeet.webfluxdemo.exception.model.CustomErrorResponse;
import com.inderjeet.webfluxdemo.exception.model.ValidationError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected Mono<ResponseEntity<Object>> handleWebExchangeBindException(WebExchangeBindException ex, HttpHeaders headers, HttpStatusCode status, ServerWebExchange exchange) {
        List<ValidationError> errors = ex.getFieldErrors().stream()
                .map(error -> ValidationError.builder()
                        .name(error.getField())
                        .message(error.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
        return Mono.just(ResponseEntity.badRequest().body(CustomErrorResponse.builder()
                .errors(errors)
                .description(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .code(HttpStatus.BAD_REQUEST.value()).build()));
    }

    @Override
    protected Mono<ResponseEntity<Object>> handleMethodNotAllowedException(MethodNotAllowedException ex, HttpHeaders headers, HttpStatusCode status, ServerWebExchange exchange) {

        return Mono.just(ResponseEntity.status(status).body(CustomErrorResponse.builder()
                .description(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())
                .code(HttpStatus.METHOD_NOT_ALLOWED.value()).build()));
    }


}
