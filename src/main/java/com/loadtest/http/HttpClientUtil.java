package com.loadtest.http;

import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.loadtest.report.ResultCollector;


import com.loadtest.config.TestConfig;

public class HttpClientUtil {

    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(java.time.Duration.ofMillis(TestConfig.TIMEOUT_MS))
            .build();

    public static int sendGetRequest() {
        try {
            long startTime = System.currentTimeMillis();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(TestConfig.TARGET_URL))
                    .header("User-Agent", "SimpleLoadTool/1.0")
                    .header("Accept", "application/json")
                    .GET()
                    .build();


            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            long endTime = System.currentTimeMillis();
            long responseTime = endTime - startTime;

            ResultCollector.recordResult(response.statusCode(), responseTime);

            System.out.println("Status: " + response.statusCode()
                    + " | Response Time: " + responseTime + " ms");

            return response.statusCode();


        } catch (Exception e) {
            System.out.println("Request failed: " + e.getMessage());
            return -1;
        }
    }
}
