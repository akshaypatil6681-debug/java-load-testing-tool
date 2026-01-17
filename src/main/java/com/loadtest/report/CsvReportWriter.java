package com.loadtest.report;

import java.io.FileWriter;
import java.io.IOException;

public class CsvReportWriter {

    public static void writeReport(
            int totalRequests,
            int success,
            int failure,
            long avgResponseTime) {

        try (FileWriter writer = new FileWriter("load-test-result.csv")) {

            writer.append("Metric,Value\n");
            writer.append("Total Requests," + totalRequests + "\n");
            writer.append("Success," + success + "\n");
            writer.append("Failure," + failure + "\n");
            writer.append("Average Response Time(ms)," + avgResponseTime + "\n");

            System.out.println("CSV report generated: load-test-result.csv");

        } catch (IOException e) {
            System.out.println("Failed to write CSV report: " + e.getMessage());
        }
    }
}
