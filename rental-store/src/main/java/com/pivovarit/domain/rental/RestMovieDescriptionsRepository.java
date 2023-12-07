package com.pivovarit.domain.rental;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Map;
import java.util.Optional;

record RestMovieDescriptionsRepository(RestClient client) implements DescriptionsRepository {

    @Override
    public Optional<String> getDescription(int movieId) {
        try {
            Map<String, String> result = client.get()
              .uri("/descriptions/{id}", movieId)
              .retrieve()
              .body(Map.class);
            return Optional.ofNullable((String) result.get("description"));
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }
}
