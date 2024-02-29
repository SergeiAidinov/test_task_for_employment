package ru.yandex.incoming34.test_task_for_employment.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.yandex.incoming34.test_task_for_employment.structures.Coordinates;
import ru.yandex.incoming34.test_task_for_employment.structures.ServiceAMessage;
import ru.yandex.incoming34.test_task_for_employment.structures.AdaptedMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

@Service
@AllArgsConstructor
public class MainService {


    private final Properties properties;
    private final RestTemplate dummyRestTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AdaptedMessage callServiceB(ServiceAMessage serviceAMessage) {
        Optional<AdaptedMessage> adaptedMessageOptional = requestTemperature(serviceAMessage.getCoordinates())
                .map(temperature -> adaptMessage(serviceAMessage, temperature));
        adaptedMessageOptional.ifPresentOrElse(adaptedMessage -> {
            try {
                dummyRestTemplate.put(properties.getProperty("dummyUrl"), adaptedMessage);
            } catch (Exception e) {
                throw new RuntimeException("Не удалось вызвать Service B");
            }
        }, () -> {
            throw new RuntimeException("Не удалось получить значение температуры");
        });
        return adaptedMessageOptional.get();
    }

    private Optional<String> requestTemperature(Coordinates coordinates) {
        HttpURLConnection connection = prepareConnection(coordinates);
        InputStream responseStream;
        JsonNode root;
        try {
            responseStream = connection.getInputStream();
            root = objectMapper.readTree(responseStream);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Не удалось получить информацию о погоде");
        }
        return Objects.nonNull(root.get("main").get("temp")) ?
                Optional.ofNullable(root.get("main").get("temp").asText())
                : Optional.empty();

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
        URL url;
        HttpURLConnection connection;
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
