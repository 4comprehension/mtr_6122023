package com.pivovarit.domain.rental;

import com.pivovarit.domain.rental.api.MovieAddRequest;
import com.pivovarit.domain.rental.api.MovieDto;
import com.pivovarit.domain.rental.api.RentMovieRequest;
import com.pivovarit.domain.rental.api.ReturnMovieRequest;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public record RentalFacade(
  MovieRepository movieRepository,
  DescriptionsRepository movieDescriptions,
  RentalHistoryRepository rentalHistory) {

    public void rentMovie(RentMovieRequest request) {
        rentalHistory.save(new RentalEvent(RentalEvent.EventType.RENT, new MovieId(request.movieId()), request.accountId()));
    }

    public void returnMovie(ReturnMovieRequest request) {
        rentalHistory.save(new RentalEvent(RentalEvent.EventType.RETURN, new MovieId(request.movieId()), request.accountId()));
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
}
