package com.example.DBS.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Healthcontroller {


@GetMapping("/health")
public ResponseEntity<String> healthcheck(){
    return ResponseEntity.ok("Spring Boot svc runnig.");
}
}