package com.pivovarit.domain.rental;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.client.RestClient;

@Configuration
@Profile("prod")
class RentalProdConfiguration {
    @Bean
    MovieRepository jdbcMovieRepository(JdbcClient jdbcClient) {
        return new JdbcMovieRepository(jdbcClient);
    }

    @Bean
    DescriptionsRepository descriptionsRepository(@Value("${url.descriptions}") String url, RestClient.Builder restClient) {
        return new RestMovieDescriptionsRepository(restClient
          .baseUrl(url)
          .build());
    }

    @Bean
    RentalHistoryRepository rentalHistoryRepository(JdbcClient jdbcClient) {
        return new JdbcRentalHistoryRepository(jdbcClient);
    }
}
