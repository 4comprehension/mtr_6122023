package com.pivovarit.domain.rental;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

@Slf4j
record AsyncMessageRelay(MessagePublisher messagePublisher, RentalHistoryRepository rentalHistory) {

//    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
    public void process() {

        for (var event : rentalHistory.findUnprocessed()) {
            messagePublisher.send(event.toRentalEvent());
            rentalHistory.markProcessed(event.eventId());
        }
    }
}
