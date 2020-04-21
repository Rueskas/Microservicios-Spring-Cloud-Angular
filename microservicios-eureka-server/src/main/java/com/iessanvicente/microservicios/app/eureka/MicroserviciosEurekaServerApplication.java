package com.iessanvicente.microservicios.app.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MicroserviciosEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosEurekaServerApplication.class, args);
	}

}
