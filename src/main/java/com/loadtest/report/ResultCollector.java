package com.loadtest.report;
import com.loadtest.report.CsvReportWriter;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ResultCollector {

    private static final AtomicInteger totalRequests = new AtomicInteger(0);
    private static final AtomicInteger successCount = new AtomicInteger(0);
    private static final AtomicInteger failureCount = new AtomicInteger(0);
    private static final AtomicLong totalResponseTime = new AtomicLong(0);

    public static void recordResult(int statusCode, long responseTime) {
        totalRequests.incrementAndGet();
        totalResponseTime.addAndGet(responseTime);

        if (statusCode == 200) {
            successCount.incrementAndGet();
        } else {
            failureCount.incrementAndGet();
        }
    }

    public static void printSummary() {
        System.out.println("\n===== LOAD TEST SUMMARY =====");
        System.out.println("Total Requests: " + totalRequests.get());
        System.out.println("Successful Requests: " + successCount.get());
        System.out.println("Failed Requests: " + failureCount.get());

        long avgResponseTime = 0;

        if (totalRequests.get() > 0) {
            avgResponseTime = totalResponseTime.get() / totalRequests.get();
            System.out.println("Average Response Time: "
                    + avgResponseTime + " ms");
        }

        CsvReportWriter.writeReport(
                totalRequests.get(),
                successCount.get(),
                failureCount.get(),
                avgResponseTime
        );

    }
}
