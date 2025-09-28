package com.allen.product.domain.model;


import com.allen.product.application.constants.StockUpdateType;

public record StockUpdateCommand(

        Long productId,
        Long warehouseId,
        int quantityChange,
       //String productSku,
        StockUpdateType updateType
) {
}
