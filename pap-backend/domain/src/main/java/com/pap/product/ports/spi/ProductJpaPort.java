package com.pap.product.ports.spi;

import com.pap.product.model.ImageDataDomainObject;
import com.pap.product.model.PageableContent;
import com.pap.product.model.ProductDomainObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;


public interface ProductJpaPort {
    List<ProductDomainObject> addProduct(List<ProductDomainObject> productDomainObjectList);
    ProductDomainObject addSingleProduct(ProductDomainObject productDomainObject);
    ProductDomainObject updateProduct(Long id, ProductDomainObject productDomainObjectList);
    ProductDomainObject getProductById(Long id);

    Long getTotalProducts();

    void deleteProduct(Long id);
    PageableContent<ProductDomainObject> findAllByPage(int page, int size);
    PageableContent<ProductDomainObject> findAllByName(int page,int size,String name);

    Set<ImageDataDomainObject> uploadImage(MultipartFile[] multipartFiles) throws IOException;
    void decreaseQuantity(Long id,Long quantity);
    boolean availableInStock(Long id,Long quantity);
}
