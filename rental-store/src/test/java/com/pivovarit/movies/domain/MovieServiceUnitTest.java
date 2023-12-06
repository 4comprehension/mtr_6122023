package com.pivovarit.movies.domain;

import com.pivovarit.movies.api.MovieAddRequest;
import com.pivovarit.movies.repository.InMemoryMovieRepository;
import com.pivovarit.movies.service.RentalService;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

class MovieServiceUnitTest {

    @RepeatedTest(10000)
    void should_add_movie() {
        RentalService service = inMemoryInstance();

        var request = new MovieAddRequest(42, "Spiderman", MovieType.NEW.toString());

        service.save(request);

        var result = service.findById((int) request.getId()).orElseThrow();

        assertThat(result.getType()).isEqualTo(MovieType.NEW);
        assertThat(result.getId().getId()).isEqualTo(request.getId());
        assertThat(result.getTitle()).isEqualTo(request.getTitle());
    }

    private static RentalService inMemoryInstance() {
        return new RentalService(new InMemoryMovieRepository());
    }
}
