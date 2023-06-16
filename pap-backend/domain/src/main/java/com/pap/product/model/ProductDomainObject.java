package com.pap.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductDomainObject {
    private Long id ;
    private String name ;
    private String description;
    private Integer quantity;
    private Double price ;
    private Set<ImageDataDomainObject> productImages;
    private LocalDate createdDate ;

}
