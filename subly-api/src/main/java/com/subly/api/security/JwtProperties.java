package com.subly.api.security;

import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @param secret HMAC-SHA256 signing key, at least 32 bytes. Injected from a Secret in k8s.
 * @param ttl    access-token lifetime (15 minutes, same as PayFlow).
 */
@ConfigurationProperties("subly.jwt")
public record JwtProperties(String secret, String issuer, Duration ttl) {

    public JwtProperties {
        if (secret == null || secret.length() < 32) {
            throw new IllegalArgumentException("subly.jwt.secret must be at least 32 characters");
        }
        if (ttl == null) {
            ttl = Duration.ofMinutes(15);
        }
    }
}
