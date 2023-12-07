package com.pivovarit.web;

import com.pivovarit.domain.descriptions.DescriptionsFacade;
import com.pivovarit.domain.descriptions.api.MovieDescription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
record DescriptionsController(DescriptionsFacade descriptions) {

    @GetMapping("/descriptions/{id}")
    public ResponseEntity<MovieDescription> getDescription(@PathVariable int id) {
        return ResponseEntity.of(descriptions.getDescription(id));
    }
}
