package com.icicaf.example.demo_api_rest.service;

import com.icicaf.example.demo_api_rest.dto.HelloDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class HelloService {
    public HelloDTO getHello(@NotBlank(message = "Name must not be empty")
                             @Size(min = 3,
                                     message = "Name must be at least 3 characters long") String name) {
        return new HelloDTO(MessageFormat.format("¡Hello {0}, from Spring Boot Java 21!", name));
    }
}
