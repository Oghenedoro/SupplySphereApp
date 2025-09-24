package com.allen.product.application.facade;

import com.allen.product.application.constants.StockUpdateType;
import com.allen.product.application.usecase.StockTransactionUseCase;
import com.allen.product.domain.model.Stock;
import com.allen.product.domain.model.StockTransaction;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class StockTransactionFacade {
    private final StockTransactionUseCase transactionService;

    public StockTransactionFacade(@Lazy StockTransactionUseCase transactionService) {
        this.transactionService = transactionService;
    }

    public void recordStockUpdate(Stock stock, StockUpdateType type, int quantityChange) {
        transactionService.recordTransaction(stock, type, quantityChange);
    }
}