package com.allen.product.application.usecase;

import com.allen.product.domain.model.Subscription;

import java.util.Optional;

public interface SubscriptionUseCase {
    Subscription subscribe(String userId, String email);
    Optional<Subscription> getSubscription(String userId);
    boolean isSubscribed(String userId);
}
