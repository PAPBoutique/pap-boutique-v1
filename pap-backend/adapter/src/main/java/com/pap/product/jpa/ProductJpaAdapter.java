package com.pap.product.jpa;


import com.pap.cart.jpa.entity.CartEntity;
import com.pap.cart.jpa.repository.CartRepository;
import com.pap.product.exception.ProductNotFoundException;
import com.pap.product.jpa.entity.ImageDataEntity;
import com.pap.product.jpa.entity.ProductEntity;
import com.pap.product.jpa.mapper.ImageMapper;
import com.pap.product.jpa.mapper.PageableMapper;
import com.pap.product.jpa.mapper.ProductMapper;
import com.pap.product.jpa.repository.ProductRepository;
import com.pap.product.model.ImageDataDomainObject;
import com.pap.product.model.PageableContent;
import com.pap.product.model.ProductDomainObject;
import com.pap.product.ports.spi.ProductJpaPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@AllArgsConstructor
@Component
public class ProductJpaAdapter implements ProductJpaPort {
    private final ProductRepository productRepository;

    @Override
    public List<ProductDomainObject> addProduct(List<ProductDomainObject> productDomainObject) {
        List<ProductEntity> productEntity = ProductMapper.INSTANCE.productsDomainObjectToProduct(productDomainObject);
        List<ProductEntity> savedProductEntity = productRepository.saveAll(productEntity);
        return ProductMapper.INSTANCE.productsToProductDomainObject(savedProductEntity);
    }

    @Override
    public ProductDomainObject addSingleProduct(ProductDomainObject productDomainObject) {
        ProductEntity productEntity = ProductMapper.INSTANCE.productDomainToProduct(productDomainObject);
        ProductEntity savedProduct = productRepository.save(productEntity);
        return ProductMapper.INSTANCE.productToProductDomain(savedProduct);
    }

    @Override
    public Set<ImageDataDomainObject> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageDataEntity> imageDataSet = new HashSet<>();
        for (MultipartFile file : multipartFiles)
        {
            ImageDataEntity imageData = new ImageDataEntity(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageDataSet.add(imageData);
        }
        Set<ImageDataDomainObject> imageDataDomainObjects = ImageMapper.INSTANCE.imageDataEntityToImageDataDomain(imageDataSet);
        return imageDataDomainObjects;
    }

    private final CartRepository cartRepository;
    @Override
    public void deleteProduct(Long id) {
        Optional<ProductEntity> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            ProductEntity product = productOptional.get();

            List<CartEntity> cartsWithProduct = cartRepository.findByProduct(product);
            for (CartEntity cart : cartsWithProduct) {
                cart.setProduct(null);
                cartRepository.save(cart);
            }
            productRepository.deleteById(id);
        }
    }

    @Override
    public PageableContent<ProductDomainObject> findAllByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> productPage = productRepository.findAll(pageable);
        return PageableMapper.INSTANCE.toPageableContent(productPage);
    }

    @Transactional
    @Override
    public PageableContent<ProductDomainObject> findAllByName(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> productPage = productRepository.findAllByNameContainingIgnoreCase(name,pageable);
        return PageableMapper.INSTANCE.toPageableContent(productPage);
    }

    @Override
    public void decreaseQuantity(Long id, Long quantity) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Order not found"));
        var oldQuantity = productEntity.getQuantity() ;
        productEntity.setQuantity((int) (oldQuantity-quantity));
    }

    @Override
    public boolean availableInStock(Long id, Long quantity) {
        ProductDomainObject product = getProductById(id);
        return product.getQuantity()>=quantity;
    }


    @Override
    public ProductDomainObject updateProduct(Long id, ProductDomainObject productDomainObject) throws ProductNotFoundException {
        Optional<ProductEntity> existingProductEntity = productRepository.findById(id);

        if (existingProductEntity.isPresent()) {
            ProductEntity updatedProductEntity = existingProductEntity.get();
            updatedProductEntity.setName(productDomainObject.getName());
            updatedProductEntity.setDescription(productDomainObject.getDescription());
            updatedProductEntity.setPrice(productDomainObject.getPrice());
            updatedProductEntity.setQuantity(productDomainObject.getQuantity());

            ProductEntity savedProductEntity = productRepository.save(updatedProductEntity);
            return ProductMapper.INSTANCE.productToProductDomain(savedProductEntity);
        } else {
            throw new ProductNotFoundException("Product not found with id " + id);
        }
    }

    @Override
    public ProductDomainObject getProductById(Long id) throws ProductNotFoundException {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isPresent()) {
            return ProductMapper.INSTANCE.productToProductDomain(productEntity.get());
        } else {
            throw new ProductNotFoundException("Product not found with id " + id);
        }
    }

    @Override
    public Long getTotalProducts() {
        return productRepository.count();
    }

    @Override
    public List<ProductDomainObject> getAllProducts() {
        List<ProductEntity> productEntity = productRepository.findAll();
        return ProductMapper.INSTANCE.productsToProductDomainObject(productEntity);
    }
}