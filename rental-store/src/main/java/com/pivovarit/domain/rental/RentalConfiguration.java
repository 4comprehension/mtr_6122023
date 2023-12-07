package com.pivovarit.domain.rental;

import com.pivovarit.domain.descriptions.DescriptionsFacade;
import com.pivovarit.domain.descriptions.api.MovieDescription;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RentalConfiguration {

    @Bean
    RentalFacade rentalFacade(MovieRepository movieRepository, DescriptionsRepository descriptions) {
        return new RentalFacade(movieRepository, descriptions);
    }

    @Bean
    DescriptionsRepository descriptionRepository(DescriptionsFacade descriptions) {
        return movieId -> descriptions.getDescription(movieId).map(MovieDescription::description);
    }
}
