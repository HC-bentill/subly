package com.subly.domain.ledger;

/** What posted a ledger entry. */
public enum LedgerSource {
    INVOICE,
    PAYMENT,
    CREDIT_NOTE,
    REFUND
}
