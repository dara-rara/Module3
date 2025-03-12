package org.example;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;


public class Task4 {

    public void getRequestHeaders () {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/headers")) //
                .build();
        var responseFuture = client.sendAsync(
                request, HttpResponse.BodyHandlers.ofString());

        responseFuture.thenAccept(response -> {
            if (response.statusCode() == 200) {
                var jsonResponse = new JSONObject(response.body());
                var headers = jsonResponse.getJSONObject("headers");
                var sbHeaders = new StringBuilder();
                for (String key : headers.keySet()) {
                    sbHeaders.append(key).append(": ").append(headers.getString(key)).append(", ");
                }
                if (sbHeaders.length() > 0) {
                    sbHeaders.setLength(sbHeaders.length() - 2);
                }
                System.out.println("Заголовки запроса: " + sbHeaders);
            } else {
                System.out.println("Ошибка: HTTP-статус " + response.statusCode());
            }
        }).exceptionally(e -> {
            System.out.println("Ошибка при выполнении запроса: " + e.getMessage());
            return null;
        }).join();
    }
}
