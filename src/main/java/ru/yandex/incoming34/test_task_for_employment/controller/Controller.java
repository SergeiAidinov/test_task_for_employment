package ru.yandex.incoming34.test_task_for_employment.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.incoming34.test_task_for_employment.service.MainService;
import ru.yandex.incoming34.test_task_for_employment.structures.ServiceAMessage;

@RestController
@RequestMapping("/api/message_from_service_a")
@AllArgsConstructor
public class Controller {


    private final MainService mainService;

    @PostMapping(value = "/new_message")
    public void handleMessageFromServiceA(@RequestBody ServiceAMessage serviceAMessage) {
        mainService.callServiceB("qqq");
    }
}
