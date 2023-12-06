package com.pivovarit.movies.service;

import com.pivovarit.movies.api.MovieAddRequest;
import com.pivovarit.movies.domain.Movie;
import com.pivovarit.movies.domain.MovieId;
import com.pivovarit.movies.domain.MovieType;
import com.pivovarit.movies.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing movie rentals.
 */
@Service
public class RentalService {

    private final MovieRepository movieRepository;

    public RentalService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void save(MovieAddRequest movieAddRequest) {
        movieRepository.save(new Movie(new MovieId(movieAddRequest.getId()), movieAddRequest.getTitle(), MovieType.valueOf(movieAddRequest.getType())));
    }

    public List<Movie> findAll() {
        return new ArrayList<>(movieRepository.findAll());
    }

    public List<Movie> findAllByType(MovieType type) {
        return findAll().stream().filter(m -> m.getType() == type).toList();
    }

    public Optional<Movie> findById(int id) {
        return movieRepository.findById(new MovieId(id));
    }
}
