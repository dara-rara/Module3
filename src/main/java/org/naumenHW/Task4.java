package org.naumenHW;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.StringJoiner;


public class Task4 {

    public void getRequestHeaders () {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/headers"))
                    .method("HEAD", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                StringJoiner headersJoiner = new StringJoiner(", ");
                response.headers().map().forEach((key, values) -> {
                    headersJoiner.add(key + ": " + String.join(", ", values));
                });
                System.out.println("Заголовки ответа: " + headersJoiner);
            } else {
                System.out.println("Ошибка: HTTP-статус " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Ошибка при выполнении запроса: " + e.getMessage());
        }
    }
}
