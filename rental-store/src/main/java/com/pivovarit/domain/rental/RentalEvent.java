package com.pivovarit.domain.rental;

record RentalEvent(EventType type, MovieId movieId, long accountId, long accountVersion) {

}
