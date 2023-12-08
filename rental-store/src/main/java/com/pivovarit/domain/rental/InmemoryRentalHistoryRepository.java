package com.pivovarit.domain.rental;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class InmemoryRentalHistoryRepository implements RentalHistoryRepository {

    private final List<PersistedRentalEvent> events = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void save(RentalEvent event) {
        events.add(new PersistedRentalEvent(events.size() + 1, event.type(), Instant.now(), event.accountId(), event.movieId()
          .id()));
    }

    @Override
    public List<PersistedRentalEvent> findAll() {
        return List.copyOf(events);
    }

    @Override
    public List<PersistedRentalEvent> findAllBy(long accountId) {
        return events.stream().filter(e -> e.accountId() == accountId).toList();
    }
}
