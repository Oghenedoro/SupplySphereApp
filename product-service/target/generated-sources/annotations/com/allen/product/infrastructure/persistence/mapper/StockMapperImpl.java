package com.allen.product.infrastructure.persistence.mapper;

import com.allen.product.domain.model.Stock;
import com.allen.product.infrastructure.persistence.entity.ProductEntity;
import com.allen.product.infrastructure.persistence.entity.StockEntity;
import com.allen.product.infrastructure.persistence.entity.WarehouseEntity;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-23T20:14:20+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (OpenLogic)"
)
@Component
public class StockMapperImpl implements StockMapper {

    @Override
    public Stock stockEntityToStock(StockEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long productId = null;
        String productSku = null;
        Long warehouseId = null;
        String warehouseName = null;
        Long stockId = null;
        Integer quantityOnHand = null;
        Integer quantityReserved = null;
        LocalDate lastUpdated = null;

        productId = entityProductEntityProductId( entity );
        productSku = entityProductEntitySku( entity );
        warehouseId = entityWarehouseWarehouseId( entity );
        warehouseName = entityWarehouseName( entity );
        stockId = entity.getStockId();
        quantityOnHand = entity.getQuantityOnHand();
        quantityReserved = entity.getQuantityReserved();
        lastUpdated = entity.getLastUpdated();

        Stock stock = new Stock( stockId, productId, productSku, warehouseId, warehouseName, quantityOnHand, quantityReserved, lastUpdated );

        return stock;
    }

    @Override
    public StockEntity StockToStockEntity(Stock stock) {
        if ( stock == null ) {
            return null;
        }

        StockEntity stockEntity = new StockEntity();

        stockEntity.setProductEntity( productEntityFromId( stock.productId() ) );
        stockEntity.setWarehouse( warehouseEntityFromId( stock.warehouseId() ) );
        stockEntity.setStockId( stock.stockId() );
        stockEntity.setQuantityOnHand( stock.quantityOnHand() );
        stockEntity.setQuantityReserved( stock.quantityReserved() );
        stockEntity.setLastUpdated( stock.lastUpdated() );

        return stockEntity;
    }

    private Long entityProductEntityProductId(StockEntity stockEntity) {
        ProductEntity productEntity = stockEntity.getProductEntity();
        if ( productEntity == null ) {
            return null;
        }
        return productEntity.getProductId();
    }

    private String entityProductEntitySku(StockEntity stockEntity) {
        ProductEntity productEntity = stockEntity.getProductEntity();
        if ( productEntity == null ) {
            return null;
        }
        return productEntity.getSku();
    }

    private Long entityWarehouseWarehouseId(StockEntity stockEntity) {
        WarehouseEntity warehouse = stockEntity.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        return warehouse.getWarehouseId();
    }

    private String entityWarehouseName(StockEntity stockEntity) {
        WarehouseEntity warehouse = stockEntity.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        return warehouse.getName();
    }
}
