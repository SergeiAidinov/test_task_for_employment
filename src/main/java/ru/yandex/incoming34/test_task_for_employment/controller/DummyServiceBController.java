package ru.yandex.incoming34.test_task_for_employment.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.incoming34.test_task_for_employment.structures.AdaptedMessage;

@RestController
@RequestMapping("/api/adapted_message")
public class DummyServiceBController {

    @PutMapping(value = "/new_message")
    public void handleAdaptedMessage(@RequestBody AdaptedMessage adaptedMessage) {
        System.out.println(adaptedMessage);
    }

}
