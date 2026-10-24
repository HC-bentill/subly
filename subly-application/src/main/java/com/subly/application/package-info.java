/**
 * Application layer: use cases (one class per command/query) and the ports they need.
 *
 * <ul>
 *   <li>{@code port.out} – interfaces implemented by infrastructure (persistence, outbox, payment provider).</li>
 *   <li>{@code catalog}, {@code subscription}, {@code billing}, {@code payment} – use cases, added phase by phase.</li>
 * </ul>
 */
package com.subly.application;
