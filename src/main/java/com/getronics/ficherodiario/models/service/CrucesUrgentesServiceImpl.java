package com.getronics.ficherodiario.models.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.getronics.ficherodiario.models.dao.ICrucesUrgentesDao;


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

	



	
	
	
}

