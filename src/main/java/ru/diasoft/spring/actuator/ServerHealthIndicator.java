package ru.diasoft.spring.actuator;

import java.util.Random;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class ServerHealthIndicator implements HealthIndicator {
    private final Random random = new Random();

    @Override
    public Health health() {
        boolean serverIsDown = random.nextBoolean();
        if (serverIsDown) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Отключение сервера")
                    .build();
        } else {
            return Health.up().withDetail("message", "Работает успешно").build();
        }
    }
}
