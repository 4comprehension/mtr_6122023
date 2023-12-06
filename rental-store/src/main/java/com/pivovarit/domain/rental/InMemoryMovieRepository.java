package com.pivovarit.domain.rental;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
class InMemoryMovieRepository implements MovieRepository {

    private final Map<MovieId, Movie> movies = new ConcurrentHashMap<>();

    @Override
    public MovieId save(Movie movie) {
        movies.put(movie.id(), movie);
        return movie.id();
    }

    @Override
    public Collection<Movie> findAll() {
        return movies.values();
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return movies.values().stream().filter(m -> m.title().equals(title)).findFirst();
    }

    @Override
    public List<Movie> findByType(MovieType type) {
        return movies.values().stream().filter(m -> m.type() == type).toList();
    }

    @Override
    public Optional<Movie> findById(MovieId id) {
        return Optional.ofNullable(movies.get(id));
    }
}
