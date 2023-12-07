package com.pivovarit.domain.rental.web;

import com.pivovarit.domain.rental.RentalFacade;
import com.pivovarit.domain.rental.api.MovieAddRequest;
import com.pivovarit.domain.rental.api.MovieDto;
import com.pivovarit.domain.rental.api.RentMovieRequest;
import com.pivovarit.domain.rental.api.ReturnMovieRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {

    private final RentalFacade movieService;

    MovieController(RentalFacade movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/movies/{id}/rent")
    public void rentMovie(@PathVariable int id, @RequestBody Account account) {
        movieService.rentMovie(new RentMovieRequest(id, account.accountId()));
    }

    @PostMapping("/movies/{id}/return")
    public void returnMovie(@PathVariable int id, @RequestBody Account account) {
        movieService.returnMovie(new ReturnMovieRequest(id, account.accountId()));
    }

    @GetMapping(value = "/movies", params = "type")
    public List<MovieDto> getMoviesByType(@RequestParam String type) {
        return movieService.findAllByType(type).stream().toList();
    }

    @GetMapping("/movies")
    public List<MovieDto> getMovies() {
        return movieService.findAll().stream().toList();
    }

    @GetMapping("/movies/{id}")
    public Optional<MovieDto> getMovies(@PathVariable int id) {
        return movieService.findById(id);
    }

    @PostMapping("/movies")
    public void addMovie(@RequestBody MovieAddRequest request) {
        movieService.save(request);
    }

    public record Account(long accountId) {
    }
}
