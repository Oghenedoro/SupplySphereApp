package com.allen.product.infrastructure.persistence.mapper;

import com.allen.product.application.constants.StockUpdateType;
import com.allen.product.domain.model.StockTransaction;
import com.allen.product.infrastructure.persistence.entity.StockTransactionEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-28T03:03:45+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class StockTransactionMapperImpl implements StockTransactionMapper {

    @Override
    public StockTransaction entityToStockTransaction(StockTransactionEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long stockTransactionId = null;
        Long productId = null;
        Long warehouseId = null;
        String warehouseName = null;
        StockUpdateType type = null;
        Integer quantityChange = null;
        Integer resultingQuantity = null;
        LocalDateTime transactionDate = null;

        stockTransactionId = entity.getStockTransactionId();
        productId = entity.getProductId();
        warehouseId = entity.getWarehouseId();
        warehouseName = entity.getWarehouseName();
        type = entity.getType();
        quantityChange = entity.getQuantityChange();
        resultingQuantity = entity.getResultingQuantity();
        transactionDate = entity.getTransactionDate();

        StockTransaction stockTransaction = new StockTransaction( stockTransactionId, productId, warehouseId, warehouseName, type, quantityChange, resultingQuantity, transactionDate );

        return stockTransaction;
    }

    @Override
    public StockTransactionEntity stockTransactionToEntity(StockTransaction domain) {
        if ( domain == null ) {
            return null;
        }

        StockTransactionEntity.StockTransactionEntityBuilder stockTransactionEntity = StockTransactionEntity.builder();

        stockTransactionEntity.productId( domain.productId() );
        stockTransactionEntity.quantityChange( domain.quantityChange() );
        stockTransactionEntity.resultingQuantity( domain.resultingQuantity() );
        stockTransactionEntity.stockTransactionId( domain.stockTransactionId() );
        stockTransactionEntity.transactionDate( domain.transactionDate() );
        stockTransactionEntity.type( domain.type() );
        stockTransactionEntity.warehouseId( domain.warehouseId() );
        stockTransactionEntity.warehouseName( domain.warehouseName() );

        return stockTransactionEntity.build();
    }

    @Override
    public List<StockTransaction> listEntityToStockTransactionList(List<StockTransactionEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<StockTransaction> list = new ArrayList<StockTransaction>( entities.size() );
        for ( StockTransactionEntity stockTransactionEntity : entities ) {
            list.add( entityToStockTransaction( stockTransactionEntity ) );
        }

        return list;
    }
}
