package com.pivovarit.domain.rental;

interface MessagePublisher {
    void send(RentalEvent event);
}
