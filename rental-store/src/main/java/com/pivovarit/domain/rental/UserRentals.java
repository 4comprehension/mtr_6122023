package com.pivovarit.domain.rental;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
class UserRentals {
    private final long accountId;
    private long version;
    private final List<MovieId> currentlyRented = new ArrayList<>();

    public UserRentals apply(RentalEvent event) {
        switch (event.type()) {
            case RENT -> {
                currentlyRented.add(event.movieId());
                version = event.accountVersion();
            }
            case RETURN -> {
                currentlyRented.remove(event.movieId());
                version = event.accountVersion();
            }
        }

        return this;
    }

    public void rent(MovieId movieId) {
        if (!canRentMovies()) {
            throw new IllegalArgumentException("User has reached the maximum number of rentals");
        }
        if (!canRent(movieId)) {
            throw new IllegalArgumentException("User cannot rent movie: " + movieId);
        }

        currentlyRented.add(movieId);
        version++;
    }

    public void returnMovie(MovieId movieId) {
        if (!canReturn(movieId)) {
            throw new IllegalArgumentException("User cannot return movie: " + movieId);
        }
        currentlyRented.remove(movieId);
        version++;
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
        return "UserRentals{accountId=%d, version=%d, currentlyRented=%s}".formatted(accountId, version, currentlyRented);
    }
}
