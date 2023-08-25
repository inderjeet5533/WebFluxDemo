package com.inderjeet.webfluxdemo.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormData {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String contact;

}
