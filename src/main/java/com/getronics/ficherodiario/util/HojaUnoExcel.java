package com.getronics.ficherodiario.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HojaUnoExcel {
	
	static Date date = new Date();
	static SimpleDateFormat formato = new SimpleDateFormat("yyyy_MM_dd");
	static String fechaActual = formato.format(date);
	
	private static final String EXCEL_FILE_PATH="C:/Log/fichero_" + fechaActual +".xlsx";

	private Logger logHojaUno = LoggerFactory.getLogger(this.getClass());
	
	public void crearHojaExcel(List<String> tipoIdentificador,List<String> tipoMovimiento, List<String> protegidoTipAseguramientoAndTipMovimiento,
			List<String> registroIpfNulo,List<String> informeAltaCruzado,List<String> codTipoAsegurado,List<String> titDobleCobertura,List<String> beneDobleCobertura,
			List<String> tipoIdentificadorMutualistas, List<String> tipoMovimientoMutualista, List<String> protegidoTipAseguramientoAndTipMovimientoMutualistas,
			List<String> registrosIpfNuloMutualistas,List<String> informeAltCruzadoConSegSocialFinalMutualistas, List<String> codTipoAseguradoMutualistas) {
		
		/*"COD_TIPO_ASEGURADO","TIPO_MOVIMIENTO","IPF","DNI_NIE","PASAPORTE","NAF","NAF_SEC1","NAF_SEC2","NAF_SEC3","NAF_SEC4",
				"NAF_SEC5","NAF_SEC6","NAF_SEC7","NAF_SEC8","NAF_SEC9","INDICATIVO_NOMBRE","APELLIDOS_NOMBRE","APELLIDO1","APELLIDO2",
				"NOMBRE	NACIONALIDAD","FECHA_NACIMIENTO","SEXO","INDICATIVO_DOMICILIO","DOMICILIO","TIPO_ASEGURAMIENTO",
				"COD_INDICADOR_DE_FARMACIA","COD_SUBINDICADOR_DE_FARMACIA","COD_SITUACION","FECHA_EFECTO_SITUACION","COD_TIPO_BENEFICIARIO",
				"IPF_TITULAR","NAF_TITULAR","NUMERO_SECUENCIA","FECHA_NACIMIENTO_RAW","IPF_ANTERIOR","COD_USUARIO_SNS","CODIGO_BADAS",
				"MOTIVO_BAJA","PROTEGIDA","INDICADOR_DOBLE_COBERTURA","CIP_MUTUALISTA","CIP_MUTUALISTA_TITULAR","INDICADOR_CONVENIO_RURAL",
				"PRESTADORA_PRIVADA" */
		
		final String[] header= {"TIPO","TOTAL_","COD_TIPO_ASEGURADO_","TIPO_MOVIMIENTO_,","COD_SITUACION_","TOTAL_","COD_TIPO_ASEGURADO_","TIPO_MOVIMIENTO_	","PROTEGIDA","TOTAL_","COD_TIPO_ASEGURADO_","TIPO_MOVIMIENTO_",	
				"COD_SITUACION_","NUMERO_","TIPO_ASEGURAMIENTO_","TIPO_MOVIMIENTO_","TOTAL_","COD_TIPO_ASEGURADO_","TOTAL_","DOBLE_COBERTURA","TOTAL_",
				"DOBLE_COBERTURA_","TOTAL_","TIPO","TOTAL_","COD_TIPO_ASEGURADO_","TIPO_MOVIMIENTO_","COD_SITUACION_","TOTAL_","COD_TIPO_ASEGURADO_",
				"TIPO_MOVIMIENTO_","PROTEGIDA","TOTAL_","COD_TIPO_ASEGURADO_","TIPO_MOVIMIENTO_","COD_SITUACION_","NUMERO_","TIPO_ASEGURAMIENTO_","TIPO_MOVIMIENTO_","TOTAL_",
				"COD_TIPO_ASEGURADO_","TOTAL_","COD_TIPO_ASEGURADO_","MOTIVO_BAJA_","TOTAL_","TIPO_ASEGURAMIENTO_","NUMERO_","ENVIO_INSS","ESTADO EN EL SNS_","NUMERO_",	
				"COD_TIPO_ASEGURADO_","MOTIVO_BAJA_","TOTAL_","COD_ESTADO_","NUMERO_","INDICADOR_DE_FARMACIA_","SUBINDICADOR_","TOTAL_","TIPO_ASEGURAMIENTO_",	
				"INDICADOR_DE_FARMACIA_","SUBINDICADOR_","TOTAL_","COD_TIPO_ASEGURADO","TIPO_MOVIMIENTO","IPF","DNI_NIE","PASAPORTE","NAF","NAF_SEC1","NAF_SEC2",
				"NAF_SEC3","NAF_SEC4","NAF_SEC5","NAF_SEC6","NAF_SEC7","NAF_SEC8","NAF_SEC9","INDICATIVO_NOMBRE","APELLIDOS_NOMBRE","APELLIDO1","APELLIDO2",	
				"NOMBRE","NACIONALIDAD","FECHA_NACIMIENTO",	"SEXO",	"INDICATIVO_DOMICILIO","DOMICILIO",	"TIPO_ASEGURAMIENTO",	"COD_INDICADOR_DE_FARMACIA","COD_SUBINDICADOR_DE_FARMACIA",
				"COD_SITUACION",	"FECHA_EFECTO_SITUACION",	"COD_TIPO_BENEFICIARIO",	"IPF_TITULAR",	"NAF_TITULAR",	"NUMERO_SECUENCIA","FECHA_NACIMIENTO_RAW","IPF_ANTERIOR",
				"COD_USUARIO_SNS","CODIGO_BADAS","MOTIVO_BAJA","PROTEGIDA","INDICADOR_DOBLE_COBERTURA",	"CIP_MUTUALISTA","CIP_MUTUALISTA_TITULAR","INDICADOR_CONVENIO_RURAL",
				"PRESTADORA_PRIVADA", "NAF","COD_TIPO_ASEGURADO","TIPO_MOVIMIENTO","IPF","DNI_NIE","PASAPORTE","NAF_1","NAF_SEC1",
				"NAF_SEC2",	"NAF_SEC3","NAF_SEC4","NAF_SEC5","NAF_SEC6","NAF_SEC7","NAF_SEC8","NAF_SEC9","INDICATIVO_NOMBRE","APELLIDOS_NOMBRE","APELLIDO1","APELLIDO2","NOMBRE",
				"NACIONALIDAD","FECHA_NACIMIENTO","SEXO","INDICATIVO_DOMICILIO","DOMICILIO","TIPO_ASEGURAMIENTO","COD_INDICADOR_DE_FARMACIA",
				"COD_SUBINDICADOR_DE_FARMACIA","COD_SITUACION","FECHA_EFECTO_SITUACION","COD_TIPO_BENEFICIARIO",
				"IPF_TITULAR","NAF_TITULAR","NUMERO_SECUENCIA","FECHA_NACIMIENTO_RAW","IPF_ANTERIOR","COD_USUARIO_SNS","CODIGO_BADAS",
				"MOTIVO_BAJA","PROTEGIDA","INDICADOR_DOBLE_COBERTURA","CIP_MUTUALISTA","CIP_MUTUALISTA_TITULAR","INDICADOR_CONVENIO_RURAL",
				"PRESTADORA_PRIVADA","COD_TIPO_ASEGURADO","TIPO_MOVIMIENTO","IPF","DNI_NIE","PASAPORTE","NAF","NAF_SEC1","NAF_SEC2",
				"NAF_SEC3","NAF_SEC4","NAF_SEC5","NAF_SEC6","NAF_SEC7","NAF_SEC8","NAF_SEC9","INDICATIVO_NOMBRE",
				"APELLIDOS_NOMBRE","APELLIDO1","APELLIDO2","NOMBRE","NACIONALIDAD","FECHA_NACIMIENTO","SEXO","INDICATIVO_DOMICILIO",
				"DOMICILIO","TIPO_ASEGURAMIENTO","COD_INDICADOR_DE_FARMACIA","COD_SUBINDICADOR_DE_FARMACIA","COD_SITUACION",
				"FECHA_EFECTO_SITUACION","COD_TIPO_BENEFICIARIO","IPF_TITULAR","NAF_TITULAR","NUMERO_SECUENCIA","FECHA_NACIMIENTO_RAW",
				"IPF_ANTERIOR","COD_USUARIO_SNS","CODIGO_BADAS","MOTIVO_BAJA","PROTEGIDA","INDICADOR_DOBLE_COBERTURA","CIP_MUTUALISTA",
				"CIP_MUTUALISTA_TITULAR","INDICADOR_CONVENIO_RURAL","PRESTADORA_PRIVADA","COD_TIPO_ASEGURADO","TIPO_MOVIMIENTO","IPF","DNI_NIE",
				"PASAPORTE","NAF","NAF_SEC1","NAF_SEC2","NAF_SEC3","NAF_SEC4","NAF_SEC5","NAF_SEC6","NAF_SEC7","NAF_SEC8",
				"NAF_SEC9","INDICATIVO_NOMBRE","APELLIDOS_NOMBRE","APELLIDO1","APELLIDO2","NOMBRE","NACIONALIDAD","FECHA_NACIMIENTO",
				"SEXO","INDICATIVO_DOMICILIO","DOMICILIO","TIPO_ASEGURAMIENTO","COD_INDICADOR_DE_FARMACIA","COD_SUBINDICADOR_DE_FARMACIA",
				"COD_SITUACION","FECHA_EFECTO_SITUACION","COD_TIPO_BENEFICIARIO","IPF_TITULAR","NAF_TITULAR","NUMERO_SECUENCIA",
				"FECHA_NACIMIENTO_RAW","IPF_ANTERIOR","COD_USUARIO_SNS","CODIGO_BADAS","MOTIVO_BAJA","PROTEGIDA","INDICADOR_DOBLE_COBERTURA",
				"CIP_MUTUALISTA","CIP_MUTUALISTA_TITULAR","INDICADOR_CONVENIO_RURAL","PRESTADORA_PRIVADA","COD_TIPO_ASEGURADO",
				"TIPO_MOVIMIENTO","IPF","DNI_NIE","PASAPORTE","NAF","NAF_SEC1","NAF_SEC2","NAF_SEC3","NAF_SEC4","NAF_SEC5",
				"NAF_SEC6","NAF_SEC7","NAF_SEC8","NAF_SEC9","INDICATIVO_NOMBRE","APELLIDOS_NOMBRE","APELLIDO1","APELLIDO2",
				"NOMBRE","NACIONALIDAD","FECHA_NACIMIENTO","SEXO","INDICATIVO_DOMICILIO","DOMICILIO","TIPO_ASEGURAMIENTO",
				"COD_INDICADOR_DE_FARMACIA","COD_SUBINDICADOR_DE_FARMACIA","COD_SITUACION","FECHA_EFECTO_SITUACION","COD_TIPO_BENEFICIARIO",
				"IPF_TITULAR","NAF_TITULAR","NUMERO_SECUENCIA","FECHA_NACIMIENTO_RAW","IPF_ANTERIOR","COD_USUARIO_SNS","CODIGO_BADAS",
				"MOTIVO_BAJA","PROTEGIDA","INDICADOR_DOBLE_COBERTURA","CIP_MUTUALISTA","CIP_MUTUALISTA_TITULAR","INDICADOR_CONVENIO_RURAL",
				"PRESTADORA_PRIVADA","COD_TIPO_ASEGURADO","TIPO_MOVIMIENTO","IPF","DNI_NIE","PASAPORTE","NAF","NAF_SEC1","NAF_SEC2","NAF_SEC3",
				"NAF_SEC4","NAF_SEC5","NAF_SEC6","NAF_SEC7","NAF_SEC8","NAF_SEC9","INDICATIVO_NOMBRE","APELLIDOS_NOMBRE","APELLIDO1",
				"APELLIDO2","NOMBRE","NACIONALIDAD","FECHA_NACIMIENTO","SEXO","INDICATIVO_DOMICILIO","DOMICILIO","TIPO_ASEGURAMIENTO",
				"COD_INDICADOR_DE_FARMACIA","COD_SUBINDICADOR_DE_FARMACIA","COD_SITUACION","FECHA_EFECTO_SITUACION","COD_TIPO_BENEFICIARIO",
				"IPF_TITULAR","NAF_TITULAR","NUMERO_SECUENCIA","FECHA_NACIMIENTO_RAW","IPF_ANTERIOR","COD_USUARIO_SNS","CODIGO_BADAS",
				"MOTIVO_BAJA","PROTEGIDA","INDICADOR_DOBLE_COBERTURA","CIP_MUTUALISTA","CIP_MUTUALISTA_TITULAR","INDICADOR_CONVENIO_RURAL",
				"PRESTADORA_PRIVADA","DOBLE_COBERTURA","TOTAL_","DOBLE_COBERTURA_","TOTAL_","INDICADOR_CONVENIO_RURAL","TOTAL_","INDICADOR_CONVENIO_RURAL_",
				"TOTAL_","REGUPRIV_REGUPUB","REGUPRIV_IRREGUPUB","REGUPRIV_NOEXISTE","TOTAL_ESPERADO_REG","IRREGUPRIV_REGUPUB","IRREGUPRIV_IRREGUPUB",
				"IRREGUPRIV_NOEXISTE","TOTAL_ESPERADO_IRREG","COD_TIPO_ASEGURADO","TIPO_MOVIMIENTO","IPF","DNI_NIE","PASAPORTE","NAF","NAF_SEC1",
				"NAF_SEC2","NAF_SEC3","NAF_SEC4","NAF_SEC5","NAF_SEC6","NAF_SEC7","NAF_SEC8","NAF_SEC9","INDICATIVO_NOMBRE",
				"APELLIDOS_NOMBRE","APELLIDO1","APELLIDO2","NOMBRE","NACIONALIDAD","FECHA_NACIMIENTO","SEXO","INDICATIVO_DOMICILIO",
				"DOMICILIO","TIPO_ASEGURAMIENTO","COD_INDICADOR_DE_FARMACIA","COD_SUBINDICADOR_DE_FARMACIA","COD_SITUACION",
				"FECHA_EFECTO_SITUACION","COD_TIPO_BENEFICIARIO","IPF_TITULAR","NAF_TITULAR","NUMERO_SECUENCIA","FECHA_NACIMIENTO_RAW",
				"IPF_ANTERIOR","COD_USUARIO_SNS","CODIGO_BADAS","MOTIVO_BAJA","PROTEGIDA","INDICADOR_DOBLE_COBERTURA","CIP_MUTUALISTA",
				"CIP_MUTUALISTA_TITULAR","INDICADOR_CONVENIO_RURAL","PRESTADORA_PRIVADA","BAJAPRIV_BAJAPUB","BAJAPRIV_BAJANOEXISTE"};
		
		Workbook workbook  = new XSSFWorkbook();
		
		// SE INDICA EL NOMBRE DE LA PÁGINA DEL EXCEL
		Sheet sheet = workbook.createSheet("DATOS");
		
		// Se crea la fila de la cabecera
		Row row = sheet.createRow(0);
		
		// Se rellena la fila con los campos de la cabecera.
	    for(int i = 0; i < header.length; i++) {
	       sheet.setColumnWidth(i, 20*256);
	       Cell cell = row.createCell(i);
	       cell.setCellValue(header[i]);
	      // cell.setCellStyle(style);
	    } 
		
	    int rowNum = 1;	       
	    
	    for(String a: tipoIdentificador) {
	    	String [] separar = a.split(",");
	    	row = sheet.createRow(rowNum++);
	        row.createCell(0).setCellValue(separar[0]);
	        row.createCell(1).setCellValue(separar[1]);
	    }
	    	 
	    
	    // Se crea un indice para comparar con las filas.
	    int indexMovimiento = 1;
	    
		for (String a : tipoMovimiento) {
			String[] separar = a.split(",");

			// Si es menor o igual se hace obtiene las filas ya creadas
			if(indexMovimiento <= tipoIdentificador.size())
				row = sheet.getRow(indexMovimiento++);
			else
			// En caso de que no exista la fila se crea. 
				row = sheet.createRow(indexMovimiento++);
			
			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(2).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(3).setCellValue(separar[1]);
			}
			if (separar[2].compareTo("null") == 0) {
				separar[2] = "";
			} else {
				row.createCell(4).setCellValue(separar[2]);
			}
			if (separar[3].compareTo("null") == 0) {
				separar[3] = "";
			} else {
				row.createCell(5).setCellValue(separar[3]);
			}
		}
	    
	   
		int indexProtegidoTip = 1;
	    
		for (String a : protegidoTipAseguramientoAndTipMovimiento) {
			String[] separar = a.split(",");

			// Si es menor o igual se hace obtiene las filas ya creadas
			if(indexProtegidoTip <= tipoIdentificador.size() || indexProtegidoTip<= tipoMovimiento.size())
				row = sheet.getRow(indexProtegidoTip++);
			else
			// En caso de que no exista la fila se crea. 
				row = sheet.createRow(indexProtegidoTip++);
			
			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(6).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(7).setCellValue(separar[1]);
			}
			if (separar[2].compareTo("null") == 0) {
				separar[2] = "";
			} else {
				row.createCell(8).setCellValue(separar[2]);
			}
			if (separar[3].compareTo("null") == 0) {
				separar[3] = "";
			} else {
				row.createCell(9).setCellValue(separar[3]);
			}
		}
		
		int indexRegiIpfNulo = 1;
	    
		for (String a : registroIpfNulo) {
			String[] separar = a.split(",");

			// Si es menor o igual se hace obtiene las filas ya creadas
			if(indexRegiIpfNulo <= tipoIdentificador.size() || indexRegiIpfNulo<= tipoMovimiento.size() ||
					indexRegiIpfNulo <= protegidoTipAseguramientoAndTipMovimiento.size())
				
				row = sheet.getRow(indexRegiIpfNulo++);
			else
			// En caso de que no exista la fila se crea. 
				row = sheet.createRow(indexRegiIpfNulo++);
			
			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(10).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(11).setCellValue(separar[1]);
			}
			if (separar[2].compareTo("null") == 0) {
				separar[2] = "";
			} else {
				row.createCell(12).setCellValue(separar[2]);
			}
			if (separar[3].compareTo("null") == 0) {
				separar[3] = "";
			} else {
				row.createCell(13).setCellValue(separar[3]);
			}
		}				
		
		int indexInforAltCruzado = 1;
	    
		for (String a : informeAltaCruzado) {
			String[] separar = a.split(",");

			// Si es menor o igual se hace obtiene las filas ya creadas
			if(indexInforAltCruzado <= tipoIdentificador.size() || indexInforAltCruzado<= tipoMovimiento.size() ||
					indexInforAltCruzado <= protegidoTipAseguramientoAndTipMovimiento.size() || indexInforAltCruzado <= registroIpfNulo.size())
				
				row = sheet.getRow(indexInforAltCruzado++);
			else
			// En caso de que no exista la fila se crea. 
				row = sheet.createRow(indexInforAltCruzado++);
			
			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(14).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(15).setCellValue(separar[1]);
			}
			if (separar[2].compareTo("null") == 0) {
				separar[2] = "";
			} else {
				row.createCell(16).setCellValue(separar[2]);
			}
		}
		
		int indexCodTipAseg = 1;
	    
		for (String a : codTipoAsegurado) {
			String[] separar = a.split(",");

			// Si es menor o igual se hace obtiene las filas ya creadas
			if(indexCodTipAseg <= tipoIdentificador.size() || indexCodTipAseg<= tipoMovimiento.size() || indexCodTipAseg <= protegidoTipAseguramientoAndTipMovimiento.size() 
					|| indexCodTipAseg <= registroIpfNulo.size() || indexCodTipAseg <=informeAltaCruzado.size())
				
				row = sheet.getRow(indexCodTipAseg++);
			else
			// En caso de que no exista la fila se crea. 
				row = sheet.createRow(indexCodTipAseg++);
			
			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(17).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(18).setCellValue(separar[1]);
			}
		}		
				
		int indexTitDoCob = 1;
	    
		for (String a : titDobleCobertura) {
			String[] separar = a.split(",");

			// Si es menor o igual se hace obtiene las filas ya creadas
			if(indexTitDoCob <= tipoIdentificador.size() || indexTitDoCob<= tipoMovimiento.size() || indexTitDoCob <= protegidoTipAseguramientoAndTipMovimiento.size() 
					|| indexTitDoCob <= registroIpfNulo.size() || indexTitDoCob <=informeAltaCruzado.size() || indexTitDoCob <= codTipoAsegurado.size())
				
				row = sheet.getRow(indexTitDoCob++);
			else
			// En caso de que no exista la fila se crea. 
				row = sheet.createRow(indexTitDoCob++);
			
			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(19).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(20).setCellValue(separar[1]);
			}
		}	
		
	
		int indexBeneDobCob = 1;
	    
		for (String a : beneDobleCobertura) {
			String[] separar = a.split(",");

			// Si es menor o igual se hace obtiene las filas ya creadas
			if(indexBeneDobCob <= tipoIdentificador.size() || indexBeneDobCob<= tipoMovimiento.size() || indexBeneDobCob <= protegidoTipAseguramientoAndTipMovimiento.size() 
					|| indexBeneDobCob <= registroIpfNulo.size() || indexBeneDobCob <=informeAltaCruzado.size() || indexBeneDobCob <= codTipoAsegurado.size() ||
					indexBeneDobCob <= titDobleCobertura.size())
				
				row = sheet.getRow(indexBeneDobCob++);			
			else
			// En caso de que no exista la fila se crea. 
				row = sheet.createRow(indexBeneDobCob++);
			
			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(21).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(22).setCellValue(separar[1]);
			}
		}
		
		/* QUERYS MUTUALISTAS. */
		
		int indexTiIdMut = 1;
	    
		for (String a : tipoIdentificadorMutualistas) {
			String[] separar = a.split(",");

			// Si es menor o igual se hace obtiene las filas ya creadas
			if(indexTiIdMut <= tipoIdentificador.size() || indexTiIdMut<= tipoMovimiento.size() || indexTiIdMut <= protegidoTipAseguramientoAndTipMovimiento.size() 
					|| indexTiIdMut <= registroIpfNulo.size() || indexTiIdMut <=informeAltaCruzado.size() || indexTiIdMut <= codTipoAsegurado.size() ||
							indexTiIdMut <= titDobleCobertura.size() || indexTiIdMut <= beneDobleCobertura.size())
				
				row = sheet.getRow(indexTiIdMut++);			
			else
			// En caso de que no exista la fila se crea. 
				row = sheet.createRow(indexTiIdMut++);
			
			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(23).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(24).setCellValue(separar[1]);
			}
		}
		
		
		
		/*   ,
		protegidoTipAseguramientoAndTipMovimientoMutualistas, registrosIpfNuloMutualistas,
		informeAltCruzadoConSegSocialFinalMutualistas,  codTipoAseguradoMutualistas */
	
		
		int indexTipMoviMut = 1;
	    
		for (String a : tipoMovimientoMutualista) {
			String[] separar = a.split(",");

			// Si es menor o igual se hace obtiene las filas ya creadas
			if(indexTipMoviMut <= tipoIdentificador.size() || indexTipMoviMut<= tipoMovimiento.size() || indexTipMoviMut <= protegidoTipAseguramientoAndTipMovimiento.size() 
					|| indexTipMoviMut <= registroIpfNulo.size() || indexTipMoviMut <=informeAltaCruzado.size() || indexTipMoviMut <= codTipoAsegurado.size() ||
							indexTipMoviMut <= titDobleCobertura.size() || indexTipMoviMut <= beneDobleCobertura.size() || indexTipMoviMut <= tipoIdentificadorMutualistas.size())
				
				row = sheet.getRow(indexTipMoviMut++);			
			else
			// En caso de que no exista la fila se crea. 
				row = sheet.createRow(indexTipMoviMut++);
			
			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(25).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(26).setCellValue(separar[1]);
			}
			if (separar[2].compareTo("null") == 0) {
				separar[2] = "";
			} else {
				row.createCell(27).setCellValue(separar[2]);
			}
			if (separar[3].compareTo("null") == 0) {
				separar[3] = "";
			} else {
				row.createCell(28).setCellValue(separar[3]);
			}
		}		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	    FileOutputStream outputStream = null;

		try {
			outputStream = new FileOutputStream(EXCEL_FILE_PATH);
			workbook.write(outputStream);
		} catch (IOException e) {
			logHojaUno.error(e.getMessage(), e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
					workbook.close();
				} catch (IOException e) {
					logHojaUno.error(e.getMessage(), e);
				}
			}
		}

	}

}
