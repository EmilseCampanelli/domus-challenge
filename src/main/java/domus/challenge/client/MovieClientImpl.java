package domus.challenge.client;

import domus.challenge.dto.MovieSearchResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Slf4j
@Component
@RequiredArgsConstructor
public class MovieClientImpl extends BaseWebClient implements MovieClient {

    private final WebClient moviesWebClient;

    private static final String SEARCH_MOVIES_PATH = "/api/movies/search?page=";


    @Override
    public Mono<MovieSearchResponse> searchMovies(int page) {

        String path = SEARCH_MOVIES_PATH + page;

        return executeGet(moviesWebClient, path, MovieSearchResponse.class)
                .doOnNext(response -> log.info("[MovieAPI] page {} → totalPages={}, movies={}",
                        page,
                        response.getTotalPages(),
                        response.getData() != null ? response.getData().size() : 0
                ));
    }
}