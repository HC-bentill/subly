# ADR-0005: Billing rules

**Status:** Proposed (finalise during Phase 0)

- **Cycles:** anniversary billing. A subscription started on the 14th renews on the 14th.
- **Billing in advance:** each invoice charges for the period that is starting.
- **Trials:** no invoice until `trial_end`; then the first full period is invoiced.
- **Proration on plan change:** credit the unused fraction of the old price and charge the remaining fraction of
  the new price, as `invoice_line` rows with `proration = true`. Fraction is computed in seconds.
- **Idempotent billing run:** at most one invoice per `(subscription_id, period_start)` (unique index).
- **Dunning:** retry failed payments on a schedule (e.g. +1, +3, +5 days). Subscription is `PAST_DUE` while retrying,
  then `CANCELED` and the invoice `UNCOLLECTIBLE` when retries run out.
- **Invoice numbers:** from a Postgres sequence on finalisation. Gaps are possible on rollback; accepted for now.
- **Currency:** one currency per customer; prices must match it.
