package com.allen.product.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "product")
@NoArgsConstructor @AllArgsConstructor
@Builder
@Getter @Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String name;
    private String description;
    private double price;
    @Column(nullable = false, unique = true)
    private String sku;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<StockEntity> stocks;
    public Long getProductId() {
        return productId;
    }
}
