package com.pivovarit.domain.rental;

import java.util.List;

interface RentalHistoryRepository {
    void save(RentalEvent event);

    List<RentalEvent> findAll();

    List<RentalEvent> findAllBy(long accountId);
}
