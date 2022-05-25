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
			" and (m.TIPO_ASEGURAMIENTO not in ('0611','0612','0613') or m.TIPO_ASEGURAMIENTO is null)" + 
			" GROUP BY INDICADOR_DOBLE_COBERTURA order by INDICADOR_DOBLE_COBERTURA asc", nativeQuery = true)
	List<String> titularseDobleCobertura();
	
	
	/* 1.7.2 Beneficiarios DOBLE_COBERTURA */
	@Query(value="select  /*+ PARALLEL (8) */ INDICADOR_DOBLE_COBERTURA, count (*) AS TOTAL" + 
			" from Z_INSS_MOV_SEP_25 m WHERE m.COD_TIPO_ASEGURADO = 'B'" + 
			" and (m.TIPO_ASEGURAMIENTO not in ('0611','0612','0613') or m.TIPO_ASEGURAMIENTO is null)" + 
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
	@Query(value="select s3.COD_TIPO_ASEGURADO, s3.TIPO_MOVIMIENTO, s3.COD_SITUACION, count(*) from Z_INSS_MOV_SEP_25 s3" + 
			" Where s3.TIPO_ASEGURAMIENTO in ('0611','0612','0613') group by s3.COD_TIPO_ASEGURADO, s3.TIPO_MOVIMIENTO, s3.COD_SITUACION" + 
			" order by 1 desc, 2, 3", nativeQuery = true)
	List<String> tipoMovimientoMutualistas();
	
	/* 2.3 Protegidos por tipo de aseguramiento y tipo de movimiento */
	@Query(value="	select s3.COD_TIPO_ASEGURADO, s3.TIPO_MOVIMIENTO, s3.PROTEGIDA, count(*) from Z_INSS_MOV_SEP_25 s3" + 
			" Where s3.TIPO_ASEGURAMIENTO in ('0611','0612','0613') group by s3.COD_TIPO_ASEGURADO, s3.TIPO_MOVIMIENTO, s3.PROTEGIDA" + 
			" order by 1 desc, 2, 3", nativeQuery = true)
	List<String> protegidoTipAseguramientoAndTipMovimientoMutualistas();
	
	/* 2.4 Registros Con el IPF Nulo */
	@Query(value="select s3.COD_TIPO_ASEGURADO, s3.TIPO_MOVIMIENTO, s3.COD_SITUACION, count(*) from Z_INSS_MOV_SEP_25 s3" + 
			" where s3.IPF is null and s3.TIPO_ASEGURAMIENTO in ('0611','0612','0613') group by s3.COD_TIPO_ASEGURADO, s3.TIPO_MOVIMIENTO, s3.COD_SITUACION" + 
			" order by 1 desc, 2, 3", nativeQuery = true)
	List<String> registrosIpfNuloMutualistas();
	
	
	/* 2.5 Comprobar que los 0501 son bajas de AL A AN -  Sacar informe de altas cruzado con segsocialfinal */
	@Query(value="select s.TIPO_ASEGURAMIENTO,S.TIPO_MOVIMIENTO, count(*) TOTAL from  Z_INSS_MOV_SEP_25 s where s.NAF is null" + 
			" AND S.TIPO_MOVIMIENTO <> 'C' and s.COD_TIPO_ASEGURADO = 'T' and s.TIPO_ASEGURAMIENTO in ('0611','0612','0613')" + 
			" group by s.TIPO_ASEGURAMIENTO, S.TIPO_MOVIMIENTO order by 1,2", nativeQuery = true)
	List<String> informeAltCruzadoConSegSocialFinalMutualistas();
	
	/* 2.6 AO a AP */
	@Query(value="select   i.COD_TIPO_ASEGURADO, count(*) TOTAL from Z_INSS_MOV_SEP_25 i where i.TIPO_ASEGURAMIENTO in ('0611','0612','0613')" + 
			" group by i.COD_TIPO_ASEGURADO ORDER BY 1", nativeQuery = true)
	List<String> codTipoAseguradoMutualistas();
	
	/* ##################### 
	  Hoja 3 - "Motivo Baja" 
	  ###################### */ 
	
	/* 3.1 REGIMEN GENERAL */
	@Query(value="select   s.COD_TIPO_ASEGURADO, S.MOTIVO_BAJA , count(*) from     Z_INSS_MOV_SEP_25 s WHERE S.TIPO_MOVIMIENTO = 'B' " + 
			"and s.TIPO_ASEGURAMIENTO not in ('0611','0612','0613') group by s.COD_TIPO_ASEGURADO, S.MOTIVO_BAJA order by 1, 2", nativeQuery = true)
	List<String> regimenGeneral(); 
	
	
	
	/* 3.2 Bajas 01 de Titulares con Estado 0 en el SNS por Tipo Aseguramiento */
	@Query(value="select  S.TIPO_ASEGURAMIENTO, count(*) AS NUMERO from Z_INSS_MOV_SEP_25 s, snsalud.inss_tit it,snsalud.inss_sns_tit i2, snsalud.usuarios u" + 
			" where  S.TIPO_MOVIMIENTO = 'B' and s.MOTIVO_BAJA = '01' and s.COD_TIPO_ASEGURADO = 'T' and s.TIPO_ASEGURAMIENTO not in ('0611','0612','0613')" + 
			" and IT.IPF = s.IPF and i2.ID_INSS=it.ID_INSS and I2.COD_USUARIO_SNS is not null and U.COD_USUARIO_SNS = I2.COD_USUARIO_SNS" + 
			" and u.COD_ESTADO = 0 group by S.TIPO_ASEGURAMIENTO", nativeQuery = true)
	List<String> bajaTitulares();
	
	/* 3.3 TOTAL envío INSS AV */
	@Query(value="select count(*) AS NUMERO from Z_INSS_MOV_SEP_25 s where S.TIPO_MOVIMIENTO =  'B' and s.MOTIVO_BAJA =  '01' and s.COD_TIPO_ASEGURADO = 'T'"
			, nativeQuery = true)
	List<String> totalEnvioInssAv();
	
	/* 3.4 Bajas de Vinculados en el SNS (De los que estan vinculados agrupados por estado) */
	@Query(value="select U.COD_ESTADO, count(*) AS NUMERO from Z_INSS_MOV_SEP_25 s, snsalud.inss_tit it,snsalud.inss_sns_tit i2, snsalud.usuarios u" + 
			" where  S.TIPO_MOVIMIENTO =  'B'	and s.MOTIVO_BAJA = '01' and s.COD_TIPO_ASEGURADO = 'T'	and s.TIPO_ASEGURAMIENTO not in ('0611','0612','0613') " + 
			" and IT.IPF = s.IPF	and i2.ID_INSS=it.ID_INSS	and I2.COD_USUARIO_SNS is not null	and U.COD_USUARIO_SNS = I2.COD_USUARIO_SNS" + 
			" group by U.COD_ESTADO order by  U.COD_ESTADO", nativeQuery = true)
	List<String> bajasVinculadosSns();
	
	
	/* 3.5 MUTUALISTAS PRIVADOS */
	@Query(value="select   s.COD_TIPO_ASEGURADO, S.MOTIVO_BAJA , count(*) from  Z_INSS_MOV_SEP_25 s WHERE S.TIPO_MOVIMIENTO = 'B'	" + 
			" and s.TIPO_ASEGURAMIENTO in ('0611','0612','0613') group by s.COD_TIPO_ASEGURADO, S.MOTIVO_BAJA order by 1, 2", nativeQuery = true)
	List<String> mutualistasPrivados();
	
	/* ############################ 
	  Hoja 4 - "Baja por Defunción"
	  ############################# */ 
	
	/* 4.1 Distribución de las Bajas por Defunción en el SNS */
	@Query(value="select  U.COD_ESTADO, count(*) AS NUMERO from Z_INSS_MOV_SEP_25 s, DATOS_COBERTURA A, usuarios u where  S.TIPO_MOVIMIENTO =  'B'" + 
			"	and    s.MOTIVO_BAJA =  '02' and s.COD_TIPO_ASEGURADO = 'T'	and s.NAF = a.naf and u.COD_USUARIO_SNS = A.COD_USUARIO_SNS" + 
			"	group by U.COD_ESTADO order by U.COD_ESTADO", nativeQuery= true)
	List<String> bajaPorDefuncion();
	
	
	/* ####################### 
	  Hoja 5 - "Aseguramiento" 
	  ######################## */ 
	
	/* 5.1 Comprobar SUBINDICADOR permitido para el INDICADO_DE_FARMACIA (TSI002 -> 00,01,02 | TSI005 -> 03, vacio) */
	@Query(value="select i.COD_INDICADOR_DE_FARMACIA, i.COD_SUBINDICADOR_DE_FARMACIA, count(*) TOTAL" + 
			" from Z_INSS_MOV_SEP_25 i group by i.COD_INDICADOR_DE_FARMACIA, i.COD_SUBINDICADOR_DE_FARMACIA" + 
			" ORDER BY 1, 2", nativeQuery = true)
	List<String> aseguramiento();

	/* 5.2 Comprobar INDICADOR_DE_FARMACIA correcto para TIPO_ASEGURAMIENTO BG A BJ */
	@Query(value="select i.TIPO_ASEGURAMIENTO, i.COD_INDICADOR_DE_FARMACIA, i.COD_SUBINDICADOR_DE_FARMACIA, count(*) TOTAL" + 
			" from Z_INSS_MOV_SEP_25 i group by i.TIPO_ASEGURAMIENTO,i.COD_INDICADOR_DE_FARMACIA, i.COD_SUBINDICADOR_DE_FARMACIA" + 
			" ORDER BY 1, 2, 3", nativeQuery = true)
	List<String> indicadorFarmacia();
	
	
	/* ############################### 
	  Hoja 6 - "Código Badas Repetido"  
	  ################################ */ 

	/* 6.1 BK A DC */
	
	@Query(value="select * 	from   Z_INSS_MOV_SEP_25 s2 	where  s2.CODIGO_BADAS in (select s.CODIGO_BADAS" + 
			" from Z_INSS_MOV_SEP_25 s  where s.CODIGO_BADAS is not  null group by  s.TIPO_MOVIMIENTO, s.DNI_NIE, s.CODIGO_BADAS" + 
			" having count(*) > 1 )order by s2.CODIGO_BADAS", nativeQuery = true)
	List<String> codigoBadasRepetido();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


