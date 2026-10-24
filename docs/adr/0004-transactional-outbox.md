# ADR-0004: Transactional outbox for events

**Status:** Accepted

## Decision
State changes and their events are written in the same database transaction: the use case inserts a row into
`outbox_event`. A relay polls unpublished rows, publishes them to Kafka, then marks them `published_at`.

## Why
PayFlow published to Kafka fire-and-forget after commit, which can silently lose an event if Kafka is down or the
process dies between commit and publish. In billing, a lost `invoice.paid` means a customer who paid keeps losing access.
The outbox gives at-least-once delivery; consumers and webhook receivers must be idempotent on event ID.
