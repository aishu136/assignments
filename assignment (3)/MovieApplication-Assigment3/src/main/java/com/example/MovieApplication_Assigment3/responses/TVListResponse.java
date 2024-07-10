package com.example.MovieApplication_Assigment3.responses;


import com.example.MovieApplication_Assigment3.model.TVList;
import lombok.Data;

import java.util.List;

@Data
public class TVListResponse {
    private int page;
    private List<TVList> results;
    private int totalPages;
    private int totalResults;
}
