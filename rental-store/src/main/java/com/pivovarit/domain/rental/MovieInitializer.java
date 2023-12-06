package com.pivovarit.domain.rental;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

// TODO
@Component
record MovieInitializer(MovieRepository movieRepository) implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        movieRepository.save(new Movie(new MovieId(1), "Spiderman", MovieType.REGULAR));
        movieRepository.save(new Movie(new MovieId(2), "Tenet", MovieType.NEW));
        movieRepository.save(new Movie(new MovieId(3), "Casablanca", MovieType.OLD));

    }
}
