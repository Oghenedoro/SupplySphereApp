package com.allen.product.domain.model;

import java.time.LocalDateTime;

public record Subscription(
        Long id,
        String userId,
        String email,
        boolean active,
        LocalDateTime subscribedAt
) {}
