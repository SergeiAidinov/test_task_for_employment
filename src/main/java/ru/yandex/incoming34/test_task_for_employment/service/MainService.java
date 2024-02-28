package ru.yandex.incoming34.test_task_for_employment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import ru.yandex.incoming34.test_task_for_employment.structures.ServiceAMessage;
import ru.yandex.incoming34.test_task_for_employment.structures.ServiceBMessage;

import java.time.LocalDateTime;

@org.springframework.stereotype.Service
public class MainService {

    @Autowired
    private RestTemplate mockRestTemplate;

    public ServiceBMessage callServiceB(ServiceAMessage serviceAMessage) {

        return convertMessage(serviceAMessage);

    }

    private ServiceBMessage convertMessage(ServiceAMessage serviceAMessage) {
        return new ServiceBMessage(serviceAMessage.getMsg(), LocalDateTime.now(), 28);
    }
}
