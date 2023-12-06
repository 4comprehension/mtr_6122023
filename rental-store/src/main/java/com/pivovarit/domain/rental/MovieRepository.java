package com.pivovarit.domain.rental;

import com.pivovarit.domain.rental.Movie;
import com.pivovarit.domain.rental.MovieId;
import com.pivovarit.domain.rental.MovieType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

interface MovieRepository {
    MovieId save(Movie movie);

    Collection<Movie> findAll();

    Optional<Movie> findByTitle(String title);

    List<Movie> findByType(MovieType type);

    Optional<Movie> findById(MovieId id);
}
