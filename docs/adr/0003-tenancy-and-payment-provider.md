# ADR-0003: Single-tenant model and mock payment provider

**Status:** Accepted (tenancy to be revisited)

## Decision
- Single-tenant: one Subly deployment bills one business's customers. No `tenant_id` columns.
- Payments go through a mock provider with configurable failure modes (decline, timeout, insufficient funds),
  not a real integration such as Stripe or Paystack.

## Consequences
- Simpler queries and schema. If Subly ever needs to serve multiple merchants, add PayFlow-style
  tenant isolation (tenant on every row, tenant resolved from the token, filtered in every repository).
- Dunning and retry logic can be exercised deterministically in tests.
