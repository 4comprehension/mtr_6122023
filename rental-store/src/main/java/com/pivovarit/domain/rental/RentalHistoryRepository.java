package com.pivovarit.domain.rental;

import java.time.Instant;
import java.util.List;

interface RentalHistoryRepository {
    void save(RentalEvent event);

    List<PersistedRentalEvent> findAll();

    List<PersistedRentalEvent> findAllBy(long accountId);

    record PersistedRentalEvent(long eventId, EventType type, Instant timestamp, long accountId, long movieId) {

    }
}
