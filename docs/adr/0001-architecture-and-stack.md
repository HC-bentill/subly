# ADR-0001: Architecture and stack

**Status:** Accepted

## Decision
- Java 25 (LTS) + Spring Boot 4.1, built with Maven.
- Clean architecture as Maven modules, with dependencies pointing inwards only:
  `api → infrastructure → application → domain`.
  The domain module has no Spring or persistence dependencies.
- PostgreSQL via Spring Data JPA, schema owned by Flyway (`ddl-auto: validate`).
- Redis for idempotency keys, distributed locks and rate limiting.
- Kafka (KRaft) for domain events and webhook delivery.

## Why
Mirrors PayFlow's Domain → Application → Infrastructure → Api layering, so the two projects read as one platform.
JPA was chosen over jOOQ for faster delivery of CRUD-heavy catalog and subscription code.
Maven modules make the dependency rule enforceable by the build rather than by convention.
