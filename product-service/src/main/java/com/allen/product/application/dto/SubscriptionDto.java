package com.allen.product.application.dto;

import java.time.LocalDateTime;

public record SubscriptionDto(
        Long id,
        String userId,
        String email,
        boolean active,
        LocalDateTime subscribedAt
) {}
