package com.pivovarit.domain.scoring;

import com.pivovarit.domain.rental.RentalFacade;
import com.pivovarit.domain.rental.api.MovieDto;

import java.util.List;

public class ScoringFacade {

    private final RentalFacade rentalFacade;

    public ScoringFacade(RentalFacade rentalFacade) {
        this.rentalFacade = rentalFacade;
    }

    public void scoreMovie() {
        List<MovieDto> aNew = rentalFacade.findAllByType("NEW");
    }
}
