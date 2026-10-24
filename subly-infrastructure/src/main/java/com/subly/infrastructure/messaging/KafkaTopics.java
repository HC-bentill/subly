package com.subly.infrastructure.messaging;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/** Topics are created on startup (single broker locally / on the homelab, so 1 replica). */
@Configuration
public class KafkaTopics {

    public static final String SUBSCRIPTION_EVENTS = "subscription.events";
    public static final String INVOICE_EVENTS = "invoice.events";
    public static final String PAYMENT_EVENTS = "payment.events";
    public static final String WEBHOOK_DELIVERY = "webhook.delivery";
    public static final String WEBHOOK_DLQ = "webhook.dlq";

    @Bean
    NewTopic subscriptionEvents() {
        return topic(SUBSCRIPTION_EVENTS);
    }

    @Bean
    NewTopic invoiceEvents() {
        return topic(INVOICE_EVENTS);
    }

    @Bean
    NewTopic paymentEvents() {
        return topic(PAYMENT_EVENTS);
    }

    @Bean
    NewTopic webhookDelivery() {
        return topic(WEBHOOK_DELIVERY);
    }

    @Bean
    NewTopic webhookDlq() {
        return topic(WEBHOOK_DLQ);
    }

    private static NewTopic topic(String name) {
        return TopicBuilder.name(name).partitions(3).replicas(1).build();
    }
}
