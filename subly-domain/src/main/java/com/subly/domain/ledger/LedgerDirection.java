package com.subly.domain.ledger;

/**
 * Accounts-receivable ledger direction from the customer's point of view.
 * Invoices DEBIT (customer owes more); payments and credit notes CREDIT.
 * Balance owed = SUM(debits) - SUM(credits). Never stored as a column.
 */
public enum LedgerDirection {
    DEBIT,
    CREDIT
}
