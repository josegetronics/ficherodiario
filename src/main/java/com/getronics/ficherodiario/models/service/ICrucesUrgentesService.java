package com.getronics.ficherodiario.models.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.getronics.ficherodiario.models.entity.CrucesUrgentes;

public interface ICrucesUrgentesService {

	/* ################################### 
	  Hoja 1 - "Resumen - Regimen General"
	  #################################### */ 	
	
	List<String> tipoIdentificador();
	
	List<String> tipoMovimiento();
	
	List<String> protegidoTipAseguramientoAndTipMovimiento();
	
	List<String> registrosIpfNulo();
	
	List<String> informeAltCruzadoConSegSocialFinal();
	
	List<String> codTipoAsegurado();
	
	List<String> titularseDobleCobertura();
	
	List<String> beneficiarioDobleCobertura();
	
	/* ############################## 
	  Hoja 2 - "Resumen - Mutualistas"
	  ############################## */ 
	
	List<String> tipoIdentificadorMutualistas();
	
	List<String> tipoMovimientoMutualistas();
	
	List<String> protegidoTipAseguramientoAndTipMovimientoMutualistas();
	
	List<String> registrosIpfNuloMutualistas();
	
	List<String> informeAltCruzadoConSegSocialFinalMutualistas();
	
	List<String> codTipoAseguradoMutualistas();
	
	/* ##################### 
	  Hoja 3 - "Motivo Baja" 
	  ###################### */ 
	
	List<String> regimenGeneral(); 
	
	List<String> bajaTitulares();
	
	List<String> totalEnvioInssAv();
	
	List<String> bajasVinculadosSns();
	
	List<String> mutualistasPrivados();
	
	/* ############################ 
	  Hoja 4 - "Baja por Defunción"
	  ############################# */ 
	
	List<String> bajaPorDefuncion();
	
	/* ####################### 
	  Hoja 5 - "Aseguramiento" 
	  ######################## */ 
	
	List<String> aseguramiento();
	
	List<String> indicadorFarmacia();
	
	/* ############################### 
	  Hoja 6 - "Código Badas Repetido"  
	  ################################ */ 
	
	List<String> codigoBadasRepetido();

	/* ############################### 
	  Hoja 7 - "Altas sin NAF"  
	  ################################ */ 
	
	List<String> altasNaf();
	

	/* ####################### 
	   Hoja 8 - "Cambios IPF"  
	  ######################## */
	
	/* 8.1 REGIMEN GENERAL */
	
	List<String> ipfAnteriorNoExisteInssTit();
	
	List<String> ipfNuevoExisteInssTit();
	
	/* 8.2 MUTUALISTAS */	
	
	List<String> ipfAnteriorNoExisteInssMut();
	
	List<String> ipfNuevoExiteInssMut();
	
	
	/* ####################### 
	   Hoja 9 - "MUTUALISTAS"  
	  ######################## */

	/* 9.1 - DOBLE_COBERTURA */
	
	List<String> titularesDobleCobertura();
	
	List<String> beneficiarioDobleCoberturaMutualistas();
	
	/* 9.2 - INDICADOR_CONVENIO_RURAL */
	
	List<String> titularesIndicadorConvenioRural();
	
	List<String> beneficiadioIndicadorConvenioRural();

	
	/* 9.3.1.1 REGULAR_PRIVADO -> REGULAR_PUBLICO */
	
	List<String> privadoRegularPublico();
	
	/* 9.3.1.2 REGULAR_PRIVADO -> IRREGULAR_PUBLICO */
	
	List<String> privadoIrregularPublico();

	/* 9.3.1.3 REGULAR_PRIVADO -> NO_EXISTE */	
	
	List<String> privadoNoExiste();
	
	/* 9.3.1.4 Alta y Modificación de Titulares con DOBLE_COBERTURA '1' */
	
	List<String> altaModificacionTitularesDobleCobertura();
	
	/* 9.3.2.1 IRREGULAR_PRIVADO -> REGULAR_PUBLICO */
	
	List<String> irregularPrivadoRegularPublico();
	
	/* 9.3.2.2  IRREGULAR_PRIVADO -> IRREGULAR_PUBLICO */
	
	List<String> irregularPrivadoIrregularPublico();
	
	/* 9.3.2.3 IRREGULAR_PRIVADO -> NO_EXISTE */
	
	List<String> irregularPrivadoNoExiste();
	
	/* 9.3.2.4 Alta y Modificación de Titulares con DOBLE_COBERTURA '2' */
	
	List<String> altaModificaTitualresDobleCoberturaDos();
	
	/* 9.3.3 Listado de registros del tipo "No existe" DE ML A OD */
	
	List<String> listadoRegistroNoExiste();
	
	List<String> listadoRegistroNoExisteDos();
	
	/* 9.4.1 PRIVADO -> PUBLICO */
	
	List<String> bajaPorDefuncionPrivadoPublico();
	
	/* 9.4.2 PRIVADO -> NO EXISTE */
	
	List<String> bajaPorDefuncionPrivadoNoExiste();
	
	/* ################ 
	  Excel datos3.xlsx  
	  ################# */ 
		
	/* Baja Titulares Motivo Baja 06 datos3.1 */
	
	List<String> titularesMotiboBaja();
	
	/* ################ 
	  Excel datos2.xlsx  
	  ################# */ 
	
	/* Baja Titulares Motivo Baja 03 */
	
	List<String> titularesMotivoBaja03();
	
}
