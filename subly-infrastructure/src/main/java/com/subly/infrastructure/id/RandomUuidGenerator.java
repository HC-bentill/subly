package com.subly.infrastructure.id;

import com.subly.application.port.out.IdGenerator;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
class RandomUuidGenerator implements IdGenerator {

    @Override
    public UUID newId() {
        return UUID.randomUUID();
    }
}
