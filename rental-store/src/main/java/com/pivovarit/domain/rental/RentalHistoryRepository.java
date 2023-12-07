package com.pivovarit.domain.rental;

import java.util.List;

interface RentalHistoryRepository {
    void save(RentalEvent event);

    List<PersistedRentalEvent> findAll();

    List<PersistedRentalEvent> findAllBy(long accountId);
}
