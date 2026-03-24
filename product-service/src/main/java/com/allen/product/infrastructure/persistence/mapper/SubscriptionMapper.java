package com.allen.product.infrastructure.persistence.mapper;

import com.allen.product.domain.model.Subscription;
import com.allen.product.infrastructure.persistence.entity.SubscriptionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    Subscription subscriptionEntityToSubscription(SubscriptionEntity entity);
    SubscriptionEntity subscriptionToEntity(Subscription subscription);
}
