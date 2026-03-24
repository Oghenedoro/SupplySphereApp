package com.allen.product.domain.port;

import com.allen.product.domain.model.Subscription;

import java.util.Optional;

public interface SubscriptionRepositoryPort {
    Subscription save(Subscription subscription);
    Optional<Subscription> findByUserId(String userId);
}
