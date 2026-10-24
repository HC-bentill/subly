package com.subly.domain.catalog;

/** How a price turns a quantity into an amount. */
public enum PriceModel {
    /** One fixed amount per period, quantity ignored. */
    FLAT,
    /** unit_amount x quantity (seats). */
    PER_SEAT,
    /** Graduated tiers defined in price_tier. */
    TIERED
}
