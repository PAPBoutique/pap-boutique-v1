package com.pap.product.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDataDTO {

    private String name;
    private String type;
    private byte[] picByte;

}
