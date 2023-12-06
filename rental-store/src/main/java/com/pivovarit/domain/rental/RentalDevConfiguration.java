package com.pivovarit.domain.rental;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
class RentalDevConfiguration {

    @Bean
    MovieRepository inmemMovieRepository() {
        return new InMemoryMovieRepository();
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
