package com.pivovarit.domain.rental;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;

@Configuration
@Profile("prod")
class RentalProdConfiguration {
    @Bean
    MovieRepository jdbcMovieRepository(JdbcClient jdbcClient) {
        return new JdbcMovieRepository(jdbcClient);
    }
}
