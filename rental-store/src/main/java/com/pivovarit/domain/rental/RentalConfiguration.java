package com.pivovarit.domain.rental;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RentalConfiguration {

    public static void main(String[] args) {
    }

    @Bean
    RentalFacade rentalFacade(MovieRepository movieRepository, DescriptionsRepository descriptions) {
        return new RentalFacade(movieRepository, descriptions);
    }
}
