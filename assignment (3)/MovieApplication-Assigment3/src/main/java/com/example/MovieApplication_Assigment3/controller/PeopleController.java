package com.example.MovieApplication_Assigment3.controller;

import com.example.MovieApplication_Assigment3.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/fetch-people")
    public String fetchPeople() {
        movieService.fetchAndSavePeople();
        return "People fetched and saved successfully";
    }
}
