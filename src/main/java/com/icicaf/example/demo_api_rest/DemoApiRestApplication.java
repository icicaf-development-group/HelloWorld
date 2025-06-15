package com.icicaf.example.demo_api_rest;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApiRestApplication.class, args);
	}

	@PostConstruct
	public void logPid() {
		long pid = ProcessHandle.current().pid();
		System.out.println("🚀 Spring Boot app running. PID = " + pid);
	}

	@PostConstruct
	public void logMemory() {
		long total = Runtime.getRuntime().totalMemory(); // Heap reservado
		long free = Runtime.getRuntime().freeMemory();   // Heap libre dentro de lo reservado
		long max = Runtime.getRuntime().maxMemory();     // Heap máximo posible

		long used = total - free;

		System.out.println("🚀 === MEMORY REPORT ===");
		System.out.println("🔹 Heap usado     : " + (used / (1024 * 1024)) + " MB");
		System.out.println("🔹 Heap reservado : " + (total / (1024 * 1024)) + " MB");
		System.out.println("🔹 Heap máximo    : " + (max / (1024 * 1024)) + " MB");
		System.out.println("⚠️  Si estás cerca del límite, considera subir -Xmx o optimizar.");
	}

}
