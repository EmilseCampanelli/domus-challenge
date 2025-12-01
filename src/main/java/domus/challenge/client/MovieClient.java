package domus.challenge.client;

import domus.challenge.dto.MovieSearchResponse;
import reactor.core.publisher.Mono;

public interface MovieClient {

    Mono<MovieSearchResponse> searchMovies(int page);
}