package com.pivovarit.domain.rental;

import com.pivovarit.domain.rental.api.MovieDto;

class MovieConverters {

    public static MovieDto from(Movie movie, String description) {
        return new MovieDto(movie.id().id(), movie.title(), movie.type().toString(), description);
    }
}
