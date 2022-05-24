package com.getronics.ficherodiario.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getronics.ficherodiario.models.dao.ICrucesUrgentesDao;


@Service
public class CrucesUrgentesServiceImpl implements ICrucesUrgentesService{

	@Autowired
	private ICrucesUrgentesDao crucesUrgentesDao;
	
	@Override
	public int contar() {
		return crucesUrgentesDao.contar();
	}

	



	
	
	
}

