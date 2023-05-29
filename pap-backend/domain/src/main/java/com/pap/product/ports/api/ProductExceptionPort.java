package com.pap.product.ports.api;

import com.pap.product.model.CustomError;

import java.util.List;
import java.util.Set;

public interface ProductExceptionPort {
    CustomError getException(Set<String> message);
}
