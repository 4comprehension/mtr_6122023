package com.pivovarit.domain.rental;

import com.pivovarit.domain.rental.api.MovieAddRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

class MovieServiceUnitTest {

    @RepeatedTest(10000)
    void should_add_movie() {
        RentalFacade service = inMemoryInstance();

        var request = new MovieAddRequest(42, "Spiderman", MovieType.NEW.toString());

        service.save(request);

        var result = service.findById((int) request.id()).orElseThrow();

        Assertions.assertThat(result.type()).isEqualTo(MovieType.NEW);
        Assertions.assertThat(result.id().id()).isEqualTo(request.id());
        Assertions.assertThat(result.title()).isEqualTo(request.title());
    }

    private static RentalFacade inMemoryInstance() {
        return new RentalFacade(new InMemoryMovieRepository());
    }
}
