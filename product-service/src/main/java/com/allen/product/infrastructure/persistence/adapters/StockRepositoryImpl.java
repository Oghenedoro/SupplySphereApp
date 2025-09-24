package com.allen.product.infrastructure.persistence.adapters;

import com.allen.product.domain.model.Stock;
import com.allen.product.domain.port.StockRepositoryPort;
import com.allen.product.infrastructure.persistence.entity.StockEntity;
import com.allen.product.infrastructure.persistence.mapper.StockMapper;
import com.allen.product.infrastructure.persistence.springdataJpaRepository.StockRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public class StockRepositoryImpl implements StockRepositoryPort {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public StockRepositoryImpl(@Lazy StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    @Override
    public Stock save(Stock stock) {

        StockEntity stockEntity = stockMapper.StockToStockEntity(stock);
        return stockMapper.stockEntityToStock(stockRepository.save(stockEntity));
    }

    @Override
    public Optional<Stock> findById(Long stockId) {

        return stockRepository.findById(stockId)
                .map(stockMapper::stockEntityToStock);
         }

    @Override
    public Optional<Stock> findByProductIdAndWarehouseId(Long productId, Long warehouseId) {
        return stockRepository.findByProductIdAndWarehouseId(productId,warehouseId)
                .map(stockMapper::stockEntityToStock);
    }

    @Override
    public List<Stock> findAll() {
        return stockRepository.findAll().stream()
                .map(stockMapper::stockEntityToStock)
                .toList();
    }

    @Override
    public void deleteById(Long stockId) {

        stockRepository.deleteById(stockId);
    }
}
