package com.example.MovieApplication_Assigment3.dao;

import com.example.MovieApplication_Assigment3.model.Movie;
import com.example.MovieApplication_Assigment3.model.People;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PeopleRepository extends MongoRepository<People, String> {
}
