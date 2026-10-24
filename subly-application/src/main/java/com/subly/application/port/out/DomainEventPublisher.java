package com.subly.application.port.out;

/**
 * Records a domain event in the transactional outbox, in the SAME database transaction
 * as the state change. A separate relay later publishes it to Kafka (see ADR-0004).
 */
public interface DomainEventPublisher {

    void publish(String eventType, String aggregateId, Object payload);
}
