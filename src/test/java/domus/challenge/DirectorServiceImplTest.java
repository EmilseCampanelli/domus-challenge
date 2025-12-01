package domus.challenge;


import domus.challenge.client.MovieClient;
import domus.challenge.dto.MovieDto;
import domus.challenge.dto.MovieSearchResponse;
import domus.challenge.service.implementations.DirectorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DirectorServiceImplTest {

    @Mock
    private MovieClient movieClient;

    private DirectorServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new DirectorServiceImpl(movieClient);
    }

    @Test
    @DisplayName("Given a negative threshold, When service is called, Then it should return an empty list and never call the API")
    void testNegativeThreshold() {
        // Given
        int threshold = -1;

        // When
        List<String> result = service.getDirectorsAboveThreshold(threshold).block();

        // Then
        assertTrue(result.isEmpty());
        verifyNoInteractions(movieClient);
    }

    @Test
    @DisplayName("Given threshold = 0, When movies are returned, Then it should return all directors sorted alphabetically")
    void testThresholdZero() {

        // Given
        MovieDto m1 = new MovieDto();
        m1.setDirector("Zack Snyder");

        MovieDto m2 = new MovieDto();
        m2.setDirector("Christopher Nolan");

        MovieSearchResponse response = new MovieSearchResponse();
        response.setTotalPages(1);
        response.setData(List.of(m1, m2));

        when(movieClient.searchMovies(1)).thenReturn(Mono.just(response));

        // When
        List<String> result = service.getDirectorsAboveThreshold(0).block();

        // Then
        assertEquals(List.of("Christopher Nolan", "Zack Snyder"), result);
        verify(movieClient, times(1)).searchMovies(1);
    }

    @Test
    @DisplayName("Given empty movie data, When service is called, Then it should return an empty list")
    void testEmptyMovieList() {

        // Given
        MovieSearchResponse empty = new MovieSearchResponse();
        empty.setTotalPages(1);
        empty.setData(Collections.emptyList());

        when(movieClient.searchMovies(1)).thenReturn(Mono.just(empty));

        // When
        List<String> result = service.getDirectorsAboveThreshold(0).block();

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Given null movie data, When service is called, Then it should not break and return an empty list")
    void testNullMovieList() {

        // Given
        MovieSearchResponse response = new MovieSearchResponse();
        response.setTotalPages(1);
        response.setData(null);

        when(movieClient.searchMovies(1)).thenReturn(Mono.just(response));

        // When
        List<String> result = service.getDirectorsAboveThreshold(0).block();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}