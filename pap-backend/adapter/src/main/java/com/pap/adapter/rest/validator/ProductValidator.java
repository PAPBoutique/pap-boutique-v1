package com.pap.adapter.rest.validator;

import com.pap.adapter.dto.ProductDTO;
import lombok.RequiredArgsConstructor;

import javax.xml.bind.ValidationException;
import java.util.List;

@RequiredArgsConstructor
public class ProductValidator {
    private final List<ProductDTO> products;

    public void validateAllFields() throws ValidationException {
        for (ProductDTO dto : products) {
            if (dto.getQuantity() == null && dto.getPrice() == null && dto.getDescription() == null && dto.getName() == null) {
                throw new ValidationException("Fields must be not null");
            }
        }
    }

        public void validateQuantity () throws ValidationException
        {
            for (ProductDTO dto : products) {
                if (dto.getQuantity() == null)
                    throw new ValidationException("Quantity should not be null");
            }
        }
        public void validatePrice () throws ValidationException
        {
            for (ProductDTO dto : products) {
                if (dto.getPrice() == null)
                    throw new ValidationException("Price should not be null");
            }
        }

        public void validateName () throws ValidationException
        {
            for (ProductDTO dto : products) {
                if (dto.getName().isEmpty() || dto.getName() == null)
                    throw new ValidationException("Name should not be null or empty");
            }
        }

    }

