package com.tcc.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<HttpStatus> checkHealth(){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
