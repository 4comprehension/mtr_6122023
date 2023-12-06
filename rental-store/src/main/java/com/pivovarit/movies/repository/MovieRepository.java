package com.pivovarit.movies.repository;

import com.pivovarit.movies.domain.Movie;
import com.pivovarit.movies.domain.MovieId;
import com.pivovarit.movies.domain.MovieType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    MovieId save(Movie movie);

    Collection<Movie> findAll();

    Optional<Movie> findByTitle(String title);

    List<Movie> findByType(MovieType type);

    Optional<Movie> findById(MovieId id);
}
