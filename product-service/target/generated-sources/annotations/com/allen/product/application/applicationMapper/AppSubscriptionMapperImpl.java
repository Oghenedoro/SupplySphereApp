package com.allen.product.application.applicationMapper;

import com.allen.product.application.dto.SubscriptionDto;
import com.allen.product.domain.model.Subscription;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-24T19:00:11+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17 (Eclipse Adoptium)"
)
@Component
public class AppSubscriptionMapperImpl implements AppSubscriptionMapper {

    @Override
    public SubscriptionDto subscriptionToDto(Subscription subscription) {
        if ( subscription == null ) {
            return null;
        }

        Long id = null;
        String userId = null;
        String email = null;
        boolean active = false;
        LocalDateTime subscribedAt = null;

        id = subscription.id();
        userId = subscription.userId();
        email = subscription.email();
        active = subscription.active();
        subscribedAt = subscription.subscribedAt();

        SubscriptionDto subscriptionDto = new SubscriptionDto( id, userId, email, active, subscribedAt );

        return subscriptionDto;
    }

    @Override
    public Subscription dtoToSubscription(SubscriptionDto dto) {
        if ( dto == null ) {
            return null;
        }

        Long id = null;
        String userId = null;
        String email = null;
        boolean active = false;
        LocalDateTime subscribedAt = null;

        id = dto.id();
        userId = dto.userId();
        email = dto.email();
        active = dto.active();
        subscribedAt = dto.subscribedAt();

        Subscription subscription = new Subscription( id, userId, email, active, subscribedAt );

        return subscription;
    }
}
