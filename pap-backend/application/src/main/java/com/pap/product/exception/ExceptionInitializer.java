package com.pap.product.exception;

import com.pap.product.model.CustomError;
import com.pap.product.ports.api.ProductExceptionPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class ExceptionInitializer implements ProductExceptionPort {
    @Override
    public CustomError getException(Set<String> message) {
        return new CustomError(message, LocalDate.now());
    }
}
