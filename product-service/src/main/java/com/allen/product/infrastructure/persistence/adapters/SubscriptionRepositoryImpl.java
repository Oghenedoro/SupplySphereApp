package com.allen.product.infrastructure.persistence.adapters;

import com.allen.product.domain.model.Subscription;
import com.allen.product.domain.port.SubscriptionRepositoryPort;
import com.allen.product.infrastructure.persistence.mapper.SubscriptionMapper;
import com.allen.product.infrastructure.persistence.springdataJpaRepository.SubscriptionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SubscriptionRepositoryImpl implements SubscriptionRepositoryPort {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionRepositoryImpl(SubscriptionRepository subscriptionRepository,
                                      SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    @Override
    public Subscription save(Subscription subscription) {
        return subscriptionMapper.subscriptionEntityToSubscription(
                subscriptionRepository.save(subscriptionMapper.subscriptionToEntity(subscription))
        );
    }

    @Override
    public Optional<Subscription> findByUserId(String userId) {
        return subscriptionRepository.findByUserId(userId)
                .map(subscriptionMapper::subscriptionEntityToSubscription);
    }
}
