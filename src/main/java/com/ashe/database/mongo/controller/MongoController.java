package com.ashe.database.mongo.controller;

import com.ashe.database.mongo.service.MongoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongo")
public class MongoController {

    private final MongoService mongoService;

    public MongoController(MongoService mongoService) {
        this.mongoService = mongoService;
    }

    @GetMapping("/getOne")
    public ResponseEntity<String> getOne(){
        return ResponseEntity.ok(mongoService.getOne());
    }

}
