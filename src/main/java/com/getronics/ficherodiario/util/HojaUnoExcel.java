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
			List<String> bajaPorDefuncion, List<String> aseguramiento, List<String> indicadorFarmacia, List<String> codigoBadasRepetido) {

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

		for (String a : tipoIdentificador) {
			String[] separar = a.split(",");
			row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(separar[0]);
			row.createCell(1).setCellValue(separar[1]);
		}

		// Se crea un indice para comparar con las filas.
		int indexMovimiento = 1;

		for (String a : tipoMovimiento) {
			String[] separar = a.split(",");

			// Si es menor o igual se hace obtiene las filas ya creadas
			if (indexMovimiento <= tipoIdentificador.size())
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
			if (indexProtegidoTip <= tipoIdentificador.size() || indexProtegidoTip <= tipoMovimiento.size())
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
			if (indexRegiIpfNulo <= tipoIdentificador.size() || indexRegiIpfNulo <= tipoMovimiento.size()
					|| indexRegiIpfNulo <= protegidoTipAseguramientoAndTipMovimiento.size())

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
			if (indexInforAltCruzado <= tipoIdentificador.size() || indexInforAltCruzado <= tipoMovimiento.size()
					|| indexInforAltCruzado <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexInforAltCruzado <= registroIpfNulo.size())

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
			if (indexCodTipAseg <= tipoIdentificador.size() || indexCodTipAseg <= tipoMovimiento.size()
					|| indexCodTipAseg <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexCodTipAseg <= registroIpfNulo.size() || indexCodTipAseg <= informeAltaCruzado.size())

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
			if (indexTitDoCob <= tipoIdentificador.size() || indexTitDoCob <= tipoMovimiento.size()
					|| indexTitDoCob <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexTitDoCob <= registroIpfNulo.size() || indexTitDoCob <= informeAltaCruzado.size()
					|| indexTitDoCob <= codTipoAsegurado.size())

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
			if (indexBeneDobCob <= tipoIdentificador.size() || indexBeneDobCob <= tipoMovimiento.size()
					|| indexBeneDobCob <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexBeneDobCob <= registroIpfNulo.size() || indexBeneDobCob <= informeAltaCruzado.size()
					|| indexBeneDobCob <= codTipoAsegurado.size() || indexBeneDobCob <= titDobleCobertura.size())

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
			if (indexTiIdMut <= tipoIdentificador.size() || indexTiIdMut <= tipoMovimiento.size()
					|| indexTiIdMut <= protegidoTipAseguramientoAndTipMovimiento.size()
					|| indexTiIdMut <= registroIpfNulo.size() || indexTiIdMut <= informeAltaCruzado.size()
					|| indexTiIdMut <= codTipoAsegurado.size() || indexTiIdMut <= titDobleCobertura.size()
					|| indexTiIdMut <= beneDobleCobertura.size())

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

		int indexTipMoviMut = 1;

		for (String a : tipoMovimientoMutualista) {
			String[] separar = a.split(",");

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

		int indexProtegidoTipAseg = 1;

		for (String a : protegidoTipAseguramientoAndTipMovimientoMutualistas) {
			String[] separar = a.split(",");

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

			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(29).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(30).setCellValue(separar[1]);
			}
			if (separar[2].compareTo("null") == 0) {
				separar[2] = "";
			} else {
				row.createCell(31).setCellValue(separar[2]);
			}
			if (separar[3].compareTo("null") == 0) {
				separar[3] = "";
			} else {
				row.createCell(32).setCellValue(separar[3]);
			}
		}

		int indexRegIpfNu = 1;

		for (String a : registrosIpfNuloMutualistas) {
			String[] separar = a.split(",");

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

			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(33).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(34).setCellValue(separar[1]);
			}
			if (separar[2].compareTo("null") == 0) {
				separar[2] = "";
			} else {
				row.createCell(35).setCellValue(separar[2]);
			}
			if (separar[3].compareTo("null") == 0) {
				separar[3] = "";
			} else {
				row.createCell(36).setCellValue(separar[3]);
			}
		}

		int indexInfFin = 1;

		for (String a : informeAltCruzadoConSegSocialFinalMutualistas) {
			String[] separar = a.split(",");

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

			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(37).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(38).setCellValue(separar[1]);
			}
			if (separar[2].compareTo("null") == 0) {
				separar[2] = "";
			} else {
				row.createCell(39).setCellValue(separar[2]);
			}
		}

		int indexCod = 1;

		for (String a : codTipoAseguradoMutualistas) {
			String[] separar = a.split(",");

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

			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(40).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(41).setCellValue(separar[1]);
			}
		}

		// Regimen General

		int indexRegGen = 1;

		for (String a : regimenGeneral) {
			String[] separar = a.split(",");

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

			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(42).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(43).setCellValue(separar[1]);
			}
			if (separar[2].compareTo("null") == 0) {
				separar[2] = "";
			} else {
				row.createCell(44).setCellValue(separar[2]);
			}
		}

		int indexbajaTitulares = 1;

		for (String a : bajaTitulares) {
			String[] separar = a.split(",");

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

			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(45).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(46).setCellValue(separar[1]);
			}
		}

		int indexTotEnvInssAv = 1;

		for (String a : totalEnvioInssAv) {
			String[] separar = a.split(",");

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

			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(47).setCellValue(separar[0]);
			}
		}
		
		int indexBajasVinculadosSns = 1;

		for (String a : bajasVinculadosSns) {
			String[] separar = a.split(",");

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

			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(48).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(49).setCellValue(separar[1]);
			}
		}
		
		int indexMutualistasPrivados = 1;

		for (String a : mutualistasPrivados) {
			String[] separar = a.split(",");

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

			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(50).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(51).setCellValue(separar[1]);
			}
			if (separar[2].compareTo("null") == 0) {
				separar[2] = "";
			} else {
				row.createCell(52).setCellValue(separar[2]);
			}
		}
				
		int indexBajaPorDefuncion = 1;

		for (String a : bajaPorDefuncion) {
			String[] separar = a.split(",");

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

			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(53).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(54).setCellValue(separar[1]);
			}			
		}		
		
		int indexAseguramiento = 1;

		for (String a : aseguramiento) {
			String[] separar = a.split(",");

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

			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(55).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(56).setCellValue(separar[1]);
			}		
			if (separar[2].compareTo("null") == 0) {
				separar[2] = "";
			} else {
				row.createCell(57).setCellValue(separar[2]);
			}
		}
		
		
		int indexIndicadorFarmacia = 1;

		for (String a : indicadorFarmacia) {
			String[] separar = a.split(",");

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

			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(58).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(59).setCellValue(separar[1]);
			}		
			if (separar[2].compareTo("null") == 0) {
				separar[2] = "";
			} else {
				row.createCell(60).setCellValue(separar[2]);
			}
			if (separar[3].compareTo("null") == 0) {
				separar[3] = "";
			} else {
				row.createCell(61).setCellValue(separar[3]);
			}
		}
		
		
		int codigoBadas = 1;

		for (String a : codigoBadasRepetido) {
			String[] separar = a.split(",");

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
			
			if (separar[0].compareTo("null") == 0) {
				separar[0] = "";
			} else {
				row.createCell(62).setCellValue(separar[0]);
			}
			if (separar[1].compareTo("null") == 0) {
				separar[1] = "";
			} else {
				row.createCell(63).setCellValue(separar[1]);
			}		
			if (separar[2].compareTo("null") == 0) {
				separar[2] = "";
			} else {
				row.createCell(64).setCellValue(separar[2]);
			}
			if (separar[3].compareTo("null") == 0) {
				separar[3] = "";
			} else {
				row.createCell(65).setCellValue(separar[3]);
			}
			if (separar[4].compareTo("null") == 0) {
				separar[4] = "";
			} else {
				row.createCell(66).setCellValue(separar[4]);
			}
			if (separar[5].compareTo("null") == 0) {
				separar[5] = "";
			} else {
				row.createCell(67).setCellValue(separar[5]);
			}
			if (separar[6].compareTo("null") == 0) {
				separar[6] = "";
			} else {
				row.createCell(68).setCellValue(separar[6]);
			}
			if (separar[7].compareTo("null") == 0) {
				separar[7] = "";
			} else {
				row.createCell(69).setCellValue(separar[7]);
			}
			if (separar[8].compareTo("null") == 0) {
				separar[8] = "";
			} else {
				row.createCell(70).setCellValue(separar[8]);
			}
			if (separar[9].compareTo("null") == 0) {
				separar[9] = "";
			} else {
				row.createCell(71).setCellValue(separar[9]);
			}
			if (separar[10].compareTo("null") == 0) {
				separar[10] = "";
			} else {
				row.createCell(72).setCellValue(separar[10]);
			}
			if (separar[11].compareTo("null") == 0) {
				separar[11] = "";
			} else {
				row.createCell(73).setCellValue(separar[11]);
			}
			if (separar[12].compareTo("null") == 0) {
				separar[12] = "";
			} else {
				row.createCell(74).setCellValue(separar[12]);
			}
			if (separar[13].compareTo("null") == 0) {
				separar[13] = "";
			} else {
				row.createCell(75).setCellValue(separar[13]);
			}
			if (separar[14].compareTo("null") == 0) {
				separar[14] = "";
			} else {
				row.createCell(76).setCellValue(separar[14]);
			}
			if (separar[15].compareTo("null") == 0) {
				separar[15] = "";
			} else {
				row.createCell(77).setCellValue(separar[15]);
			}
			if (separar[16].compareTo("null") == 0) {
				separar[16] = "";
			} else {
				row.createCell(78).setCellValue(separar[16]);
			}
			if (separar[17].compareTo("null") == 0) {
				separar[17] = "";
			} else {
				row.createCell(79).setCellValue(separar[17]);
			}
			if (separar[18].compareTo("null") == 0) {
				separar[18] = "";
			} else {
				row.createCell(80).setCellValue(separar[18]);
			}
			if (separar[19].compareTo("null") == 0) {
				separar[19] = "";
			} else {
				row.createCell(81).setCellValue(separar[19]);
			}
			if (separar[20].compareTo("null") == 0) {
				separar[20] = "";
			} else {
				row.createCell(82).setCellValue(separar[20]);
			}
			if (separar[21].compareTo("null") == 0) {
				separar[21] = "";
			} else {
				row.createCell(83).setCellValue(separar[21]);
			}
			if (separar[22].compareTo("null") == 0) {
				separar[22] = "";
			} else {
				row.createCell(84).setCellValue(separar[22]);
			}
			if (separar[23].compareTo("null") == 0) {
				separar[23] = "";
			} else {
				row.createCell(85).setCellValue(separar[23]);
			}
			if (separar[24].compareTo("null") == 0) {
				separar[24] = "";
			} else {
				row.createCell(86).setCellValue(separar[24]);
			}
			if (separar[25].compareTo("null") == 0) {
				separar[25] = "";
			} else {
				row.createCell(87).setCellValue(separar[25]);
			}
			if (separar[26].compareTo("null") == 0) {
				separar[26] = "";
			} else {
				row.createCell(88).setCellValue(separar[26]);
			}
			if (separar[27].compareTo("null") == 0) {
				separar[27] = "";
			} else {
				row.createCell(89).setCellValue(separar[27]);
			}
			if (separar[28].compareTo("null") == 0) {
				separar[28] = "";
			} else {
				row.createCell(90).setCellValue(separar[28]);
			}
			if (separar[29].compareTo("null") == 0) {
				separar[29] = "";
			} else {
				row.createCell(91).setCellValue(separar[29]);
			}
			if (separar[30].compareTo("null") == 0) {
				separar[30] = "";
			} else {
				row.createCell(92).setCellValue(separar[30]);
			}
			if (separar[31].compareTo("null") == 0) {
				separar[31] = "";
			} else {
				row.createCell(93).setCellValue(separar[31]);
			}
			if (separar[32].compareTo("null") == 0) {
				separar[32] = "";
			} else {
				row.createCell(94).setCellValue(separar[32]);
			}
			if (separar[33].compareTo("null") == 0) {
				separar[33] = "";
			} else {
				row.createCell(95).setCellValue(separar[33]);
			}
			if (separar[34].compareTo("null") == 0) {
				separar[34] = "";
			} else {
				row.createCell(96).setCellValue(separar[34]);
			}
			if (separar[35].compareTo("null") == 0) {
				separar[35] = "";
			} else {
				row.createCell(97).setCellValue(separar[35]);
			}
			if (separar[36].compareTo("null") == 0) {
				separar[36] = "";
			} else {
				row.createCell(98).setCellValue(separar[36]);
			}
			if (separar[37].compareTo("null") == 0) {
				separar[37] = "";
			} else {
				row.createCell(99).setCellValue(separar[37]);
			}
			if (separar[38].compareTo("null") == 0) {
				separar[38] = "";
			} else {
				row.createCell(100).setCellValue(separar[38]);
			}
			if (separar[39].compareTo("null") == 0) {
				separar[39] = "";
			} else {
				row.createCell(101).setCellValue(separar[39]);
			}
			if (separar[40].compareTo("null") == 0) {
				separar[40] = "";
			} else {
				row.createCell(102).setCellValue(separar[40]);
			}
			if (separar[41].compareTo("null") == 0) {
				separar[41] = "";
			} else {
				row.createCell(103).setCellValue(separar[41]);
			}
			if (separar[42].compareTo("null") == 0) {
				separar[42] = "";
			} else {
				row.createCell(104).setCellValue(separar[42]);
			}
			if (separar[43].compareTo("null") == 0) {
				separar[43] = "";
			} else {
				row.createCell(105).setCellValue(separar[43]);
			}
			if (separar[44].compareTo("null") == 0) {
				separar[44] = "";
			} else {
				row.createCell(106).setCellValue(separar[44]);
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
