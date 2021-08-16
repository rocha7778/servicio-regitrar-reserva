package com.rocha.app.reservas.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import com.rocha.app.reservas.exceptions.BadRequestException;

public class ManageMentDate {
	
	public static Date getDateFromString(String dateSting) throws BadRequestException {
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		
		validarFecha(dateSting);
		
		try {
			date = format.parse(dateSting);
		} catch (ParseException e) {
			throw new BadRequestException("Error en el formato de fecha");
		}
		
		return date;
	}
	
	
	private static  void validarFecha(String fecha) throws BadRequestException {
        try {
        	LocalDate.parse(fecha);
        } catch (Exception e) {
        	throw new BadRequestException("Error en el formato de fecha");
        }
     
    }

}
