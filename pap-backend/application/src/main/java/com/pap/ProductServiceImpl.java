package com.pap;

import com.pap.model.product.ProductDomainObject;
import com.pap.ports.api.ProductServicePort;
import com.pap.ports.spi.ProductJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductServicePort {

    private final ProductJpaPort productJpaPort;
    @Override
    public List<ProductDomainObject> addProduct(List<ProductDomainObject> product) {

        return productJpaPort.addProduct(product);
    }

    @Override
    public List<ProductDomainObject> getProducts() {
        return productJpaPort.getProducts();

    }

}