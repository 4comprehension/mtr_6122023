package com.pivovarit.domain.rental;

import com.pivovarit.domain.descriptions.DescriptionsFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RentalConfiguration {

    @Bean
    RentalFacade rentalFacade(MovieRepository movieRepository, DescriptionsFacade descriptions) {
        return new RentalFacade(movieRepository, descriptions);
    }
}
