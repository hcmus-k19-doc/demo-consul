package com.example.secondservice;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SecondServiceApplication {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private DiscoveryClient discoveryClient;

  @GetMapping("/msg")
  public String getMessageFromFirstService() {
    URI uri = discoveryClient.getInstances("first-service").stream()
        .map(si -> si.getUri()).findFirst()
        .map(uri1 -> uri1.resolve("/msg"))
        .get();
    return restTemplate.getForObject(uri, String.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(SecondServiceApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

}
