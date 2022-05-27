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
import org.springframework.stereotype.Component;

import com.getronics.ficherodiario.models.entity.CrucesUrgentes;

@Component
public class HojaUnoExcel {

	static Date date = new Date();
	static SimpleDateFormat formato = new SimpleDateFormat("yyyy_MM_dd");
	static String fechaActual = formato.format(date);

	private static final String EXCEL_FILE_PATH = "C:/Log/fichero_" + fechaActual + ".xlsx";

	private Logger logHojaUno = LoggerFactory.getLogger(this.getClass());

	public void crearHojaExcel(List<String> tipoIdentificador, List<String> tipoMovimiento,
			List<String> protegidoTipAseguramientoAndTipMovimiento, List<String> registroIpfNulo,
			List<String> informeAltaCruzado, List<String> codTipoAsegurado, List<String> titDobleCobertura,
			List<String> beneDobleCobertura, List<String> tipoIdentificadorMutualistas,
			List<String> tipoMovimientoMutualista, List<String> protegidoTipAseguramientoAndTipMovimientoMutualistas,
			List<String> registrosIpfNuloMutualistas, List<String> informeAltCruzadoConSegSocialFinalMutualistas,
			List<String> codTipoAseguradoMutualistas, List<String> regimenGeneral, List<String> bajaTitulares,
			List<String> totalEnvioInssAv, List<String> bajasVinculadosSns, List<String> mutualistasPrivados,
			List<String> bajaPorDefuncion, List<String> aseguramiento, List<String> indicadorFarmacia, List<String> codigoBadasRepetido,
			List<String> altasSinNaf, List<String> cambiosIpf, List<String> ipfNuevoExisteInssTit,List<String> ipfAnteriorNoExisteInssMut,
			List<String> ipfNuevoExiteInssMut, List<String> titularesDobleCobertura,List<String> beneficiarioDobleCoberturaMutualistas,List<String> titularesIndicadorConvenioRural,		
			List<String> beneficiadioIndicadorConvenioRural,List<String> privadoRegularPublico,List<String> privadoIrregularPublico,			
			List<String> privadoNoExiste,List<String> altaModificacionTitularesDobleCobertura,List<String> irregularPrivadoRegularPublico,
			List<String> irregularPrivadoIrregularPublico,List<String> irregularPrivadoNoExiste,List<String> altaModificaTitualresDobleCoberturaDos,
			List<String> listadoRegistroNoExiste,List<String> listadoRegistroNoExisteDos,List<String> bajaPorDefuncionPrivadoPublico,
			List<String> bajaPorDefuncionPrivadoNoExiste) {

		/*
		 * "COD_TIPO_ASEGURADO","TIPO_MOVIMIENTO","IPF","DNI_NIE","PASAPORTE","NAF",
		 * "NAF_SEC1","NAF_SEC2","NAF_SEC3","NAF_SEC4",
		 * "NAF_SEC5","NAF_SEC6","NAF_SEC7","NAF_SEC8","NAF_SEC9","INDICATIVO_NOMBRE",
		 * "APELLIDOS_NOMBRE","APELLIDO1","APELLIDO2",
		 * "NOMBRE	NACIONALIDAD","FECHA_NACIMIENTO","SEXO","INDICATIVO_DOMICILIO",
		 * "DOMICILIO","TIPO_ASEGURAMIENTO",
		 * "COD_INDICADOR_DE_FARMACIA","COD_SUBINDICADOR_DE_FARMACIA","COD_SITUACION",
		 * "FECHA_EFECTO_SITUACION","COD_TIPO_BENEFICIARIO",
		 * "IPF_TITULAR","NAF_TITULAR","NUMERO_SECUENCIA","FECHA_NACIMIENTO_RAW",
		 * "IPF_ANTERIOR","COD_USUARIO_SNS","CODIGO_BADAS",
		 * "MOTIVO_BAJA","PROTEGIDA","INDICADOR_DOBLE_COBERTURA","CIP_MUTUALISTA",
		 * "CIP_MUTUALISTA_TITULAR","INDICADOR_CONVENIO_RURAL", "PRESTADORA_PRIVADA"
		 */

		final String[] header = { "TIPO", "TOTAL_", "COD_TIPO_ASEGURADO_", "TIPO_MOVIMIENTO_,", "COD_SITUACION_",
				"TOTAL_", "COD_TIPO_ASEGURADO_", "TIPO_MOVIMIENTO_	", "PROTEGIDA", "TOTAL_", "COD_TIPO_ASEGURADO_",
				"TIPO_MOVIMIENTO_", "COD_SITUACION_", "NUMERO_", "TIPO_ASEGURAMIENTO_", "TIPO_MOVIMIENTO_", "TOTAL_",
				"COD_TIPO_ASEGURADO_", "TOTAL_", "DOBLE_COBERTURA", "TOTAL_", "DOBLE_COBERTURA_", "TOTAL_", "TIPO",
				"TOTAL_", "COD_TIPO_ASEGURADO_", "TIPO_MOVIMIENTO_", "COD_SITUACION_", "TOTAL_", "COD_TIPO_ASEGURADO_",
				"TIPO_MOVIMIENTO_", "PROTEGIDA", "TOTAL_", "COD_TIPO_ASEGURADO_", "TIPO_MOVIMIENTO_", "COD_SITUACION_",
				"NUMERO_", "TIPO_ASEGURAMIENTO_", "TIPO_MOVIMIENTO_", "TOTAL_", "COD_TIPO_ASEGURADO_", "TOTAL_",
				"COD_TIPO_ASEGURADO_", "MOTIVO_BAJA_", "TOTAL_", "TIPO_ASEGURAMIENTO_", "NUMERO_", "ENVIO_INSS",
				"ESTADO EN EL SNS_", "NUMERO_", "COD_TIPO_ASEGURADO_", "MOTIVO_BAJA_", "TOTAL_", "COD_ESTADO_",
				"NUMERO_", "INDICADOR_DE_FARMACIA_", "SUBINDICADOR_", "TOTAL_", "TIPO_ASEGURAMIENTO_",
				"INDICADOR_DE_FARMACIA_", "SUBINDICADOR_", "TOTAL_", "COD_TIPO_ASEGURADO", "TIPO_MOVIMIENTO", "IPF",
				"DNI_NIE", "PASAPORTE", "NAF", "NAF_SEC1", "NAF_SEC2", "NAF_SEC3", "NAF_SEC4", "NAF_SEC5", "NAF_SEC6",
				"NAF_SEC7", "NAF_SEC8", "NAF_SEC9", "INDICATIVO_NOMBRE", "APELLIDOS_NOMBRE", "APELLIDO1", "APELLIDO2",
				"NOMBRE", "NACIONALIDAD", "FECHA_NACIMIENTO", "SEXO", "INDICATIVO_DOMICILIO", "DOMICILIO",
				"TIPO_ASEGURAMIENTO", "COD_INDICADOR_DE_FARMACIA", "COD_SUBINDICADOR_DE_FARMACIA", "COD_SITUACION",
				"FECHA_EFECTO_SITUACION", "COD_TIPO_BENEFICIARIO", "IPF_TITULAR", "NAF_TITULAR", "NUMERO_SECUENCIA",
				"FECHA_NACIMIENTO_RAW", "IPF_ANTERIOR", "COD_USUARIO_SNS", "CODIGO_BADAS", "MOTIVO_BAJA", "PROTEGIDA",
				"INDICADOR_DOBLE_COBERTURA", "CIP_MUTUALISTA", "CIP_MUTUALISTA_TITULAR", "INDICADOR_CONVENIO_RURAL",
				"PRESTADORA_PRIVADA", "NAF", "COD_TIPO_ASEGURADO", "TIPO_MOVIMIENTO", "IPF", "DNI_NIE", "PASAPORTE",
				"NAF_1", "NAF_SEC1", "NAF_SEC2", "NAF_SEC3", "NAF_SEC4", "NAF_SEC5", "NAF_SEC6", "NAF_SEC7", "NAF_SEC8",
				"NAF_SEC9", "INDICATIVO_NOMBRE", "APELLIDOS_NOMBRE", "APELLIDO1", "APELLIDO2", "NOMBRE", "NACIONALIDAD",
				"FECHA_NACIMIENTO", "SEXO", "INDICATIVO_DOMICILIO", "DOMICILIO", "TIPO_ASEGURAMIENTO",
				"COD_INDICADOR_DE_FARMACIA", "COD_SUBINDICADOR_DE_FARMACIA", "COD_SITUACION", "FECHA_EFECTO_SITUACION",
				"COD_TIPO_BENEFICIARIO", "IPF_TITULAR", "NAF_TITULAR", "NUMERO_SECUENCIA", "FECHA_NACIMIENTO_RAW",
				"IPF_ANTERIOR", "COD_USUARIO_SNS", "CODIGO_BADAS", "MOTIVO_BAJA", "PROTEGIDA",
				"INDICADOR_DOBLE_COBERTURA", "CIP_MUTUALISTA", "CIP_MUTUALISTA_TITULAR", "INDICADOR_CONVENIO_RURAL",
				"PRESTADORA_PRIVADA", "COD_TIPO_ASEGURADO", "TIPO_MOVIMIENTO", "IPF", "DNI_NIE", "PASAPORTE", "NAF",
				"NAF_SEC1", "NAF_SEC2", "NAF_SEC3", "NAF_SEC4", "NAF_SEC5", "NAF_SEC6", "NAF_SEC7", "NAF_SEC8",
				"NAF_SEC9", "INDICATIVO_NOMBRE", "APELLIDOS_NOMBRE", "APELLIDO1", "APELLIDO2", "NOMBRE", "NACIONALIDAD",
				"FECHA_NACIMIENTO", "SEXO", "INDICATIVO_DOMICILIO", "DOMICILIO", "TIPO_ASEGURAMIENTO",
				"COD_INDICADOR_DE_FARMACIA", "COD_SUBINDICADOR_DE_FARMACIA", "COD_SITUACION", "FECHA_EFECTO_SITUACION",
				"COD_TIPO_BENEFICIARIO", "IPF_TITULAR", "NAF_TITULAR", "NUMERO_SECUENCIA", "FECHA_NACIMIENTO_RAW",
				"IPF_ANTERIOR", "COD_USUARIO_SNS", "CODIGO_BADAS", "MOTIVO_BAJA", "PROTEGIDA",
				"INDICADOR_DOBLE_COBERTURA", "CIP_MUTUALISTA", "CIP_MUTUALISTA_TITULAR", "INDICADOR_CONVENIO_RURAL",
				"PRESTADORA_PRIVADA", "COD_TIPO_ASEGURADO", "TIPO_MOVIMIENTO", "IPF", "DNI_NIE", "PASAPORTE", "NAF",
				"NAF_SEC1", "NAF_SEC2", "NAF_SEC3", "NAF_SEC4", "NAF_SEC5", "NAF_SEC6", "NAF_SEC7", "NAF_SEC8",
				"NAF_SEC9", "INDICATIVO_NOMBRE", "APELLIDOS_NOMBRE", "APELLIDO1", "APELLIDO2", "NOMBRE", "NACIONALIDAD",
				"FECHA_NACIMIENTO", "SEXO", "INDICATIVO_DOMICILIO", "DOMICILIO", "TIPO_ASEGURAMIENTO",
				"COD_INDICADOR_DE_FARMACIA", "COD_SUBINDICADOR_DE_FARMACIA", "COD_SITUACION", "FECHA_EFECTO_SITUACION",
				"COD_TIPO_BENEFICIARIO", "IPF_TITULAR", "NAF_TITULAR", "NUMERO_SECUENCIA", "FECHA_NACIMIENTO_RAW",
				"IPF_ANTERIOR", "COD_USUARIO_SNS", "CODIGO_BADAS", "MOTIVO_BAJA", "PROTEGIDA",
				"INDICADOR_DOBLE_COBERTURA", "CIP_MUTUALISTA", "CIP_MUTUALISTA_TITULAR", "INDICADOR_CONVENIO_RURAL",
				"PRESTADORA_PRIVADA", "COD_TIPO_ASEGURADO", "TIPO_MOVIMIENTO", "IPF", "DNI_NIE", "PASAPORTE", "NAF",
				"NAF_SEC1", "NAF_SEC2", "NAF_SEC3", "NAF_SEC4", "NAF_SEC5", "NAF_SEC6", "NAF_SEC7", "NAF_SEC8",
				"NAF_SEC9", "INDICATIVO_NOMBRE", "APELLIDOS_NOMBRE", "APELLIDO1", "APELLIDO2", "NOMBRE", "NACIONALIDAD",
				"FECHA_NACIMIENTO", "SEXO", "INDICATIVO_DOMICILIO", "DOMICILIO", "TIPO_ASEGURAMIENTO",
				"COD_INDICADOR_DE_FARMACIA", "COD_SUBINDICADOR_DE_FARMACIA", "COD_SITUACION", "FECHA_EFECTO_SITUACION",
				"COD_TIPO_BENEFICIARIO", "IPF_TITULAR", "NAF_TITULAR", "NUMERO_SECUENCIA", "FECHA_NACIMIENTO_RAW",
				"IPF_ANTERIOR", "COD_USUARIO_SNS", "CODIGO_BADAS", "MOTIVO_BAJA", "PROTEGIDA",
				"INDICADOR_DOBLE_COBERTURA", "CIP_MUTUALISTA", "CIP_MUTUALISTA_TITULAR", "INDICADOR_CONVENIO_RURAL",
				"PRESTADORA_PRIVADA", "COD_TIPO_ASEGURADO", "TIPO_MOVIMIENTO", "IPF", "DNI_NIE", "PASAPORTE", "NAF",
				"NAF_SEC1", "NAF_SEC2", "NAF_SEC3", "NAF_SEC4", "NAF_SEC5", "NAF_SEC6", "NAF_SEC7", "NAF_SEC8",
				"NAF_SEC9", "INDICATIVO_NOMBRE", "APELLIDOS_NOMBRE", "APELLIDO1", "APELLIDO2", "NOMBRE", "NACIONALIDAD",
				"FECHA_NACIMIENTO", "SEXO", "INDICATIVO_DOMICILIO", "DOMICILIO", "TIPO_ASEGURAMIENTO",
				"COD_INDICADOR_DE_FARMACIA", "COD_SUBINDICADOR_DE_FARMACIA", "COD_SITUACION", "FECHA_EFECTO_SITUACION",
				"COD_TIPO_BENEFICIARIO", "IPF_TITULAR", "NAF_TITULAR", "NUMERO_SECUENCIA", "FECHA_NACIMIENTO_RAW",
				"IPF_ANTERIOR", "COD_USUARIO_SNS", "CODIGO_BADAS", "MOTIVO_BAJA", "PROTEGIDA",
				"INDICADOR_DOBLE_COBERTURA", "CIP_MUTUALISTA", "CIP_MUTUALISTA_TITULAR", "INDICADOR_CONVENIO_RURAL",
				"PRESTADORA_PRIVADA", "DOBLE_COBERTURA", "TOTAL_", "DOBLE_COBERTURA_", "TOTAL_",
				"INDICADOR_CONVENIO_RURAL", "TOTAL_", "INDICADOR_CONVENIO_RURAL_", "TOTAL_", "REGUPRIV_REGUPUB",
				"REGUPRIV_IRREGUPUB", "REGUPRIV_NOEXISTE", "TOTAL_ESPERADO_REG", "IRREGUPRIV_REGUPUB",
				"IRREGUPRIV_IRREGUPUB", "IRREGUPRIV_NOEXISTE", "TOTAL_ESPERADO_IRREG", "COD_TIPO_ASEGURADO",
				"TIPO_MOVIMIENTO", "IPF", "DNI_NIE", "PASAPORTE", "NAF", "NAF_SEC1", "NAF_SEC2", "NAF_SEC3", "NAF_SEC4",
				"NAF_SEC5", "NAF_SEC6", "NAF_SEC7", "NAF_SEC8", "NAF_SEC9", "INDICATIVO_NOMBRE", "APELLIDOS_NOMBRE",
				"APELLIDO1", "APELLIDO2", "NOMBRE", "NACIONALIDAD", "FECHA_NACIMIENTO", "SEXO", "INDICATIVO_DOMICILIO",
				"DOMICILIO", "TIPO_ASEGURAMIENTO", "COD_INDICADOR_DE_FARMACIA", "COD_SUBINDICADOR_DE_FARMACIA",
				"COD_SITUACION", "FECHA_EFECTO_SITUACION", "COD_TIPO_BENEFICIARIO", "IPF_TITULAR", "NAF_TITULAR",
				"NUMERO_SECUENCIA", "FECHA_NACIMIENTO_RAW", "IPF_ANTERIOR", "COD_USUARIO_SNS", "CODIGO_BADAS",
				"MOTIVO_BAJA", "PROTEGIDA", "INDICADOR_DOBLE_COBERTURA", "CIP_MUTUALISTA", "CIP_MUTUALISTA_TITULAR",
				"INDICADOR_CONVENIO_RURAL", "PRESTADORA_PRIVADA", "BAJAPRIV_BAJAPUB", "BAJAPRIV_BAJANOEXISTE" };

		Workbook workbook = new XSSFWorkbook();

		// SE INDICA EL NOMBRE DE LA PÁGINA DEL EXCEL
		Sheet sheet = workbook.createSheet("DATOS");

		// Se crea la fila de la cabecera
		Row row = sheet.createRow(0);

		// Se rellena la fila con los campos de la cabecera.
		for (int i = 0; i < header.length; i++) {
			sheet.setColumnWidth(i, 20 * 256);
			Cell cell = row.createCell(i);
			cell.setCellValue(header[i]);
			// cell.setCellStyle(style);
		}

		int rowNum = 1;
		int celda;
		int ultimaCelda = 0;

		for (String a : tipoIdentificador) {
			String[] separar = a.split(",");
			row = sheet.createRow(rowNum++);

			int i;

			for (i = 0; i < separar.length; i++) {
				boolean isNumeric = separar[i].chars().allMatch( Character::isDigit );
				if(isNumeric == true){
					Integer n = Integer.parseInt(separar[i]);
					row.createCell(i).setCellValue(n);
				}else {				
				row.createCell(i).setCellValue(separar[i]);
				}
			}
			ultimaCelda = i;
		}
		
		// Se crea un indice para comparar con las filas.
		int indexMovimiento = 1;
		int j = 0;
		
		for (String a : tipoMovimiento) {
			String[] separar = a.split(",");

			int i;

			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexMovimiento <= tipoIdentificador.size())
				row = sheet.getRow(indexMovimiento++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexMovimiento++);

			for (j = ultimaCelda; j <= separar.length; j++) {
				for (i = 0; i < separar.length; i++) {

					boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

					if (separar[i].compareTo("null") == 0) {
						separar[i] = "";
					} else {
						if (isNumeric == true) {
							Integer n = Integer.parseInt(separar[i]);
							row.createCell(j).setCellValue(n);
						} else {
							row.createCell(j).setCellValue(separar[i]);
						}
					}
					j++;
				}
			}
		}
		ultimaCelda = j-1;		
		celda = ultimaCelda;

		int indexProtegidoTip = 1;

		for (String a : protegidoTipAseguramientoAndTipMovimiento) {
			String[] separar = a.split(",");
			
			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexProtegidoTip <= tipoIdentificador.size() || indexProtegidoTip <= tipoMovimiento.size())
				row = sheet.getRow(indexProtegidoTip++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexProtegidoTip++);

			for (j = ultimaCelda; j > separar.length; j++) {
				for (i = 0; i < separar.length; i++) {

					boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

					if (separar[i].compareTo("null") == 0) {
						separar[i] = "";
					} else {
						if (isNumeric == true) {
							Integer n = Integer.parseInt(separar[i]);
							row.createCell(j).setCellValue(n);
						} else {
							row.createCell(j).setCellValue(separar[i]);
						}
					}
					j++;
				}
				j = 0;
			}
			celda = ultimaCelda+separar.length;
		}
		ultimaCelda = celda;

		
		int indexRegiIpfNulo = 1;

		for (String a : registroIpfNulo) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexRegiIpfNulo <= tipoIdentificador.size() || indexRegiIpfNulo <= tipoMovimiento.size()
					|| indexRegiIpfNulo <= protegidoTipAseguramientoAndTipMovimiento.size())

				row = sheet.getRow(indexRegiIpfNulo++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexRegiIpfNulo++);

			for (j = ultimaCelda; j > separar.length; j++) {
				for (i = 0; i < separar.length; i++) {

					boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

					if (separar[i].compareTo("null") == 0) {
						separar[i] = "";
					} else {
						if (isNumeric == true) {
							Integer n = Integer.parseInt(separar[i]);
							row.createCell(j).setCellValue(n);
						} else {
							row.createCell(j).setCellValue(separar[i]);
						}
					}
					j++;
				}
				j = 0;
			}
			celda = ultimaCelda+separar.length;
		}
		ultimaCelda = celda;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		int indexInforAltCruzado = 1;

		for (String a : informeAltaCruzado) {
			String[] separar = a.split(",");
			
			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexInforAltCruzado <= tipoIdentificador.size() || indexInforAltCruzado <= tipoMovimiento.size()
					|| indexInforAltCruzado <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexInforAltCruzado <= registroIpfNulo.size())

				row = sheet.getRow(indexInforAltCruzado++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexInforAltCruzado++);

			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(14).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(15).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(16).setCellValue(separar[i]);
			}
		}

		int indexCodTipAseg = 1;

		for (String a : codTipoAsegurado) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexCodTipAseg <= tipoIdentificador.size() || indexCodTipAseg <= tipoMovimiento.size()
					|| indexCodTipAseg <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexCodTipAseg <= registroIpfNulo.size() || indexCodTipAseg <= informeAltaCruzado.size())

				row = sheet.getRow(indexCodTipAseg++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexCodTipAseg++);

			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(17).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(18).setCellValue(separar[i]);
			}
		}

		int indexTitDoCob = 1;

		for (String a : titDobleCobertura) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexTitDoCob <= tipoIdentificador.size() || indexTitDoCob <= tipoMovimiento.size()
					|| indexTitDoCob <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexTitDoCob <= registroIpfNulo.size() || indexTitDoCob <= informeAltaCruzado.size()
					|| indexTitDoCob <= codTipoAsegurado.size())

				row = sheet.getRow(indexTitDoCob++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexTitDoCob++);

			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(19).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(20).setCellValue(separar[i]);
			}
		}

		int indexBeneDobCob = 1;

		for (String a : beneDobleCobertura) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexBeneDobCob <= tipoIdentificador.size() || indexBeneDobCob <= tipoMovimiento.size()
					|| indexBeneDobCob <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexBeneDobCob <= registroIpfNulo.size() || indexBeneDobCob <= informeAltaCruzado.size()
					|| indexBeneDobCob <= codTipoAsegurado.size() || indexBeneDobCob <= titDobleCobertura.size())

				row = sheet.getRow(indexBeneDobCob++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexBeneDobCob++);

			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(21).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(22).setCellValue(separar[i]);
			}
		}

		/* QUERYS MUTUALISTAS. */

		int indexTiIdMut = 1;

		for (String a : tipoIdentificadorMutualistas) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexTiIdMut <= tipoIdentificador.size() || indexTiIdMut <= tipoMovimiento.size()
					|| indexTiIdMut <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexTiIdMut <= registroIpfNulo.size() || indexTiIdMut <= informeAltaCruzado.size()
					|| indexTiIdMut <= codTipoAsegurado.size() || indexTiIdMut <= titDobleCobertura.size()
					|| indexTiIdMut <= beneDobleCobertura.size())

				row = sheet.getRow(indexTiIdMut++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexTiIdMut++);

			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(23).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(24).setCellValue(separar[i]);
			}
		}

		int indexTipMoviMut = 1;

		for (String a : tipoMovimientoMutualista) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexTipMoviMut <= tipoIdentificador.size() || indexTipMoviMut <= tipoMovimiento.size()
					|| indexTipMoviMut <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexTipMoviMut <= registroIpfNulo.size() || indexTipMoviMut <= informeAltaCruzado.size()
					|| indexTipMoviMut <= codTipoAsegurado.size() || indexTipMoviMut <= titDobleCobertura.size()
					|| indexTipMoviMut <= beneDobleCobertura.size()
					|| indexTipMoviMut <= tipoIdentificadorMutualistas.size())

				row = sheet.getRow(indexTipMoviMut++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexTipMoviMut++);

			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(25).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(26).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(27).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(28).setCellValue(separar[i]);
			}
		}

		int indexProtegidoTipAseg = 1;

		for (String a : protegidoTipAseguramientoAndTipMovimientoMutualistas) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexProtegidoTipAseg <= tipoIdentificador.size() || indexProtegidoTipAseg <= tipoMovimiento.size()
					|| indexProtegidoTipAseg <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexProtegidoTipAseg <= registroIpfNulo.size()
					|| indexProtegidoTipAseg <= informeAltaCruzado.size()
					|| indexProtegidoTipAseg <= codTipoAsegurado.size()
					|| indexProtegidoTipAseg <= titDobleCobertura.size()
					|| indexProtegidoTipAseg <= beneDobleCobertura.size()
					|| indexProtegidoTipAseg <= tipoIdentificadorMutualistas.size()
					|| indexProtegidoTipAseg <= tipoMovimientoMutualista.size())

				row = sheet.getRow(indexProtegidoTipAseg++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexProtegidoTipAseg++);

			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(29).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(30).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(31).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(32).setCellValue(separar[i]);
			}
		}

		int indexRegIpfNu = 1;

		for (String a : registrosIpfNuloMutualistas) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexRegIpfNu <= tipoIdentificador.size() || indexRegIpfNu <= tipoMovimiento.size()
					|| indexRegIpfNu <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexRegIpfNu <= registroIpfNulo.size() || indexRegIpfNu <= informeAltaCruzado.size()
					|| indexRegIpfNu <= codTipoAsegurado.size() || indexRegIpfNu <= titDobleCobertura.size()
					|| indexRegIpfNu <= beneDobleCobertura.size()
					|| indexRegIpfNu <= tipoIdentificadorMutualistas.size()
					|| indexRegIpfNu <= tipoMovimientoMutualista.size()
					|| indexRegIpfNu <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size())

				row = sheet.getRow(indexRegIpfNu++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexRegIpfNu++);

			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(33).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(34).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(35).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(36).setCellValue(separar[i]);
			}
		}

		int indexInfFin = 1;

		for (String a : informeAltCruzadoConSegSocialFinalMutualistas) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexInfFin <= tipoIdentificador.size() || indexInfFin <= tipoMovimiento.size()
					|| indexInfFin <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexInfFin <= registroIpfNulo.size() || indexInfFin <= informeAltaCruzado.size()
					|| indexInfFin <= codTipoAsegurado.size() || indexInfFin <= titDobleCobertura.size()
					|| indexInfFin <= beneDobleCobertura.size() || indexInfFin <= tipoIdentificadorMutualistas.size()
					|| indexInfFin <= tipoMovimientoMutualista.size()
					|| indexInfFin <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexInfFin <= registrosIpfNuloMutualistas.size())

				row = sheet.getRow(indexInfFin++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexInfFin++);

			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(37).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(38).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(39).setCellValue(separar[i]);
			}
		}

		int indexCod = 1;

		for (String a : codTipoAseguradoMutualistas) {
			String[] separar = a.split(",");

			int i =0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexCod <= tipoIdentificador.size() || indexCod <= tipoMovimiento.size()
					|| indexCod <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexCod <= registroIpfNulo.size() || indexCod <= informeAltaCruzado.size()
					|| indexCod <= codTipoAsegurado.size() || indexCod <= titDobleCobertura.size()
					|| indexCod <= beneDobleCobertura.size() || indexCod <= tipoIdentificadorMutualistas.size()
					|| indexCod <= tipoMovimientoMutualista.size()
					|| indexCod <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexCod <= registrosIpfNuloMutualistas.size()
					|| indexCod <= informeAltCruzadoConSegSocialFinalMutualistas.size())

				row = sheet.getRow(indexCod++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexCod++);

			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(40).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(41).setCellValue(separar[i]);
			}
		}

		// Regimen General

		int indexRegGen = 1;

		for (String a : regimenGeneral) {
			String[] separar = a.split(",");

			int i =0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexRegGen <= tipoIdentificador.size() || indexRegGen <= tipoMovimiento.size()
					|| indexRegGen <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexRegGen <= registroIpfNulo.size() || indexRegGen <= informeAltaCruzado.size()
					|| indexRegGen <= codTipoAsegurado.size() || indexRegGen <= titDobleCobertura.size()
					|| indexRegGen <= beneDobleCobertura.size() || indexRegGen <= tipoIdentificadorMutualistas.size()
					|| indexRegGen <= tipoMovimientoMutualista.size()
					|| indexRegGen <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexRegGen <= registrosIpfNuloMutualistas.size()
					|| indexRegGen <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexRegGen <= codTipoAseguradoMutualistas.size())

				row = sheet.getRow(indexRegGen++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexRegGen++);

			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(42).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(43).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(44).setCellValue(separar[i]);
			}
		}

		int indexbajaTitulares = 1;

		for (String a : bajaTitulares) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexbajaTitulares <= tipoIdentificador.size() || indexbajaTitulares <= tipoMovimiento.size()
					|| indexbajaTitulares <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexbajaTitulares <= registroIpfNulo.size() || indexbajaTitulares <= informeAltaCruzado.size()
					|| indexbajaTitulares <= codTipoAsegurado.size() || indexbajaTitulares <= titDobleCobertura.size()
					|| indexbajaTitulares <= beneDobleCobertura.size()
					|| indexbajaTitulares <= tipoIdentificadorMutualistas.size()
					|| indexbajaTitulares <= tipoMovimientoMutualista.size()
					|| indexbajaTitulares <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexbajaTitulares <= registrosIpfNuloMutualistas.size()
					|| indexbajaTitulares <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexbajaTitulares <= codTipoAseguradoMutualistas.size()
					|| indexbajaTitulares <= regimenGeneral.size())

				row = sheet.getRow(indexbajaTitulares++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexbajaTitulares++);

			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(45).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(46).setCellValue(separar[i]);
			}
		}

		int indexTotEnvInssAv = 1;

		for (String a : totalEnvioInssAv) {
			String[] separar = a.split(",");

			int i =0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexTotEnvInssAv <= tipoIdentificador.size() || indexTotEnvInssAv <= tipoMovimiento.size()
					|| indexTotEnvInssAv <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexTotEnvInssAv <= registroIpfNulo.size() || indexTotEnvInssAv <= informeAltaCruzado.size()
					|| indexTotEnvInssAv <= codTipoAsegurado.size() || indexTotEnvInssAv <= titDobleCobertura.size()
					|| indexTotEnvInssAv <= beneDobleCobertura.size()
					|| indexTotEnvInssAv <= tipoIdentificadorMutualistas.size()
					|| indexTotEnvInssAv <= tipoMovimientoMutualista.size()
					|| indexTotEnvInssAv <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexTotEnvInssAv <= registrosIpfNuloMutualistas.size()
					|| indexTotEnvInssAv <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexTotEnvInssAv <= codTipoAseguradoMutualistas.size()
					|| indexTotEnvInssAv <= regimenGeneral.size() || indexTotEnvInssAv <= bajaTitulares.size())

				row = sheet.getRow(indexTotEnvInssAv++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexTotEnvInssAv++);

			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(47).setCellValue(separar[i]);
			}
		}
		
		int indexBajasVinculadosSns = 1;

		for (String a : bajasVinculadosSns) {
			String[] separar = a.split(",");

			int i =0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexBajasVinculadosSns <= tipoIdentificador.size() || indexBajasVinculadosSns <= tipoMovimiento.size()
					|| indexBajasVinculadosSns <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexBajasVinculadosSns <= registroIpfNulo.size() || indexBajasVinculadosSns <= informeAltaCruzado.size()
					|| indexBajasVinculadosSns <= codTipoAsegurado.size() || indexBajasVinculadosSns <= titDobleCobertura.size()
					|| indexBajasVinculadosSns <= beneDobleCobertura.size()
					|| indexBajasVinculadosSns <= tipoIdentificadorMutualistas.size()
					|| indexBajasVinculadosSns <= tipoMovimientoMutualista.size()
					|| indexBajasVinculadosSns <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexBajasVinculadosSns <= registrosIpfNuloMutualistas.size()
					|| indexBajasVinculadosSns <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexBajasVinculadosSns <= codTipoAseguradoMutualistas.size()
					|| indexBajasVinculadosSns <= regimenGeneral.size() || indexBajasVinculadosSns <= bajaTitulares.size()
					|| indexBajasVinculadosSns <= totalEnvioInssAv.size())

				row = sheet.getRow(indexBajasVinculadosSns++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexBajasVinculadosSns++);

			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(48).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(49).setCellValue(separar[i]);
			}
		}
		
		int indexMutualistasPrivados = 1;

		for (String a : mutualistasPrivados) {
			String[] separar = a.split(",");

			int i =0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexMutualistasPrivados <= tipoIdentificador.size() || indexMutualistasPrivados <= tipoMovimiento.size()
					|| indexMutualistasPrivados <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexMutualistasPrivados <= registroIpfNulo.size() || indexMutualistasPrivados <= informeAltaCruzado.size()
					|| indexMutualistasPrivados <= codTipoAsegurado.size() || indexMutualistasPrivados <= titDobleCobertura.size()
					|| indexMutualistasPrivados <= beneDobleCobertura.size()
					|| indexMutualistasPrivados <= tipoIdentificadorMutualistas.size()
					|| indexMutualistasPrivados <= tipoMovimientoMutualista.size()
					|| indexMutualistasPrivados <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexMutualistasPrivados <= registrosIpfNuloMutualistas.size()
					|| indexMutualistasPrivados <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexMutualistasPrivados <= codTipoAseguradoMutualistas.size()
					|| indexMutualistasPrivados <= regimenGeneral.size() || indexMutualistasPrivados <= bajaTitulares.size()
					|| indexMutualistasPrivados <= totalEnvioInssAv.size() || indexMutualistasPrivados <= bajasVinculadosSns.size())

				row = sheet.getRow(indexMutualistasPrivados++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexMutualistasPrivados++);

			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(50).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(51).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(52).setCellValue(separar[i]);
			}
		}
				
		int indexBajaPorDefuncion = 1;

		for (String a : bajaPorDefuncion) {
			String[] separar = a.split(",");

			int i =0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexBajaPorDefuncion <= tipoIdentificador.size() || indexBajaPorDefuncion <= tipoMovimiento.size()
					|| indexBajaPorDefuncion <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexBajaPorDefuncion <= registroIpfNulo.size() || indexBajaPorDefuncion <= informeAltaCruzado.size()
					|| indexBajaPorDefuncion <= codTipoAsegurado.size() || indexBajaPorDefuncion <= titDobleCobertura.size()
					|| indexBajaPorDefuncion <= beneDobleCobertura.size()
					|| indexBajaPorDefuncion <= tipoIdentificadorMutualistas.size()
					|| indexBajaPorDefuncion <= tipoMovimientoMutualista.size()
					|| indexBajaPorDefuncion <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexBajaPorDefuncion <= registrosIpfNuloMutualistas.size()
					|| indexBajaPorDefuncion <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexBajaPorDefuncion <= codTipoAseguradoMutualistas.size()
					|| indexBajaPorDefuncion <= regimenGeneral.size() || indexBajaPorDefuncion <= bajaTitulares.size()
					|| indexBajaPorDefuncion <= totalEnvioInssAv.size() || indexBajaPorDefuncion <= bajasVinculadosSns.size()
					|| indexBajaPorDefuncion <= mutualistasPrivados.size() )

				row = sheet.getRow(indexBajaPorDefuncion++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexBajaPorDefuncion++);

			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(53).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(54).setCellValue(separar[i]);
			}			
		}		
		
		int indexAseguramiento = 1;

		for (String a : aseguramiento) {
			String[] separar = a.split(",");

			int i =0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexAseguramiento <= tipoIdentificador.size() || indexAseguramiento <= tipoMovimiento.size()
					|| indexAseguramiento <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexAseguramiento <= registroIpfNulo.size() || indexAseguramiento <= informeAltaCruzado.size()
					|| indexAseguramiento <= codTipoAsegurado.size() || indexAseguramiento <= titDobleCobertura.size()
					|| indexAseguramiento <= beneDobleCobertura.size()
					|| indexAseguramiento <= tipoIdentificadorMutualistas.size()
					|| indexAseguramiento <= tipoMovimientoMutualista.size()
					|| indexAseguramiento <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexAseguramiento <= registrosIpfNuloMutualistas.size()
					|| indexAseguramiento <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexAseguramiento <= codTipoAseguradoMutualistas.size()
					|| indexAseguramiento <= regimenGeneral.size() || indexAseguramiento <= bajaTitulares.size()
					|| indexAseguramiento <= totalEnvioInssAv.size() || indexAseguramiento <= bajasVinculadosSns.size()
					|| indexAseguramiento <= mutualistasPrivados.size() || indexAseguramiento <= bajaPorDefuncion.size())

				row = sheet.getRow(indexAseguramiento++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexAseguramiento++);

			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(55).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(56).setCellValue(separar[i]);
			}		
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(57).setCellValue(separar[i]);
			}
		}
		
		
		int indexIndicadorFarmacia = 1;

		for (String a : indicadorFarmacia) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexIndicadorFarmacia <= tipoIdentificador.size() || indexIndicadorFarmacia <= tipoMovimiento.size()
					|| indexIndicadorFarmacia <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexIndicadorFarmacia <= registroIpfNulo.size() || indexIndicadorFarmacia <= informeAltaCruzado.size()
					|| indexIndicadorFarmacia <= codTipoAsegurado.size() || indexIndicadorFarmacia <= titDobleCobertura.size()
					|| indexIndicadorFarmacia <= beneDobleCobertura.size()
					|| indexIndicadorFarmacia <= tipoIdentificadorMutualistas.size()
					|| indexIndicadorFarmacia <= tipoMovimientoMutualista.size()
					|| indexIndicadorFarmacia <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexIndicadorFarmacia <= registrosIpfNuloMutualistas.size()
					|| indexIndicadorFarmacia <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexIndicadorFarmacia <= codTipoAseguradoMutualistas.size()
					|| indexIndicadorFarmacia <= regimenGeneral.size() || indexIndicadorFarmacia <= bajaTitulares.size()
					|| indexIndicadorFarmacia <= totalEnvioInssAv.size() || indexIndicadorFarmacia <= bajasVinculadosSns.size()
					|| indexIndicadorFarmacia <= mutualistasPrivados.size() || indexIndicadorFarmacia <= bajaPorDefuncion.size()
					|| indexIndicadorFarmacia <= aseguramiento.size())

				row = sheet.getRow(indexIndicadorFarmacia++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexIndicadorFarmacia++);

			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(58).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(59).setCellValue(separar[i]);
			}		
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(60).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(61).setCellValue(separar[i]);
			}
		}
		
		
		int codigoBadas = 1;		
			
		for (String a : codigoBadasRepetido) {
			String[] separar = a.split(",");

			int i = 0;
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (codigoBadas <= tipoIdentificador.size() || codigoBadas <= tipoMovimiento.size()
					|| codigoBadas <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| codigoBadas <= registroIpfNulo.size() || codigoBadas <= informeAltaCruzado.size()
					|| codigoBadas <= codTipoAsegurado.size() || codigoBadas <= titDobleCobertura.size()
					|| codigoBadas <= beneDobleCobertura.size()
					|| codigoBadas <= tipoIdentificadorMutualistas.size()
					|| codigoBadas <= tipoMovimientoMutualista.size()
					|| codigoBadas <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| codigoBadas <= registrosIpfNuloMutualistas.size()
					|| codigoBadas <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| codigoBadas <= codTipoAseguradoMutualistas.size()
					|| codigoBadas <= regimenGeneral.size() || codigoBadas <= bajaTitulares.size()
					|| codigoBadas <= totalEnvioInssAv.size() || codigoBadas <= bajasVinculadosSns.size()
					|| codigoBadas <= mutualistasPrivados.size() || codigoBadas <= bajaPorDefuncion.size()
					|| codigoBadas <= aseguramiento.size() || codigoBadas <= indicadorFarmacia.size())

				row = sheet.getRow(codigoBadas++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(codigoBadas++);
			
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(62).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(63).setCellValue(separar[i]);
			}		
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(64).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(65).setCellValue(separar[i]);			
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(66).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(67).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(68).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(69).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(70).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(71).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(72).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(73).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(74).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(75).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(76).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(77).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(78).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(79).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(80).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(81).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(82).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(83).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(84).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(85).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(86).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(87).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(88).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(89).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(90).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(91).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(92).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(93).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(94).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(95).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(96).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(97).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(98).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(99).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(100).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(101).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(102).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(103).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(104).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(105).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(106).setCellValue(separar[i]);
			}				
		}
		

		int altaSNaf = 1;		
		
		for (String a : altasSinNaf) {
			String[] separar = a.split(",");
			
			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (altaSNaf <= tipoIdentificador.size() || altaSNaf <= tipoMovimiento.size()
					|| altaSNaf <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| altaSNaf <= registroIpfNulo.size() || altaSNaf <= informeAltaCruzado.size()
					|| altaSNaf <= codTipoAsegurado.size() || altaSNaf <= titDobleCobertura.size()
					|| altaSNaf <= beneDobleCobertura.size()
					|| altaSNaf <= tipoIdentificadorMutualistas.size()
					|| altaSNaf <= tipoMovimientoMutualista.size()
					|| altaSNaf <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| altaSNaf <= registrosIpfNuloMutualistas.size()
					|| altaSNaf <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| altaSNaf <= codTipoAseguradoMutualistas.size()
					|| altaSNaf <= regimenGeneral.size() || altaSNaf <= bajaTitulares.size()
					|| altaSNaf <= totalEnvioInssAv.size() || altaSNaf <= bajasVinculadosSns.size()
					|| altaSNaf <= mutualistasPrivados.size() || altaSNaf <= bajaPorDefuncion.size()
					|| altaSNaf <= aseguramiento.size() || altaSNaf <= indicadorFarmacia.size()
					|| altaSNaf <= codigoBadasRepetido.size())

				row = sheet.getRow(altaSNaf++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(altaSNaf++);
			
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(107).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(108).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(109).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(110).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(111).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(112).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(113).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(114).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(115).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(116).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(117).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(118).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(119).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(120).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(121).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(122).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(123).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(124).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(125).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(126).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(127).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(128).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(129).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(130).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(131).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(132).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(133).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(134).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(135).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(136).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(137).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(138).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(139).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(140).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(141).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(142).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(143).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(144).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(145).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(146).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(147).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(148).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(149).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(150).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(151).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(152).setCellValue(separar[i]);
			}	
		}	
		
		int cambioIpf = 1;		
		
		for (String a : cambiosIpf) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (cambioIpf <= tipoIdentificador.size() || cambioIpf <= tipoMovimiento.size()
					|| cambioIpf <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| cambioIpf <= registroIpfNulo.size() || cambioIpf <= informeAltaCruzado.size()
					|| cambioIpf <= codTipoAsegurado.size() || cambioIpf <= titDobleCobertura.size()
					|| cambioIpf <= beneDobleCobertura.size()
					|| cambioIpf <= tipoIdentificadorMutualistas.size()
					|| cambioIpf <= tipoMovimientoMutualista.size()
					|| cambioIpf <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| cambioIpf <= registrosIpfNuloMutualistas.size()
					|| cambioIpf <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| cambioIpf <= codTipoAseguradoMutualistas.size()
					|| cambioIpf <= regimenGeneral.size() || cambioIpf <= bajaTitulares.size()
					|| cambioIpf <= totalEnvioInssAv.size() || cambioIpf <= bajasVinculadosSns.size()
					|| cambioIpf <= mutualistasPrivados.size() || cambioIpf <= bajaPorDefuncion.size()
					|| cambioIpf <= aseguramiento.size() || cambioIpf <= indicadorFarmacia.size()
					|| cambioIpf <= codigoBadasRepetido.size() || cambioIpf <= altasSinNaf.size())
				
				row = sheet.getRow(cambioIpf++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(cambioIpf++);
			
			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(153).setCellValue(separar[0]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(154).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(155).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(156).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(157).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(158).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(159).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(160).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(161).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(162).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(163).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(164).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(165).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(166).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(167).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(168).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(169).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(170).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(171).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(172).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(173).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(174).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(175).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(176).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(177).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(178).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(179).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(180).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(181).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(182).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(183).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(184).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(185).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(186).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(187).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(188).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(189).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(190).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(191).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(192).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(193).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(194).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(195).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(196).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(197).setCellValue(separar[i]);
			}	
		}
						
		int indexIpfNu = 1;		
		
		for (String a : ipfNuevoExisteInssTit) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexIpfNu <= tipoIdentificador.size() || indexIpfNu <= tipoMovimiento.size()
					|| indexIpfNu <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexIpfNu <= registroIpfNulo.size() || indexIpfNu <= informeAltaCruzado.size()
					|| indexIpfNu <= codTipoAsegurado.size() || indexIpfNu <= titDobleCobertura.size()
					|| indexIpfNu <= beneDobleCobertura.size()
					|| indexIpfNu <= tipoIdentificadorMutualistas.size()
					|| indexIpfNu <= tipoMovimientoMutualista.size()
					|| indexIpfNu <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexIpfNu <= registrosIpfNuloMutualistas.size()
					|| indexIpfNu <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexIpfNu <= codTipoAseguradoMutualistas.size()
					|| indexIpfNu <= regimenGeneral.size() || indexIpfNu <= bajaTitulares.size()
					|| indexIpfNu <= totalEnvioInssAv.size() || indexIpfNu <= bajasVinculadosSns.size()
					|| indexIpfNu <= mutualistasPrivados.size() || indexIpfNu <= bajaPorDefuncion.size()
					|| indexIpfNu <= aseguramiento.size() || indexIpfNu <= indicadorFarmacia.size()
					|| indexIpfNu <= codigoBadasRepetido.size() || indexIpfNu <= altasSinNaf.size()
					|| indexIpfNu <= cambiosIpf.size())
				
				row = sheet.getRow(indexIpfNu++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexIpfNu++);
			
			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(198).setCellValue(separar[0]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(199).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(200).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(201).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(202).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(203).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(204).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(205).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(206).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(207).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(208).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(209).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(210).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(211).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(212).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(213).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(214).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(215).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(216).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(217).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(218).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(219).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(220).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(221).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(222).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(223).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(224).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(225).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(226).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(227).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(228).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(229).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(230).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(231).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(232).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(233).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(234).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(235).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(236).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(237).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(238).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(239).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(240).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(241).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(242).setCellValue(separar[i]);
			}				
		}
		
		int indexipfAnteInssMut = 1;		
		
		for (String a : ipfAnteriorNoExisteInssMut) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexipfAnteInssMut <= tipoIdentificador.size() || indexipfAnteInssMut <= tipoMovimiento.size()
					|| indexipfAnteInssMut <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexipfAnteInssMut <= registroIpfNulo.size() || indexipfAnteInssMut <= informeAltaCruzado.size()
					|| indexipfAnteInssMut <= codTipoAsegurado.size() || indexipfAnteInssMut <= titDobleCobertura.size()
					|| indexipfAnteInssMut <= beneDobleCobertura.size()
					|| indexipfAnteInssMut <= tipoIdentificadorMutualistas.size()
					|| indexipfAnteInssMut <= tipoMovimientoMutualista.size()
					|| indexipfAnteInssMut <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexipfAnteInssMut <= registrosIpfNuloMutualistas.size()
					|| indexipfAnteInssMut <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexipfAnteInssMut <= codTipoAseguradoMutualistas.size()
					|| indexipfAnteInssMut <= regimenGeneral.size() || indexipfAnteInssMut <= bajaTitulares.size()
					|| indexipfAnteInssMut <= totalEnvioInssAv.size() || indexipfAnteInssMut <= bajasVinculadosSns.size()
					|| indexipfAnteInssMut <= mutualistasPrivados.size() || indexipfAnteInssMut <= bajaPorDefuncion.size()
					|| indexipfAnteInssMut <= aseguramiento.size() || indexipfAnteInssMut <= indicadorFarmacia.size()
					|| indexipfAnteInssMut <= codigoBadasRepetido.size() || indexipfAnteInssMut <= altasSinNaf.size()
					|| indexipfAnteInssMut <= cambiosIpf.size() || indexipfAnteInssMut <= ipfNuevoExisteInssTit.size() )
				
				row = sheet.getRow(indexipfAnteInssMut++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexipfAnteInssMut++);
			
			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(243).setCellValue(separar[0]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(244).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(245).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(246).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(247).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(248).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(249).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(250).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(251).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(252).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(253).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(254).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(255).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(256).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(257).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(258).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(259).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(260).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(261).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(262).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(263).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(264).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(265).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(266).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(267).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(268).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(269).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(270).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(271).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(272).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(273).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(274).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(275).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(276).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(277).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(278).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(279).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(280).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(281).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(282).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(283).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(284).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(285).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(286).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(287).setCellValue(separar[i]);
			}	
		}
				

		int indexIpfNuevoExiteInssMut = 1;		
		
		for (String a : ipfNuevoExiteInssMut) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexIpfNuevoExiteInssMut <= tipoIdentificador.size() || indexIpfNuevoExiteInssMut <= tipoMovimiento.size()
					|| indexIpfNuevoExiteInssMut <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexIpfNuevoExiteInssMut <= registroIpfNulo.size() || indexIpfNuevoExiteInssMut <= informeAltaCruzado.size()
					|| indexIpfNuevoExiteInssMut <= codTipoAsegurado.size() || indexIpfNuevoExiteInssMut <= titDobleCobertura.size()
					|| indexIpfNuevoExiteInssMut <= beneDobleCobertura.size()
					|| indexIpfNuevoExiteInssMut <= tipoIdentificadorMutualistas.size()
					|| indexIpfNuevoExiteInssMut <= tipoMovimientoMutualista.size()
					|| indexIpfNuevoExiteInssMut <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexIpfNuevoExiteInssMut <= registrosIpfNuloMutualistas.size()
					|| indexIpfNuevoExiteInssMut <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexIpfNuevoExiteInssMut <= codTipoAseguradoMutualistas.size()
					|| indexIpfNuevoExiteInssMut <= regimenGeneral.size() || indexIpfNuevoExiteInssMut <= bajaTitulares.size()
					|| indexIpfNuevoExiteInssMut <= totalEnvioInssAv.size() || indexIpfNuevoExiteInssMut <= bajasVinculadosSns.size()
					|| indexIpfNuevoExiteInssMut <= mutualistasPrivados.size() || indexIpfNuevoExiteInssMut <= bajaPorDefuncion.size()
					|| indexIpfNuevoExiteInssMut <= aseguramiento.size() || indexIpfNuevoExiteInssMut <= indicadorFarmacia.size()
					|| indexIpfNuevoExiteInssMut <= codigoBadasRepetido.size() || indexIpfNuevoExiteInssMut <= altasSinNaf.size()
					|| indexIpfNuevoExiteInssMut <= cambiosIpf.size() || indexIpfNuevoExiteInssMut <= ipfNuevoExisteInssTit.size() 
					|| indexIpfNuevoExiteInssMut <= ipfAnteriorNoExisteInssMut.size())
				
				row = sheet.getRow(indexIpfNuevoExiteInssMut++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexIpfNuevoExiteInssMut++);
			
			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(288).setCellValue(separar[0]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(289).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(290).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(291).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(292).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(293).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(294).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(295).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(296).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(297).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(298).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(299).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(300).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(301).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(302).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(303).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(304).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(305).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(306).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(307).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(308).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(309).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(310).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(311).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(312).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(313).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(314).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(315).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(316).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(317).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(318).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(319).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(320).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(321).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(322).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(323).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(324).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(325).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(326).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(327).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(328).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(329).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(330).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(331).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(332).setCellValue(separar[i]);
			}	
		}		
		
		int indexTitDobleCobertura = 1;		
		
		for (String a : titularesDobleCobertura) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexTitDobleCobertura <= tipoIdentificador.size() || indexTitDobleCobertura <= tipoMovimiento.size()
					|| indexTitDobleCobertura <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexTitDobleCobertura <= registroIpfNulo.size() || indexTitDobleCobertura <= informeAltaCruzado.size()
					|| indexTitDobleCobertura <= codTipoAsegurado.size() || indexTitDobleCobertura <= titDobleCobertura.size()
					|| indexTitDobleCobertura <= beneDobleCobertura.size()
					|| indexTitDobleCobertura <= tipoIdentificadorMutualistas.size()
					|| indexTitDobleCobertura <= tipoMovimientoMutualista.size()
					|| indexTitDobleCobertura <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexTitDobleCobertura <= registrosIpfNuloMutualistas.size()
					|| indexTitDobleCobertura <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexTitDobleCobertura <= codTipoAseguradoMutualistas.size()
					|| indexTitDobleCobertura <= regimenGeneral.size() || indexTitDobleCobertura <= bajaTitulares.size()
					|| indexTitDobleCobertura <= totalEnvioInssAv.size() || indexTitDobleCobertura <= bajasVinculadosSns.size()
					|| indexTitDobleCobertura <= mutualistasPrivados.size() || indexTitDobleCobertura <= bajaPorDefuncion.size()
					|| indexTitDobleCobertura <= aseguramiento.size() || indexTitDobleCobertura <= indicadorFarmacia.size()
					|| indexTitDobleCobertura <= codigoBadasRepetido.size() || indexTitDobleCobertura <= altasSinNaf.size()
					|| indexTitDobleCobertura <= cambiosIpf.size() || indexTitDobleCobertura <= ipfNuevoExisteInssTit.size() 
					|| indexTitDobleCobertura <= ipfAnteriorNoExisteInssMut.size() || indexTitDobleCobertura <= ipfNuevoExiteInssMut.size())
				
				row = sheet.getRow(indexTitDobleCobertura++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexTitDobleCobertura++);
			
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(333).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(334).setCellValue(separar[i]);
			}				
		}
		

		
	int indexBeneDob = 1;		
		
		for (String a : beneficiarioDobleCoberturaMutualistas) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexBeneDob <= tipoIdentificador.size() || indexBeneDob <= tipoMovimiento.size()
					|| indexBeneDob <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexBeneDob <= registroIpfNulo.size() || indexBeneDob <= informeAltaCruzado.size()
					|| indexBeneDob <= codTipoAsegurado.size() || indexBeneDob <= titDobleCobertura.size()
					|| indexBeneDob <= beneDobleCobertura.size()
					|| indexBeneDob <= tipoIdentificadorMutualistas.size()
					|| indexBeneDob <= tipoMovimientoMutualista.size()
					|| indexBeneDob <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexBeneDob <= registrosIpfNuloMutualistas.size()
					|| indexBeneDob <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexBeneDob <= codTipoAseguradoMutualistas.size()
					|| indexBeneDob <= regimenGeneral.size() || indexBeneDob <= bajaTitulares.size()
					|| indexBeneDob <= totalEnvioInssAv.size() || indexBeneDob <= bajasVinculadosSns.size()
					|| indexBeneDob <= mutualistasPrivados.size() || indexBeneDob <= bajaPorDefuncion.size()
					|| indexBeneDob <= aseguramiento.size() || indexBeneDob <= indicadorFarmacia.size()
					|| indexBeneDob <= codigoBadasRepetido.size() || indexBeneDob <= altasSinNaf.size()
					|| indexBeneDob <= cambiosIpf.size() || indexBeneDob <= ipfNuevoExisteInssTit.size() 
					|| indexBeneDob <= ipfAnteriorNoExisteInssMut.size() || indexBeneDob <= ipfNuevoExiteInssMut.size()
					|| indexBeneDob <= titularesDobleCobertura.size())
				
				row = sheet.getRow(indexBeneDob++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexBeneDob++);
			
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(335).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(336).setCellValue(separar[i]);
			}				
		}
		
		
	int indexTituIndCoRu = 1;		
		
		for (String a : titularesIndicadorConvenioRural) {
			String[] separar = a.split(",");

			int i =0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexTituIndCoRu <= tipoIdentificador.size() || indexTituIndCoRu <= tipoMovimiento.size()
					|| indexTituIndCoRu <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexTituIndCoRu <= registroIpfNulo.size() || indexTituIndCoRu <= informeAltaCruzado.size()
					|| indexTituIndCoRu <= codTipoAsegurado.size() || indexTituIndCoRu <= titDobleCobertura.size()
					|| indexTituIndCoRu <= beneDobleCobertura.size()
					|| indexTituIndCoRu <= tipoIdentificadorMutualistas.size()
					|| indexTituIndCoRu <= tipoMovimientoMutualista.size()
					|| indexTituIndCoRu <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexTituIndCoRu <= registrosIpfNuloMutualistas.size()
					|| indexTituIndCoRu <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexTituIndCoRu <= codTipoAseguradoMutualistas.size()
					|| indexTituIndCoRu <= regimenGeneral.size() || indexTituIndCoRu <= bajaTitulares.size()
					|| indexTituIndCoRu <= totalEnvioInssAv.size() || indexTituIndCoRu <= bajasVinculadosSns.size()
					|| indexTituIndCoRu <= mutualistasPrivados.size() || indexTituIndCoRu <= bajaPorDefuncion.size()
					|| indexTituIndCoRu <= aseguramiento.size() || indexTituIndCoRu <= indicadorFarmacia.size()
					|| indexTituIndCoRu <= codigoBadasRepetido.size() || indexTituIndCoRu <= altasSinNaf.size()
					|| indexTituIndCoRu <= cambiosIpf.size() || indexTituIndCoRu <= ipfNuevoExisteInssTit.size() 
					|| indexTituIndCoRu <= ipfAnteriorNoExisteInssMut.size() || indexTituIndCoRu <= ipfNuevoExiteInssMut.size()
					|| indexTituIndCoRu <= titularesDobleCobertura.size() || indexTituIndCoRu <= beneficiarioDobleCoberturaMutualistas.size())
				
				row = sheet.getRow(indexTituIndCoRu++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexTituIndCoRu++);
			
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(337).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(338).setCellValue(separar[i]);
			}				
		}
		
		
	int indexBenIndCoRural = 1;		
		
		for (String a : beneficiadioIndicadorConvenioRural) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexBenIndCoRural <= tipoIdentificador.size() || indexBenIndCoRural <= tipoMovimiento.size()
					|| indexBenIndCoRural <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexBenIndCoRural <= registroIpfNulo.size() || indexBenIndCoRural <= informeAltaCruzado.size()
					|| indexBenIndCoRural <= codTipoAsegurado.size() || indexBenIndCoRural <= titDobleCobertura.size()
					|| indexBenIndCoRural <= beneDobleCobertura.size()
					|| indexBenIndCoRural <= tipoIdentificadorMutualistas.size()
					|| indexBenIndCoRural <= tipoMovimientoMutualista.size()
					|| indexBenIndCoRural <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexBenIndCoRural <= registrosIpfNuloMutualistas.size()
					|| indexBenIndCoRural <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexBenIndCoRural <= codTipoAseguradoMutualistas.size()
					|| indexBenIndCoRural <= regimenGeneral.size() || indexBenIndCoRural <= bajaTitulares.size()
					|| indexBenIndCoRural <= totalEnvioInssAv.size() || indexBenIndCoRural <= bajasVinculadosSns.size()
					|| indexBenIndCoRural <= mutualistasPrivados.size() || indexBenIndCoRural <= bajaPorDefuncion.size()
					|| indexBenIndCoRural <= aseguramiento.size() || indexBenIndCoRural <= indicadorFarmacia.size()
					|| indexBenIndCoRural <= codigoBadasRepetido.size() || indexBenIndCoRural <= altasSinNaf.size()
					|| indexBenIndCoRural <= cambiosIpf.size() || indexBenIndCoRural <= ipfNuevoExisteInssTit.size() 
					|| indexBenIndCoRural <= ipfAnteriorNoExisteInssMut.size() || indexBenIndCoRural <= ipfNuevoExiteInssMut.size()
					|| indexBenIndCoRural <= titularesDobleCobertura.size() || indexBenIndCoRural <= beneficiarioDobleCoberturaMutualistas.size()
					|| indexBenIndCoRural <= titularesIndicadorConvenioRural.size())
				
				row = sheet.getRow(indexBenIndCoRural++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexBenIndCoRural++);
			
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(339).setCellValue(separar[i]);
			}
			i ++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(340).setCellValue(separar[i]);
			}				
		}
		
	int indexPrivadoRegularPublico = 1;		
		
		for (String a : privadoRegularPublico) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexPrivadoRegularPublico <= tipoIdentificador.size() || indexPrivadoRegularPublico <= tipoMovimiento.size()
					|| indexPrivadoRegularPublico <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexPrivadoRegularPublico <= registroIpfNulo.size() || indexPrivadoRegularPublico <= informeAltaCruzado.size()
					|| indexPrivadoRegularPublico <= codTipoAsegurado.size() || indexPrivadoRegularPublico <= titDobleCobertura.size()
					|| indexPrivadoRegularPublico <= beneDobleCobertura.size()
					|| indexPrivadoRegularPublico <= tipoIdentificadorMutualistas.size()
					|| indexPrivadoRegularPublico <= tipoMovimientoMutualista.size()
					|| indexPrivadoRegularPublico <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexPrivadoRegularPublico <= registrosIpfNuloMutualistas.size()
					|| indexPrivadoRegularPublico <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexPrivadoRegularPublico <= codTipoAseguradoMutualistas.size()
					|| indexPrivadoRegularPublico <= regimenGeneral.size() || indexPrivadoRegularPublico <= bajaTitulares.size()
					|| indexPrivadoRegularPublico <= totalEnvioInssAv.size() || indexPrivadoRegularPublico <= bajasVinculadosSns.size()
					|| indexPrivadoRegularPublico <= mutualistasPrivados.size() || indexPrivadoRegularPublico <= bajaPorDefuncion.size()
					|| indexPrivadoRegularPublico <= aseguramiento.size() || indexPrivadoRegularPublico <= indicadorFarmacia.size()
					|| indexPrivadoRegularPublico <= codigoBadasRepetido.size() || indexPrivadoRegularPublico <= altasSinNaf.size()
					|| indexPrivadoRegularPublico <= cambiosIpf.size() || indexPrivadoRegularPublico <= ipfNuevoExisteInssTit.size() 
					|| indexPrivadoRegularPublico <= ipfAnteriorNoExisteInssMut.size() || indexPrivadoRegularPublico <= ipfNuevoExiteInssMut.size()
					|| indexPrivadoRegularPublico <= titularesDobleCobertura.size() || indexPrivadoRegularPublico <= beneficiarioDobleCoberturaMutualistas.size()
					|| indexPrivadoRegularPublico <= titularesIndicadorConvenioRural.size() || indexPrivadoRegularPublico <= beneficiadioIndicadorConvenioRural.size() )
				
				row = sheet.getRow(indexPrivadoRegularPublico++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexPrivadoRegularPublico++);
			
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(341).setCellValue(separar[i]);
			}				
		}
		
				
	int indexPrivadoIrregularPublico = 1;		
		
		for (String a : privadoIrregularPublico) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexPrivadoIrregularPublico <= tipoIdentificador.size() || indexPrivadoIrregularPublico <= tipoMovimiento.size()
					|| indexPrivadoIrregularPublico <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexPrivadoIrregularPublico <= registroIpfNulo.size() || indexPrivadoIrregularPublico <= informeAltaCruzado.size()
					|| indexPrivadoIrregularPublico <= codTipoAsegurado.size() || indexPrivadoIrregularPublico <= titDobleCobertura.size()
					|| indexPrivadoIrregularPublico <= beneDobleCobertura.size()
					|| indexPrivadoIrregularPublico <= tipoIdentificadorMutualistas.size()
					|| indexPrivadoIrregularPublico <= tipoMovimientoMutualista.size()
					|| indexPrivadoIrregularPublico <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexPrivadoIrregularPublico <= registrosIpfNuloMutualistas.size()
					|| indexPrivadoIrregularPublico <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexPrivadoIrregularPublico <= codTipoAseguradoMutualistas.size()
					|| indexPrivadoIrregularPublico <= regimenGeneral.size() || indexPrivadoIrregularPublico <= bajaTitulares.size()
					|| indexPrivadoIrregularPublico <= totalEnvioInssAv.size() || indexPrivadoIrregularPublico <= bajasVinculadosSns.size()
					|| indexPrivadoIrregularPublico <= mutualistasPrivados.size() || indexPrivadoIrregularPublico <= bajaPorDefuncion.size()
					|| indexPrivadoIrregularPublico <= aseguramiento.size() || indexPrivadoIrregularPublico <= indicadorFarmacia.size()
					|| indexPrivadoIrregularPublico <= codigoBadasRepetido.size() || indexPrivadoIrregularPublico <= altasSinNaf.size()
					|| indexPrivadoIrregularPublico <= cambiosIpf.size() || indexPrivadoIrregularPublico <= ipfNuevoExisteInssTit.size() 
					|| indexPrivadoIrregularPublico <= ipfAnteriorNoExisteInssMut.size() || indexPrivadoIrregularPublico <= ipfNuevoExiteInssMut.size()
					|| indexPrivadoIrregularPublico <= titularesDobleCobertura.size() || indexPrivadoIrregularPublico <= beneficiarioDobleCoberturaMutualistas.size()
					|| indexPrivadoIrregularPublico <= titularesIndicadorConvenioRural.size() || indexPrivadoIrregularPublico <= beneficiadioIndicadorConvenioRural.size()
					|| indexPrivadoIrregularPublico <= privadoRegularPublico.size())
				
				row = sheet.getRow(indexPrivadoIrregularPublico++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexPrivadoIrregularPublico++);
			
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(342).setCellValue(separar[i]);
			}				
		}
		
		
	int indexPrivadoNoExiste = 1;		
		
		for (String a : privadoNoExiste) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexPrivadoNoExiste <= tipoIdentificador.size() || indexPrivadoNoExiste <= tipoMovimiento.size()
					|| indexPrivadoNoExiste <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexPrivadoNoExiste <= registroIpfNulo.size() || indexPrivadoNoExiste <= informeAltaCruzado.size()
					|| indexPrivadoNoExiste <= codTipoAsegurado.size() || indexPrivadoNoExiste <= titDobleCobertura.size()
					|| indexPrivadoNoExiste <= beneDobleCobertura.size()
					|| indexPrivadoNoExiste <= tipoIdentificadorMutualistas.size()
					|| indexPrivadoNoExiste <= tipoMovimientoMutualista.size()
					|| indexPrivadoNoExiste <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexPrivadoNoExiste <= registrosIpfNuloMutualistas.size()
					|| indexPrivadoNoExiste <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexPrivadoNoExiste <= codTipoAseguradoMutualistas.size()
					|| indexPrivadoNoExiste <= regimenGeneral.size() || indexPrivadoNoExiste <= bajaTitulares.size()
					|| indexPrivadoNoExiste <= totalEnvioInssAv.size() || indexPrivadoNoExiste <= bajasVinculadosSns.size()
					|| indexPrivadoNoExiste <= mutualistasPrivados.size() || indexPrivadoNoExiste <= bajaPorDefuncion.size()
					|| indexPrivadoNoExiste <= aseguramiento.size() || indexPrivadoNoExiste <= indicadorFarmacia.size()
					|| indexPrivadoNoExiste <= codigoBadasRepetido.size() || indexPrivadoNoExiste <= altasSinNaf.size()
					|| indexPrivadoNoExiste <= cambiosIpf.size() || indexPrivadoNoExiste <= ipfNuevoExisteInssTit.size() 
					|| indexPrivadoNoExiste <= ipfAnteriorNoExisteInssMut.size() || indexPrivadoNoExiste <= ipfNuevoExiteInssMut.size()
					|| indexPrivadoNoExiste <= titularesDobleCobertura.size() || indexPrivadoNoExiste <= beneficiarioDobleCoberturaMutualistas.size()
					|| indexPrivadoNoExiste <= titularesIndicadorConvenioRural.size() || indexPrivadoNoExiste <= beneficiadioIndicadorConvenioRural.size()
					|| indexPrivadoNoExiste <= privadoRegularPublico.size() || indexPrivadoNoExiste <= privadoIrregularPublico.size() )
				
				row = sheet.getRow(indexPrivadoNoExiste++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexPrivadoNoExiste++);
			
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(343).setCellValue(separar[i]);
			}				
		}
				
		
	int indexAltaModifTitDoCo = 1;		
		
		for (String a : altaModificacionTitularesDobleCobertura) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexAltaModifTitDoCo <= tipoIdentificador.size() || indexAltaModifTitDoCo <= tipoMovimiento.size()
					|| indexAltaModifTitDoCo <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexAltaModifTitDoCo <= registroIpfNulo.size() || indexAltaModifTitDoCo <= informeAltaCruzado.size()
					|| indexAltaModifTitDoCo <= codTipoAsegurado.size() || indexAltaModifTitDoCo <= titDobleCobertura.size()
					|| indexAltaModifTitDoCo <= beneDobleCobertura.size()
					|| indexAltaModifTitDoCo <= tipoIdentificadorMutualistas.size()
					|| indexAltaModifTitDoCo <= tipoMovimientoMutualista.size()
					|| indexAltaModifTitDoCo <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexAltaModifTitDoCo <= registrosIpfNuloMutualistas.size()
					|| indexAltaModifTitDoCo <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexAltaModifTitDoCo <= codTipoAseguradoMutualistas.size()
					|| indexAltaModifTitDoCo <= regimenGeneral.size() || indexAltaModifTitDoCo <= bajaTitulares.size()
					|| indexAltaModifTitDoCo <= totalEnvioInssAv.size() || indexAltaModifTitDoCo <= bajasVinculadosSns.size()
					|| indexAltaModifTitDoCo <= mutualistasPrivados.size() || indexAltaModifTitDoCo <= bajaPorDefuncion.size()
					|| indexAltaModifTitDoCo <= aseguramiento.size() || indexAltaModifTitDoCo <= indicadorFarmacia.size()
					|| indexAltaModifTitDoCo <= codigoBadasRepetido.size() || indexAltaModifTitDoCo <= altasSinNaf.size()
					|| indexAltaModifTitDoCo <= cambiosIpf.size() || indexAltaModifTitDoCo <= ipfNuevoExisteInssTit.size() 
					|| indexAltaModifTitDoCo <= ipfAnteriorNoExisteInssMut.size() || indexAltaModifTitDoCo <= ipfNuevoExiteInssMut.size()
					|| indexAltaModifTitDoCo <= titularesDobleCobertura.size() || indexAltaModifTitDoCo <= beneficiarioDobleCoberturaMutualistas.size()
					|| indexAltaModifTitDoCo <= titularesIndicadorConvenioRural.size() || indexAltaModifTitDoCo <= beneficiadioIndicadorConvenioRural.size()
					|| indexAltaModifTitDoCo <= privadoRegularPublico.size() || indexAltaModifTitDoCo <= privadoIrregularPublico.size()
					|| indexAltaModifTitDoCo <= privadoNoExiste.size())
				
				row = sheet.getRow(indexAltaModifTitDoCo++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexAltaModifTitDoCo++);
			
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(344).setCellValue(separar[i]);
			}				
		}
		
		
	int  indexIrrPrivRePublico= 1;		
		
		for (String a : irregularPrivadoRegularPublico) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexIrrPrivRePublico <= tipoIdentificador.size() || indexIrrPrivRePublico <= tipoMovimiento.size()
					|| indexIrrPrivRePublico <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexIrrPrivRePublico <= registroIpfNulo.size() || indexIrrPrivRePublico <= informeAltaCruzado.size()
					|| indexIrrPrivRePublico <= codTipoAsegurado.size() || indexIrrPrivRePublico <= titDobleCobertura.size()
					|| indexIrrPrivRePublico <= beneDobleCobertura.size()
					|| indexIrrPrivRePublico <= tipoIdentificadorMutualistas.size()
					|| indexIrrPrivRePublico <= tipoMovimientoMutualista.size()
					|| indexIrrPrivRePublico <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexIrrPrivRePublico <= registrosIpfNuloMutualistas.size()
					|| indexIrrPrivRePublico <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexIrrPrivRePublico <= codTipoAseguradoMutualistas.size()
					|| indexIrrPrivRePublico <= regimenGeneral.size() || indexIrrPrivRePublico <= bajaTitulares.size()
					|| indexIrrPrivRePublico <= totalEnvioInssAv.size() || indexIrrPrivRePublico <= bajasVinculadosSns.size()
					|| indexIrrPrivRePublico <= mutualistasPrivados.size() || indexIrrPrivRePublico <= bajaPorDefuncion.size()
					|| indexIrrPrivRePublico <= aseguramiento.size() || indexIrrPrivRePublico <= indicadorFarmacia.size()
					|| indexIrrPrivRePublico <= codigoBadasRepetido.size() || indexIrrPrivRePublico <= altasSinNaf.size()
					|| indexIrrPrivRePublico <= cambiosIpf.size() || indexIrrPrivRePublico <= ipfNuevoExisteInssTit.size() 
					|| indexIrrPrivRePublico <= ipfAnteriorNoExisteInssMut.size() || indexIrrPrivRePublico <= ipfNuevoExiteInssMut.size()
					|| indexIrrPrivRePublico <= titularesDobleCobertura.size() || indexIrrPrivRePublico <= beneficiarioDobleCoberturaMutualistas.size()
					|| indexIrrPrivRePublico <= titularesIndicadorConvenioRural.size() || indexIrrPrivRePublico <= beneficiadioIndicadorConvenioRural.size()
					|| indexIrrPrivRePublico <= privadoRegularPublico.size() || indexIrrPrivRePublico <= privadoIrregularPublico.size()
					|| indexIrrPrivRePublico <= privadoNoExiste.size() || indexIrrPrivRePublico <= altaModificacionTitularesDobleCobertura.size() )
				
				row = sheet.getRow(indexIrrPrivRePublico++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexIrrPrivRePublico++);
			
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(345).setCellValue(separar[i]);
			}				
		}
		

		
	int  indexIrrPrivIrPublico= 1;		
		
		for (String a : irregularPrivadoIrregularPublico) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexIrrPrivIrPublico <= tipoIdentificador.size() || indexIrrPrivIrPublico <= tipoMovimiento.size()
					|| indexIrrPrivIrPublico <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexIrrPrivIrPublico <= registroIpfNulo.size() || indexIrrPrivIrPublico <= informeAltaCruzado.size()
					|| indexIrrPrivIrPublico <= codTipoAsegurado.size() || indexIrrPrivIrPublico <= titDobleCobertura.size()
					|| indexIrrPrivIrPublico <= beneDobleCobertura.size()
					|| indexIrrPrivIrPublico <= tipoIdentificadorMutualistas.size()
					|| indexIrrPrivIrPublico <= tipoMovimientoMutualista.size()
					|| indexIrrPrivIrPublico <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexIrrPrivIrPublico <= registrosIpfNuloMutualistas.size()
					|| indexIrrPrivIrPublico <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexIrrPrivIrPublico <= codTipoAseguradoMutualistas.size()
					|| indexIrrPrivIrPublico <= regimenGeneral.size() || indexIrrPrivIrPublico <= bajaTitulares.size()
					|| indexIrrPrivIrPublico <= totalEnvioInssAv.size() || indexIrrPrivIrPublico <= bajasVinculadosSns.size()
					|| indexIrrPrivIrPublico <= mutualistasPrivados.size() || indexIrrPrivIrPublico <= bajaPorDefuncion.size()
					|| indexIrrPrivIrPublico <= aseguramiento.size() || indexIrrPrivIrPublico <= indicadorFarmacia.size()
					|| indexIrrPrivIrPublico <= codigoBadasRepetido.size() || indexIrrPrivIrPublico <= altasSinNaf.size()
					|| indexIrrPrivIrPublico <= cambiosIpf.size() || indexIrrPrivIrPublico <= ipfNuevoExisteInssTit.size() 
					|| indexIrrPrivIrPublico <= ipfAnteriorNoExisteInssMut.size() || indexIrrPrivIrPublico <= ipfNuevoExiteInssMut.size()
					|| indexIrrPrivIrPublico <= titularesDobleCobertura.size() || indexIrrPrivIrPublico <= beneficiarioDobleCoberturaMutualistas.size()
					|| indexIrrPrivIrPublico <= titularesIndicadorConvenioRural.size() || indexIrrPrivIrPublico <= beneficiadioIndicadorConvenioRural.size()
					|| indexIrrPrivIrPublico <= privadoRegularPublico.size() || indexIrrPrivIrPublico <= privadoIrregularPublico.size()
					|| indexIrrPrivIrPublico <= privadoNoExiste.size() || indexIrrPrivIrPublico <= altaModificacionTitularesDobleCobertura.size()
					|| indexIrrPrivIrPublico <= irregularPrivadoRegularPublico.size())
				
				row = sheet.getRow(indexIrrPrivIrPublico++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexIrrPrivIrPublico++);
			
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(346).setCellValue(separar[i]);
			}				
		}
		
		
	int  indexIrrPriIrrPublico= 1;		
		
		for (String a : irregularPrivadoIrregularPublico) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexIrrPriIrrPublico <= tipoIdentificador.size() || indexIrrPriIrrPublico <= tipoMovimiento.size()
					|| indexIrrPriIrrPublico <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexIrrPriIrrPublico <= registroIpfNulo.size() || indexIrrPriIrrPublico <= informeAltaCruzado.size()
					|| indexIrrPriIrrPublico <= codTipoAsegurado.size() || indexIrrPriIrrPublico <= titDobleCobertura.size()
					|| indexIrrPriIrrPublico <= beneDobleCobertura.size()
					|| indexIrrPriIrrPublico <= tipoIdentificadorMutualistas.size()
					|| indexIrrPriIrrPublico <= tipoMovimientoMutualista.size()
					|| indexIrrPriIrrPublico <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexIrrPriIrrPublico <= registrosIpfNuloMutualistas.size()
					|| indexIrrPriIrrPublico <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexIrrPriIrrPublico <= codTipoAseguradoMutualistas.size()
					|| indexIrrPriIrrPublico <= regimenGeneral.size() || indexIrrPriIrrPublico <= bajaTitulares.size()
					|| indexIrrPriIrrPublico <= totalEnvioInssAv.size() || indexIrrPriIrrPublico <= bajasVinculadosSns.size()
					|| indexIrrPriIrrPublico <= mutualistasPrivados.size() || indexIrrPriIrrPublico <= bajaPorDefuncion.size()
					|| indexIrrPriIrrPublico <= aseguramiento.size() || indexIrrPriIrrPublico <= indicadorFarmacia.size()
					|| indexIrrPriIrrPublico <= codigoBadasRepetido.size() || indexIrrPriIrrPublico <= altasSinNaf.size()
					|| indexIrrPriIrrPublico <= cambiosIpf.size() || indexIrrPriIrrPublico <= ipfNuevoExisteInssTit.size() 
					|| indexIrrPriIrrPublico <= ipfAnteriorNoExisteInssMut.size() || indexIrrPriIrrPublico <= ipfNuevoExiteInssMut.size()
					|| indexIrrPriIrrPublico <= titularesDobleCobertura.size() || indexIrrPriIrrPublico <= beneficiarioDobleCoberturaMutualistas.size()
					|| indexIrrPriIrrPublico <= titularesIndicadorConvenioRural.size() || indexIrrPriIrrPublico <= beneficiadioIndicadorConvenioRural.size()
					|| indexIrrPriIrrPublico <= privadoRegularPublico.size() || indexIrrPriIrrPublico <= privadoIrregularPublico.size()
					|| indexIrrPriIrrPublico <= privadoNoExiste.size() || indexIrrPriIrrPublico <= altaModificacionTitularesDobleCobertura.size()
					|| indexIrrPriIrrPublico <= irregularPrivadoRegularPublico.size() || indexIrrPriIrrPublico <= irregularPrivadoIrregularPublico.size())
				
				row = sheet.getRow(indexIrrPriIrrPublico++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexIrrPriIrrPublico++);
			
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(347).setCellValue(separar[i]);
			}				
		}
		

		
	int  indexIrregularPrivadoNoExiste= 1;		
		
		for (String a : irregularPrivadoNoExiste) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexIrregularPrivadoNoExiste <= tipoIdentificador.size() || indexIrregularPrivadoNoExiste <= tipoMovimiento.size()
					|| indexIrregularPrivadoNoExiste <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexIrregularPrivadoNoExiste <= registroIpfNulo.size() || indexIrregularPrivadoNoExiste <= informeAltaCruzado.size()
					|| indexIrregularPrivadoNoExiste <= codTipoAsegurado.size() || indexIrregularPrivadoNoExiste <= titDobleCobertura.size()
					|| indexIrregularPrivadoNoExiste <= beneDobleCobertura.size()
					|| indexIrregularPrivadoNoExiste <= tipoIdentificadorMutualistas.size()
					|| indexIrregularPrivadoNoExiste <= tipoMovimientoMutualista.size()
					|| indexIrregularPrivadoNoExiste <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexIrregularPrivadoNoExiste <= registrosIpfNuloMutualistas.size()
					|| indexIrregularPrivadoNoExiste <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexIrregularPrivadoNoExiste <= codTipoAseguradoMutualistas.size()
					|| indexIrregularPrivadoNoExiste <= regimenGeneral.size() || indexIrregularPrivadoNoExiste <= bajaTitulares.size()
					|| indexIrregularPrivadoNoExiste <= totalEnvioInssAv.size() || indexIrregularPrivadoNoExiste <= bajasVinculadosSns.size()
					|| indexIrregularPrivadoNoExiste <= mutualistasPrivados.size() || indexIrregularPrivadoNoExiste <= bajaPorDefuncion.size()
					|| indexIrregularPrivadoNoExiste <= aseguramiento.size() || indexIrregularPrivadoNoExiste <= indicadorFarmacia.size()
					|| indexIrregularPrivadoNoExiste <= codigoBadasRepetido.size() || indexIrregularPrivadoNoExiste <= altasSinNaf.size()
					|| indexIrregularPrivadoNoExiste <= cambiosIpf.size() || indexIrregularPrivadoNoExiste <= ipfNuevoExisteInssTit.size() 
					|| indexIrregularPrivadoNoExiste <= ipfAnteriorNoExisteInssMut.size() || indexIrregularPrivadoNoExiste <= ipfNuevoExiteInssMut.size()
					|| indexIrregularPrivadoNoExiste <= titularesDobleCobertura.size() || indexIrregularPrivadoNoExiste <= beneficiarioDobleCoberturaMutualistas.size()
					|| indexIrregularPrivadoNoExiste <= titularesIndicadorConvenioRural.size() || indexIrregularPrivadoNoExiste <= beneficiadioIndicadorConvenioRural.size()
					|| indexIrregularPrivadoNoExiste <= privadoRegularPublico.size() || indexIrregularPrivadoNoExiste <= privadoIrregularPublico.size()
					|| indexIrregularPrivadoNoExiste <= privadoNoExiste.size() || indexIrregularPrivadoNoExiste <= altaModificacionTitularesDobleCobertura.size()
					|| indexIrregularPrivadoNoExiste <= irregularPrivadoRegularPublico.size() || indexIrregularPrivadoNoExiste <= irregularPrivadoIrregularPublico.size()
					|| indexIrregularPrivadoNoExiste <= irregularPrivadoIrregularPublico.size())
				
				row = sheet.getRow(indexIrregularPrivadoNoExiste++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexIrregularPrivadoNoExiste++);
			
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(348).setCellValue(separar[i]);
			}				
		}
				
		
	int  indexAltModTitsDobCoberturaDos= 1;		
		
		for (String a : altaModificaTitualresDobleCoberturaDos) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexAltModTitsDobCoberturaDos <= tipoIdentificador.size() || indexAltModTitsDobCoberturaDos <= tipoMovimiento.size()
					|| indexAltModTitsDobCoberturaDos <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexAltModTitsDobCoberturaDos <= registroIpfNulo.size() || indexAltModTitsDobCoberturaDos <= informeAltaCruzado.size()
					|| indexAltModTitsDobCoberturaDos <= codTipoAsegurado.size() || indexAltModTitsDobCoberturaDos <= titDobleCobertura.size()
					|| indexAltModTitsDobCoberturaDos <= beneDobleCobertura.size()
					|| indexAltModTitsDobCoberturaDos <= tipoIdentificadorMutualistas.size()
					|| indexAltModTitsDobCoberturaDos <= tipoMovimientoMutualista.size()
					|| indexAltModTitsDobCoberturaDos <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexAltModTitsDobCoberturaDos <= registrosIpfNuloMutualistas.size()
					|| indexAltModTitsDobCoberturaDos <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexAltModTitsDobCoberturaDos <= codTipoAseguradoMutualistas.size()
					|| indexAltModTitsDobCoberturaDos <= regimenGeneral.size() || indexAltModTitsDobCoberturaDos <= bajaTitulares.size()
					|| indexAltModTitsDobCoberturaDos <= totalEnvioInssAv.size() || indexAltModTitsDobCoberturaDos <= bajasVinculadosSns.size()
					|| indexAltModTitsDobCoberturaDos <= mutualistasPrivados.size() || indexAltModTitsDobCoberturaDos <= bajaPorDefuncion.size()
					|| indexAltModTitsDobCoberturaDos <= aseguramiento.size() || indexAltModTitsDobCoberturaDos <= indicadorFarmacia.size()
					|| indexAltModTitsDobCoberturaDos <= codigoBadasRepetido.size() || indexAltModTitsDobCoberturaDos <= altasSinNaf.size()
					|| indexAltModTitsDobCoberturaDos <= cambiosIpf.size() || indexAltModTitsDobCoberturaDos <= ipfNuevoExisteInssTit.size() 
					|| indexAltModTitsDobCoberturaDos <= ipfAnteriorNoExisteInssMut.size() || indexAltModTitsDobCoberturaDos <= ipfNuevoExiteInssMut.size()
					|| indexAltModTitsDobCoberturaDos <= titularesDobleCobertura.size() || indexAltModTitsDobCoberturaDos <= beneficiarioDobleCoberturaMutualistas.size()
					|| indexAltModTitsDobCoberturaDos <= titularesIndicadorConvenioRural.size() || indexAltModTitsDobCoberturaDos <= beneficiadioIndicadorConvenioRural.size()
					|| indexAltModTitsDobCoberturaDos <= privadoRegularPublico.size() || indexAltModTitsDobCoberturaDos <= privadoIrregularPublico.size()
					|| indexAltModTitsDobCoberturaDos <= privadoNoExiste.size() || indexAltModTitsDobCoberturaDos <= altaModificacionTitularesDobleCobertura.size()
					|| indexAltModTitsDobCoberturaDos <= irregularPrivadoRegularPublico.size() || indexAltModTitsDobCoberturaDos <= irregularPrivadoIrregularPublico.size()
					|| indexAltModTitsDobCoberturaDos <= irregularPrivadoIrregularPublico.size() || indexAltModTitsDobCoberturaDos <= irregularPrivadoNoExiste.size())
				
				row = sheet.getRow(indexAltModTitsDobCoberturaDos++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexAltModTitsDobCoberturaDos++);
			
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(349).setCellValue(separar[i]);
			}				
		}	
		
		
	int  indexListadoRegistroNoExiste= 1;		
		
		for (String a : listadoRegistroNoExiste) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexListadoRegistroNoExiste <= tipoIdentificador.size() || indexListadoRegistroNoExiste <= tipoMovimiento.size()
					|| indexListadoRegistroNoExiste <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexListadoRegistroNoExiste <= registroIpfNulo.size() || indexListadoRegistroNoExiste <= informeAltaCruzado.size()
					|| indexListadoRegistroNoExiste <= codTipoAsegurado.size() || indexListadoRegistroNoExiste <= titDobleCobertura.size()
					|| indexListadoRegistroNoExiste <= beneDobleCobertura.size()
					|| indexListadoRegistroNoExiste <= tipoIdentificadorMutualistas.size()
					|| indexListadoRegistroNoExiste <= tipoMovimientoMutualista.size()
					|| indexListadoRegistroNoExiste <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexListadoRegistroNoExiste <= registrosIpfNuloMutualistas.size()
					|| indexListadoRegistroNoExiste <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexListadoRegistroNoExiste <= codTipoAseguradoMutualistas.size()
					|| indexListadoRegistroNoExiste <= regimenGeneral.size() || indexListadoRegistroNoExiste <= bajaTitulares.size()
					|| indexListadoRegistroNoExiste <= totalEnvioInssAv.size() || indexListadoRegistroNoExiste <= bajasVinculadosSns.size()
					|| indexListadoRegistroNoExiste <= mutualistasPrivados.size() || indexListadoRegistroNoExiste <= bajaPorDefuncion.size()
					|| indexListadoRegistroNoExiste <= aseguramiento.size() || indexListadoRegistroNoExiste <= indicadorFarmacia.size()
					|| indexListadoRegistroNoExiste <= codigoBadasRepetido.size() || indexListadoRegistroNoExiste <= altasSinNaf.size()
					|| indexListadoRegistroNoExiste <= cambiosIpf.size() || indexListadoRegistroNoExiste <= ipfNuevoExisteInssTit.size() 
					|| indexListadoRegistroNoExiste <= ipfAnteriorNoExisteInssMut.size() || indexListadoRegistroNoExiste <= ipfNuevoExiteInssMut.size()
					|| indexListadoRegistroNoExiste <= titularesDobleCobertura.size() || indexListadoRegistroNoExiste <= beneficiarioDobleCoberturaMutualistas.size()
					|| indexListadoRegistroNoExiste <= titularesIndicadorConvenioRural.size() || indexListadoRegistroNoExiste <= beneficiadioIndicadorConvenioRural.size()
					|| indexListadoRegistroNoExiste <= privadoRegularPublico.size() || indexListadoRegistroNoExiste <= privadoIrregularPublico.size()
					|| indexListadoRegistroNoExiste <= privadoNoExiste.size() || indexListadoRegistroNoExiste <= altaModificacionTitularesDobleCobertura.size()
					|| indexListadoRegistroNoExiste <= irregularPrivadoRegularPublico.size() || indexListadoRegistroNoExiste <= irregularPrivadoIrregularPublico.size()
					|| indexListadoRegistroNoExiste <= irregularPrivadoIrregularPublico.size() || indexListadoRegistroNoExiste <= irregularPrivadoNoExiste.size()
					|| indexListadoRegistroNoExiste <= altaModificaTitualresDobleCoberturaDos.size())
				
				row = sheet.getRow(indexListadoRegistroNoExiste++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexListadoRegistroNoExiste++);
			
			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(349).setCellValue(separar[0]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(350).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(351).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(352).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(353).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(354).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(355).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(356).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(357).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(358).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(359).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(360).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(361).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(362).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(363).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(364).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(365).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(366).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(367).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(368).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(369).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(370).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(371).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(372).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(373).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(374).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(375).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(376).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(377).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(378).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(379).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(380).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(381).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(382).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(383).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(384).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(385).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(386).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(387).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(388).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(389).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(390).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(391).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(392).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(393).setCellValue(separar[i]);
			}	
		}
		
		
	int  indexlistadoRegistroNoExisteDos= 1;		
		
		for (String a : listadoRegistroNoExisteDos) {
			String[] separar = a.split(",");

			int i = 0;
			
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexlistadoRegistroNoExisteDos <= tipoIdentificador.size() || indexlistadoRegistroNoExisteDos <= tipoMovimiento.size()
					|| indexlistadoRegistroNoExisteDos <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexlistadoRegistroNoExisteDos <= registroIpfNulo.size() || indexlistadoRegistroNoExisteDos <= informeAltaCruzado.size()
					|| indexlistadoRegistroNoExisteDos <= codTipoAsegurado.size() || indexlistadoRegistroNoExisteDos <= titDobleCobertura.size()
					|| indexlistadoRegistroNoExisteDos <= beneDobleCobertura.size()
					|| indexlistadoRegistroNoExisteDos <= tipoIdentificadorMutualistas.size()
					|| indexlistadoRegistroNoExisteDos <= tipoMovimientoMutualista.size()
					|| indexlistadoRegistroNoExisteDos <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexlistadoRegistroNoExisteDos <= registrosIpfNuloMutualistas.size()
					|| indexlistadoRegistroNoExisteDos <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexlistadoRegistroNoExisteDos <= codTipoAseguradoMutualistas.size()
					|| indexlistadoRegistroNoExisteDos <= regimenGeneral.size() || indexlistadoRegistroNoExisteDos <= bajaTitulares.size()
					|| indexlistadoRegistroNoExisteDos <= totalEnvioInssAv.size() || indexlistadoRegistroNoExisteDos <= bajasVinculadosSns.size()
					|| indexlistadoRegistroNoExisteDos <= mutualistasPrivados.size() || indexlistadoRegistroNoExisteDos <= bajaPorDefuncion.size()
					|| indexlistadoRegistroNoExisteDos <= aseguramiento.size() || indexlistadoRegistroNoExisteDos <= indicadorFarmacia.size()
					|| indexlistadoRegistroNoExisteDos <= codigoBadasRepetido.size() || indexlistadoRegistroNoExisteDos <= altasSinNaf.size()
					|| indexlistadoRegistroNoExisteDos <= cambiosIpf.size() || indexlistadoRegistroNoExisteDos <= ipfNuevoExisteInssTit.size() 
					|| indexlistadoRegistroNoExisteDos <= ipfAnteriorNoExisteInssMut.size() || indexlistadoRegistroNoExisteDos <= ipfNuevoExiteInssMut.size()
					|| indexlistadoRegistroNoExisteDos <= titularesDobleCobertura.size() || indexlistadoRegistroNoExisteDos <= beneficiarioDobleCoberturaMutualistas.size()
					|| indexlistadoRegistroNoExisteDos <= titularesIndicadorConvenioRural.size() || indexlistadoRegistroNoExisteDos <= beneficiadioIndicadorConvenioRural.size()
					|| indexlistadoRegistroNoExisteDos <= privadoRegularPublico.size() || indexlistadoRegistroNoExisteDos <= privadoIrregularPublico.size()
					|| indexlistadoRegistroNoExisteDos <= privadoNoExiste.size() || indexlistadoRegistroNoExisteDos <= altaModificacionTitularesDobleCobertura.size()
					|| indexlistadoRegistroNoExisteDos <= irregularPrivadoRegularPublico.size() || indexlistadoRegistroNoExisteDos <= irregularPrivadoIrregularPublico.size()
					|| indexlistadoRegistroNoExisteDos <= irregularPrivadoIrregularPublico.size() || indexlistadoRegistroNoExisteDos <= irregularPrivadoNoExiste.size()
					|| indexlistadoRegistroNoExisteDos <= altaModificaTitualresDobleCoberturaDos.size() || indexlistadoRegistroNoExisteDos <= listadoRegistroNoExiste.size())
				
				row = sheet.getRow(indexlistadoRegistroNoExisteDos++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexlistadoRegistroNoExisteDos++);
			
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(394).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(395).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(396).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(397).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(398).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(399).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(400).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(401).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(402).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(403).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(404).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(405).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(406).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(407).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(408).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(409).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(410).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(411).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(412).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(413).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(414).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(415).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(416).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(417).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(418).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(419).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(420).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(421).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(422).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(423).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(424).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(425).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(426).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(427).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(428).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(429).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(430).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(431).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(432).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(433).setCellValue(separar[i]);
			}
			i++;
			if (separar[40].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(434).setCellValue(separar[40]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(435).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(436).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(437).setCellValue(separar[i]);
			}
			i++;
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(438).setCellValue(separar[i]);
			}			
		}
				
		
	int  indexBajaPorDefuncionPrivadoPublico= 1;		
		
		for (String a : bajaPorDefuncionPrivadoPublico) {
			String[] separar = a.split(",");

			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexBajaPorDefuncionPrivadoPublico <= tipoIdentificador.size() || indexBajaPorDefuncionPrivadoPublico <= tipoMovimiento.size()
					|| indexBajaPorDefuncionPrivadoPublico <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexBajaPorDefuncionPrivadoPublico <= registroIpfNulo.size() || indexBajaPorDefuncionPrivadoPublico <= informeAltaCruzado.size()
					|| indexBajaPorDefuncionPrivadoPublico <= codTipoAsegurado.size() || indexBajaPorDefuncionPrivadoPublico <= titDobleCobertura.size()
					|| indexBajaPorDefuncionPrivadoPublico <= beneDobleCobertura.size()
					|| indexBajaPorDefuncionPrivadoPublico <= tipoIdentificadorMutualistas.size()
					|| indexBajaPorDefuncionPrivadoPublico <= tipoMovimientoMutualista.size()
					|| indexBajaPorDefuncionPrivadoPublico <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexBajaPorDefuncionPrivadoPublico <= registrosIpfNuloMutualistas.size()
					|| indexBajaPorDefuncionPrivadoPublico <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexBajaPorDefuncionPrivadoPublico <= codTipoAseguradoMutualistas.size()
					|| indexBajaPorDefuncionPrivadoPublico <= regimenGeneral.size() || indexBajaPorDefuncionPrivadoPublico <= bajaTitulares.size()
					|| indexBajaPorDefuncionPrivadoPublico <= totalEnvioInssAv.size() || indexBajaPorDefuncionPrivadoPublico <= bajasVinculadosSns.size()
					|| indexBajaPorDefuncionPrivadoPublico <= mutualistasPrivados.size() || indexBajaPorDefuncionPrivadoPublico <= bajaPorDefuncion.size()
					|| indexBajaPorDefuncionPrivadoPublico <= aseguramiento.size() || indexBajaPorDefuncionPrivadoPublico <= indicadorFarmacia.size()
					|| indexBajaPorDefuncionPrivadoPublico <= codigoBadasRepetido.size() || indexBajaPorDefuncionPrivadoPublico <= altasSinNaf.size()
					|| indexBajaPorDefuncionPrivadoPublico <= cambiosIpf.size() || indexBajaPorDefuncionPrivadoPublico <= ipfNuevoExisteInssTit.size() 
					|| indexBajaPorDefuncionPrivadoPublico <= ipfAnteriorNoExisteInssMut.size() || indexBajaPorDefuncionPrivadoPublico <= ipfNuevoExiteInssMut.size()
					|| indexBajaPorDefuncionPrivadoPublico <= titularesDobleCobertura.size() || indexBajaPorDefuncionPrivadoPublico <= beneficiarioDobleCoberturaMutualistas.size()
					|| indexBajaPorDefuncionPrivadoPublico <= titularesIndicadorConvenioRural.size() || indexBajaPorDefuncionPrivadoPublico <= beneficiadioIndicadorConvenioRural.size()
					|| indexBajaPorDefuncionPrivadoPublico <= privadoRegularPublico.size() || indexBajaPorDefuncionPrivadoPublico <= privadoIrregularPublico.size()
					|| indexBajaPorDefuncionPrivadoPublico <= privadoNoExiste.size() || indexBajaPorDefuncionPrivadoPublico <= altaModificacionTitularesDobleCobertura.size()
					|| indexBajaPorDefuncionPrivadoPublico <= irregularPrivadoRegularPublico.size() || indexBajaPorDefuncionPrivadoPublico <= irregularPrivadoIrregularPublico.size()
					|| indexBajaPorDefuncionPrivadoPublico <= irregularPrivadoIrregularPublico.size() || indexBajaPorDefuncionPrivadoPublico <= irregularPrivadoNoExiste.size()
					|| indexBajaPorDefuncionPrivadoPublico <= altaModificaTitualresDobleCoberturaDos.size() || indexBajaPorDefuncionPrivadoPublico <= listadoRegistroNoExiste.size()
					|| indexBajaPorDefuncionPrivadoPublico <= listadoRegistroNoExiste.size() || indexBajaPorDefuncionPrivadoPublico <= listadoRegistroNoExisteDos.size())
				
				row = sheet.getRow(indexBajaPorDefuncionPrivadoPublico++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexBajaPorDefuncionPrivadoPublico++);
			
			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(439).setCellValue(separar[0]);
			}		
		}
		
	int  indexBajaPorDefuncionPrivadoNoExiste= 1;		
		
		for (String a : bajaPorDefuncionPrivadoNoExiste) {
			String[] separar = a.split(",");

			int i = 0;
			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexBajaPorDefuncionPrivadoNoExiste <= tipoIdentificador.size() || indexBajaPorDefuncionPrivadoNoExiste <= tipoMovimiento.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= registroIpfNulo.size() || indexBajaPorDefuncionPrivadoNoExiste <= informeAltaCruzado.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= codTipoAsegurado.size() || indexBajaPorDefuncionPrivadoNoExiste <= titDobleCobertura.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= beneDobleCobertura.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= tipoIdentificadorMutualistas.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= tipoMovimientoMutualista.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= registrosIpfNuloMutualistas.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= informeAltCruzadoConSegSocialFinalMutualistas.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= codTipoAseguradoMutualistas.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= regimenGeneral.size() || indexBajaPorDefuncionPrivadoNoExiste <= bajaTitulares.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= totalEnvioInssAv.size() || indexBajaPorDefuncionPrivadoNoExiste <= bajasVinculadosSns.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= mutualistasPrivados.size() || indexBajaPorDefuncionPrivadoNoExiste <= bajaPorDefuncion.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= aseguramiento.size() || indexBajaPorDefuncionPrivadoNoExiste <= indicadorFarmacia.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= codigoBadasRepetido.size() || indexBajaPorDefuncionPrivadoNoExiste <= altasSinNaf.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= cambiosIpf.size() || indexBajaPorDefuncionPrivadoNoExiste <= ipfNuevoExisteInssTit.size() 
					|| indexBajaPorDefuncionPrivadoNoExiste <= ipfAnteriorNoExisteInssMut.size() || indexBajaPorDefuncionPrivadoNoExiste <= ipfNuevoExiteInssMut.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= titularesDobleCobertura.size() || indexBajaPorDefuncionPrivadoNoExiste <= beneficiarioDobleCoberturaMutualistas.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= titularesIndicadorConvenioRural.size() || indexBajaPorDefuncionPrivadoNoExiste <= beneficiadioIndicadorConvenioRural.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= privadoRegularPublico.size() || indexBajaPorDefuncionPrivadoNoExiste <= privadoIrregularPublico.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= privadoNoExiste.size() || indexBajaPorDefuncionPrivadoNoExiste <= altaModificacionTitularesDobleCobertura.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= irregularPrivadoRegularPublico.size() || indexBajaPorDefuncionPrivadoNoExiste <= irregularPrivadoIrregularPublico.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= irregularPrivadoIrregularPublico.size() || indexBajaPorDefuncionPrivadoNoExiste <= irregularPrivadoNoExiste.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= altaModificaTitualresDobleCoberturaDos.size() || indexBajaPorDefuncionPrivadoNoExiste <= listadoRegistroNoExiste.size()
					|| indexBajaPorDefuncionPrivadoNoExiste <= listadoRegistroNoExisteDos.size() || indexBajaPorDefuncionPrivadoNoExiste <= bajaPorDefuncionPrivadoPublico.size())
				
				row = sheet.getRow(indexBajaPorDefuncionPrivadoNoExiste++);
			else
				// En caso de que no exista la fila se crea.
				row = sheet.createRow(indexBajaPorDefuncionPrivadoNoExiste++);
			
			if (separar[i].compareTo("null") == 0) {
				separar[i] = "";
			} else {
				row.createCell(440).setCellValue(separar[i]);
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
