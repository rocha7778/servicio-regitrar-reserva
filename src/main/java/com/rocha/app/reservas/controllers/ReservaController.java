package com.rocha.app.reservas.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rocha.app.commons.model.Reserva;
import com.rocha.app.commons.pojo.ReservaDTO;
import com.rocha.app.reservas.exceptions.BadRequestException;
import com.rocha.app.reservas.util.ManageMentDate;

@RestController
public class ReservaController {
	
	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;
	
	
	@Value("${cloud.aws.end-point.uri}")
	private String sqsEndpoint;
	
	@PostMapping("/reservas")
	@ResponseStatus(HttpStatus.CREATED)
	public void reserva(@Valid @RequestBody ReservaDTO reserva) throws BadRequestException{
		queueMessagingTemplate.convertAndSend(sqsEndpoint,getEntity(reserva));
	}
	
	private Reserva getEntity(ReservaDTO dto) throws BadRequestException {

		Date fechaIngreso = null;
		Date fechaSalida = null;
		
		if (dto.getFechaIngreso() != null ) {
			try {
				fechaIngreso = ManageMentDate.getDateFromString(dto.getFechaIngreso());
			} catch (BadRequestException e) {
				// TODO Auto-generated catch block
				throw new BadRequestException("Error en el formato Fecha de Ingreso "+dto.getFechaIngreso());
			}
		}
		
		if (dto.getFechaSalida() != null ) {
			try {
				fechaSalida = ManageMentDate.getDateFromString(dto.getFechaSalida());
			} catch (BadRequestException e) {
				// TODO Auto-generated catch block
				throw new BadRequestException("Error en el formato Fecha de Salida "+dto.getFechaSalida());
			}
		}
		
		boolean resultado = fechaIngreso.before(fechaSalida);
		if(!resultado) {
			 throw new BadRequestException("La fecha de ingreso debe ser menor a la fecha de salida");
		}
		
		Reserva reserva = new Reserva();
		reserva.setEmail(dto.getEmail());
		reserva.setFechaIngreso(fechaIngreso);
		reserva.setFechaSalida(fechaSalida);
		reserva.setNoHabitaciones(dto.getNoHabitaciones());
		reserva.setNoMenores(dto.getNoMenores());
		reserva.setNoPersonas(dto.getNoPersonas());
		reserva.setTitularReserva(dto.getTitularReserva());
		
		return reserva;
	}

}
