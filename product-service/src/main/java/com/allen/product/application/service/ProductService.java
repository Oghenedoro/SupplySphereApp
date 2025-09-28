package com.allen.product.application.service;

import com.allen.product.application.usecase.ProductUseCase;
import com.allen.product.domain.model.Product;
import com.allen.product.domain.port.ProductRepositoryPort;
import com.allen.product.domain.util.SkuGeneration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class ProductService implements ProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final SkuGeneration skuService;
    @Lazy
    public ProductService(ProductRepositoryPort productRepositoryPort, SkuGeneration skuService) {
        this.productRepositoryPort = productRepositoryPort;
        this.skuService = skuService;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepositoryPort.findAllProduct();
        return products;
    }
    @Override
    public Optional<Product> getProductById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        return productRepositoryPort.findByProductId(id);
    }

    @Override
    public Product createProduct(Product product) {
        Objects.requireNonNull(product, "Product cannot be null");

        String sku;
        do {
            sku = skuService.generateRandomSku();  // only random generation
        } while (productRepositoryPort.existsBySku(sku)); // uniqueness check here

        Product productWithSku = new Product(
                product.productId(),
                product.name(),
                product.description(),
                product.price(),
                sku,
                product.stocks()
        );
        return productRepositoryPort.saveProduct(productWithSku);
    }


    @Override
    public void deleteProduct(Long id) {
        // First, check if the id is null
        if (id == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        // Proceed with the delete operation if id is not null
        productRepositoryPort.deleteById(id);
    }

    @Override
    public Product updateProduct(Long productId, Product updatedProductData) {

        Product existingProduct = productRepositoryPort.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found !"));

        Product updatedProduct = new Product(
                existingProduct.productId(),
                updatedProductData.name(),
                updatedProductData.description(),
                updatedProductData.price(),
                existingProduct.sku(),
                existingProduct.stocks()
        );
        return updatedProduct;
    }

}
