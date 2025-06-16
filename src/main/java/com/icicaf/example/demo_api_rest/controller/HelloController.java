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

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class HelloController {

    private final HelloService helloService;
    private static final long BYTES_IN_MB = 1024 * 1024;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public ResponseEntity<HelloDTO> sayHelloWorld(@RequestBody @Valid HelloRequest helloRequest) {
        log.info("Log......");
        return ResponseEntity.ok(helloService.getHello(helloRequest.getName()));
    }

    @GetMapping("/api/memory/heap")
    public Map<String, Object> getHeapMemory() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();

        Map<String, Object> heapInfo = new HashMap<>();
        heapInfo.put("initMB", toMB(heapUsage.getInit()));
        heapInfo.put("usedMB", toMB(heapUsage.getUsed()));
        heapInfo.put("committedMB", toMB(heapUsage.getCommitted()));
        heapInfo.put("maxMB", toMB(heapUsage.getMax()));

        return heapInfo;
    }

    private long toMB(long bytes) {
        return bytes / BYTES_IN_MB;
    }
}
