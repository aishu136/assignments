package com.example.MovieApplication_Assigment3.dao;

import com.example.MovieApplication_Assigment3.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> {
}
