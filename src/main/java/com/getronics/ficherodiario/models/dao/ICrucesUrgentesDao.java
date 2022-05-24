package com.getronics.ficherodiario.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.getronics.ficherodiario.models.entity.CrucesUrgentes;


public interface ICrucesUrgentesDao extends JpaRepository<CrucesUrgentes, String>{

	/* ################################### 
	  Hoja 1 - "Resumen - Regimen General"
	  #################################### */ 	
	
	/* 1.1 TIPOS DE IDENTIFICADOR */
	@Query( value="select S.TIPO_MOVIMIENTO , count(*) from Z_INSS_MOV_SEP_25 s Where S.TIPO_ASEGURAMIENTO not in ('0611','0612','0613') "
			+ "or S.TIPO_ASEGURAMIENTO is null group by S.TIPO_MOVIMIENTO  order by 1", nativeQuery = true)
	List<String> tipoIdentificador(); 
	
	/* 1.2 TIPO_MOVIMIENTO */
	@Query(value="select s3.COD_TIPO_ASEGURADO, s3.TIPO_MOVIMIENTO, s3.COD_SITUACION, count(*) " + 
			" from Z_INSS_MOV_SEP_25 s3 where s3.TIPO_ASEGURAMIENTO not in ('0611','0612','0613') or s3.TIPO_ASEGURAMIENTO is null" + 
			" group by s3.COD_TIPO_ASEGURADO, s3.TIPO_MOVIMIENTO, s3.COD_SITUACION order by 1 desc,2,3", nativeQuery = true)
	List<String> tipoMovimiento();
	
	/* 1.3 Protegidos por tipo de aseguramiento y tipo de movimiento */
	@Query(value="select s3.COD_TIPO_ASEGURADO, s3.TIPO_MOVIMIENTO, s3.PROTEGIDA, count(*) " + 
			" from Z_INSS_MOV_SEP_25 s3 where s3.TIPO_ASEGURAMIENTO not in ('0611','0612','0613')" + 
			" or s3.TIPO_ASEGURAMIENTO is null group by s3.COD_TIPO_ASEGURADO, s3.TIPO_MOVIMIENTO, s3.PROTEGIDA" + 
			" order by 1 desc, 2, 3", nativeQuery = true)
	List<String> protegidoTipAseguramientoAndTipMovimiento();
	
	/* 1.4 Registros Con el IPF Nulo */
	@Query(value="select s3.COD_TIPO_ASEGURADO, s3.TIPO_MOVIMIENTO, s3.COD_SITUACION, count(*)" + 
			" from Z_INSS_MOV_SEP_25 s3 where    s3.IPF is null and s3.TIPO_ASEGURAMIENTO not in ('0611','0612','0613')" + 
			" group by s3.COD_TIPO_ASEGURADO, s3.TIPO_MOVIMIENTO, s3.COD_SITUACION order by 1 desc, 2, 3", nativeQuery = true)
	List<String> registrosIpfNulo();
	
	/*  1.5	-- Comprobar que los 0501 son bajas, Sacar informe de altas cruzado con segsocialfinal */
	@Query(value="select s.TIPO_ASEGURAMIENTO,S.TIPO_MOVIMIENTO, count(*) TOTAL" + 
			" from Z_INSS_MOV_SEP_25 s where  s.NAF is null AND S.TIPO_MOVIMIENTO <> 'C' and s.COD_TIPO_ASEGURADO = 'T'" + 
			" and s.TIPO_ASEGURAMIENTO not in ('0611','0612','0613') group by s.TIPO_ASEGURAMIENTO, S.TIPO_MOVIMIENTO order by 1,2",
			nativeQuery = true)
	List<String> informeAltCruzadoConSegSocialFinal();	
	
	/* 1.6 */
	@Query(value="select i.COD_TIPO_ASEGURADO, count(*) TOTAL from Z_INSS_MOV_SEP_25 i " + 
			" where i.TIPO_ASEGURAMIENTO not in ('0611','0612','0613') or i.TIPO_ASEGURAMIENTO is null" + 
			" group by i.COD_TIPO_ASEGURADO ORDER BY 1", nativeQuery = true)
	List<String> codTipoAsegurado();
	
	
	/* 1.7 - DOBLE COBERTUERA*/
	
	/* 1.7.1 Titulares DOBLE_COBERTURA */
	@Query(value="	select /*+ PARALLEL (8) */ INDICADOR_DOBLE_COBERTURA, count (*) AS TOTAL " + 
			" from Z_INSS_MOV_SEP_25 m WHERE m.COD_TIPO_ASEGURADO = 'T'" + 
			" and (m.TIPO_ASEGURAMIENTO not in ('0611','0612','0613') or m.TIPO_ASEGURAMIENTO is null)\r\n" + 
			" GROUP BY INDICADOR_DOBLE_COBERTURA order by INDICADOR_DOBLE_COBERTURA asc", nativeQuery = true)
	List<String> titularseDobleCobertura();
	
	
	/* 1.7.2 Beneficiarios DOBLE_COBERTURA */
	@Query(value="select  /*+ PARALLEL (8) */ INDICADOR_DOBLE_COBERTURA, count (*) AS TOTAL" + 
			" from Z_INSS_MOV_SEP_25 m WHERE m.COD_TIPO_ASEGURADO = 'B'\r\n" + 
			" and (m.TIPO_ASEGURAMIENTO not in ('0611','0612','0613') or m.TIPO_ASEGURAMIENTO is null)\r\n" + 
			" GROUP BY INDICADOR_DOBLE_COBERTURA order by INDICADOR_DOBLE_COBERTURA asc", nativeQuery = true)
	List<String> beneficiarioDobleCobertura();
	
	
	/* ############################## 
	  Hoja 2 - "Resumen - Mutualistas"
	  ############################## */ 
	
	
	/* 2.1 TIPOS DE IDENTIFICADOR */
	@Query(value="select   S.TIPO_MOVIMIENTO , count(*) from Z_INSS_MOV_SEP_25 s Where S.TIPO_ASEGURAMIENTO in ('0611','0612','0613')" + 
			" group by S.TIPO_MOVIMIENTO order by 1", nativeQuery = true)
	List<String> tipoIdentificadorMutualistas();
	
	/* 2.2 Comprobar que los TIPO_MOVIMIENTO se corresponden con los COD_SITUACION (A-->A, B-->B) */
	@Query(value="select s3.COD_TIPO_ASEGURADO, s3.TIPO_MOVIMIENTO, s3.COD_SITUACION, count(*) from Z_INSS_MOV_SEP_25 s3\r\n" + 
			" Where s3.TIPO_ASEGURAMIENTO in ('0611','0612','0613') group by s3.COD_TIPO_ASEGURADO, s3.TIPO_MOVIMIENTO, s3.COD_SITUACION" + 
			" order by 1 desc, 2, 3", nativeQuery = true)
	List<String> tipoMovimientoMutualistas();
	
	/* 2.3 Protegidos por tipo de aseguramiento y tipo de movimiento */
	@Query(value="	select s3.COD_TIPO_ASEGURADO, s3.TIPO_MOVIMIENTO, s3.PROTEGIDA, count(*) from Z_INSS_MOV_SEP_25 s3\r\n" + 
			" Where s3.TIPO_ASEGURAMIENTO in ('0611','0612','0613') group by s3.COD_TIPO_ASEGURADO, s3.TIPO_MOVIMIENTO, s3.PROTEGIDA" + 
			" order by 1 desc, 2, 3", nativeQuery = true)
	List<String> protegidoTipAseguramientoAndTipMovimientoMutualistas();
	
	/* 2.4 Registros Con el IPF Nulo */
	@Query(value="select s3.COD_TIPO_ASEGURADO, s3.TIPO_MOVIMIENTO, s3.COD_SITUACION, count(*) from Z_INSS_MOV_SEP_25 s3\r\n" + 
			" where s3.IPF is null and s3.TIPO_ASEGURAMIENTO in ('0611','0612','0613') group by s3.COD_TIPO_ASEGURADO, s3.TIPO_MOVIMIENTO, s3.COD_SITUACION \r\n" + 
			" order by 1 desc, 2, 3", nativeQuery = true)
	List<String> registrosIpfNuloMutualistas();
	
	
	/* 2.5 Comprobar que los 0501 son bajas de AL A AN -  Sacar informe de altas cruzado con segsocialfinal */
	@Query(value="select s.TIPO_ASEGURAMIENTO,S.TIPO_MOVIMIENTO, count(*) TOTAL from  Z_INSS_MOV_SEP_25 s\r\n where s.NAF is null" + 
			" AND S.TIPO_MOVIMIENTO <> 'C' and s.COD_TIPO_ASEGURADO = 'T' and s.TIPO_ASEGURAMIENTO in ('0611','0612','0613') \r\n" + 
			" group by s.TIPO_ASEGURAMIENTO, S.TIPO_MOVIMIENTO order by 1,2", nativeQuery = true)
	List<String> informeAltCruzadoConSegSocialFinalMutualistas();
	
	/* 2.6 AO a AP */
	@Query(value="select   i.COD_TIPO_ASEGURADO, count(*) TOTAL from Z_INSS_MOV_SEP_25 i where i.TIPO_ASEGURAMIENTO in ('0611','0612','0613')" + 
			" group by i.COD_TIPO_ASEGURADO ORDER BY 1", nativeQuery = true)
	List<String> codTipoAseguradoMutualistas();
	
	
}


