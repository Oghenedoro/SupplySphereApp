package com.allen.product.application.applicationMapper;

import com.allen.product.application.dto.SubscriptionDto;
import com.allen.product.domain.model.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppSubscriptionMapper {
    SubscriptionDto subscriptionToDto(Subscription subscription);
    Subscription dtoToSubscription(SubscriptionDto dto);
}
