package com.pivovarit.domain.rental;

import java.util.ArrayList;
import java.util.List;

class UserRentals {
    private final long accountId;
    private final List<MovieId> currentlyRented = new ArrayList<>();

    public UserRentals(long accountId) {
        this.accountId = accountId;
    }

    public UserRentals apply(RentalEvent event) {
        switch (event.type()) {
            case RENT -> currentlyRented.add(event.movieId());
            case RETURN -> currentlyRented.remove(event.movieId());
        }

        return this;
    }

    public UserRentals apply(List<RentalEvent> events) {
        events.forEach(this::apply);
        return this;
    }

    public boolean canRentMovies() {
        return currentlyRented.size() < 5;
    }

    public boolean canRent(MovieId movieId) {
        return !currentlyRented.contains(movieId);
    }

    public boolean canReturn(MovieId movieId) {
        return currentlyRented.contains(movieId);
    }

    @Override
    public String toString() {
        return "UserRentals{accountId=%d, currentlyRented=%s}".formatted(accountId, currentlyRented);
    }
}
