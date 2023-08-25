package com.inderjeet.webfluxdemo.exception.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorResponse {
    private int code;
    private String description;
    private List<ValidationError> errors = new ArrayList<>();

}
