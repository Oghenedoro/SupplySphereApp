package com.allen.product.domain.port;

import com.allen.product.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
    boolean existsBySku(String sku);

}