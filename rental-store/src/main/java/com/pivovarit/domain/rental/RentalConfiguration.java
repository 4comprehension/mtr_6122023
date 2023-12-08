package com.pivovarit.domain.rental;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RentalConfiguration {

    public static void main(String[] args) {
    }

    @Bean
    RentalFacade rentalFacade(MovieRepository movieRepository, DescriptionsRepository descriptions, RentalHistoryRepository rentalHistoryRepository, RentalProjections rentalProjections) {
        return new RentalFacade(movieRepository, descriptions, rentalHistoryRepository, rentalProjections);
    }

    @Bean
    RentalProjections rentalProjections(RentalHistoryRepository rentalHistoryRepository) {
        return new RentalProjections(rentalHistoryRepository);
    }

    @Bean
    ApplicationRunner rentalHistoryPeeker(RentalHistoryRepository rentalHistoryRepository) {
        return args -> rentalHistoryRepository.findAll().forEach(System.out::println);
    }
}
