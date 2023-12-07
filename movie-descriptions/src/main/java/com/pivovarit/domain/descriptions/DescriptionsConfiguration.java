package com.pivovarit.domain.descriptions;

import com.pivovarit.domain.descriptions.DescriptionsFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DescriptionsConfiguration {

    @Bean
    public DescriptionsFacade descriptionsFacade() {
        return new DescriptionsFacade();
    }
}
