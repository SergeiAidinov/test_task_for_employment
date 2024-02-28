package ru.yandex.incoming34.test_task_for_employment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@org.springframework.stereotype.Service
public class MainService {

    @Autowired
    private RestTemplate mockRestTemplate;

    public void callServiceB(String id) {
        /*ResponseEntity resp =
                restTemplate.getForEntity("http://localhost:8080/serviceb/api/message" + id, String.class);*/
        mockRestTemplate.put("http://localhost:8080/serviceb/api/message", String.class);

        //return resp.getStatusCode() == HttpStatus.OK ? resp.getBody() : null;
    }
}
