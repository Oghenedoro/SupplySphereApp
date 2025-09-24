package com.allen.product.application.dto;

import com.allen.product.application.constants.StockUpdateType;

public record StockUpdateCommandDTO(
        Long productId,
        Long warehouseId,
        int quantityChange,
        StockUpdateType updateType
) {
}
