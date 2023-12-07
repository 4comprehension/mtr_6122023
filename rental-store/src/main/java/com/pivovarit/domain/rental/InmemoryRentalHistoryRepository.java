package com.pivovarit.domain.rental;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class InmemoryRentalHistoryRepository implements RentalHistoryRepository {

    private final List<RentalEvent> events = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void save(RentalEvent event) {
        System.out.println(event.toString());
        events.add(event);
    }

    @Override
    public List<RentalEvent> findAll() {
        return List.copyOf(events);
    }

    @Override
    public List<RentalEvent> findAllBy(long accountId) {
        return events.stream().filter(e -> e.accountId() == accountId).toList();
    }
}
