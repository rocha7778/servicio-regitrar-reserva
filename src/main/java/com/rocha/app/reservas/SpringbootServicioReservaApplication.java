package com.rocha.app.reservas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.rocha.app.commons.model"})
public class SpringbootServicioReservaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioReservaApplication.class, args);
	}

}
