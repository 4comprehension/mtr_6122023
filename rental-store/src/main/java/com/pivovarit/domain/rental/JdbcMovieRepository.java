package com.pivovarit.domain.rental;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
record JdbcMovieRepository(JdbcClient jdbcClient) implements MovieRepository {

    @Override
    public MovieId save(Movie movie) {
        jdbcClient.sql("INSERT INTO movies(id, title, type) VALUES(?, ?, ?)")
          .param(movie.id().id())
          .param(movie.title())
          .param(movie.type().toString())
          .update();

        return movie.id();
    }

    @Override
    public Collection<Movie> findAll() {
        return jdbcClient.sql("SELECT * FROM movies").query(asMovie()).list();
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return jdbcClient.sql("SELECT * FROM movies m WHERE m.title = ?")
          .param(title)
          .query(asMovie())
          .optional();
    }

    @Override
    public List<Movie> findByType(MovieType type) {
        return jdbcClient.sql("SELECT * FROM movies m WHERE m.type = ?")
          .param(type.toString())
          .query(asMovie())
          .list();
    }

    @Override
    public Optional<Movie> findById(MovieId id) {
        return jdbcClient.sql("SELECT * FROM movies m WHERE m.id = ?")
          .param(id.id())
          .query(asMovie())
          .optional();
    }

    private static RowMapper<Movie> asMovie() {
        return (rs, __) -> new Movie(new MovieId(rs.getLong("id")), rs.getString("title"), MovieType.valueOf(rs.getString("type")));
    }
}
