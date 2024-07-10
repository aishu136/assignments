package com.example.MovieApplication_Assigment3.controller;

import com.example.MovieApplication_Assigment3.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TVListController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/fetch-tvList")
    public String fetchTvlists() {
        movieService.fetchAndSaveTVList();
        return "Tv list fetched and saved successfully";
    }
}