package ru.yandex.incoming34.test_task_for_employment.structures;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ServiceAMessage {

    private final String msg;
    private final Languages lng;
    private final Coordinates coordinates;
}
