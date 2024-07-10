package com.example.MovieApplication_Assigment3.dao;

import com.example.MovieApplication_Assigment3.model.People;
import com.example.MovieApplication_Assigment3.model.TVList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TvListRepository extends MongoRepository<TVList, String> {
}