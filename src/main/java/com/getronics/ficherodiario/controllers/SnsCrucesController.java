package com.getronics.ficherodiario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.getronics.ficherodiario.models.entity.CrucesUrgentes;
import com.getronics.ficherodiario.models.service.ICrucesUrgentesService;
import com.getronics.ficherodiario.util.HojaUnoExcel;

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
		
		return "inicio";
	}
	
	@GetMapping("/ejecutarHojaUno")
	public String Hoja1(RedirectAttributes flash, SessionStatus status) {
		
		List<String> tipoIdentificador = null;	
		List<String> tipoMovimiento = null;
		List<String> protegidoTipAseguramientoAndTipMovimiento = null;
		List<String> registroIpfNulo = null;
		List<String> informeAltaCruzado = null;
		List<String> codTipoAsegurado = null;
		List<String> titDobleCobertura = null;
		List<String> beneDobleCobertura = null;
		
		try {
			
		 	/*###################  
		 	  TIPO IDENTIFICACIÓN
		 	 ################### */
			
			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> TIPOS DE IDENTIFICADOR");
			tipoIdentificador = crucesService.tipoIdentificador();
		 	
		 	for(String identificadorTipo: tipoIdentificador) {
		 		logAplicacion.info(identificadorTipo);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + tipoIdentificador.size() + " registros.");
		 	
		 	/*###################  
		 	  TIPO MOVIMIENTO
		 	 ################### */
		 	
			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> TIPO MOVIMIENTO");
			tipoMovimiento = crucesService.tipoMovimiento();
		 	
		 	for(String tipoMovi: tipoMovimiento) {
		 		logAplicacion.info(tipoMovi);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + tipoMovimiento.size() + " registros.");		 	
		 	
		 	/*#########################################  
		 	  PROTEGIDOS TIP.ASEGURAMIENTO Y MOVIMIENTO
		 	 ########################################## */
		 	
			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> PROTEGIDOS TIP.ASEGURAMIENTO Y MOVIMIENTO");
			protegidoTipAseguramientoAndTipMovimiento = crucesService.protegidoTipAseguramientoAndTipMovimiento();
		 	
		 	for(String protegidoTip: protegidoTipAseguramientoAndTipMovimiento) {
		 		logAplicacion.info(protegidoTip);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + protegidoTipAseguramientoAndTipMovimiento.size() + " registros.");		
		 	
		 	/*###################  
		 	  REGISTROS IPF NULO
		 	 ################### */
		 	
			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> REGISTROS IPF NULO");
			registroIpfNulo = crucesService.registrosIpfNulo();
		 	
		 	for(String registroIp: registroIpfNulo) {
		 		logAplicacion.info(registroIp);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + registroIpfNulo.size() + " registros.");	
		 	
		 	
		 	/*###################  
		 	  INFORME ALTA CRUZADO
		 	 ################### */
		 	
			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> INFORME ALTA CRUZADO");
			informeAltaCruzado = crucesService.informeAltCruzadoConSegSocialFinal();
		 	
		 	for(String informeAlt: informeAltaCruzado) {
		 		logAplicacion.info(informeAlt);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + informeAltaCruzado.size() + " registros.");			 	
		 	
		 	/*###################  
		 	  COD_TIPOASEGURADO
		 	 ################### */
		 	
			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> COD_TIPO_ASEGURADO");
			codTipoAsegurado = crucesService.codTipoAsegurado();
		 	
		 	for(String codTipo: codTipoAsegurado) {
		 		logAplicacion.info(codTipo);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + codTipoAsegurado.size() + " registros.");	
		 	
		 	/*###################  
		 	  TITULARES DOBLE COBERTURA
		 	 ################### */
		 
			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> TITULARES DOBLE COBERTURA");
			titDobleCobertura = crucesService.titularseDobleCobertura();
		 	
		 	for(String titDoble: titDobleCobertura) {
		 		logAplicacion.info(titDoble);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + titDobleCobertura.size() + " registros.");
		 	
		 	/*###################  
		 	  BENEFICIARIOS DOBLE COBERTURA
		 	 ################### */
		 	
			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> BENEFICIARIOS DOBLE COBERTURA");
			beneDobleCobertura = crucesService.beneficiarioDobleCobertura();
		 	
		 	for(String beneDoble: beneDobleCobertura) {
		 		logAplicacion.info(beneDoble);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + beneDobleCobertura.size() + " registros.");
		 	
		 	// Se pasa la informacíon a la clase que crea los ficheros excel.
		 	HojaUnoExcel excel = new HojaUnoExcel();
		 	excel.crearHojaExcel(tipoIdentificador, tipoMovimiento, protegidoTipAseguramientoAndTipMovimiento, registroIpfNulo,
		 	informeAltaCruzado, codTipoAsegurado, titDobleCobertura, beneDobleCobertura);
		 	
		 	
		 	
		 	String mensajeFlash = "Se ha terminado la ejecucíon correctamente";
		 	status.setComplete();
		 	flash.addFlashAttribute("success", mensajeFlash);	 	
		 	
		} catch (Exception e) {
			logAplicacion.error(e.getMessage(), e);
		}
		
		return "inicio";
	}
	
	@GetMapping("/ejecutarHojaDos")
	public String hoja2(RedirectAttributes flash, SessionStatus status, Model model) {
		
		List<String> tipoIdentificadorMutualistas = null;	
		List<String> tipoMovimientoMutualista = null;
		List<String> protegidoTipAseguramientoAndTipMovimientoMutualistas = null;
		List<String> registrosIpfNuloMutualistas = null;
		List<String> informeAltCruzadoConSegSocialFinalMutualistas = null;
		List<String> codTipoAseguradoMutualistas = null;

		try{
		
			/*###############################  
		 	  TIPO IDENTIFICACIÓN MUTUALISTAS
		 	 ############################### */
			
			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> TIPOS DE IDENTIFICADOR MUTUALISTAS");
			tipoIdentificadorMutualistas = crucesService.tipoIdentificadorMutualistas();
		 	
		 	for(String identificadorTipoMutu: tipoIdentificadorMutualistas) {
		 		logAplicacion.info(identificadorTipoMutu);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + tipoIdentificadorMutualistas.size() + " registros.");
			
			/*###############################  
		 	  TIPO MOVIMIENTO MUTUALISTAS
		 	 ############################### */
			
			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> TIPO MOVIMIENTO MUTUALISTAS");
			tipoMovimientoMutualista = crucesService.tipoMovimientoMutualistas();
		 	
		 	for(String tipMoviMutualista: tipoMovimientoMutualista) {
		 		logAplicacion.info(tipMoviMutualista);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + tipoMovimientoMutualista.size() + " registros.");
		 	
			/*##################################################### 
		 	  PROTEGIDOS TIP.ASEGURAMIENTO Y MOVIMIENTO MUTUALISTAS
		 	 ###################################################### */
			
			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> TIPO MOVIMIENTO MUTUALISTAS");
			protegidoTipAseguramientoAndTipMovimientoMutualistas = crucesService.protegidoTipAseguramientoAndTipMovimientoMutualistas();
		 	
		 	for(String proTipoMutualistas: protegidoTipAseguramientoAndTipMovimientoMutualistas) {
		 		logAplicacion.info(proTipoMutualistas);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + protegidoTipAseguramientoAndTipMovimientoMutualistas.size() + " registros.");
		 	
		 	
			/*############################## 
		 	  REGISTROS IPF NULO MUTUALISTAS
		 	 ############################### */
			
			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> REGISTROS IPF NULO MUTUALISTAS");
			registrosIpfNuloMutualistas = crucesService.registrosIpfNuloMutualistas();
		 	
		 	for(String regisIpfMutualistas: registrosIpfNuloMutualistas) {
		 		logAplicacion.info(regisIpfMutualistas);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + registrosIpfNuloMutualistas.size() + " registros.");
		 	

			/*################################ 
		 	  INFORME ALTA CRUZADO MUTUALISTAS
		 	 ################################# */
			
			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> REGISTROS IPF NULO MUTUALISTAS");
			informeAltCruzadoConSegSocialFinalMutualistas = crucesService.informeAltCruzadoConSegSocialFinalMutualistas();
		 	
		 	for(String infAltSegMutualistas: informeAltCruzadoConSegSocialFinalMutualistas) {
		 		logAplicacion.info(infAltSegMutualistas);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + informeAltCruzadoConSegSocialFinalMutualistas.size() + " registros.");
		 	
			/*############################# 
		 	  COD_TIPOASEGURADO MUTUALISTAS
		 	 ############################## */
			
			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> REGISTROS IPF NULO MUTUALISTAS");
			codTipoAseguradoMutualistas = crucesService.codTipoAseguradoMutualistas();
		 	
		 	for(String codTipAseMutualistas: codTipoAseguradoMutualistas) {
		 		logAplicacion.info(codTipAseMutualistas);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + codTipoAseguradoMutualistas.size() + " registros.");			 		 
		 	
		}catch (Exception e) {
			logAplicacion.error(e.getMessage(), e);
		}
		
		return "inicio";
		
	}
	

}
