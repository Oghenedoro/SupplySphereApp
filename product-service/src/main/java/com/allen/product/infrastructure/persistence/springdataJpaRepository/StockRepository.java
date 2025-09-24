package com.allen.product.infrastructure.persistence.springdataJpaRepository;

import com.allen.product.domain.model.Stock;
import com.allen.product.infrastructure.persistence.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {
    Optional<StockEntity> findByProductIdAndWarehouseId(Long productId, Long warehouseId);
}
