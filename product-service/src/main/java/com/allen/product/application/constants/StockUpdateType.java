package com.allen.product.application.constants;

public enum StockUpdateType {
    PURCHASE,   // Increase quantity
    SALE,       // Decrease quantity
    RESERVATION // Decrease quantity (reserved)
}