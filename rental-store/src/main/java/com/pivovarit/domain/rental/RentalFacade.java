package com.pivovarit.domain.rental;

import com.pivovarit.domain.rental.api.MovieAddRequest;
import com.pivovarit.domain.rental.api.MovieDto;

import java.util.List;
import java.util.Optional;

public class RentalFacade {

    private final MovieRepository movieRepository;

    public RentalFacade(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void save(MovieAddRequest movieAddRequest) {
        movieRepository.save(new Movie(new MovieId(movieAddRequest.id()), movieAddRequest.title(), MovieType.valueOf(movieAddRequest.type())));
    }

    public List<MovieDto> findAll() {
        return movieRepository.findAll().stream().map(MovieConverters::from).toList();
    }

    public List<MovieDto> findAllByType(String type) {
        return movieRepository.findByType(MovieType.valueOf(type)).stream().map(MovieConverters::from).toList();
    }

    public Optional<MovieDto> findById(int id) {
        return movieRepository.findById(new MovieId(id)).map(MovieConverters::from);
    }
}
