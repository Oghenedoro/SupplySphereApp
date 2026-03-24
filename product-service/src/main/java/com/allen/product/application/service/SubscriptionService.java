package com.allen.product.application.service;

import com.allen.product.application.usecase.SubscriptionUseCase;
import com.allen.product.domain.model.Subscription;
import com.allen.product.domain.port.SubscriptionRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SubscriptionService implements SubscriptionUseCase {

    private final SubscriptionRepositoryPort subscriptionRepositoryPort;

    public SubscriptionService(SubscriptionRepositoryPort subscriptionRepositoryPort) {
        this.subscriptionRepositoryPort = subscriptionRepositoryPort;
    }

    @Override
    public Subscription subscribe(String userId, String email) {
        Optional<Subscription> existing = subscriptionRepositoryPort.findByUserId(userId);
        if (existing.isPresent() && existing.get().active()) {
            return existing.get();
        }
        Subscription subscription = new Subscription(null, userId, email, true, LocalDateTime.now());
        return subscriptionRepositoryPort.save(subscription);
    }

    @Override
    public Optional<Subscription> getSubscription(String userId) {
        return subscriptionRepositoryPort.findByUserId(userId);
    }

    @Override
    public boolean isSubscribed(String userId) {
        return subscriptionRepositoryPort.findByUserId(userId)
                .map(Subscription::active)
                .orElse(false);
    }
}
