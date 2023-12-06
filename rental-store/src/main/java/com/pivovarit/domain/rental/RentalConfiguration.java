package com.pivovarit.domain.rental;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RentalConfiguration {

    @Bean
    RentalFacade rentalFacade(MovieRepository movieRepository) {
        return new RentalFacade(movieRepository);
    }
}
