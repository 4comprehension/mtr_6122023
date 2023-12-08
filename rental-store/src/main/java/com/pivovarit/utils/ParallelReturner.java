package com.pivovarit.utils;

import org.springframework.web.client.RestClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ParallelReturner {

    public static void main(String[] args) {
        var client = RestClient.builder()
          .baseUrl("http://localhost:8081")
          .build();

        int parallelism = 5;
        try (var executor = Executors.newFixedThreadPool(parallelism)) {

            CountDownLatch countDownLatch = new CountDownLatch(parallelism);

            for (int i = 1; i <= parallelism; i++) {
                int finalI = i;
                executor.submit(() -> {
                    countDownLatch.countDown();
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                    }
                    try {
                        client.post()
                          .uri("/movies/{id}/rent", finalI)
                          .body(new Account(1))
                          .retrieve()
                          .toBodilessEntity();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    record Account(long accountId) {
    }
}
