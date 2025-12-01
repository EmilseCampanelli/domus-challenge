package domus.challenge.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerWebFluxConfig {

    @Bean
    public GroupedOpenApi directorsApi() {
        return GroupedOpenApi.builder()
                .group("directors")
                .packagesToScan("domus.challenge.controller")
                .build();
    }
}
