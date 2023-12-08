package com.pivovarit.domain.rental;

import com.pivovarit.domain.rental.api.MovieAddRequest;
import com.pivovarit.domain.rental.api.MovieDto;
import com.pivovarit.domain.rental.api.RentMovieRequest;
import com.pivovarit.domain.rental.api.ReturnMovieRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

@Slf4j
public record RentalFacade(
  MovieRepository movieRepository,
  DescriptionsRepository movieDescriptions,
  RentalHistoryRepository rentalHistory,
  RentalProjections rentalProjections,
  MessagePublisher messagePublisher
) {

    public void rentMovie(RentMovieRequest request) {
        updateWithRetry(request.accountId(), () -> {
            var userRentals = rentalProjections.userRentals(request.accountId());
            userRentals.rent(new MovieId(request.movieId()));
            var event = new RentalEvent(EventType.RENT, new MovieId(request.movieId()), request.accountId(), userRentals.getVersion());
            rentalHistory.save(event);
        }, 3);
    }

    public void returnMovie(ReturnMovieRequest request) {
        updateWithRetry(request.accountId(), () -> {
            var userRentals = rentalProjections.userRentals(request.accountId());
            userRentals.returnMovie(new MovieId(request.movieId()));
            var event = new RentalEvent(EventType.RETURN, new MovieId(request.movieId()), request.accountId(), userRentals.getVersion());
            rentalHistory.save(event);
        }, 3);
    }

    public void save(MovieAddRequest movieAddRequest) {
        movieRepository.save(new Movie(new MovieId(movieAddRequest.id()), movieAddRequest.title(), MovieType.valueOf(movieAddRequest.type())));
    }

    public List<MovieDto> findAll() {
        return movieRepository.findAll().stream().map(toMovieWithDescription()).toList();
    }

    public List<MovieDto> findAllByType(String type) {
        return movieRepository.findByType(MovieType.valueOf(type)).stream().map(toMovieWithDescription()).toList();
    }

    public Optional<MovieDto> findById(int id) {
        return movieRepository.findById(new MovieId(id)).map(toMovieWithDescription());
    }

    private Function<Movie, MovieDto> toMovieWithDescription() {
        return movie -> movieDescriptions.getDescription((int) movie.id().id())
          .map(desc -> MovieConverters.from(movie, desc))
          .orElseGet(() -> MovieConverters.from(movie, ""));
    }

    private static void updateWithRetry(long accountId, Runnable action, int retries) {
        for (int i = 0; i < retries; i++) {
            try {
                action.run();
                return;
            } catch (DuplicateKeyException e) {
                log.warn("identified concurrent modification of {}, retrying...", accountId);
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(50));
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        throw new IllegalStateException("could not modify account: " + accountId);
    }
}
