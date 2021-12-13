package com.luxoft.springmvc01;

import java.util.Random;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class RandomHealthCheck implements HealthIndicator {
    @Override
    public Health health() {
        return new Random().nextBoolean()
        ? Health.up().build()
        : Health.down().build();
    }
}
