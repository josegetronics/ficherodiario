package com.getronics.ficherodiario.models.service;



import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.getronics.ficherodiario.models.dao.ICrucesUrgentesDao;
import com.getronics.ficherodiario.models.entity.CrucesUrgentes;


@Service
public class CrucesUrgentesServiceImpl implements ICrucesUrgentesService{

	@Autowired
	private ICrucesUrgentesDao crucesUrgentesDao;
	
	/* ################################### 
	  Hoja 1 - "Resumen - Regimen General"
	  #################################### */ 
	
	@Override
	@Transactional(readOnly = true)
	public List<String> tipoIdentificador() {
		return crucesUrgentesDao.tipoIdentificador();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> tipoMovimiento() {
		return crucesUrgentesDao.tipoMovimiento();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> protegidoTipAseguramientoAndTipMovimiento() {
		return crucesUrgentesDao.protegidoTipAseguramientoAndTipMovimiento();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> registrosIpfNulo() {
		return crucesUrgentesDao.registrosIpfNulo();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> informeAltCruzadoConSegSocialFinal() {
		return crucesUrgentesDao.informeAltCruzadoConSegSocialFinal();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> codTipoAsegurado() {
		return crucesUrgentesDao.codTipoAsegurado();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> titularseDobleCobertura() {
		return crucesUrgentesDao.titularseDobleCobertura();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> beneficiarioDobleCobertura() {
		return crucesUrgentesDao.beneficiarioDobleCobertura();
	}

	/* ############################## 
	  Hoja 2 - "Resumen - Mutualistas"
	  ############################## */ 
	
	@Override
	@Transactional(readOnly = true)
	public List<String> tipoIdentificadorMutualistas() {
		return crucesUrgentesDao.tipoIdentificadorMutualistas();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> tipoMovimientoMutualistas() {
		return crucesUrgentesDao.tipoMovimientoMutualistas();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> protegidoTipAseguramientoAndTipMovimientoMutualistas() {
		return crucesUrgentesDao.protegidoTipAseguramientoAndTipMovimientoMutualistas();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> registrosIpfNuloMutualistas() {
		return crucesUrgentesDao.registrosIpfNuloMutualistas();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> informeAltCruzadoConSegSocialFinalMutualistas() {
		return crucesUrgentesDao.informeAltCruzadoConSegSocialFinalMutualistas();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> codTipoAseguradoMutualistas() {
		return crucesUrgentesDao.codTipoAseguradoMutualistas();
	}

	/* ##################### 
	  Hoja 3 - "Motivo Baja" 
	  ###################### */ 
	
	@Override
	@Transactional(readOnly = true)
	public List<String> regimenGeneral() {
		return crucesUrgentesDao.regimenGeneral();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> bajaTitulares() {
		return crucesUrgentesDao.bajaTitulares();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> totalEnvioInssAv() {
		return crucesUrgentesDao.totalEnvioInssAv();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> bajasVinculadosSns() {
		return crucesUrgentesDao.bajasVinculadosSns();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> mutualistasPrivados() {
		return crucesUrgentesDao.mutualistasPrivados();
	}

	/* ############################ 
	  Hoja 4 - "Baja por Defunción"
	  ############################# */ 
	
	@Override
	@Transactional(readOnly = true)
	public List<String> bajaPorDefuncion() {
		return crucesUrgentesDao.bajaPorDefuncion();
	}

	/* ####################### 
	  Hoja 5 - "Aseguramiento" 
	  ######################## */ 
	
	@Override
	@Transactional(readOnly = true)
	public List<String> aseguramiento() {
		return crucesUrgentesDao.aseguramiento();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> indicadorFarmacia() {
		return crucesUrgentesDao.indicadorFarmacia();
	}

	/* ############################### 
	  Hoja 6 - "Código Badas Repetido"  
	  ################################ */ 
	
	@Override
	@Transactional(readOnly = true)
	public List<String> codigoBadasRepetido() {
		return crucesUrgentesDao.codigoBadasRepetido();
	}

	/* ############################### 
	  Hoja 7 - "Altas sin NAF"  
	  ################################ */ 
	
	@Override
	@Transactional(readOnly = true)
	public List<String> altasNaf() {
		return crucesUrgentesDao.altasNaf();
	}
	
	/* ####################### 
	   Hoja 8 - "Cambios IPF"  
	  ######################## */

	@Override
	@Transactional(readOnly = true)
	public List<String> ipfAnteriorNoExisteInssTit() {
		return crucesUrgentesDao.ipfAnteriorNoExisteInssTit();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> ipfNuevoExisteInssTit() {
		return crucesUrgentesDao.ipfNuevoExisteInssTit();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> ipfAnteriorNoExisteInssMut() {
		return crucesUrgentesDao.ipfAnteriorNoExisteInssMut();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> ipfNuevoExiteInssMut() {
		return crucesUrgentesDao.ipfNuevoExiteInssMut();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> titularesDobleCobertura() {
		return crucesUrgentesDao.titularesDobleCobertura();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> beneficiarioDobleCoberturaMutualistas() {
		return crucesUrgentesDao.beneficiarioDobleCoberturaMutualistas();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> titularesIndicadorConvenioRural() {
		return crucesUrgentesDao.titularesIndicadorConvenioRural();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> beneficiadioIndicadorConvenioRural() {
		return crucesUrgentesDao.beneficiadioIndicadorConvenioRural();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> privadoRegularPublico() {
		return crucesUrgentesDao.privadoRegularPublico();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> privadoIrregularPublico() {
		return crucesUrgentesDao.privadoIrregularPublico();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> privadoNoExiste() {
		return crucesUrgentesDao.privadoNoExiste();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> altaModificacionTitularesDobleCobertura() {
		return crucesUrgentesDao.altaModificacionTitularesDobleCobertura();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> irregularPrivadoRegularPublico() {
		return crucesUrgentesDao.irregularPrivadoRegularPublico();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> irregularPrivadoIrregularPublico() {
		return crucesUrgentesDao.irregularPrivadoIrregularPublico();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> irregularPrivadoNoExiste() {
		return crucesUrgentesDao.irregularPrivadoNoExiste();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> altaModificaTitualresDobleCoberturaDos() {
		return crucesUrgentesDao.altaModificaTitualresDobleCoberturaDos();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> listadoRegistroNoExiste() {
		return crucesUrgentesDao.listadoRegistroNoExiste();
	}

	@Override
	public List<String> listadoRegistroNoExisteDos() {
		return crucesUrgentesDao.listadoRegistroNoExisteDos();
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<String> bajaPorDefuncionPrivadoPublico() {
		return crucesUrgentesDao.bajaPorDefuncionPrivadoPublico();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> bajaPorDefuncionPrivadoNoExiste() {
		return crucesUrgentesDao.bajaPorDefuncionPrivadoNoExiste();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> titularesMotiboBaja() {
		return crucesUrgentesDao.titularesMotiboBaja();
	}


	



	
	
	
}

