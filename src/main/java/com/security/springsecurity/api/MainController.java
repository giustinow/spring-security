package com.security.springsecurity.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("")
    public ResponseEntity<String> greeting() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

}
