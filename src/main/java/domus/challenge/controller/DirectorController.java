package domus.challenge.controller;


import domus.challenge.dto.DirectorsResponse;
import domus.challenge.service.interfaces.DirectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/directors")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    @Operation(
            summary = "Get directors with movie count above threshold",
            description = "Returns the list of director names whose number of movies is strictly greater than the given threshold. " +
                    "The result is sorted alphabetically."
    )
    public Mono<DirectorsResponse> getDirectors(
            @Parameter(
                    description = "Threshold for minimum number of movies. " +
                            "Directors with movie count strictly greater than this value are returned."
            )
            @RequestParam(name = "threshold") int threshold
    ) {
        return directorService.getDirectorsAboveThreshold(threshold)
                .map(DirectorsResponse::new);
    }
}