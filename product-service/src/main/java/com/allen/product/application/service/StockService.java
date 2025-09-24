package com.allen.product.application.service;

import com.allen.product.application.constants.StockUpdateType;
import com.allen.product.application.usecase.StockTransactionUseCase;
import com.allen.product.application.usecase.StockUseCase;
import com.allen.product.domain.model.*;
import com.allen.product.domain.port.StockRepositoryPort;
import com.allen.product.domain.port.WarehouseRepositoryPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class StockService implements StockUseCase {
    private static final Logger logger = LoggerFactory.getLogger(StockService.class);
    private final StockTransactionUseCase transactionService;
    private final StockRepositoryPort stockRepositoryPort;
    private final WarehouseRepositoryPort warehouseRepositoryPort;

    public StockService(@Lazy StockTransactionUseCase transactionService,
                        StockRepositoryPort stockRepositoryPort,
                        WarehouseRepositoryPort warehouseRepositoryPort) {
        this.transactionService = transactionService;
        this.stockRepositoryPort = stockRepositoryPort;
        this.warehouseRepositoryPort = warehouseRepositoryPort;
    }
    @Override
    public StockStatus trackStock(Long productId, Long warehouseId, int lowStockThreshold) {

        Stock stock = findByProductIdAndWarehouseId(productId, warehouseId)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
        int availableQty = stock.getAvailableQuantity();
        boolean isLowStock = stock.isLowStock(lowStockThreshold);
        if (isLowStock) {
            logger.warn("Low stock detected for product {} in warehouse {}: available={}",
                    productId, warehouseId, availableQty);
        }
        return new StockStatus(stock.productId(), stock.warehouseId(), availableQty, isLowStock);
    }

    @Override
    @Transactional
    public Stock createOrUpdateStock(StockUpdateCommand command) {
        Stock updatedStock = stockRepositoryPort.findByProductIdAndWarehouseId(command.productId(), command.warehouseId())
                .map(existingStock -> updateExistingStock(existingStock, command))
                .orElseGet(() -> createNewStock(command));

        // Record the transaction directly through the service
        transactionService.recordTransaction(updatedStock, command.updateType(), command.quantityChange());
        return updatedStock;
    }

    private Stock createNewStock(StockUpdateCommand command) {
        // Fetch warehouse info only
        Warehouse warehouse = warehouseRepositoryPort.findById(command.warehouseId())
                .orElseThrow(() -> new IllegalArgumentException("Warehouse not found: " + command.warehouseId()));

        // Start from initial quantity 0
        int newQuantity = calculateNewQuantity(0, command.quantityChange(), command.updateType());

        Stock newStock = new Stock(
                null,
                command.productId(),
                command.productSku(),   // SKU passed from command
                warehouse.warehouseId(),
                warehouse.name(),
                newQuantity,
                0,
                LocalDate.now()
        );

        return stockRepositoryPort.save(newStock);
    }


    private Stock updateExistingStock(Stock existingStock, StockUpdateCommand command) {
        int currentQuantity = existingStock.quantityOnHand();

        int newQuantity = calculateNewQuantity(
                currentQuantity,
                command.quantityChange(),
                command.updateType()
        );

        Stock newStock = new Stock(
                existingStock.stockId(),
                existingStock.productId(),
                existingStock.productSku(),
                existingStock.warehouseId(),
                existingStock.warehouseName(),
                newQuantity,
                existingStock.quantityReserved(),
                LocalDate.now() // better to refresh timestamp
        );
        return stockRepositoryPort.save(newStock);
    }

    @Override
    public void deletStock(Long stockId) {
      stockRepositoryPort.deleteById(stockId);

    }

    @Override
    public List<Stock> getStockList() {
        return stockRepositoryPort.findAll();
    }

    @Override
    public Optional<Stock> findByProductIdAndWarehouseId(Long productId, Long warehouseId) {
        return stockRepositoryPort.findByProductIdAndWarehouseId(productId, warehouseId);
    }

    @Override
    public Optional<Stock> findById(Long stockId) {
        return stockRepositoryPort.findById(stockId);
    }

    @Override
    public Stock reserveStock(int quantity, Long idStock) {

        Stock stock = findById(idStock).orElseThrow(() -> new RuntimeException("Stock non trouvé !"));
        int available = stock.quantityOnHand() - stock.quantityReserved();
        if (quantity > available) {
            throw new IllegalArgumentException("Not enough stock available");
        }
        return new Stock(
                stock.stockId(),
                stock.productId(),
                stock.productSku(),
                stock.warehouseId(),
                stock.warehouseName(),
                stock.quantityOnHand(),
                stock.quantityReserved() + quantity,
                LocalDate.now() // or lastUpdated, depending on your logic
        );
    }

    private int calculateNewQuantity(int currentQuantity, int quantityChange, StockUpdateType updateType) {
        int newQuantity;

        switch (updateType) {
            case PURCHASE:
                newQuantity = currentQuantity + quantityChange;
                break;
            case SALE:
            case RESERVATION:
                newQuantity = currentQuantity - quantityChange;
                if (newQuantity <= 0) {
                    throw new IllegalArgumentException(
                            "Insufficient stock quantity for " + updateType +
                                    ". Current: " + currentQuantity +
                                    ", requested: " + quantityChange
                    );
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown StockUpdateType: " + updateType);
        }
        return newQuantity;
    }

}