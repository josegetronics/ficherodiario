package com.getronics.ficherodiario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.getronics.ficherodiario.models.entity.CrucesUrgentes;
import com.getronics.ficherodiario.models.service.ICrucesUrgentesService;
import com.getronics.ficherodiario.util.HojaDatosTresExcel;
import com.getronics.ficherodiario.util.HojaUnoExcel;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@SessionAttributes("crucesUrgentes")
public class SnsCrucesController {

	private Logger logAplicacion = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ICrucesUrgentesService crucesService;
	
	@Autowired
	private MessageSource messageSource;
	

	@GetMapping({ "/inicio", "/" })
	public String inicio() {
		
		return "inicio";
	}
	
	@GetMapping("/ejecucionFicheros")
	public String ejecucionFicheros() {
		
		return "ejecucionFicheros";
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
		
		/* VARIABLE BAJA POR DENFUNCIÓN */
		List<String> bajaPorDefuncion = null;
		
		/* VARIABLES ASEGURAMIENTO */
		
		List<String> aseguramiento = null;
		List<String> indicadorFarmacia= null;
		
		/* VARIABLE CODIGO BADAS REPETIDO */
		List<String> codigoBadasRepetido = null;
		
		/* VARIABLE ALTA SIN NAF */
		List<String> altaSinNaf = null;
		
		/* VARIABLES CAMBIOS IPF */
		List<String> cambiosIpf = null;
		
		/* VARIABLES CAMBIOS IPF */
		List<String> ipfAnteriorNoExisteInssTit = null;
		List<String> ipfNuevoExisteInssTit = null;
		List<String> ipfAnteriorNoExisteInssMut = null;
		List<String> ipfNuevoExiteInssMut  = null;
		
		
		/* VARIABLE DOBLE COBERTURA*/		
		List<String> titularesDobleCobertura = null;		
		List<String> beneficiarioDobleCoberturaMutualistas = null;
		List<String> titularesIndicadorConvenioRural = null;		
		List<String> beneficiadioIndicadorConvenioRural = null;
		List<String> privadoRegularPublico= null;
		List<String> privadoIrregularPublico = null;			
		List<String> privadoNoExiste = null;		
		List<String> altaModificacionTitularesDobleCobertura = null;		
		List<String> irregularPrivadoRegularPublico = null;
		List<String> irregularPrivadoIrregularPublico = null;		
		List<String> irregularPrivadoNoExiste = null;
		List<String> altaModificaTitualresDobleCoberturaDos = null;
		List<String> listadoRegistroNoExiste = null;
		List<String> listadoRegistroNoExisteDos = null;
		List<String> bajaPorDefuncionPrivadoPublico = null;
		List<String> bajaPorDefuncionPrivadoNoExiste = null;
		
		
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
		 	
		 	/*#########################  
		 	  TITULARES DOBLE COBERTURA
		 	 ########################## */
		 
			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> TITULARES DOBLE COBERTURA");
			titDobleCobertura = crucesService.titularseDobleCobertura();
		 	
		 	for(String titDoble: titDobleCobertura) {
		 		logAplicacion.info(titDoble);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + titDobleCobertura.size() + " registros.");
		 	
		 	/*#############################  
		 	  BENEFICIARIOS DOBLE COBERTURA
		 	 ############################## */
		 	
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
			logAplicacion.info("Ejecutando query -> PROTEGIDOS TIP.ASEGURAMIENTO Y MOVIMIENTO MUTUALISTAS");
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
			logAplicacion.info("Ejecutando query -> INFORME ALTA CRUZADO MUTUALISTAS");
			informeAltCruzadoConSegSocialFinalMutualistas = crucesService.informeAltCruzadoConSegSocialFinalMutualistas();
		 	
		 	for(String infAltSegMutualistas: informeAltCruzadoConSegSocialFinalMutualistas) {
		 		logAplicacion.info(infAltSegMutualistas);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + informeAltCruzadoConSegSocialFinalMutualistas.size() + " registros.");
		 	
			/*############################# 
		 	  COD_TIPOASEGURADO MUTUALISTAS
		 	 ############################## */
			
			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> COD_TIPOASEGURADO MUTUALISTAS");
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
	
		 	
			/*############# 
		 	  ALTAS SIN NAF
		 	 ############## */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> ALTAS SIN NAF");
			altaSinNaf = crucesService.altasNaf();
		 	
		 	for(String altaSNaf: altaSinNaf) {
		 		logAplicacion.info(altaSNaf);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + altaSinNaf.size() + " registros.");
		 	
		 	
			/*################################## 
		 	  IPF Anterior no existe en INSS_TIT
		 	 ################################### */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> IPF Anterior no existe en INSS_TIT");
			cambiosIpf = crucesService.ipfAnteriorNoExisteInssTit();
		 	
		 	for(String caIpf: cambiosIpf) {
		 		logAplicacion.info(caIpf);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + cambiosIpf.size() + " registros.");
		 	
		 	
			/*############################ 
		 	  IPF Nuevo existe en INSS_TIT
		 	 ############################# */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query ->  IPF Nuevo existe en INSS_TIT");
			ipfNuevoExisteInssTit = crucesService.ipfNuevoExisteInssTit();
		 	
		 	for(String ipfN: ipfNuevoExisteInssTit) {
		 		logAplicacion.info(ipfN);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + ipfNuevoExisteInssTit.size() + " registros.");
		 	
		 	
			/*###################################### 
		 	  IPF Anterior no existe en INSS_MUT_TIT
		 	 ####################################### */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> IPF Anterior no existe en INSS_MUT_TIT");
			ipfAnteriorNoExisteInssMut = crucesService.ipfAnteriorNoExisteInssMut();
		 	
		 	for(String ipfAnt: ipfAnteriorNoExisteInssMut) {
		 		logAplicacion.info(ipfAnt);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + ipfAnteriorNoExisteInssMut.size() + " registros.");
		 	
			/*################################ 
		 	  IPF Nuevo existe en INSS_MUT_TIT
		 	 ################################# */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> IPF Nuevo existe en INSS_MUT_TIT");
			ipfNuevoExiteInssMut = crucesService.ipfNuevoExiteInssMut();
		 	
		 	for(String ipfNue: ipfNuevoExiteInssMut) {
		 		logAplicacion.info(ipfNue);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + ipfNuevoExiteInssMut.size() + " registros.");
		 	
		 	
			/*###################################### 
		 	  Titulares DOBLE_COBERTURA EMPEZA EN LV
		 	 ####################################### */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> Titulares DOBLE_COBERTURA EMPEZA EN LV");
			titularesDobleCobertura = crucesService.titularesDobleCobertura();
		 	
		 	for(String lV: titularesDobleCobertura) {
		 		logAplicacion.info(lV);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + titularesDobleCobertura.size() + " registros.");
		 			 			 
		 	
			/*############################# 
		 	  Beneficiarios DOBLE_COBERTURA
		 	 ############################## */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> Beneficiarios DOBLE_COBERTURA");
			beneficiarioDobleCoberturaMutualistas = crucesService.beneficiarioDobleCoberturaMutualistas();
		 	
		 	for(String bdm: beneficiarioDobleCoberturaMutualistas) {
		 		logAplicacion.info(bdm);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + beneficiarioDobleCoberturaMutualistas.size() + " registros.");

		 	
			/*################################## 
		 	  Titulares INDICADOR_CONVENIO_RURAL
		 	 ################################### */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> Titulares INDICADOR_CONVENIO_RURAL");
			titularesIndicadorConvenioRural = crucesService.titularesIndicadorConvenioRural();
		 	
		 	for(String tIndRural: titularesIndicadorConvenioRural) {
		 		logAplicacion.info(tIndRural);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + titularesIndicadorConvenioRural.size() + " registros.");


		 	
			/*###################################### 
		 	  Beneficiarios INDICADOR_CONVENIO_RURAL
		 	 ####################################### */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> Beneficiarios INDICADOR_CONVENIO_RURAL");
			beneficiadioIndicadorConvenioRural = crucesService.beneficiadioIndicadorConvenioRural();
		 	
		 	for(String bICR: beneficiadioIndicadorConvenioRural) {
		 		logAplicacion.info(bICR);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + beneficiadioIndicadorConvenioRural.size() + " registros.");
		 	
		 			 	
			/*################################## 
		 	  REGULAR_PRIVADO -> REGULAR_PUBLICO
		 	 ################################### */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> REGULAR_PRIVADO -> REGULAR_PUBLICO");
			privadoRegularPublico = crucesService.privadoRegularPublico();
		 	
		 	for(String pRp: privadoRegularPublico) {
		 		logAplicacion.info(pRp);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + privadoRegularPublico.size() + " registros.");
		 	
		 			 	
			/*#################################### 
		 	  REGULAR_PRIVADO -> IRREGULAR_PUBLICO
		 	 ##################################### */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> REGULAR_PRIVADO -> IRREGULAR_PUBLICO");
			privadoIrregularPublico = crucesService.privadoIrregularPublico();
		 	
		 	for(String pIp: privadoIrregularPublico) {
		 		logAplicacion.info(pIp);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + privadoIrregularPublico.size() + " registros.");	
		 	
		 	
			/*############################ 
		 	  REGULAR_PRIVADO -> NO_EXISTE
		 	 #############################*/
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> REGULAR_PRIVADO -> NO_EXISTE");
			privadoNoExiste = crucesService.privadoNoExiste();
		 	
		 	for(String pNe: privadoNoExiste) {
		 		logAplicacion.info(pNe);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + privadoNoExiste.size() + " registros.");	
		 	
		 	
			/*######################################################## 
		 	  Alta y Modificación de Titulares con DOBLE_COBERTURA '1'
		 	 ######################################################### */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> Alta y Modificación de Titulares con DOBLE_COBERTURA '1'");
			altaModificacionTitularesDobleCobertura = crucesService.altaModificacionTitularesDobleCobertura();
		 	
		 	for(String altaModTitu: altaModificacionTitularesDobleCobertura) {
		 		logAplicacion.info(altaModTitu);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + altaModificacionTitularesDobleCobertura.size() + " registros.");	
		 	
		 	
			/*#################################### 
		 	  IRREGULAR_PRIVADO -> REGULAR_PUBLICO
		 	 ##################################### */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> IRREGULAR_PRIVADO -> REGULAR_PUBLICO");
			irregularPrivadoRegularPublico = crucesService.irregularPrivadoRegularPublico();
		 	
		 	for(String irreguPriva: irregularPrivadoRegularPublico) {
		 		logAplicacion.info(irreguPriva);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + irregularPrivadoRegularPublico.size() + " registros.");	
		 			 	
		 	
			/*###################################### 
		 	  IRREGULAR_PRIVADO -> IRREGULAR_PUBLICO
		 	 ####################################### */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> IRREGULAR_PRIVADO -> IRREGULAR_PUBLICO");
			irregularPrivadoIrregularPublico = crucesService.irregularPrivadoIrregularPublico();
		 	
		 	for(String irreguPrivaIrre: irregularPrivadoIrregularPublico) {
		 		logAplicacion.info(irreguPrivaIrre);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + irregularPrivadoIrregularPublico.size() + " registros.");	
		 			 
		 	
			/*############################## 
		 	  IRREGULAR_PRIVADO -> NO_EXISTE
		 	 ############################### */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> IRREGULAR_PRIVADO -> NO_EXISTE");
			irregularPrivadoNoExiste = crucesService.irregularPrivadoNoExiste();
		 	
		 	for(String irreguPrivaNo: irregularPrivadoNoExiste) {
		 		logAplicacion.info(irreguPrivaNo);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + irregularPrivadoNoExiste.size() + " registros.");
		 			 	
		 	
			/*######################################################## 
		 	  Alta y Modificación de Titulares con DOBLE_COBERTURA '2'
		 	 ######################################################### */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> Alta y Modificación de Titulares con DOBLE_COBERTURA '2'");
			altaModificaTitualresDobleCoberturaDos = crucesService.altaModificaTitualresDobleCoberturaDos();
		 	
		 	for(String altModiTitu: altaModificaTitualresDobleCoberturaDos) {
		 		logAplicacion.info(altModiTitu);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + altaModificaTitualresDobleCoberturaDos.size() + " registros.");
		 	
		 	
			/*######################################### 
		 	  Listado de registros del tipo "No existe"
		 	 ########################################## */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> Listado de registros del tipo 'No existe'");
			listadoRegistroNoExiste = crucesService.listadoRegistroNoExiste();
			listadoRegistroNoExisteDos = crucesService.listadoRegistroNoExisteDos();
		 	
		 	for(String listRegNoEx: listadoRegistroNoExiste) {
		 		logAplicacion.info(listRegNoEx);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + listadoRegistroNoExiste.size() + " registros.");
		 			 	
		 	

			/*######################################################### 
		 	  Baja por defunción con doble cobertura PRIVADO -> PUBLICO
		 	 ########################################################## */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> Baja por defunción con doble cobertura PRIVADO -> PUBLICO");
			bajaPorDefuncionPrivadoPublico = crucesService.bajaPorDefuncionPrivadoPublico();
		 	
		 	for(String bajaDefPri: bajaPorDefuncionPrivadoPublico) {
		 		logAplicacion.info(bajaDefPri);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + bajaPorDefuncionPrivadoPublico.size() + " registros.");
		 	
		 	
			/*########################################################### 
		 	  Baja por defunción con doble cobertura PRIVADO -> NO EXISTE
		 	 ############################################################ */
			
		 	logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> Baja por defunción con doble cobertura PRIVADO -> NO EXISTE");
			bajaPorDefuncionPrivadoNoExiste = crucesService.bajaPorDefuncionPrivadoNoExiste();
		 	
		 	for(String bajaDefPri: bajaPorDefuncionPrivadoNoExiste) {
		 		logAplicacion.info(bajaDefPri);		 		
		 	}
		 	logAplicacion.info("Existe un total de " + bajaPorDefuncionPrivadoNoExiste.size() + " registros.");
		 	
		 	
		 	
		 	// Se pasa la informacíon a la clase que crea los ficheros excel.
		 	HojaUnoExcel excel = new HojaUnoExcel();
		 	excel.crearHojaExcel(tipoIdentificador, tipoMovimiento, protegidoTipAseguramientoAndTipMovimiento, registroIpfNulo,
		 	informeAltaCruzado, codTipoAsegurado, titDobleCobertura, beneDobleCobertura, tipoIdentificadorMutualistas,tipoMovimientoMutualista,
		 	protegidoTipAseguramientoAndTipMovimientoMutualistas, registrosIpfNuloMutualistas,informeAltCruzadoConSegSocialFinalMutualistas,  
		 	codTipoAseguradoMutualistas, regimenGeneral, bajaTitulares, totalEnvioInssAv, bajasVinculadosSns, mutualistasPrivados, bajaPorDefuncion, 
		 	aseguramiento, indicadorFarmacia, codigoBadasRepetido,altaSinNaf,cambiosIpf,ipfNuevoExisteInssTit,ipfAnteriorNoExisteInssMut,ipfNuevoExiteInssMut,
		 	titularesDobleCobertura, beneficiarioDobleCoberturaMutualistas, titularesIndicadorConvenioRural,beneficiadioIndicadorConvenioRural, privadoRegularPublico, 
		 	privadoIrregularPublico,privadoNoExiste, altaModificacionTitularesDobleCobertura, irregularPrivadoRegularPublico, irregularPrivadoIrregularPublico,
		 	irregularPrivadoNoExiste,altaModificaTitualresDobleCoberturaDos,listadoRegistroNoExiste,listadoRegistroNoExisteDos,bajaPorDefuncionPrivadoPublico, 
		 	bajaPorDefuncionPrivadoNoExiste);
		 	
		 	flash.addFlashAttribute("success", "Se ha ejecutado el fichero diario correctamente");
		 	
		} catch (Exception e) {
			logAplicacion.error(e.getMessage(), e);
		}
		
		return "ejecucionFicheros";
	}
 
	@GetMapping("/ejecutarHojaDatosUno")
	public String hojaUno( Model model) {

		try {
			List<String> datosUno = null;

			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query -> DATOS 1");
			datosUno = crucesService.datosUno();

			for (String dato : datosUno) {
				logAplicacion.info(dato);
			}
			logAplicacion.info("Existe un total de " + datosUno.size() + " registros.");

			model.addAttribute("crucesUrgentes", datosUno);
			
		} catch (Exception e) {
			logAplicacion.error(e.getMessage(), e);
		}

		return "hojaUno";

	}

	@GetMapping("/ejecutarHojaDos")
	public String hojaDos(RedirectAttributes flash, SessionStatus status, Model model, Locale locale,
			 Map<String, Object> mensajeAlerta) {

		List<String> ejecucionDatosDos;

		try {
			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query para el excel Datos Dos-> Baja Titulares Motivo Baja 03");
			ejecucionDatosDos = crucesService.titularesMotivoBaja03();

			if (ejecucionDatosDos.isEmpty()) {
				// Pasamos el mensaje a la vista.
				model.addAttribute("info",
						"La consulta viene vacía, no se procederá a generar ningun fichero 'xlsx' de salida");
				
				logAplicacion
						.info("La consulta viene vacía, no se procederá a generar ningun fichero 'xlsx' de salida");
				return "ejecucionFicheros";

			} else {

				for (String a : ejecucionDatosDos) {
					logAplicacion.info(a);
				}
				
				logAplicacion.info("La consulta viene con datos, se debe enviar el corre avisando de ello");
				model.addAttribute("crucesUrgentes", ejecucionDatosDos);
				
				/* Mensaje de alerta, consulta lanzada contiene datos.(Otra forma de pasar el mensaje a la vista) 
				 Ahora mismo cuando se ejecuta el Servlet no salta el mensaje avisando de que hay datos, directamente descarga el xlsx.
				 */
				mensajeAlerta.put("alertaFicheroDos",
						"La consulta viene con datos, se debe enviar el corre avisando de ello");
			}

			logAplicacion.info("Existe un total de " + ejecucionDatosDos.size() + " registros.");

		} catch (Exception e) {
			logAplicacion.info(e.getMessage(), e);
		}
		
		return "hojaDos";
	}
	
	
	@GetMapping("/ejecutarHojaTres")
	public String hojaTres(RedirectAttributes flash, SessionStatus status, Model model, Locale locale) {

		List<String> ejecucionDatosTres;

		try {
			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query para el excel Datos Tres-> Baja Titulares Motivo Baja 06 datos");
			ejecucionDatosTres = crucesService.titularesMotiboBaja();

			for (String a : ejecucionDatosTres) {
				logAplicacion.info(a);
			}
			model.addAttribute("crucesUrgentes", ejecucionDatosTres);
			logAplicacion.info("Existe un total de " + ejecucionDatosTres.size() + " registros.");

		} catch (Exception e) {
			logAplicacion.info(e.getMessage(), e);
		}

		return "hojaTres";
	}
	

	@GetMapping("/ejecutarHojaCuatro")
	public String hojaCuatro(Model model) {

		List<String> ejecucionDatosCuatro;
		try {

			logAplicacion.info("\n");
			logAplicacion.info("Ejecutando query para el excel Datos Tres-> IPF Repetidos datos 4.1");
			ejecucionDatosCuatro = crucesService.ipfRepetidosDatos();
			
			for (String a : ejecucionDatosCuatro) {
				logAplicacion.info(a);
			}
			model.addAttribute("crucesUrgentes", ejecucionDatosCuatro);
			logAplicacion.info("Existe un total de " + ejecucionDatosCuatro.size() + " registros.");
			
		} catch (Exception e) {
			logAplicacion.info(e.getMessage(), e);
		}

		return "hojaCuatro";
	}
	
	

}
