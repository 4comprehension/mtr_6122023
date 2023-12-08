package com.pivovarit.domain.rental;

import com.pivovarit.domain.rental.api.MovieAddRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class MovieServiceUnitTest {

    @Test
    void should_add_movie() {
        RentalFacade service = inMemoryInstance();

        var request = new MovieAddRequest(42, "Spiderman", MovieType.NEW.toString());

        service.save(request);

        var result = service.findById((int) request.id()).orElseThrow();

        Assertions.assertThat(result.type()).isEqualTo("NEW");
        Assertions.assertThat(result.id()).isEqualTo(request.id());
        Assertions.assertThat(result.title()).isEqualTo(request.title());
    }

    private static RentalFacade inMemoryInstance() {
        var rentalHistory = new InMemoryRentalHistoryRepository();
        return new RentalFacade(new InMemoryMovieRepository(), movieId -> Optional.empty(), rentalHistory, new RentalProjections(rentalHistory), event -> {});
    }
}
