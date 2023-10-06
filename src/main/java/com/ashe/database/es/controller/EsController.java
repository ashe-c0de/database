package com.ashe.database.es.controller;

import com.ashe.database.es.service.EsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/a")
public class EsController {

    private final EsService esService;

    public EsController(EsService esService) {
        this.esService = esService;
    }

    @PostMapping("/b")
    public ResponseEntity<Void> creatIndex(){
        esService.creatIndex();
        return ResponseEntity.ok().build();
    }
}
