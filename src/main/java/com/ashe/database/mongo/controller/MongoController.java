package com.ashe.database.mongo.controller;

import com.ashe.database.mongo.domain.UserDTO;
import com.ashe.database.mongo.service.MongoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mongo")
public class MongoController {

    private final MongoService mongoService;

    public MongoController(MongoService mongoService) {
        this.mongoService = mongoService;
    }

    @GetMapping("/getOne")
    public ResponseEntity<String> getOne() {
        return ResponseEntity.ok(mongoService.getOne());
    }

    @PostMapping("/insert")
    public ResponseEntity<Void> insert(@RequestBody UserDTO dto) {
        mongoService.insert(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody UserDTO dto) {
        mongoService.update(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> remove(@PathVariable String id) {
        mongoService.remove(id);
        return ResponseEntity.ok().build();
    }

}
