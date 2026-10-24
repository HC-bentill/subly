# Changelog

## [Unreleased]

### Added
- Phase 0/1 scaffold: Maven multi-module clean architecture (domain, application, infrastructure, api).
- Domain: `Money` value object, subscription and invoice state machines, catalog/payment/ledger enums.
- Flyway `V1__init_schema.sql` covering the full domain model.
- Security skeleton (stateless JWT, anonymous health/metrics), correlation-ID filter, problem-detail errors.
- docker-compose (Postgres, Redis, Kafka KRaft, Prometheus, Grafana), multi-stage Dockerfile.
- GitHub Actions CI with a self-hosted runner deploy job; k3s manifests.
