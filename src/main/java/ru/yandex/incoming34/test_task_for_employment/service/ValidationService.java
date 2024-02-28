package ru.yandex.incoming34.test_task_for_employment.service;

import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Service;
import ru.yandex.incoming34.test_task_for_employment.structures.ServiceAMessage;

@Service
public class ValidationService {

    private final String MESSAGE_INVALID = "Неверный формат сообщения";
    private final ValidatorFactory factory = jakarta.validation.Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void throwExceptionIfInvalid(ServiceAMessage serviceAMessage) {
        if(!validator.validate(serviceAMessage).isEmpty()) throw  new RuntimeException(MESSAGE_INVALID);
    }

}
