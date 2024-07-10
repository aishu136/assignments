package com.example.assignment.controller;

import com.example.assignment.model.ResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HashMapController {

    @GetMapping("/api/response")
    public ResponseModel getResponse() {
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        data.put("key3", "value3");

        ResponseModel response = new ResponseModel();
        response.setData(data);

        return response;
    }
}
