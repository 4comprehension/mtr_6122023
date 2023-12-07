package com.pivovarit.domain.descriptions;

import com.pivovarit.domain.descriptions.api.MovieDescription;

import java.util.Optional;

public class DescriptionsFacade {

    public Optional<MovieDescription> getDescription(int movieId) {
        return switch (movieId) {
            case 1 ->
              Optional.of(new MovieDescription("Spiderman is a superhero movie based on the Marvel Comics character of the same name. The film follows Peter Parker who becomes Spiderman after being bitten by a genetically altered spider. The film details his struggles to balance life, love, and his newfound powers."));
            case 2 ->
              Optional.of(new MovieDescription("Tenet is a science fiction action-thriller film. The film follows a secret agent who learns to manipulate time in order to prevent an attack from the future that threatens to annihilate the present world. The film is noted for its complex storyline and thematic richness."));
            case 3 ->
              Optional.of(new MovieDescription("Casablanca is a classic romantic drama set during World War II in the Vichy-controlled Moroccan city of Casablanca. The film follows Rick Blaine who owns a nightclub and sees his old flame walk into his establishment one day. The film is about sacrifice, love, and duty."));
            default -> Optional.empty();
        };
    }
}
