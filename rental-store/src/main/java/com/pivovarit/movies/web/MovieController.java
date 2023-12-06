package com.pivovarit.movies.web;

import com.pivovarit.movies.api.MovieAddRequest;
import com.pivovarit.movies.api.MovieDto;
import com.pivovarit.movies.domain.Movie;
import com.pivovarit.movies.domain.MovieType;
import com.pivovarit.movies.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class MovieController {

    private final MovieService movieService;

    MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<MovieDto> getMovies(@RequestParam(required = false) String type) {
        if (type != null) {
            return movieService.findAllByType(MovieType.valueOf(type)).stream().map(MovieController::from).toList();
        } else {
            return movieService.findAll().stream().map(MovieController::from).toList();
        }
    }

    @GetMapping("/movies/{id}")
    public MovieDto getMovies(@PathVariable int id) {
        return from(movieService.findById(id));
    }

    @PostMapping("/movies")
    public void addMovie(@RequestBody MovieAddRequest request) {
        movieService.save(request);
    }

    public static MovieDto from(Movie movie) {
        return new MovieDto(movie.getId().getId(), movie.getTitle(), movie.getType().toString());
    }
}
