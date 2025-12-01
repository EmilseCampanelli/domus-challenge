package domus.challenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    @Bean
    public WebClient moviesWebClient(
            @Value("${movies.api.base-url}") String baseUrl
    ) {
        return WebClient.create(baseUrl);
    }
}
