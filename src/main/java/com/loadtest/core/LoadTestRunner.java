package com.loadtest.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import com.loadtest.report.ResultCollector;


import com.loadtest.config.TestConfig;
import com.loadtest.http.HttpClientUtil;

public class LoadTestRunner {

    public static void main(String[] args) {

        System.out.println("Starting load test...");
        System.out.println("Users: " + TestConfig.NUM_USERS);
        System.out.println("Requests per user: " + TestConfig.REQUESTS_PER_USER);

        ExecutorService executor =
                Executors.newFixedThreadPool(TestConfig.NUM_USERS);

        for (int i = 1; i <= TestConfig.NUM_USERS; i++) {
            final int userId = i;

            executor.submit(() -> {
                System.out.println("User " + userId + " started");

                for (int j = 1; j <= TestConfig.REQUESTS_PER_USER; j++) {
                    HttpClientUtil.sendGetRequest();
                }

                System.out.println("User " + userId + " finished");
            });
        }

        executor.shutdown();

        try {
            executor.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ResultCollector.printSummary();
        System.out.println("Load test completed.");
    }
}
