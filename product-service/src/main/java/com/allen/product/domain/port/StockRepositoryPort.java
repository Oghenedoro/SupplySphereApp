package com.allen.product.domain.port;

import com.allen.product.domain.model.Stock;
import java.util.List;
import java.util.Optional;

public interface StockRepositoryPort {

    Stock save(Stock stock);
    Optional<Stock> findById(Long stockId);
    Optional<Stock> findByProductIdAndWarehouseId(Long productId, Long warehouseId);
    List<Stock> findAll();
    void deleteById(Long stockId);

}
