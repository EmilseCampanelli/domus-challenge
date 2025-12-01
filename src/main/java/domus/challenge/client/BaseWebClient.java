package domus.challenge.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.http.HttpStatusCode;
import reactor.core.publisher.Mono;
import domus.challenge.exception.MovieClientException;
import domus.challenge.exception.MovieApiTechnicalException;

@Slf4j
public abstract class BaseWebClient {

    protected <T> Mono<T> executeGet(WebClient client, String path, Class<T> responseType) {

        log.info("[HTTP] GET {}", path);

        return client.get()
                .uri(path)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, resp ->
                        resp.bodyToMono(String.class).flatMap(body -> {
                            log.error("[HTTP] 4xx: {}", body);
                            return Mono.error(new MovieClientException(body));
                        })
                )
                .onStatus(HttpStatusCode::is5xxServerError, resp ->
                        resp.bodyToMono(String.class).flatMap(body -> {
                            log.error("[HTTP] 5xx: {}", body);
                            return Mono.error(new MovieApiTechnicalException(body));
                        })
                )
                .bodyToMono(responseType);
    }
}