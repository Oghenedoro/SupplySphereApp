package com.allen.product.domain.model;

import com.allen.product.application.constants.StockUpdateType;

import java.time.LocalDateTime;

public record StockTransaction(

         Long stockTransactionId,
         Long productId,
         String productSku,
         Long warehouseId,
         String warehouseName,
         StockUpdateType type,
         Integer quantityChange,
         Integer resultingQuantity,
         LocalDateTime transactionDate
) {
}
