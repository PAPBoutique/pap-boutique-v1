package com.pap.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

import java.util.Set;


@Data
@AllArgsConstructor
public class CustomError {
    private Set<String> messages ;
    private LocalDate dateTime ;
}
