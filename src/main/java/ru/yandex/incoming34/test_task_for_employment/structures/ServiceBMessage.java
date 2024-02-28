package ru.yandex.incoming34.test_task_for_employment.structures;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ServiceBMessage {
    private final String msg;
    private final LocalDateTime createdDt;
    private final Integer currentTemp;
}
