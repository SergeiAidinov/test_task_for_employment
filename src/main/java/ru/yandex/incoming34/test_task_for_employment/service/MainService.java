package ru.yandex.incoming34.test_task_for_employment.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.yandex.incoming34.test_task_for_employment.structures.ServiceAMessage;
import ru.yandex.incoming34.test_task_for_employment.structures.AdaptedMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

@org.springframework.stereotype.Service
public class MainService {

    @Autowired
    private RestTemplate dummyRestTemplate;

    public AdaptedMessage callServiceB(ServiceAMessage serviceAMessage) throws IOException {
        String OPENWEATHER_API = "7549b3ff11a7b2f3cd25b56d21c83c6a";
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?"
                + "lat=80&lon=80&"
                +"appid=" + OPENWEATHER_API + "&lang=ru&"
               + "units=metric");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseStream);
        System.out.println(root.path("fact").asText());
        String temperature = root.get("main").get("temp").asText();
        AdaptedMessage adaptedMessage = adaptMessage(serviceAMessage, temperature);
        dummyRestTemplate.put("http://localhost:8080//api//adapted_message//new_message", adaptedMessage);
        return adaptedMessage;

    }

    private AdaptedMessage adaptMessage(ServiceAMessage serviceAMessage, String temperature) {
        return new AdaptedMessage(serviceAMessage.getMsg(), LocalDateTime.now(), temperature);
    }
}
