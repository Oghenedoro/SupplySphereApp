package com.allen.product.infrastructure.persistence.mapper;

import com.allen.product.domain.model.Product;
import com.allen.product.domain.model.Stock;
import com.allen.product.infrastructure.persistence.entity.ProductEntity;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-28T03:03:45+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product productEntityToProduct(ProductEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long productId = null;
        String name = null;
        String description = null;
        double price = 0.0d;
        String sku = null;

        productId = entity.getProductId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        sku = entity.getSku();

        List<Stock> stocks = null;

        Product product = new Product( productId, name, description, price, sku, stocks );

        return product;
    }

    @Override
    public ProductEntity productToEntity(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductEntity.ProductEntityBuilder productEntity = ProductEntity.builder();

        productEntity.description( product.description() );
        productEntity.name( product.name() );
        productEntity.price( product.price() );
        productEntity.productId( product.productId() );
        productEntity.sku( product.sku() );

        return productEntity.build();
    }
}
