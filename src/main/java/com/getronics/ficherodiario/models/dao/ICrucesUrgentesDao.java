package com.getronics.ficherodiario.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.getronics.ficherodiario.models.entity.CrucesUrgentes;


public interface ICrucesUrgentesDao extends JpaRepository<CrucesUrgentes, String>{

	@Query(value="select count(*) from Z_INSS_MOV_SEP_25" , nativeQuery = true)
	public int contar();
	
	
	/* 1.1
	TIPOS DE IDENTIFICADOR */
	/*@Query( value="select S.TIPO_MOVIMIENTO , count(*) from Z_INSS_MOV_SEP_25 s Where S.TIPO_ASEGURAMIENTO not in ('0611','0612','0613') "
			+ "or S.TIPO_ASEGURAMIENTO is null group by S.TIPO_MOVIMIENTO  order by 1")
	public 
	*/
	
	
	
	
}


