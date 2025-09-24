package com.allen.product.infrastructure.web.rest;

import com.allen.product.application.usecase.StockUseCase;
import com.allen.product.domain.model.Stock;
import com.allen.product.domain.model.StockUpdateCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
public class StockController {


    private final StockUseCase stockUseCase;

    public StockController(StockUseCase stockUseCase) {
        this.stockUseCase = stockUseCase;
    }
    @PostMapping
    public ResponseEntity<Stock> createOrUpdateStock(@PathVariable StockUpdateCommand command) {
        Stock stock = stockUseCase.createOrUpdateStock(command);
        return new ResponseEntity<>(stock, HttpStatus.CREATED);

    }
}
