package com.example.MovieApplication_Assigment3.service;

import com.example.MovieApplication_Assigment3.dao.MovieRepository;
import com.example.MovieApplication_Assigment3.dao.PeopleRepository;
import com.example.MovieApplication_Assigment3.dao.TvListRepository;
import com.example.MovieApplication_Assigment3.responses.MovieResponse;
import com.example.MovieApplication_Assigment3.responses.PeopleResponse;
import com.example.MovieApplication_Assigment3.responses.TVListResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MovieService {

    private final WebClient webClient;
    private final MovieRepository movieRepository;
    private final PeopleRepository peopleRepository;
    private final TvListRepository tvListRepository;

    public MovieService(WebClient.Builder webClientBuilder, MovieRepository movieRepository,PeopleRepository peopleRepository, TvListRepository tvListRepository) {
        this.webClient = webClientBuilder.baseUrl("https://api.themoviedb.org/3").build();
        this.movieRepository = movieRepository;
        this.peopleRepository = peopleRepository;
        this.tvListRepository = tvListRepository;
    }

    public void fetchAndSaveMovies() {
        Mono<MovieResponse> responseMono = this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/changes")
                        .queryParam("page", 1)
                        .build())
                .header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YmUwYWUzMjllNGEzZDFkOWU0MDU1N2Y2Mjc5NzNjNiIsIm5iZiI6MTcyMDQzNDA2Mi4wMDc3MzEsInN1YiI6IjY2OGJiYmI1YTg2YTEzMjIzZWIxZTY5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Z2z_U3hOStO2a2Y5DK2Kp7vVlOASEt4WuG89XQBtA4Y")
                .header("accept", "application/json")
                .retrieve()
                .bodyToMono(MovieResponse.class);

        responseMono.subscribe(response -> {
            response.getResults().forEach(movie -> {
                movieRepository.save(movie);
            });
        }, error -> {
            // Handle error
            System.err.println("Error fetching movies: " + error.getMessage());
        });
    }
    public void fetchAndSavePeople() {
        Mono<PeopleResponse> responseMono = this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/person/changes")
                        .queryParam("page", 1)
                        .build())
                .header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YmUwYWUzMjllNGEzZDFkOWU0MDU1N2Y2Mjc5NzNjNiIsIm5iZiI6MTcyMDQzNDA2Mi4wMDc3MzEsInN1YiI6IjY2OGJiYmI1YTg2YTEzMjIzZWIxZTY5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Z2z_U3hOStO2a2Y5DK2Kp7vVlOASEt4WuG89XQBtA4Y")
                .header("accept", "application/json")
                .retrieve()
                .bodyToMono(PeopleResponse.class);

        responseMono.subscribe(response -> {
            response.getResults().forEach(movie -> {
                peopleRepository.save(movie);
            });
        }, error -> {
            // Handle error
            System.err.println("Error fetching movies: " + error.getMessage());
        });
    }

    public void fetchAndSaveTVList() {
        Mono<TVListResponse> responseMono = this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/tv/changes")
                        .queryParam("page", 1)
                        .build())
                .header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YmUwYWUzMjllNGEzZDFkOWU0MDU1N2Y2Mjc5NzNjNiIsIm5iZiI6MTcyMDQzNDA2Mi4wMDc3MzEsInN1YiI6IjY2OGJiYmI1YTg2YTEzMjIzZWIxZTY5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Z2z_U3hOStO2a2Y5DK2Kp7vVlOASEt4WuG89XQBtA4Y")
                .header("accept", "application/json")
                .retrieve()
                .bodyToMono(TVListResponse.class);

        responseMono.subscribe(response -> {
            response.getResults().forEach(tvList -> {
                tvListRepository.save(tvList);
            });
        }, error -> {
            // Handle error
            System.err.println("Error fetching tvList: " + error.getMessage());
        });
    }
}