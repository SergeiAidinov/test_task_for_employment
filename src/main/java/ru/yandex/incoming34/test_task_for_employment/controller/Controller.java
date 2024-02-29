package ru.yandex.incoming34.test_task_for_employment.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.incoming34.test_task_for_employment.service.MainService;
import ru.yandex.incoming34.test_task_for_employment.service.ValidationService;
import ru.yandex.incoming34.test_task_for_employment.structures.ServiceAMessage;
import ru.yandex.incoming34.test_task_for_employment.structures.AdaptedMessage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/message_from_service_a")
@AllArgsConstructor
public class Controller {


    private final MainService mainService;
    private final ValidationService validationService;

    @PostMapping(value = "/new_message")
    public Optional<AdaptedMessage> handleMessageFromServiceA(@RequestBody ServiceAMessage serviceAMessage) throws IOException {
        System.out.println(serviceAMessage);
        validationService.throwExceptionIfInvalid(serviceAMessage);
        return mainService.callServiceB(serviceAMessage);
    }

    @ExceptionHandler(value = Exception.class)
    private ResponseEntity<String> handleException(RuntimeException exception) {
        return new ResponseEntity<>(Objects.nonNull(exception.getMessage()) ? exception.getMessage() : "Unknown Error", HttpStatus.OK);
    }

}
