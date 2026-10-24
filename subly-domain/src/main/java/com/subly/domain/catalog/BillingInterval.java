package com.subly.domain.catalog;

import java.time.ZonedDateTime;

/** Length of a billing period. Billing is anniversary-based: periods start on the subscribe date. */
public enum BillingInterval {
    MONTH,
    YEAR;

    public ZonedDateTime nextPeriodEnd(ZonedDateTime periodStart) {
        return switch (this) {
            case MONTH -> periodStart.plusMonths(1);
            case YEAR -> periodStart.plusYears(1);
        };
    }
}
