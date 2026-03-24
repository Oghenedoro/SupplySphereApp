package com.allen.product.infrastructure.persistence.mapper;

import com.allen.product.domain.model.Subscription;
import com.allen.product.infrastructure.persistence.entity.SubscriptionEntity;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-24T19:00:11+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17 (Eclipse Adoptium)"
)
@Component
public class SubscriptionMapperImpl implements SubscriptionMapper {

    @Override
    public Subscription subscriptionEntityToSubscription(SubscriptionEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String userId = null;
        String email = null;
        boolean active = false;
        LocalDateTime subscribedAt = null;

        id = entity.getId();
        userId = entity.getUserId();
        email = entity.getEmail();
        active = entity.isActive();
        subscribedAt = entity.getSubscribedAt();

        Subscription subscription = new Subscription( id, userId, email, active, subscribedAt );

        return subscription;
    }

    @Override
    public SubscriptionEntity subscriptionToEntity(Subscription subscription) {
        if ( subscription == null ) {
            return null;
        }

        SubscriptionEntity.SubscriptionEntityBuilder subscriptionEntity = SubscriptionEntity.builder();

        subscriptionEntity.id( subscription.id() );
        subscriptionEntity.userId( subscription.userId() );
        subscriptionEntity.email( subscription.email() );
        subscriptionEntity.active( subscription.active() );
        subscriptionEntity.subscribedAt( subscription.subscribedAt() );

        return subscriptionEntity.build();
    }
}
