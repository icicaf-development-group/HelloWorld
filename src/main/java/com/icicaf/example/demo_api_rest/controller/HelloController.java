package com.icicaf.example.demo_api_rest.controller;

import com.icicaf.example.demo_api_rest.dto.HelloDTO;
import com.icicaf.example.demo_api_rest.dto.HelloRequest;
import com.icicaf.example.demo_api_rest.service.HelloService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public ResponseEntity<HelloDTO> sayHelloWorld(@RequestBody @Valid HelloRequest helloRequest) {
        log.info("Log......");
        return ResponseEntity.ok(helloService.getHello(helloRequest.getName()));
    }

}
