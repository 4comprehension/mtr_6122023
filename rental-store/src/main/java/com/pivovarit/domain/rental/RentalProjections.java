package com.pivovarit.domain.rental;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

record RentalProjections(RentalHistoryRepository rentalHistory) {

    UserRentals userRentals(long accountId) {
        return rentalHistory.findAllBy(accountId)
          .stream().map(PersistedRentalEvent::toRentalEvent)
          .collect(collectingAndThen(
            toList(),
            list -> new UserRentals(accountId).apply(list)));
    }
}
