package com.pivovarit.movies.repository;

import com.pivovarit.movies.domain.Movie;
import com.pivovarit.movies.domain.MovieId;
import com.pivovarit.movies.domain.MovieType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MovieRepository {

    private final Map<MovieId, Movie> movies = new ConcurrentHashMap<>();

    public MovieId save(Movie movie) {
        movies.put(movie.getId(), movie);
        return movie.getId();
    }

    public Collection<Movie> findAll() {
        return movies.values();
    }

    public Optional<Movie> findByTitle(String title) {
        return movies.values().stream().filter(m -> m.getTitle().equals(title)).findFirst();
    }

    public List<Movie> findByType(MovieType type) {
        return movies.values().stream().filter(m -> m.getType() == type).toList();
    }

    public Optional<Movie> findById(MovieId id) {
        return Optional.ofNullable(movies.get(id));
    }
}
