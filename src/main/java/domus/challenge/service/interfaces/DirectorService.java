package domus.challenge.service.interfaces;

import reactor.core.publisher.Mono;

import java.util.List;

public interface DirectorService {

    Mono<List<String>> getDirectorsAboveThreshold(int threshold);
}
