package com.pivovarit.domain.rental;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
class JdbcMovieRepository implements MovieRepository {

    private final JdbcClient jdbcClient;

    JdbcMovieRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public MovieId save(Movie movie) {
        return null;
    }

    @Override
    public Collection<Movie> findAll() {
        return null;
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public List<Movie> findByType(MovieType type) {
        return null;
    }

    @Override
    public Optional<Movie> findById(MovieId id) {
        return Optional.empty();
    }
}
