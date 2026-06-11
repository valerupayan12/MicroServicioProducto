package com.example.MicroProducto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class MicroProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroProductoApplication.class, args);
	}

}

