package com.pivovarit.domain.rental;

import java.util.Optional;

interface DescriptionsRepository {
    Optional<String> getDescription(int movieId);
}
