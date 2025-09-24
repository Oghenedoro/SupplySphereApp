package com.allen.product.domain.model;

import java.time.LocalDate;

public record Stock(
        Long stockId,
        Long productId,
        String productSku,
        Long warehouseId,
        String warehouseName,
        Integer quantityOnHand,
        Integer quantityReserved,
        LocalDate lastUpdated) {

    public int getAvailableQuantity() {
        return quantityOnHand - quantityReserved;
    }
    public boolean isLowStock(int threshold) {
        return getAvailableQuantity() <= threshold;
    }
}






