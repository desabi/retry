package com.desabi.controller;

import com.desabi.service.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetryController {

    @Autowired
    private RetryService sampleService;

    @GetMapping("/")
    public ResponseEntity<HttpStatus> index() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/prueba/")
    public ResponseEntity<String> callService()  {
        return ResponseEntity.ok(sampleService.retry());
    }

}
