package com.allen.product.infrastructure.web.rest;

import com.allen.product.application.usecase.SubscriptionUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ide")
public class IdeAccessController {

    private final SubscriptionUseCase subscriptionUseCase;

    public IdeAccessController(SubscriptionUseCase subscriptionUseCase) {
        this.subscriptionUseCase = subscriptionUseCase;
    }

    @GetMapping("/access")
    public ResponseEntity<Map<String, Object>> getIdeAccess(@RequestParam String userId) {
        if (subscriptionUseCase.isSubscribed(userId)) {
            return ResponseEntity.ok(Map.of(
                    "access", true,
                    "userId", userId,
                    "message", "IDE access granted. Welcome to your workspace."
            ));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of(
                        "access", false,
                        "userId", userId,
                        "message", "IDE access denied. An active subscription is required."
                ));
    }
}
