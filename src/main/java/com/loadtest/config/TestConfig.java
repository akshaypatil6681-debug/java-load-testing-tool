package com.loadtest.config;

public class TestConfig {

    // URL of the API or web service to test
	public static final String TARGET_URL = "https://httpbin.org/get";

    // Number of simulated users (threads)
    public static final int NUM_USERS = 10;

    // Number of requests each user will send
    public static final int REQUESTS_PER_USER = 5;

    // Timeout for each request in milliseconds
    public static final int TIMEOUT_MS = 5000;

}
