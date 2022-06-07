package com.getronics.ficherodiario.models.dao;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	
	@Query(value="select COD_TIPO_ASEGURADO, TIPO_MOVIMIENTO, IPF, DNI_NIE, PASAPORTE, NAF," + 
			" NAF_SEC1, NAF_SEC2, NAF_SEC3, NAF_SEC4, NAF_SEC5, NAF_SEC6, NAF_SEC7, NAF_SEC8," + 
			" NAF_SEC9, INDICATIVO_NOMBRE, APELLIDOS_NOMBRE, APELLIDO1, APELLIDO2, NOMBRE, NACIONALIDAD," + 
			" FECHA_NACIMIENTO, SEXO, INDICATIVO_DOMICILIO, REPLACE(DOMICILIO,',',' '), TIPO_ASEGURAMIENTO," + 
			" COD_INDICADOR_DE_FARMACIA, COD_SUBINDICADOR_DE_FARMACIA, COD_SITUACION, FECHA_EFECTO_SITUACION," + 
			" COD_TIPO_BENEFICIARIO, IPF_TITULAR, NAF_TITULAR, NUMERO_SECUENCIA, FECHA_NACIMIENTO_RAW," + 
			" IPF_ANTERIOR, COD_USUARIO_SNS, CODIGO_BADAS, MOTIVO_BAJA, PROTEGIDA," + 
			" INDICADOR_DOBLE_COBERTURA, CIP_MUTUALISTA, CIP_MUTUALISTA_TITULAR, INDICADOR_CONVENIO_RURAL," + 
			" PRESTADORA_PRIVADA 	from   Z_INSS_MOV_SEP_25 s2 	where  s2.CODIGO_BADAS in (select s.CODIGO_BADAS" + 
			" from Z_INSS_MOV_SEP_25 s  where s.CODIGO_BADAS is not  null group by  s.TIPO_MOVIMIENTO, s.DNI_NIE, s.CODIGO_BADAS" + 
			" having count(*) > 1 )order by s2.CODIGO_BADAS", nativeQuery = true)
	List<String> codigoBadasRepetido();
	
	
	/* ####################### 
	  Hoja 7 - "Altas sin NAF"  
	  ######################## */ 
	
	@Query(value="select a.NAF NAFseg, s.COD_TIPO_ASEGURADO, s.TIPO_MOVIMIENTO, s.IPF, s.DNI_NIE, s.PASAPORTE, s.NAF NAFins," + 
			" s.NAF_SEC1, s.NAF_SEC2, s.NAF_SEC3, s.NAF_SEC4, s.NAF_SEC5, s.NAF_SEC6, s.NAF_SEC7, s.NAF_SEC8," + 
			" s.NAF_SEC9, INDICATIVO_NOMBRE, s.APELLIDOS_NOMBRE,s. APELLIDO1, s.APELLIDO2, s.NOMBRE, s.NACIONALIDAD," + 
			" s.FECHA_NACIMIENTO,s.SEXO, s.INDICATIVO_DOMICILIO, REPLACE(s.DOMICILIO,',',' '), s.TIPO_ASEGURAMIENTO," + 
			" s.COD_INDICADOR_DE_FARMACIA, s.COD_SUBINDICADOR_DE_FARMACIA, s.COD_SITUACION, s.FECHA_EFECTO_SITUACION," + 
			" s.COD_TIPO_BENEFICIARIO, s.IPF_TITULAR, s.NAF_TITULAR, s.NUMERO_SECUENCIA, s.FECHA_NACIMIENTO_RAW," + 
			" s.IPF_ANTERIOR, s.COD_USUARIO_SNS, s.CODIGO_BADAS, s.MOTIVO_BAJA, s.PROTEGIDA," + 
			" s.INDICADOR_DOBLE_COBERTURA, s.CIP_MUTUALISTA, s.CIP_MUTUALISTA_TITULAR, s.INDICADOR_CONVENIO_RURAL," + 
			" s.PRESTADORA_PRIVADA" + 
			" from   Z_INSS_MOV_SEP_25 s, segsocialfinal a" + 
			" where  s.NAF is NULL" + 
			" AND    S.TIPO_MOVIMIENTO = 'A'" + 
			" and    s.COD_TIPO_ASEGURADO = 'T'" + 
			" and    s.DNI_NIE = a.DNI_NIE(+)" + 
			" order by 2,3",nativeQuery = true)
	List<String> altasNaf();

	/* ####################### 
	   Hoja 8 - "Cambios IPF"  
	  ######################## */

	/* 8.1 REGIMEN GENERAL */
	
	/* 8.1.1 IPF Anterior no existe en INSS_TIT */
	@Query(value="select s.COD_TIPO_ASEGURADO, s.TIPO_MOVIMIENTO, s.IPF, s.DNI_NIE, s.PASAPORTE, s.NAF,\r\n" + 
			"s.NAF_SEC1, s.NAF_SEC2, s.NAF_SEC3, s.NAF_SEC4, s.NAF_SEC5, s.NAF_SEC6, s.NAF_SEC7, s.NAF_SEC8,\r\n" + 
			"s.NAF_SEC9, INDICATIVO_NOMBRE, s.APELLIDOS_NOMBRE,s. APELLIDO1, s.APELLIDO2, s.NOMBRE, s.NACIONALIDAD,\r\n" + 
			"s.FECHA_NACIMIENTO,s.SEXO, s.INDICATIVO_DOMICILIO, REPLACE(s.DOMICILIO,',',' '), s.TIPO_ASEGURAMIENTO,\r\n" + 
			"s.COD_INDICADOR_DE_FARMACIA, s.COD_SUBINDICADOR_DE_FARMACIA, s.COD_SITUACION, s.FECHA_EFECTO_SITUACION,\r\n" + 
			"s.COD_TIPO_BENEFICIARIO, s.IPF_TITULAR, s.NAF_TITULAR, s.NUMERO_SECUENCIA, s.FECHA_NACIMIENTO_RAW,\r\n" + 
			"s.IPF_ANTERIOR, s.COD_USUARIO_SNS, s.CODIGO_BADAS, s.MOTIVO_BAJA, s.PROTEGIDA,\r\n" + 
			"s.INDICADOR_DOBLE_COBERTURA, s.CIP_MUTUALISTA, s.CIP_MUTUALISTA_TITULAR, s.INDICADOR_CONVENIO_RURAL,\r\n" + 
			"s.PRESTADORA_PRIVADA from Z_INSS_MOV_SEP_25 S	where s.COD_TIPO_ASEGURADO = 'T' and s.TIPO_MOVIMIENTO  = 'C'\r\n" + 
			"	and S.CIP_MUTUALISTA is null and not exists  ( select 'X' from   snsalud.inss_tit i where  i.IPF = s.IPF_ANTERIOR)",
			nativeQuery = true)	
	List<String> ipfAnteriorNoExisteInssTit();
	
	
	/* 8.1.2 IPF Nuevo existe en INSS_TIT */
	@Query(value="select s.COD_TIPO_ASEGURADO, s.TIPO_MOVIMIENTO, s.IPF, s.DNI_NIE, s.PASAPORTE, s.NAF,\r\n" + 
			"s.NAF_SEC1, s.NAF_SEC2, s.NAF_SEC3, s.NAF_SEC4, s.NAF_SEC5, s.NAF_SEC6, s.NAF_SEC7, s.NAF_SEC8,\r\n" + 
			"s.NAF_SEC9, INDICATIVO_NOMBRE, s.APELLIDOS_NOMBRE,s. APELLIDO1, s.APELLIDO2, s.NOMBRE, s.NACIONALIDAD,\r\n" + 
			"s.FECHA_NACIMIENTO,s.SEXO, s.INDICATIVO_DOMICILIO, REPLACE(s.DOMICILIO,',',' '), s.TIPO_ASEGURAMIENTO,\r\n" + 
			"s.COD_INDICADOR_DE_FARMACIA, s.COD_SUBINDICADOR_DE_FARMACIA, s.COD_SITUACION, s.FECHA_EFECTO_SITUACION,\r\n" + 
			"s.COD_TIPO_BENEFICIARIO, s.IPF_TITULAR, s.NAF_TITULAR, s.NUMERO_SECUENCIA, s.FECHA_NACIMIENTO_RAW,\r\n" + 
			"s.IPF_ANTERIOR, s.COD_USUARIO_SNS, s.CODIGO_BADAS, s.MOTIVO_BAJA, s.PROTEGIDA,\r\n" + 
			"s.INDICADOR_DOBLE_COBERTURA, s.CIP_MUTUALISTA, s.CIP_MUTUALISTA_TITULAR, s.INDICADOR_CONVENIO_RURAL,\r\n" + 
			"s.PRESTADORA_PRIVADA from Z_INSS_MOV_SEP_25 S where s.COD_TIPO_ASEGURADO = 'T' and s.TIPO_MOVIMIENTO = 'C'" + 
			" and S.CIP_MUTUALISTA is null and exists (select 'X' from   snsalud.inss_tit i where  i.IPF = s.IPF)",
			nativeQuery = true)
	List<String> ipfNuevoExisteInssTit();
	
	
	/* 8.1 MUTUALISTAS */
	
	/* 8.2.1 IPF Anterior no existe en INSS_MUT_TIT*/
	@Query(value="select s.COD_TIPO_ASEGURADO, s.TIPO_MOVIMIENTO, s.IPF, s.DNI_NIE, s.PASAPORTE, s.NAF,\r\n" + 
			"s.NAF_SEC1, s.NAF_SEC2, s.NAF_SEC3, s.NAF_SEC4, s.NAF_SEC5, s.NAF_SEC6, s.NAF_SEC7, s.NAF_SEC8,\r\n" + 
			"s.NAF_SEC9, INDICATIVO_NOMBRE, s.APELLIDOS_NOMBRE,s. APELLIDO1, s.APELLIDO2, s.NOMBRE, s.NACIONALIDAD,\r\n" + 
			"s.FECHA_NACIMIENTO,s.SEXO, s.INDICATIVO_DOMICILIO, REPLACE(s.DOMICILIO,',',' '), s.TIPO_ASEGURAMIENTO,\r\n" + 
			"s.COD_INDICADOR_DE_FARMACIA, s.COD_SUBINDICADOR_DE_FARMACIA, s.COD_SITUACION, s.FECHA_EFECTO_SITUACION,\r\n" + 
			"s.COD_TIPO_BENEFICIARIO, s.IPF_TITULAR, s.NAF_TITULAR, s.NUMERO_SECUENCIA, s.FECHA_NACIMIENTO_RAW,\r\n" + 
			"s.IPF_ANTERIOR, s.COD_USUARIO_SNS, s.CODIGO_BADAS, s.MOTIVO_BAJA, s.PROTEGIDA,\r\n" + 
			"s.INDICADOR_DOBLE_COBERTURA, s.CIP_MUTUALISTA, s.CIP_MUTUALISTA_TITULAR, s.INDICADOR_CONVENIO_RURAL,\r\n" + 
			"s.PRESTADORA_PRIVADA from Z_INSS_MOV_SEP_25 S	where s.COD_TIPO_ASEGURADO = 'T'	and s.TIPO_MOVIMIENTO    = 'C'" + 
			" and S.CIP_MUTUALISTA is not null and  not exists  (select 'X' from  snsalud.INSS_MUT_TIT i where  i.IPF = s.IPF_ANTERIOR)",
			nativeQuery = true)
	List<String> ipfAnteriorNoExisteInssMut();
	
	
	/* 8.2.2 IPF Nuevo existe en INSS_MUT_TIT */
	@Query(value="select s.COD_TIPO_ASEGURADO, s.TIPO_MOVIMIENTO, s.IPF, s.DNI_NIE, s.PASAPORTE, s.NAF,\r\n" + 
			"s.NAF_SEC1, s.NAF_SEC2, s.NAF_SEC3, s.NAF_SEC4, s.NAF_SEC5, s.NAF_SEC6, s.NAF_SEC7, s.NAF_SEC8,\r\n" + 
			"s.NAF_SEC9, INDICATIVO_NOMBRE, s.APELLIDOS_NOMBRE,s. APELLIDO1, s.APELLIDO2, s.NOMBRE, s.NACIONALIDAD,\r\n" + 
			"s.FECHA_NACIMIENTO,s.SEXO, s.INDICATIVO_DOMICILIO, REPLACE(s.DOMICILIO,',',' '), s.TIPO_ASEGURAMIENTO,\r\n" + 
			"s.COD_INDICADOR_DE_FARMACIA, s.COD_SUBINDICADOR_DE_FARMACIA, s.COD_SITUACION, s.FECHA_EFECTO_SITUACION,\r\n" + 
			"s.COD_TIPO_BENEFICIARIO, s.IPF_TITULAR, s.NAF_TITULAR, s.NUMERO_SECUENCIA, s.FECHA_NACIMIENTO_RAW,\r\n" + 
			"s.IPF_ANTERIOR, s.COD_USUARIO_SNS, s.CODIGO_BADAS, s.MOTIVO_BAJA, s.PROTEGIDA,\r\n" + 
			"s.INDICADOR_DOBLE_COBERTURA, s.CIP_MUTUALISTA, s.CIP_MUTUALISTA_TITULAR, s.INDICADOR_CONVENIO_RURAL,\r\n" + 
			"s.PRESTADORA_PRIVADA from Z_INSS_MOV_SEP_25 S where s.COD_TIPO_ASEGURADO = 'T' and s.TIPO_MOVIMIENTO = 'C'" + 
			"	and S.CIP_MUTUALISTA is not null and exists (select 'X' from   snsalud.INSS_MUT_TIT i where  i.IPF = s.IPF)",
			nativeQuery = true)
	List<String> ipfNuevoExiteInssMut();
	
	
	/* ####################### 
	   Hoja 9 - "MUTUALISTAS"  
	  ######################## */
	
	
	/* 9.1 - DOBLE_COBERTURA */

	/* 9.1.1 Titulares DOBLE_COBERTURA EMPEZA EN LV */
	
	@Query(value="select  /*+ PARALLEL (8) */ INDICADOR_DOBLE_COBERTURA, count (*) AS TOTAL" + 
			" from Z_INSS_MOV_SEP_25 m WHERE m.COD_TIPO_ASEGURADO = 'T' and m.TIPO_ASEGURAMIENTO in ('0611','0612','0613')" + 
			" GROUP BY INDICADOR_DOBLE_COBERTURA", nativeQuery = true)
	List<String> titularesDobleCobertura();
	
	
	
	/* 9.1.2 Beneficiarios DOBLE_COBERTURA */
	@Query(value="select  /*+ PARALLEL (8) */ INDICADOR_DOBLE_COBERTURA, count (*) AS TOTAL" + 
			" from Z_INSS_MOV_SEP_25 m WHERE m.COD_TIPO_ASEGURADO = 'B' and m.TIPO_ASEGURAMIENTO in ('0611','0612','0613')" + 
			" GROUP BY INDICADOR_DOBLE_COBERTURA", nativeQuery = true)
	List<String> beneficiarioDobleCoberturaMutualistas();
		
	
	/* 9.2 - INDICADOR_CONVENIO_RURAL */

	/* 9.2.1 Titulares INDICADOR_CONVENIO_RURAL */
	@Query(value="select  /*+ PARALLEL (8) */ INDICADOR_CONVENIO_RURAL, count (*) AS TOTAL from Z_INSS_MOV_SEP_25 m WHERE m.COD_TIPO_ASEGURADO = 'T'" + 
			"	and m.TIPO_ASEGURAMIENTO in ('0611','0612','0613') 	GROUP BY INDICADOR_CONVENIO_RURAL", nativeQuery = true)
	List<String> titularesIndicadorConvenioRural();
	
		
	/* 9.2.2 Beneficiarios INDICADOR_CONVENIO_RURAL */
	@Query(value="select  /*+ PARALLEL (8) */ INDICADOR_CONVENIO_RURAL, count (*) AS TOTAL from Z_INSS_MOV_SEP_25 m" + 
			"	WHERE m.COD_TIPO_ASEGURADO = 'B' and m.TIPO_ASEGURAMIENTO in ('0611','0612','0613')	GROUP BY INDICADOR_CONVENIO_RURAL",
			nativeQuery = true)
	List<String> beneficiadioIndicadorConvenioRural();

	
	/* 9.3.1.1 REGULAR_PRIVADO -> REGULAR_PUBLICO */
	@Query(value="Select COUNT(*) as REGUPRIV_REGUPUB from Z_INSS_MOV_SEP_25 M, Z_INSS_MOV_SEP_25 B WHERE M.IPF = B.IPF" + 
			" and M.TIPO_ASEGURAMIENTO in ('0611','0612','0613') and M.INDICADOR_DOBLE_COBERTURA='1' and M.COD_TIPO_ASEGURADO = 'T'" + 
			" and M.TIPO_MOVIMIENTO in ('M','A') and B.TIPO_ASEGURAMIENTO NOT IN ('0611','0612','0613') and B.INDICADOR_DOBLE_COBERTURA='1'" + 
			" and B.TIPO_MOVIMIENTO in ('M','A')", nativeQuery = true)
	List<String> privadoRegularPublico();
	
	
	/* 9.3.1.2 REGULAR_PRIVADO -> IRREGULAR_PUBLICO */
	@Query(value="Select COUNT(*) as REGUPRIV_IRREGUPUB from Z_INSS_MOV_SEP_25 M, Z_INSS_MOV_SEP_25 B	WHERE M.IPF = B.IPF" + 
			" and M.TIPO_ASEGURAMIENTO in ('0611','0612','0613')	and M.INDICADOR_DOBLE_COBERTURA='1'	and M.COD_TIPO_ASEGURADO = 'T'" + 
			" and M.TIPO_MOVIMIENTO in ('M','A') and B.TIPO_ASEGURAMIENTO NOT IN ('0611','0612','0613') and B.INDICADOR_DOBLE_COBERTURA='2'" + 
			" and B.TIPO_MOVIMIENTO in ('M','A')", nativeQuery = true)
	List<String> privadoIrregularPublico();
	
	
	/* 9.3.1.3 REGULAR_PRIVADO -> NO_EXISTE */
	@Query(value="Select COUNT(*) as REGUPRIV_NOEXISTE from ( Select * from Z_INSS_MOV_SEP_25 where TIPO_ASEGURAMIENTO in ('0611','0612','0613')" + 
			" and INDICADOR_DOBLE_COBERTURA='1'and COD_TIPO_ASEGURADO = 'T'and TIPO_MOVIMIENTO in ('M','A')) M" + 
			" left join(Select * from Z_INSS_MOV_SEP_25 where TIPO_ASEGURAMIENTO NOT IN ('0611','0612','0613') and TIPO_MOVIMIENTO in ('M','A')) B" + 
			" on  M.IPF  = B.IPF where B.ipf is null", nativeQuery = true)
	List<String> privadoNoExiste();
	
	/* 9.3.1.4 Alta y Modificación de Titulares con DOBLE_COBERTURA '1' */
	@Query(value="select  count (*) AS TOTAL_ESPERADO_REG from Z_INSS_MOV_SEP_25 m WHERE m.COD_TIPO_ASEGURADO = 'T'" + 
			" and m.TIPO_ASEGURAMIENTO in ('0611','0612','0613') 	and m.TIPO_MOVIMIENTO in ('M','A') and m.INDICADOR_DOBLE_COBERTURA='1'" + 
			" GROUP BY INDICADOR_DOBLE_COBERTURA", nativeQuery = true)
	List<String> altaModificacionTitularesDobleCobertura();
	
	/* 9.3.2.1 IRREGULAR_PRIVADO -> REGULAR_PUBLICO */
	@Query(value="Select COUNT(*) as IRREGUPRIV_REGUPUB	from Z_INSS_MOV_SEP_25 M, Z_INSS_MOV_SEP_25 B WHERE M.IPF = B.IPF" + 
			" and M.TIPO_ASEGURAMIENTO in ('0611','0612','0613') and M.INDICADOR_DOBLE_COBERTURA='2' and M.COD_TIPO_ASEGURADO = 'T'" + 
			" and M.TIPO_MOVIMIENTO in ('M','A') and B.TIPO_ASEGURAMIENTO NOT IN ('0611','0612','0613') and B.INDICADOR_DOBLE_COBERTURA='1'" + 
			" and B.TIPO_MOVIMIENTO in ('M','A')", nativeQuery = true)
	List<String> irregularPrivadoRegularPublico();
	
	
	/* 9.3.2.2  IRREGULAR_PRIVADO -> IRREGULAR_PUBLICO */
	@Query(value="Select COUNT(*) as IRREGUPRIV_IRREGUPUB from Z_INSS_MOV_SEP_25 M, Z_INSS_MOV_SEP_25 B WHERE M.IPF = B.IPF" + 
			" and M.TIPO_ASEGURAMIENTO in ('0611','0612','0613') and M.INDICADOR_DOBLE_COBERTURA='2' and M.COD_TIPO_ASEGURADO = 'T'" + 
			" and M.TIPO_MOVIMIENTO in ('M','A') and B.TIPO_ASEGURAMIENTO NOT IN ('0611','0612','0613') and B.INDICADOR_DOBLE_COBERTURA='2'" + 
			" and B.TIPO_MOVIMIENTO in ('M','A')", nativeQuery = true)
	List<String> irregularPrivadoIrregularPublico();
	
	
	
	/* 9.3.2.3 IRREGULAR_PRIVADO -> NO_EXISTE */
	@Query(value="Select COUNT(*) as IRREGUPRIV_NOEXISTE from (Select * from Z_INSS_MOV_SEP_25 where TIPO_ASEGURAMIENTO in ('0611','0612','0613')" + 
			"  and INDICADOR_DOBLE_COBERTURA='2'and COD_TIPO_ASEGURADO = 'T'and TIPO_MOVIMIENTO in ('M','A')) M" + 
			" left join ( Select * from Z_INSS_MOV_SEP_25 where TIPO_ASEGURAMIENTO NOT IN ('0611','0612','0613') and TIPO_MOVIMIENTO in ('M','A')) B" + 
			" on  M.IPF  = B.IPF where B.ipf is null", nativeQuery = true)
	List<String> irregularPrivadoNoExiste();
	
	
	/* 9.3.2.4 Alta y Modificación de Titulares con DOBLE_COBERTURA '2' */
	@Query(value="select  count (*) AS TOTAL_ESPERADO_IRREG from Z_INSS_MOV_SEP_25 m WHERE m.COD_TIPO_ASEGURADO = 'T'" + 
			" and m.TIPO_ASEGURAMIENTO in ('0611','0612','0613') and m.TIPO_MOVIMIENTO in ('M','A') and m.INDICADOR_DOBLE_COBERTURA='2'" + 
			" GROUP BY INDICADOR_DOBLE_COBERTURA", nativeQuery = true)
	List<String> altaModificaTitualresDobleCoberturaDos();
	
	
	
	/* 9.3.3 Listado de registros del tipo "No existe" DE ML A OD */
	
	@Query(value="Select M.* from ( Select * from Z_INSS_MOV_SEP_25 where TIPO_ASEGURAMIENTO in ('0611','0612','0613')" + 
			" and INDICADOR_DOBLE_COBERTURA in ('1','2') and COD_TIPO_ASEGURADO = 'T' and TIPO_MOVIMIENTO in ('M','A')) M" + 
			" left join ( Select * from Z_INSS_MOV_SEP_25 where TIPO_ASEGURAMIENTO NOT IN ('0611','0612','0613')" + 
			" and TIPO_MOVIMIENTO in ('M','A')) B on  M.IPF  = B.IPF where B.ipf is null",nativeQuery = true)
	List<String> listadoRegistroNoExiste();
	
	
	@Query(value="Select B.* from \r\n" + 
			"    (\r\n" + 
			"    Select * from Z_INSS_MOV_SEP_25\r\n" + 
			"    where TIPO_ASEGURAMIENTO in ('0611','0612','0613')\r\n" + 
			"    and INDICADOR_DOBLE_COBERTURA in ('1','2')\r\n" + 
			"    and COD_TIPO_ASEGURADO = 'T'\r\n" + 
			"    and TIPO_MOVIMIENTO in ('M','A')\r\n" + 
			"    ) M\r\n" + 
			"left join\r\n" + 
			"    (\r\n" + 
			"    Select * from Z_INSS_MOV_SEP_25\r\n" + 
			"   where TIPO_ASEGURAMIENTO NOT IN ('0611','0612','0613')\r\n" + 
			"    and TIPO_MOVIMIENTO in ('M','A')\r\n" + 
			"    ) B\r\n" + 
			"on  M.IPF  = B.IPF\r\n" + 
			"where B.ipf is null",
			nativeQuery = true)
	List<String> listadoRegistroNoExisteDos();
	
	
	
	
	/* 9.4 Baja por defunción con doble cobertura */

	/* 9.4.1 PRIVADO -> PUBLICO */
	@Query(value="Select COUNT(*) as BAJAPRIV_BAJAPUB	from Z_INSS_MOV_SEP_25 M, Z_INSS_MOV_SEP_25 B WHERE M.IPF = B.IPF" + 
			" and M.TIPO_ASEGURAMIENTO in ('0611','0612','0613') and M.INDICADOR_DOBLE_COBERTURA in ('1','2') and M.COD_TIPO_ASEGURADO = 'T'" + 
			" and M.TIPO_MOVIMIENTO='B' and B.TIPO_ASEGURAMIENTO NOT IN ('0611','0612','0613') and B.INDICADOR_DOBLE_COBERTURA in ('1','2')" + 
			" and B.TIPO_MOVIMIENTO='B'", nativeQuery = true)
	List<String> bajaPorDefuncionPrivadoPublico();
	
	
	
	/* 9.4.2 PRIVADO -> NO EXISTE */
	@Query(value="Select COUNT(*) as BAJAPRIV_BAJANOEXISTE from Z_INSS_MOV_SEP_25 M, Z_INSS_MOV_SEP_25 B" + 
			" WHERE M.IPF = B.IPF and M.TIPO_ASEGURAMIENTO in ('0611','0612','0613')	and M.INDICADOR_DOBLE_COBERTURA in ('1','2')" + 
			" and M.COD_TIPO_ASEGURADO = 'T'	and M.TIPO_MOVIMIENTO='B'	and B.TIPO_ASEGURAMIENTO NOT IN ('0611','0612','0613')" + 
			" and B.INDICADOR_DOBLE_COBERTURA in ('1','2') and B.TIPO_MOVIMIENTO <>'B'", nativeQuery = true)
	List<String> bajaPorDefuncionPrivadoNoExiste();
	
	
	/* ###############################
	"DATOS 1"
	################################ */

	@Query(value="SELECT * FROM (\r\n" + 
			"SELECT * FROM (\r\n" + 
			"select COD_TIPO_ASEGURADO, TIPO_MOVIMIENTO, IPF, DNI_NIE, PASAPORTE, NAF,\r\n" + 
			"NAF_SEC1, NAF_SEC2, NAF_SEC3, NAF_SEC4, NAF_SEC5, NAF_SEC6, NAF_SEC7, NAF_SEC8,\r\n" + 
			"NAF_SEC9, INDICATIVO_NOMBRE, APELLIDOS_NOMBRE, APELLIDO1, APELLIDO2, NOMBRE, NACIONALIDAD,\r\n" + 
			"FECHA_NACIMIENTO, SEXO, INDICATIVO_DOMICILIO, REPLACE(DOMICILIO,',',' ') DOMICILIO , TIPO_ASEGURAMIENTO,\r\n" + 
			"COD_INDICADOR_DE_FARMACIA, COD_SUBINDICADOR_DE_FARMACIA, COD_SITUACION, FECHA_EFECTO_SITUACION,\r\n" + 
			"COD_TIPO_BENEFICIARIO, IPF_TITULAR, NAF_TITULAR, NUMERO_SECUENCIA, FECHA_NACIMIENTO_RAW,\r\n" + 
			"IPF_ANTERIOR, COD_USUARIO_SNS, CODIGO_BADAS, MOTIVO_BAJA, PROTEGIDA,\r\n" + 
			"INDICADOR_DOBLE_COBERTURA, CIP_MUTUALISTA, CIP_MUTUALISTA_TITULAR, INDICADOR_CONVENIO_RURAL,\r\n" + 
			"PRESTADORA_PRIVADA\r\n" + 
			"from Z_INSS_MOV_SEP_25 s2\r\n" + 
			"where S2.DNI_NIE in (\r\n" + 
			"select S.DNI_NIE\r\n" + 
			"from Z_INSS_MOV_SEP_25 s\r\n" + 
			"WHERE S.TIPO_MOVIMIENTO = 'B' and S.COD_TIPO_ASEGURADO = 'T' and S.MOTIVO_BAJA = 03\r\n" + 
			")\r\n" + 
			")\r\n" + 
			"order by DNI_NIE)\r\n" + 
			"UNION ALL\r\n" + 
			"select COD_TIPO_ASEGURADO, TIPO_MOVIMIENTO, IPF, DNI_NIE, PASAPORTE, NAF,\r\n" + 
			"NAF_SEC1, NAF_SEC2, NAF_SEC3, NAF_SEC4, NAF_SEC5, NAF_SEC6, NAF_SEC7, NAF_SEC8,\r\n" + 
			"NAF_SEC9, INDICATIVO_NOMBRE, APELLIDOS_NOMBRE, APELLIDO1, APELLIDO2, NOMBRE, NACIONALIDAD,\r\n" + 
			"FECHA_NACIMIENTO, SEXO, INDICATIVO_DOMICILIO, REPLACE(DOMICILIO,',',' ') DOMICILIO , TIPO_ASEGURAMIENTO,\r\n" + 
			"COD_INDICADOR_DE_FARMACIA, COD_SUBINDICADOR_DE_FARMACIA, COD_SITUACION, FECHA_EFECTO_SITUACION,\r\n" + 
			"COD_TIPO_BENEFICIARIO, IPF_TITULAR, NAF_TITULAR, NUMERO_SECUENCIA, FECHA_NACIMIENTO_RAW,\r\n" + 
			"IPF_ANTERIOR, COD_USUARIO_SNS, CODIGO_BADAS, MOTIVO_BAJA, PROTEGIDA,\r\n" + 
			"INDICADOR_DOBLE_COBERTURA, CIP_MUTUALISTA, CIP_MUTUALISTA_TITULAR, INDICADOR_CONVENIO_RURAL,\r\n" + 
			"PRESTADORA_PRIVADA\r\n" + 
			"from Z_INSS_MOV_SEP_25 s\r\n" + 
			"WHERE S.TIPO_MOVIMIENTO = 'B' and S.COD_TIPO_ASEGURADO = 'T' and S.MOTIVO_BAJA = 03 and S.DNI_NIE is NULL", nativeQuery = true)
	List<String> datosUno();
	
	
	/* ################ 
	  Excel datos2.xlsx  
	  ################# */ 
	
	
	/* Baja Titulares Motivo Baja 03 datos2.1 => LO CORRECTO ES QUE VENGA VACIA. Se dan de baja en copia_badas pero no en badas (creo). Habria que informar. */
	@Query(value="select   U.COD_ESTADO, U.COD_USUARIO_SNS, DP.DNI_NIE, S.IPF from Z_INSS_MOV_SEP_25 s, snsalud.datos_personales dp, snsalud.usuarios u" + 
			" WHERE S.TIPO_MOVIMIENTO = 'B' and S.COD_TIPO_ASEGURADO = 'T' and S.MOTIVO_BAJA  = 04 and DP.DNI_NIE = S.DNI_NIE" + 
			" and U.COD_USUARIO_SNS = DP.COD_USUARIO_SNS", nativeQuery = true)
	List<String> titularesMotivoBaja03();
	
	
	/* ################ 
	  Excel datos3.xlsx  
	  ################# */ 
		
	/* Baja Titulares Motivo Baja 06 datos3.1 */
	
	@Query(value="select U.COD_ESTADO, U.COD_USUARIO_SNS, DP.DNI_NIE, DC.COD_USUARIO_SNS_TITULAR" + 
			" from Z_INSS_MOV_SEP_25 s, snsalud.datos_personales dp, snsalud.usuarios u,snsalud.datos_cobertura dc" + 
			" WHERE S.TIPO_MOVIMIENTO = 'B' and S.COD_TIPO_ASEGURADO = 'T' and S.MOTIVO_BAJA  = 06" + 
			" and DP.DNI_NIE = S.DNI_NIE and U.COD_USUARIO_SNS = DP.COD_USUARIO_SNS" + 
			" and DC.COD_USUARIO_SNS = U.COD_USUARIO_SNS", nativeQuery = true)
	List<String> titularesMotiboBaja();
	
	/* ################ 
	  Excel datos4.xlsx  
	  ################# */ 		
	
	/* IPF Repetidos datos 4.1 */
	
	@Query(value="select * from ("
			+ "SELECT COD_TIPO_ASEGURADO, TIPO_MOVIMIENTO, IPF, DNI_NIE, PASAPORTE, NAF, NAF_SEC1, NAF_SEC2, NAF_SEC3, NAF_SEC4, NAF_SEC5, NAF_SEC6, NAF_SEC7, "
			+ "NAF_SEC8,NAF_SEC9, INDICATIVO_NOMBRE, APELLIDOS_NOMBRE, APELLIDO1, APELLIDO2, NOMBRE, NACIONALIDAD," + 
			" FECHA_NACIMIENTO, SEXO, INDICATIVO_DOMICILIO, REPLACE(DOMICILIO,',',' ') DOMICILIO, TIPO_ASEGURAMIENTO," + 
			" COD_INDICADOR_DE_FARMACIA, COD_SUBINDICADOR_DE_FARMACIA, COD_SITUACION, FECHA_EFECTO_SITUACION," + 
			" COD_TIPO_BENEFICIARIO, IPF_TITULAR, NAF_TITULAR, NUMERO_SECUENCIA, FECHA_NACIMIENTO_RAW," + 
			" IPF_ANTERIOR, COD_USUARIO_SNS, CODIGO_BADAS, MOTIVO_BAJA, PROTEGIDA," + 
			" INDICADOR_DOBLE_COBERTURA, CIP_MUTUALISTA, CIP_MUTUALISTA_TITULAR, INDICADOR_CONVENIO_RURAL," + 
			" PRESTADORA_PRIVADA FROM Z_INSS_MOV_SEP_25 z WHERE z.COD_TIPO_ASEGURADO = 'T' AND z.TIPO_MOVIMIENTO <> 'C'" + 
			" AND z.IPF IN ( SELECT ipf FROM Z_INSS_MOV_SEP_25 z WHERE z.IPF IS NOT NULL AND z.COD_TIPO_ASEGURADO = 'T'" + 
			" AND z.TIPO_MOVIMIENTO <> 'C' GROUP BY ipf HAVING COUNT (*) > 1) ORDER BY ipf) a", nativeQuery = true)
	List<String> ipfRepetidosDatos();
	
	
}


