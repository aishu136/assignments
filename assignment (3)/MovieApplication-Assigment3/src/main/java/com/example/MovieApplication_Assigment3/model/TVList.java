package com.example.MovieApplication_Assigment3.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "TVList")
public class TVList {
    @Id
    private String id;
    private boolean adult;
}

