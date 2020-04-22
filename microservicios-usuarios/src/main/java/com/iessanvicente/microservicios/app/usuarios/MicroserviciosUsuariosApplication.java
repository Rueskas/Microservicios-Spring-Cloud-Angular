package com.iessanvicente.microservicios.app.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"com.iessanvicente.microservicios.app.usuarios.clients","com.iessanvicente.microservicios.app.usuarios.controllers"})
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.iessanvicente.microservicios.commons.alumnos.models.entities"})
public class MicroserviciosUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosUsuariosApplication.class, args);
	}

}
