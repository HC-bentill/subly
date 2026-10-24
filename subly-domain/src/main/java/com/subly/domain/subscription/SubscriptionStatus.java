package com.subly.domain.subscription;

import com.subly.domain.shared.InvalidStateTransitionException;
import java.util.EnumSet;
import java.util.Set;

/**
 * Subscription lifecycle.
 *
 * <pre>
 * TRIALING ──► ACTIVE ◄──► PAST_DUE
 *    │           │            │
 *    └───────────┴────────────┴──► CANCELED (terminal)
 * </pre>
 */
public enum SubscriptionStatus {
    TRIALING,
    ACTIVE,
    PAST_DUE,
    CANCELED;

    public Set<SubscriptionStatus> allowedNext() {
        return switch (this) {
            case TRIALING -> EnumSet.of(ACTIVE, PAST_DUE, CANCELED);
            case ACTIVE -> EnumSet.of(PAST_DUE, CANCELED);
            case PAST_DUE -> EnumSet.of(ACTIVE, CANCELED);
            case CANCELED -> EnumSet.noneOf(SubscriptionStatus.class);
        };
    }

    public boolean canTransitionTo(SubscriptionStatus next) {
        return allowedNext().contains(next);
    }

    public SubscriptionStatus transitionTo(SubscriptionStatus next) {
        if (!canTransitionTo(next)) {
            throw new InvalidStateTransitionException(this, next);
        }
        return next;
    }
}
