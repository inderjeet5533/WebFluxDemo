package com.inderjeet.webfluxdemo.controller;

import com.inderjeet.webfluxdemo.model.FormData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.ContentType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Validated
@RestController
public class HelloWebFluxController {

    @GetMapping("/getHello")
    public ResponseEntity<String> getHelloWebFlux(){

        return ResponseEntity.ok("Hello WebFlux");
    }

    @PostMapping(value = "/saveForm", consumes = "application/json")
    public Mono<ResponseEntity<String>> saveFormData(@Valid @RequestBody FormData formData){

        return Mono.just(ResponseEntity.ok("Hello "+formData.getFirstName()+" WebFlux"));
    }
}
