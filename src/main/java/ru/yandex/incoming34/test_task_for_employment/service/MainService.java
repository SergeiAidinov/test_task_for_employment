package ru.yandex.incoming34.test_task_for_employment.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import ru.yandex.incoming34.test_task_for_employment.structures.Coordinates;
import ru.yandex.incoming34.test_task_for_employment.structures.ServiceAMessage;
import ru.yandex.incoming34.test_task_for_employment.structures.AdaptedMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Properties;

@Service
@AllArgsConstructor
public class MainService {


    private final Properties properties;
    private final RestTemplate dummyRestTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AdaptedMessage callServiceB(ServiceAMessage serviceAMessage) {
        HttpURLConnection connection = prepareConnection(serviceAMessage.getCoordinates());
        InputStream responseStream = null;
        JsonNode root = null;
        try {
            responseStream = connection.getInputStream();
            root = objectMapper.readTree(responseStream);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Не удалось получить информацию о погоде");
        }
        String temperature = root.get("main").get("temp").asText();
        AdaptedMessage adaptedMessage = adaptMessage(serviceAMessage, temperature);
        dummyRestTemplate.put("http://localhost:8080//api//adapted_message//new_message", adaptedMessage);
        return adaptedMessage;

    }

    private AdaptedMessage adaptMessage(ServiceAMessage serviceAMessage, String temperature) {
        return new AdaptedMessage(serviceAMessage.getMsg(), LocalDateTime.now(), temperature);
    }

    private HttpURLConnection prepareConnection(Coordinates coordinates) {
        String request = new StringBuilder(properties.getProperty("apiHttp"))
                .append("?")
                .append("lat=")
                .append(coordinates.getLatitude())
                .append("&lon=")
                .append(coordinates.getLongitude())
                .append("&appid=")
                .append(properties.getProperty("appId"))
                .append("&lang=ru&")
                .append("units=metric")
                .toString();
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(request);
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        connection.setRequestProperty("accept", "application/json");
        return connection;
    }
}
