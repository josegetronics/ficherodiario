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
		
		/* VARIABLES MUTUALISTAS*/
		
		List<String> tipoIdentificadorMutualistas = null;	
		List<String> tipoMovimientoMutualista = null;
		List<String> protegidoTipAseguramientoAndTipMovimientoMutualistas = null;
		List<String> registrosIpfNuloMutualistas = null;
		List<String> informeAltCruzadoConSegSocialFinalMutualistas = null;
		List<String> codTipoAseguradoMutualistas = null;
		
		/* VARIABLES MOTIVO BAJA */
		List<String> regimenGeneral = null;
		List<String> bajaTitulares = null;
		List<String> totalEnvioInssAv = null;
		List<String> bajasVinculadosSns = null;		
		List<String> mutualistasPrivados = null;
		
		/* VARIABLE BAJA POR DENFUNCIÓN*/
		List<String> bajaPorDefuncion = null;
		
		/* VARIABLES ASEGURAMIENTO */
		
		List<String> aseguramiento = null;
		List<String> indicadorFarmacia= null;
		
		/* VARIABLE CODIGO BADAS REPETIDO*/
		List<String> codigoBadasRepetido = null;
		
		
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
		 			 	
		 	
			/*############### 
		 	  REGIMEN_GENERAL
		 	 ################ */
		 	
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> REGIMEN GENERAL");
			regimenGeneral = crucesService.regimenGeneral(); 
		 	
		 	for(String regGen: regimenGeneral) {
		 		logAplicacion.info(regGen);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + regimenGeneral.size() + " registros.");	
		 	
		 	
			/*############### 
		 	  BAJAS TITULARES
		 	 ################ */
		 	
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> BAJAS TITULARES");
			bajaTitulares = crucesService.bajaTitulares(); 
		 	
		 	for(String bajaTitu: bajaTitulares) {
		 		logAplicacion.info(bajaTitu);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + bajaTitulares.size() + " registros.");	
		 	

			/*#################### 
		 	  TOTAL ENVIO INSS AV
		 	 ##################### */
		 	
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> TOTAL ENVIO INSS AV");
			totalEnvioInssAv = crucesService.totalEnvioInssAv();
		 	
		 	for(String totalEnvi: totalEnvioInssAv) {
		 		logAplicacion.info(totalEnvi);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + totalEnvioInssAv.size() + " registros.");	
		 	
			/*#################### 
		 	  BAJAS VINCULADOS SNS
		 	 ##################### */
		 	
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> BAJAS VINCULADOS SNS");
			bajasVinculadosSns = crucesService.bajasVinculadosSns();
		 	
		 	for(String vincuSns: bajasVinculadosSns) {
		 		logAplicacion.info(vincuSns);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + bajasVinculadosSns.size() + " registros.");
		 	
		 	
			/*#################### 
		 	  MUTUALISTA PRIVADOS
		 	 ##################### */
		 	
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> MUTUALISTAS PRIVADOS");
			mutualistasPrivados = crucesService.mutualistasPrivados();
		 	
		 	for(String mutuPrivados: mutualistasPrivados) {
		 		logAplicacion.info(mutuPrivados);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + mutualistasPrivados.size() + " registros.");
			
			/*#################### 
		 	  BAJAS POR DEFUNCION
		 	 ##################### */
		 	
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> BAJAS TITULARES");
			bajaPorDefuncion = crucesService.bajaPorDefuncion();
		 	
		 	for(String bajDefuncion: bajaPorDefuncion) {
		 		logAplicacion.info(bajDefuncion);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + bajaPorDefuncion.size() + " registros.");
		 	
			/*############# 
		 	  ASEGURAMIENTO
		 	 ############## */
		 	
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> ASEGURAMIENTO");
			aseguramiento = crucesService.aseguramiento();
		 	
		 	for(String asegura: aseguramiento) {
		 		logAplicacion.info(asegura);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + aseguramiento.size() + " registros.");
		 	
			/*################## 
		 	  INDICADOR FARMACIA
		 	 ################### */
		 	
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> INDICADOR FARMACIA");
			indicadorFarmacia = crucesService.indicadorFarmacia();
		 	
		 	for(String indicaFarm: indicadorFarmacia) {
		 		logAplicacion.info(indicaFarm);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + indicadorFarmacia.size() + " registros.");
			
			/*##################### 
		 	  CODIGO BADAS REPETIDO
		 	 ###################### */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> CÓDIGO BADAS REPETIDO");
			codigoBadasRepetido = crucesService.codigoBadasRepetido();
		 	
		 	for(String codigoBadas: codigoBadasRepetido) {
		 		logAplicacion.info(codigoBadas);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + codigoBadasRepetido.size() + " registros.");
	
		 	
		 	
		 	// Se pasa la informacíon a la clase que crea los ficheros excel.
		 	HojaUnoExcel excel = new HojaUnoExcel();
		 	excel.crearHojaExcel(tipoIdentificador, tipoMovimiento, protegidoTipAseguramientoAndTipMovimiento, registroIpfNulo,
		 	informeAltaCruzado, codTipoAsegurado, titDobleCobertura, beneDobleCobertura, tipoIdentificadorMutualistas,tipoMovimientoMutualista,
		 	protegidoTipAseguramientoAndTipMovimientoMutualistas, registrosIpfNuloMutualistas,informeAltCruzadoConSegSocialFinalMutualistas,  
		 	codTipoAseguradoMutualistas, regimenGeneral, bajaTitulares, totalEnvioInssAv, bajasVinculadosSns, mutualistasPrivados, bajaPorDefuncion, 
		 	aseguramiento, indicadorFarmacia, codigoBadasRepetido);
		 	
		 	
	 	
		 	
		} catch (Exception e) {
			logAplicacion.error(e.getMessage(), e);
		}
		
		return "inicio";
	}
	
	@GetMapping("/ejecutarHojaDos")
	public String hoja2(RedirectAttributes flash, SessionStatus status, Model model) {		

		try{
			logAplicacion.info("LÓGICA DE NEGOCIO NO IMPLEMENTADA.");	 		 
		 	
		}catch (Exception e) {
			logAplicacion.error(e.getMessage(), e);
		}
		
		return "inicio";
		
	}
	

}
