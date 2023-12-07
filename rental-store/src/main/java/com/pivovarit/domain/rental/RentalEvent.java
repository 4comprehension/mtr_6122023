package com.pivovarit.domain.rental;

record RentalEvent(EventType type, MovieId id, long accountId) {

    enum EventType {
        RENT, RETURN
    }
}
