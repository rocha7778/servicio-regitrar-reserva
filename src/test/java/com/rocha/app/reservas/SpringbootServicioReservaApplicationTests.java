package com.rocha.app.reservas;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rocha.app.commons.model.Reserva;

@SpringBootTest
class SpringbootServicioReservaApplicationTests {
	
	private static final String BASE_URL_API = "/reservas";
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}


	@Test
	void contextLoads() {
	}
	
	@Test
	public void sholuld_create_expect_201_http() throws Exception {
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
		Date fechaIngreso = dateFormat.parse("2021-12-01");
        Date fechaSalida = dateFormat.parse("2021-12-31");
		
		Reserva reserva = new Reserva();
		reserva.setFechaIngreso(fechaIngreso);
		reserva.setFechaSalida(fechaSalida);
		reserva.setNoPersonas(2);
		reserva.setTitularReserva("Rocha");
		reserva.setNoHabitaciones(2);
		reserva.setEmail("rocha7778a@hotmail.com");
		
		String json = ow.writeValueAsString(reserva);
		System.out.println(json);
		
		this.mockMvc
		.perform(post(BASE_URL_API).contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().is(HttpStatus.CREATED.value()));
	
		
		
	}
	
	@Test
	public void sholuld_not_create_expect_400_http() throws Exception {
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Reserva reserva = new Reserva();
		reserva.setNoPersonas(2);
		reserva.setTitularReserva("Rocha");
		reserva.setNoHabitaciones(2);
		reserva.setEmail("rocha7778a@hotmail.com");
		
		String json = ow.writeValueAsString(reserva);
		System.out.println(json);
		
		this.mockMvc
		.perform(post(BASE_URL_API).contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}
	
	@Test
	public void sholuld_not_create_wrong_fisrt_date_expect_400_http() throws Exception {
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
		Date fechaIngreso = dateFormat.parse("2022-12-01");
        Date fechaSalida = dateFormat.parse("2021-12-31");
		
		Reserva reserva = new Reserva();
		reserva.setFechaIngreso(fechaIngreso);
		reserva.setFechaSalida(fechaSalida);
		
		reserva.setNoPersonas(2);
		reserva.setTitularReserva("Rocha");
		reserva.setNoHabitaciones(2);
		reserva.setEmail("rocha7778a@hotmail.com");
		
		String json = ow.writeValueAsString(reserva);
		System.out.println(json);
		
		this.mockMvc
		.perform(post(BASE_URL_API).contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}
		
		
	
	
	
	
	

}
