package com.pivovarit.domain.rental;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

@Configuration
@Profile("dev")
class RentalDevConfiguration {

    @Bean
    MovieRepository inmemMovieRepository() {
        return new InMemoryMovieRepository();
    }

    @Bean
    DescriptionsRepository inmemDescriptionsRepository() {
        return movieId -> Optional.of("lorem ipsum");
    }

    @Bean
    RentalHistoryRepository inmemRentalHistoryRepository() {
        return new InMemoryRentalHistoryRepository();
    }

    @Bean
    MessagePublisher loggingMessagePublisher() {
        return event -> System.out.println("sending: " + event);
    }

    @Bean
    ApplicationRunner moviePopulator(MovieRepository movieRepository) {
        return args -> {
            movieRepository.save(new Movie(new MovieId(1), "Spiderman", MovieType.REGULAR));
            movieRepository.save(new Movie(new MovieId(2), "Tenet", MovieType.NEW));
            movieRepository.save(new Movie(new MovieId(3), "Casablanca", MovieType.OLD));
        };
    }
}
