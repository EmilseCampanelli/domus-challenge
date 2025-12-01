package domus.challenge.service.implementations;

import domus.challenge.client.MovieClient;
import domus.challenge.dto.MovieDto;
import domus.challenge.dto.MovieSearchResponse;
import domus.challenge.service.interfaces.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final MovieClient movieClient;

    @Override
    public Mono<List<String>> getDirectorsAboveThreshold(int threshold) {

        if (threshold < 0) {
            return Mono.just(Collections.emptyList());
        }

        return movieClient.searchMovies(1)
                .flatMapMany(firstPage -> {

                    int totalPages = firstPage.getTotalPages();

                    Flux<Integer> pages = Flux.range(2, totalPages - 1);

                    return Flux.concat(
                            Mono.just(firstPage),
                            pages.flatMap(movieClient::searchMovies)
                    );
                })
                .filter(page -> page.getData() != null)
                .flatMapIterable(MovieSearchResponse::getData)
                .filter(movie -> movie.getDirector() != null && !movie.getDirector().isBlank())
                .map(MovieDto::getDirector)
                .groupBy(name -> name)
                .flatMap(g -> g.count().map(count -> Map.entry(g.key(), count)))
                .filter(entry -> entry.getValue() > threshold)
                .map(Map.Entry::getKey)
                .sort()
                .collectList();
    }
}
