package com.pap.product.ports.api;

import com.pap.product.model.ImageDataDomainObject;
import com.pap.product.model.PageableContent;
import com.pap.product.model.ProductDomainObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ProductServicePort {
    List<ProductDomainObject> addProduct(List<ProductDomainObject> productDomainObjectList);

    ProductDomainObject addSingleProduct(ProductDomainObject productDomainObject);

    Long getTotalProducts();

    void deleteProduct(Long id);
    ProductDomainObject updateProduct(Long id, ProductDomainObject productDomainObjectList);
    ProductDomainObject getProductById(Long id);
    PageableContent<ProductDomainObject> findAllByPages(int page, int size,String filterValue);
    Set<ImageDataDomainObject> uploadImage(MultipartFile[] multipartFiles) throws IOException;


}
