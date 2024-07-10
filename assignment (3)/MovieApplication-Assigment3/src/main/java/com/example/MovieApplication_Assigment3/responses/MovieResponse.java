package com.example.MovieApplication_Assigment3.responses;

import com.example.MovieApplication_Assigment3.model.Movie;
import lombok.Data;

import java.util.List;

@Data
public class MovieResponse {
    private int page;
    private List<Movie> results;
    private int totalPages;
    private int totalResults;
}
