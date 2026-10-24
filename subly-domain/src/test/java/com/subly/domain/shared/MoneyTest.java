package com.subly.domain.shared;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void scalesToCurrencyMinorUnits() {
        assertThat(Money.of("10", "USD").amount()).isEqualByComparingTo("10.00");
        assertThat(Money.of("10.5", "JPY").amount()).isEqualByComparingTo("10");
    }

    @Test
    void usesBankersRounding() {
        assertThat(Money.of("0.125", "USD").amount()).isEqualByComparingTo("0.12");
        assertThat(Money.of("0.135", "USD").amount()).isEqualByComparingTo("0.14");
    }

    @Test
    void addsAndSubtractsSameCurrency() {
        Money result = Money.of("10.00", "USD").plus(Money.of("2.50", "USD")).minus(Money.of("1", "USD"));
        assertThat(result).isEqualTo(Money.of("11.50", "USD"));
    }

    @Test
    void prorates() {
        Money half = Money.of("30.00", "USD").times(new BigDecimal("0.5"));
        assertThat(half).isEqualTo(Money.of("15.00", "USD"));
    }

    @Test
    void rejectsCurrencyMismatch() {
        assertThatThrownBy(() -> Money.of("1", "USD").plus(Money.of("1", "GHS")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
