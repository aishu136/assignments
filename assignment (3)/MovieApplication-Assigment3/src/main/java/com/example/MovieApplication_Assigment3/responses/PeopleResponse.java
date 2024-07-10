package com.example.MovieApplication_Assigment3.responses;

import com.example.MovieApplication_Assigment3.model.Movie;
import com.example.MovieApplication_Assigment3.model.People;
import lombok.Data;

import java.util.List;

@Data
public class PeopleResponse {
    private int page;
    private List<People> results;
    private int totalPages;
    private int totalResults;
}
