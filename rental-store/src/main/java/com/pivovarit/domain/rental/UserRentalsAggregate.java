package com.pivovarit.domain.rental;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@ToString
class UserRentalsAggregate {
    private final long accountId;
    private long version;
    private final List<MovieId> currentlyRented = new ArrayList<>();

    public UserRentalsAggregate apply(RentalEvent event) {
        switch (event.type()) {
            case RENT -> rent(event.movieId());
            case RETURN -> returnMovie(event.movieId());
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

    public UserRentalsAggregate apply(List<RentalEvent> events) {
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
}
