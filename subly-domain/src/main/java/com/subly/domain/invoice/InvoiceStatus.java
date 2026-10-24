package com.subly.domain.invoice;

import com.subly.domain.shared.InvalidStateTransitionException;
import java.util.EnumSet;
import java.util.Set;

/**
 * Invoice lifecycle.
 *
 * <pre>
 * DRAFT ──► OPEN ──► PAID (terminal)
 *   │         ├────► VOID (terminal)
 *   └──► VOID └────► UNCOLLECTIBLE ──► PAID | VOID
 * </pre>
 */
public enum InvoiceStatus {
    DRAFT,
    OPEN,
    PAID,
    VOID,
    UNCOLLECTIBLE;

    public Set<InvoiceStatus> allowedNext() {
        return switch (this) {
            case DRAFT -> EnumSet.of(OPEN, VOID);
            case OPEN -> EnumSet.of(PAID, VOID, UNCOLLECTIBLE);
            case UNCOLLECTIBLE -> EnumSet.of(PAID, VOID);
            case PAID, VOID -> EnumSet.noneOf(InvoiceStatus.class);
        };
    }

    public boolean canTransitionTo(InvoiceStatus next) {
        return allowedNext().contains(next);
    }

    public InvoiceStatus transitionTo(InvoiceStatus next) {
        if (!canTransitionTo(next)) {
            throw new InvalidStateTransitionException(this, next);
        }
        return next;
    }
}
