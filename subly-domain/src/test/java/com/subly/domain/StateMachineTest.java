package com.subly.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.subly.domain.invoice.InvoiceStatus;
import com.subly.domain.shared.InvalidStateTransitionException;
import com.subly.domain.subscription.SubscriptionStatus;
import org.junit.jupiter.api.Test;

class StateMachineTest {

    @Test
    void subscriptionHappyPathAndDunning() {
        SubscriptionStatus s = SubscriptionStatus.TRIALING
                .transitionTo(SubscriptionStatus.ACTIVE)
                .transitionTo(SubscriptionStatus.PAST_DUE)
                .transitionTo(SubscriptionStatus.ACTIVE)
                .transitionTo(SubscriptionStatus.CANCELED);
        assertThat(s).isEqualTo(SubscriptionStatus.CANCELED);
    }

    @Test
    void canceledSubscriptionIsTerminal() {
        assertThat(SubscriptionStatus.CANCELED.allowedNext()).isEmpty();
        assertThatThrownBy(() -> SubscriptionStatus.CANCELED.transitionTo(SubscriptionStatus.ACTIVE))
                .isInstanceOf(InvalidStateTransitionException.class);
    }

    @Test
    void invoiceCannotSkipFinalisation() {
        assertThat(InvoiceStatus.DRAFT.canTransitionTo(InvoiceStatus.PAID)).isFalse();
        assertThat(InvoiceStatus.DRAFT.transitionTo(InvoiceStatus.OPEN).transitionTo(InvoiceStatus.PAID))
                .isEqualTo(InvoiceStatus.PAID);
    }

    @Test
    void uncollectibleInvoiceCanStillBePaid() {
        assertThat(InvoiceStatus.UNCOLLECTIBLE.canTransitionTo(InvoiceStatus.PAID)).isTrue();
    }
}
