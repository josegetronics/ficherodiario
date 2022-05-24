package com.getronics.ficherodiario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.getronics.ficherodiario.models.entity.CrucesUrgentes;
import com.getronics.ficherodiario.models.service.ICrucesUrgentesService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@SessionAttributes("crucesUrgentes")
public class SnsCrucesController {

	private Logger logAplicacion = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ICrucesUrgentesService crucesService;

	@GetMapping({ "/inicio", "/" })
	public String inicio() {
		int cruces = 0;
		CrucesUrgentes unCruce = new CrucesUrgentes();
		
		logAplicacion.info("\n");
		logAplicacion.info("Leyendo la informacion inicial");
		try {
		 cruces = crucesService.contar();	

		} catch (Exception e) {
			logAplicacion.error(e.getMessage(), e);
		}
		String a = String.valueOf(cruces);
		logAplicacion.info("El número de registros contados son: " + a);

		return "inicio";

	}

}
