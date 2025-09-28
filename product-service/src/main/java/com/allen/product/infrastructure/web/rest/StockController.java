package com.allen.product.infrastructure.web.rest;

import com.allen.product.application.applicationMapper.AppStockMapper;
import com.allen.product.application.dto.StockDTO;
import com.allen.product.application.usecase.StockUseCase;
import com.allen.product.domain.model.Stock;
import com.allen.product.domain.model.StockUpdateCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private final StockUseCase stockUseCase;
    private final AppStockMapper mapper;

    public StockController(StockUseCase stockUseCase, AppStockMapper mapper) {
        this.stockUseCase = stockUseCase;
        this.mapper = mapper;
    }
    @PostMapping
    public ResponseEntity<StockDTO> createOrUpdateStock(@RequestBody StockUpdateCommand command) {
        Stock stock = stockUseCase.createOrUpdateStock(command);
        StockDTO stockDTO = mapper.stockDomainToDTO(stock);
        return new ResponseEntity<>(stockDTO, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<StockDTO>> getStockList() {
        List<StockDTO> stockDTOs = stockUseCase.getStockList().stream()
                .map(stock -> mapper.stockDomainToDTO(stock))
                .toList();
        if (stockDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(stockDTOs);
        }
    }
}
