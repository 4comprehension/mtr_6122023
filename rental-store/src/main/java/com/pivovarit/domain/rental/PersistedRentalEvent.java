package com.pivovarit.domain.rental;

import java.time.Instant;

record PersistedRentalEvent(long eventId, EventType type, Instant timestamp, long accountId, long movieId, long accountVersion) {

    RentalEvent toRentalEvent() {
        return new RentalEvent(type, new MovieId(movieId), accountId, accountVersion);
    }
}
