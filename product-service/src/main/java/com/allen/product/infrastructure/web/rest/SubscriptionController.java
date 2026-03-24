package com.allen.product.infrastructure.web.rest;

import com.allen.product.application.applicationMapper.AppSubscriptionMapper;
import com.allen.product.application.dto.SubscriptionDto;
import com.allen.product.application.dto.SubscriptionRequest;
import com.allen.product.application.usecase.SubscriptionUseCase;
import com.allen.product.domain.model.Subscription;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionUseCase subscriptionUseCase;
    private final AppSubscriptionMapper subscriptionMapper;

    public SubscriptionController(SubscriptionUseCase subscriptionUseCase,
                                  AppSubscriptionMapper subscriptionMapper) {
        this.subscriptionUseCase = subscriptionUseCase;
        this.subscriptionMapper = subscriptionMapper;
    }

    @PostMapping
    public ResponseEntity<SubscriptionDto> subscribe(@RequestBody SubscriptionRequest request) {
        Subscription subscription = subscriptionUseCase.subscribe(request.userId(), request.email());
        return new ResponseEntity<>(subscriptionMapper.subscriptionToDto(subscription), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<SubscriptionDto> getSubscription(@PathVariable String userId) {
        return subscriptionUseCase.getSubscription(userId)
                .map(s -> ResponseEntity.ok(subscriptionMapper.subscriptionToDto(s)))
                .orElse(ResponseEntity.notFound().build());
    }
}
