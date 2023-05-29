package com.pap.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableContent<T> {
    List<T> content;
    int totalPages;
    long totalElements;
}
