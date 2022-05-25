package com.getronics.ficherodiario.models.service;

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

	
	
}
