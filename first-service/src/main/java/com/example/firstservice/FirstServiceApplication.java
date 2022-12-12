package com.example.firstservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FirstServiceApplication {
  @GetMapping("/msg")
  public String getMessage() {
    return "Hello world message from first service!";
  }

  public static void main(String[] args) {
    SpringApplication.run(FirstServiceApplication.class, args);
  }

}
