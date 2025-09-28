package com.allen.product.infrastructure.persistence.entity;

import com.allen.product.application.constants.StockUpdateType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_transaction")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StockTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockTransactionId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "warehouse_id", nullable = false)
    private Long warehouseId;

    @Column(name = "warehouse_name", nullable = false)
    private String warehouseName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StockUpdateType type;

    @Column(name = "quantity_change", nullable = false)
    private Integer quantityChange;

    @Column(name = "resulting_quantity", nullable = false)
    private Integer resultingQuantity;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

  }