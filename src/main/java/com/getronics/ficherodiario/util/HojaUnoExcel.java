package com.getronics.ficherodiario.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
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

	@SuppressWarnings("unused")
	public void crearHojaExcel(List<String> tipoIdentificador, List<String> tipoMovimiento,
			List<String> protegidoTipAseguramientoAndTipMovimiento, List<String> registroIpfNulo,
			List<String> informeAltaCruzado, List<String> codTipoAsegurado, List<String> titDobleCobertura,
			List<String> beneDobleCobertura, List<String> tipoIdentificadorMutualistas,
			List<String> tipoMovimientoMutualista, List<String> protegidoTipAseguramientoAndTipMovimientoMutualistas,
			List<String> registrosIpfNuloMutualistas, List<String> informeAltCruzadoConSegSocialFinalMutualistas,
			List<String> codTipoAseguradoMutualistas, List<String> regimenGeneral, List<String> bajaTitulares,
			List<String> totalEnvioInssAv, List<String> bajasVinculadosSns, List<String> mutualistasPrivados,
			List<String> bajaPorDefuncion, List<String> aseguramiento, List<String> indicadorFarmacia,
			List<String> codigoBadasRepetido, List<String> altasSinNaf, List<String> cambiosIpf,
			List<String> ipfNuevoExisteInssTit, List<String> ipfAnteriorNoExisteInssMut,
			List<String> ipfNuevoExiteInssMut, List<String> titularesDobleCobertura,
			List<String> beneficiarioDobleCoberturaMutualistas, List<String> titularesIndicadorConvenioRural,
			List<String> beneficiadioIndicadorConvenioRural, List<String> privadoRegularPublico,
			List<String> privadoIrregularPublico, List<String> privadoNoExiste,
			List<String> altaModificacionTitularesDobleCobertura, List<String> irregularPrivadoRegularPublico,
			List<String> irregularPrivadoIrregularPublico, List<String> irregularPrivadoNoExiste,
			List<String> altaModificaTitualresDobleCoberturaDos, List<String> listadoRegistroNoExiste,
			List<String> listadoRegistroNoExisteDos, List<String> bajaPorDefuncionPrivadoPublico,
			List<String> bajaPorDefuncionPrivadoNoExiste) {

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

		if (tipoIdentificador.isEmpty()) {
			ultimaCelda = 2;
		} else {
			for (String a : tipoIdentificador) {
				String[] separar = a.split(",");
				row = sheet.createRow(rowNum++);

				int i;

				for (i = 0; i < separar.length; i++) {
					boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);
					if (isNumeric == true) {
						if (separar[i].length() <= 10) {
							Integer n = Integer.parseInt(separar[i]);
							row.createCell(i).setCellValue(n);
						} else {
							DecimalFormat df = new DecimalFormat("0");
							Long n;
							row.createCell(i).setCellValue(df.format(n = Long.parseLong(separar[i])));
						}
					} else {
						row.createCell(i).setCellValue(separar[i]);
					}
				}
				ultimaCelda = i;
			}
		}

		// Se crea un indice para comparar con las filas.
		int indexMovimiento = 1;
		int j = 0;

		if (tipoMovimiento.isEmpty()) {
			celda = 6;
		} else {

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
								if (separar[i].length() <= 10) {
									Integer n = Integer.parseInt(separar[i]);
									row.createCell(j).setCellValue(n);
								} else {
									DecimalFormat df = new DecimalFormat("0");
									Long n;
									row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
								}
							} else {
								row.createCell(j).setCellValue(separar[i]);
							}
						}
						j++;
					}
				}
			}
			ultimaCelda = j - 1;
			celda = ultimaCelda;

			int indexProtegidoTip = 1;

			if (protegidoTipAseguramientoAndTipMovimiento.isEmpty()) {
				celda = 10;
			} else {

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
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexRegiIpfNulo = 1;

			if (registroIpfNulo.isEmpty()) {
				celda = 14;
			} else {

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
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexInforAltCruzado = 1;

			if (informeAltaCruzado.isEmpty()) {
				celda = 17;
			} else {

				for (String a : informeAltaCruzado) {
					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexInforAltCruzado <= tipoIdentificador.size()
							|| indexInforAltCruzado <= tipoMovimiento.size()
							|| indexInforAltCruzado <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexInforAltCruzado <= registroIpfNulo.size())

						row = sheet.getRow(indexInforAltCruzado++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexInforAltCruzado++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexCodTipAseg = 1;

			if (codTipoAsegurado.isEmpty()) {
				celda = 19;
			} else {

				for (String a : codTipoAsegurado) {
					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexCodTipAseg <= tipoIdentificador.size() || indexCodTipAseg <= tipoMovimiento.size()
							|| indexCodTipAseg <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexCodTipAseg <= registroIpfNulo.size()
							|| indexCodTipAseg <= informeAltaCruzado.size())

						row = sheet.getRow(indexCodTipAseg++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexCodTipAseg++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexTitDoCob = 1;

			if (titDobleCobertura.isEmpty()) {
				celda = 21;
			} else {

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
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexBeneDobCob = 1;

			if (beneDobleCobertura.isEmpty()) {
				celda = 23;
			} else {

				for (String a : beneDobleCobertura) {
					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexBeneDobCob <= tipoIdentificador.size() || indexBeneDobCob <= tipoMovimiento.size()
							|| indexBeneDobCob <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexBeneDobCob <= registroIpfNulo.size() || indexBeneDobCob <= informeAltaCruzado.size()
							|| indexBeneDobCob <= codTipoAsegurado.size()
							|| indexBeneDobCob <= titDobleCobertura.size())

						row = sheet.getRow(indexBeneDobCob++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexBeneDobCob++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			/* QUERYS MUTUALISTAS. */

			int indexTiIdMut = 1;

			if (tipoIdentificadorMutualistas.isEmpty()) {
				celda = 25;
			} else {

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

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexTipMoviMut = 1;

			if (tipoMovimientoMutualista.isEmpty()) {
				celda = 29;
			} else {

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

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexProtegidoTipAseg = 1;

			if (protegidoTipAseguramientoAndTipMovimientoMutualistas.isEmpty()) {
				celda = 33;
			} else {

				for (String a : protegidoTipAseguramientoAndTipMovimientoMutualistas) {
					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexProtegidoTipAseg <= tipoIdentificador.size()
							|| indexProtegidoTipAseg <= tipoMovimiento.size()
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

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexRegIpfNu = 1;

			if (registrosIpfNuloMutualistas.isEmpty()) {
				celda = 37;
			} else {

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

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexInfFin = 1;

			if (informeAltCruzadoConSegSocialFinalMutualistas.isEmpty()) {
				celda = 40;
			} else {

				for (String a : informeAltCruzadoConSegSocialFinalMutualistas) {
					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexInfFin <= tipoIdentificador.size() || indexInfFin <= tipoMovimiento.size()
							|| indexInfFin <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexInfFin <= registroIpfNulo.size() || indexInfFin <= informeAltaCruzado.size()
							|| indexInfFin <= codTipoAsegurado.size() || indexInfFin <= titDobleCobertura.size()
							|| indexInfFin <= beneDobleCobertura.size()
							|| indexInfFin <= tipoIdentificadorMutualistas.size()
							|| indexInfFin <= tipoMovimientoMutualista.size()
							|| indexInfFin <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
							|| indexInfFin <= registrosIpfNuloMutualistas.size())

						row = sheet.getRow(indexInfFin++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexInfFin++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexCod = 1;

			if (codTipoAseguradoMutualistas.isEmpty()) {
				celda = 42;
			} else {

				for (String a : codTipoAseguradoMutualistas) {
					String[] separar = a.split(",");

					int i = 0;

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

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			// Regimen General

			int indexRegGen = 1;

			if (regimenGeneral.isEmpty()) {
				celda = 45;
			} else {

				for (String a : regimenGeneral) {
					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexRegGen <= tipoIdentificador.size() || indexRegGen <= tipoMovimiento.size()
							|| indexRegGen <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexRegGen <= registroIpfNulo.size() || indexRegGen <= informeAltaCruzado.size()
							|| indexRegGen <= codTipoAsegurado.size() || indexRegGen <= titDobleCobertura.size()
							|| indexRegGen <= beneDobleCobertura.size()
							|| indexRegGen <= tipoIdentificadorMutualistas.size()
							|| indexRegGen <= tipoMovimientoMutualista.size()
							|| indexRegGen <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
							|| indexRegGen <= registrosIpfNuloMutualistas.size()
							|| indexRegGen <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexRegGen <= codTipoAseguradoMutualistas.size())

						row = sheet.getRow(indexRegGen++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexRegGen++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexbajaTitulares = 1;

			if (bajaTitulares.isEmpty()) {
				celda = 47;
			} else {

				for (String a : bajaTitulares) {
					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexbajaTitulares <= tipoIdentificador.size() || indexbajaTitulares <= tipoMovimiento.size()
							|| indexbajaTitulares <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexbajaTitulares <= registroIpfNulo.size()
							|| indexbajaTitulares <= informeAltaCruzado.size()
							|| indexbajaTitulares <= codTipoAsegurado.size()
							|| indexbajaTitulares <= titDobleCobertura.size()
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

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexTotEnvInssAv = 1;

			if (totalEnvioInssAv.isEmpty()) {
				celda = 48;
			} else {

				for (String a : totalEnvioInssAv) {
					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexTotEnvInssAv <= tipoIdentificador.size() || indexTotEnvInssAv <= tipoMovimiento.size()
							|| indexTotEnvInssAv <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexTotEnvInssAv <= registroIpfNulo.size()
							|| indexTotEnvInssAv <= informeAltaCruzado.size()
							|| indexTotEnvInssAv <= codTipoAsegurado.size()
							|| indexTotEnvInssAv <= titDobleCobertura.size()
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

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexBajasVinculadosSns = 1;

			if (bajasVinculadosSns.isEmpty()) {
				celda = 50;
			} else {
				for (String a : bajasVinculadosSns) {
					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexBajasVinculadosSns <= tipoIdentificador.size()
							|| indexBajasVinculadosSns <= tipoMovimiento.size()
							|| indexBajasVinculadosSns <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexBajasVinculadosSns <= registroIpfNulo.size()
							|| indexBajasVinculadosSns <= informeAltaCruzado.size()
							|| indexBajasVinculadosSns <= codTipoAsegurado.size()
							|| indexBajasVinculadosSns <= titDobleCobertura.size()
							|| indexBajasVinculadosSns <= beneDobleCobertura.size()
							|| indexBajasVinculadosSns <= tipoIdentificadorMutualistas.size()
							|| indexBajasVinculadosSns <= tipoMovimientoMutualista.size()
							|| indexBajasVinculadosSns <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
							|| indexBajasVinculadosSns <= registrosIpfNuloMutualistas.size()
							|| indexBajasVinculadosSns <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexBajasVinculadosSns <= codTipoAseguradoMutualistas.size()
							|| indexBajasVinculadosSns <= regimenGeneral.size()
							|| indexBajasVinculadosSns <= bajaTitulares.size()
							|| indexBajasVinculadosSns <= totalEnvioInssAv.size())

						row = sheet.getRow(indexBajasVinculadosSns++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexBajasVinculadosSns++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexMutualistasPrivados = 1;

			if (mutualistasPrivados.isEmpty()) {
				celda = 53;
			} else {
				for (String a : mutualistasPrivados) {
					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexMutualistasPrivados <= tipoIdentificador.size()
							|| indexMutualistasPrivados <= tipoMovimiento.size()
							|| indexMutualistasPrivados <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexMutualistasPrivados <= registroIpfNulo.size()
							|| indexMutualistasPrivados <= informeAltaCruzado.size()
							|| indexMutualistasPrivados <= codTipoAsegurado.size()
							|| indexMutualistasPrivados <= titDobleCobertura.size()
							|| indexMutualistasPrivados <= beneDobleCobertura.size()
							|| indexMutualistasPrivados <= tipoIdentificadorMutualistas.size()
							|| indexMutualistasPrivados <= tipoMovimientoMutualista.size()
							|| indexMutualistasPrivados <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
							|| indexMutualistasPrivados <= registrosIpfNuloMutualistas.size()
							|| indexMutualistasPrivados <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexMutualistasPrivados <= codTipoAseguradoMutualistas.size()
							|| indexMutualistasPrivados <= regimenGeneral.size()
							|| indexMutualistasPrivados <= bajaTitulares.size()
							|| indexMutualistasPrivados <= totalEnvioInssAv.size()
							|| indexMutualistasPrivados <= bajasVinculadosSns.size())

						row = sheet.getRow(indexMutualistasPrivados++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexMutualistasPrivados++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexBajaPorDefuncion = 1;

			if (bajaPorDefuncion.isEmpty()) {
				celda = 55;
			} else {
				for (String a : bajaPorDefuncion) {
					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexBajaPorDefuncion <= tipoIdentificador.size()
							|| indexBajaPorDefuncion <= tipoMovimiento.size()
							|| indexBajaPorDefuncion <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexBajaPorDefuncion <= registroIpfNulo.size()
							|| indexBajaPorDefuncion <= informeAltaCruzado.size()
							|| indexBajaPorDefuncion <= codTipoAsegurado.size()
							|| indexBajaPorDefuncion <= titDobleCobertura.size()
							|| indexBajaPorDefuncion <= beneDobleCobertura.size()
							|| indexBajaPorDefuncion <= tipoIdentificadorMutualistas.size()
							|| indexBajaPorDefuncion <= tipoMovimientoMutualista.size()
							|| indexBajaPorDefuncion <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
							|| indexBajaPorDefuncion <= registrosIpfNuloMutualistas.size()
							|| indexBajaPorDefuncion <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexBajaPorDefuncion <= codTipoAseguradoMutualistas.size()
							|| indexBajaPorDefuncion <= regimenGeneral.size()
							|| indexBajaPorDefuncion <= bajaTitulares.size()
							|| indexBajaPorDefuncion <= totalEnvioInssAv.size()
							|| indexBajaPorDefuncion <= bajasVinculadosSns.size()
							|| indexBajaPorDefuncion <= mutualistasPrivados.size())

						row = sheet.getRow(indexBajaPorDefuncion++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexBajaPorDefuncion++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexAseguramiento = 1;

			if (aseguramiento.isEmpty()) {
				celda = 58;
			} else {
				for (String a : aseguramiento) {
					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexAseguramiento <= tipoIdentificador.size() || indexAseguramiento <= tipoMovimiento.size()
							|| indexAseguramiento <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexAseguramiento <= registroIpfNulo.size()
							|| indexAseguramiento <= informeAltaCruzado.size()
							|| indexAseguramiento <= codTipoAsegurado.size()
							|| indexAseguramiento <= titDobleCobertura.size()
							|| indexAseguramiento <= beneDobleCobertura.size()
							|| indexAseguramiento <= tipoIdentificadorMutualistas.size()
							|| indexAseguramiento <= tipoMovimientoMutualista.size()
							|| indexAseguramiento <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
							|| indexAseguramiento <= registrosIpfNuloMutualistas.size()
							|| indexAseguramiento <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexAseguramiento <= codTipoAseguradoMutualistas.size()
							|| indexAseguramiento <= regimenGeneral.size() || indexAseguramiento <= bajaTitulares.size()
							|| indexAseguramiento <= totalEnvioInssAv.size()
							|| indexAseguramiento <= bajasVinculadosSns.size()
							|| indexAseguramiento <= mutualistasPrivados.size()
							|| indexAseguramiento <= bajaPorDefuncion.size())

						row = sheet.getRow(indexAseguramiento++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexAseguramiento++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexIndicadorFarmacia = 1;

			if (indicadorFarmacia.isEmpty()) {
				celda = 62;
			} else {
				for (String a : indicadorFarmacia) {
					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexIndicadorFarmacia <= tipoIdentificador.size()
							|| indexIndicadorFarmacia <= tipoMovimiento.size()
							|| indexIndicadorFarmacia <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexIndicadorFarmacia <= registroIpfNulo.size()
							|| indexIndicadorFarmacia <= informeAltaCruzado.size()
							|| indexIndicadorFarmacia <= codTipoAsegurado.size()
							|| indexIndicadorFarmacia <= titDobleCobertura.size()
							|| indexIndicadorFarmacia <= beneDobleCobertura.size()
							|| indexIndicadorFarmacia <= tipoIdentificadorMutualistas.size()
							|| indexIndicadorFarmacia <= tipoMovimientoMutualista.size()
							|| indexIndicadorFarmacia <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
							|| indexIndicadorFarmacia <= registrosIpfNuloMutualistas.size()
							|| indexIndicadorFarmacia <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexIndicadorFarmacia <= codTipoAseguradoMutualistas.size()
							|| indexIndicadorFarmacia <= regimenGeneral.size()
							|| indexIndicadorFarmacia <= bajaTitulares.size()
							|| indexIndicadorFarmacia <= totalEnvioInssAv.size()
							|| indexIndicadorFarmacia <= bajasVinculadosSns.size()
							|| indexIndicadorFarmacia <= mutualistasPrivados.size()
							|| indexIndicadorFarmacia <= bajaPorDefuncion.size()
							|| indexIndicadorFarmacia <= aseguramiento.size())

						row = sheet.getRow(indexIndicadorFarmacia++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexIndicadorFarmacia++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int codigoBadas = 1;

			if (codigoBadasRepetido.isEmpty()) {
				celda = 107;
			} else {
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
							|| codigoBadas <= codTipoAseguradoMutualistas.size() || codigoBadas <= regimenGeneral.size()
							|| codigoBadas <= bajaTitulares.size() || codigoBadas <= totalEnvioInssAv.size()
							|| codigoBadas <= bajasVinculadosSns.size() || codigoBadas <= mutualistasPrivados.size()
							|| codigoBadas <= bajaPorDefuncion.size() || codigoBadas <= aseguramiento.size()
							|| codigoBadas <= indicadorFarmacia.size())

						row = sheet.getRow(codigoBadas++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(codigoBadas++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int altaSNaf = 1;

			if (altasSinNaf.isEmpty()) {
				celda = 153;
			} else {

				for (String a : altasSinNaf) {
					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (altaSNaf <= tipoIdentificador.size() || altaSNaf <= tipoMovimiento.size()
							|| altaSNaf <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| altaSNaf <= registroIpfNulo.size() || altaSNaf <= informeAltaCruzado.size()
							|| altaSNaf <= codTipoAsegurado.size() || altaSNaf <= titDobleCobertura.size()
							|| altaSNaf <= beneDobleCobertura.size() || altaSNaf <= tipoIdentificadorMutualistas.size()
							|| altaSNaf <= tipoMovimientoMutualista.size()
							|| altaSNaf <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
							|| altaSNaf <= registrosIpfNuloMutualistas.size()
							|| altaSNaf <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| altaSNaf <= codTipoAseguradoMutualistas.size() || altaSNaf <= regimenGeneral.size()
							|| altaSNaf <= bajaTitulares.size() || altaSNaf <= totalEnvioInssAv.size()
							|| altaSNaf <= bajasVinculadosSns.size() || altaSNaf <= mutualistasPrivados.size()
							|| altaSNaf <= bajaPorDefuncion.size() || altaSNaf <= aseguramiento.size()
							|| altaSNaf <= indicadorFarmacia.size() || altaSNaf <= codigoBadasRepetido.size())

						row = sheet.getRow(altaSNaf++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(altaSNaf++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int cambioIpf = 1;

			if (cambiosIpf.isEmpty()) {
				celda = 198;
			} else {

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
							|| cambioIpf <= codTipoAseguradoMutualistas.size() || cambioIpf <= regimenGeneral.size()
							|| cambioIpf <= bajaTitulares.size() || cambioIpf <= totalEnvioInssAv.size()
							|| cambioIpf <= bajasVinculadosSns.size() || cambioIpf <= mutualistasPrivados.size()
							|| cambioIpf <= bajaPorDefuncion.size() || cambioIpf <= aseguramiento.size()
							|| cambioIpf <= indicadorFarmacia.size() || cambioIpf <= codigoBadasRepetido.size()
							|| cambioIpf <= altasSinNaf.size())

						row = sheet.getRow(cambioIpf++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(cambioIpf++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexIpfNu = 1;

			if (ipfNuevoExisteInssTit.isEmpty()) {
				celda = 243;
			} else {

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
							|| indexIpfNu <= codTipoAseguradoMutualistas.size() || indexIpfNu <= regimenGeneral.size()
							|| indexIpfNu <= bajaTitulares.size() || indexIpfNu <= totalEnvioInssAv.size()
							|| indexIpfNu <= bajasVinculadosSns.size() || indexIpfNu <= mutualistasPrivados.size()
							|| indexIpfNu <= bajaPorDefuncion.size() || indexIpfNu <= aseguramiento.size()
							|| indexIpfNu <= indicadorFarmacia.size() || indexIpfNu <= codigoBadasRepetido.size()
							|| indexIpfNu <= altasSinNaf.size() || indexIpfNu <= cambiosIpf.size())

						row = sheet.getRow(indexIpfNu++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexIpfNu++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexipfAnteInssMut = 1;

			if (ipfAnteriorNoExisteInssMut.isEmpty()) {
				celda = 288;
			} else {
				for (String a : ipfAnteriorNoExisteInssMut) {

					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexipfAnteInssMut <= tipoIdentificador.size() || indexipfAnteInssMut <= tipoMovimiento.size()
							|| indexipfAnteInssMut <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexipfAnteInssMut <= registroIpfNulo.size()
							|| indexipfAnteInssMut <= informeAltaCruzado.size()
							|| indexipfAnteInssMut <= codTipoAsegurado.size()
							|| indexipfAnteInssMut <= titDobleCobertura.size()
							|| indexipfAnteInssMut <= beneDobleCobertura.size()
							|| indexipfAnteInssMut <= tipoIdentificadorMutualistas.size()
							|| indexipfAnteInssMut <= tipoMovimientoMutualista.size()
							|| indexipfAnteInssMut <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
							|| indexipfAnteInssMut <= registrosIpfNuloMutualistas.size()
							|| indexipfAnteInssMut <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexipfAnteInssMut <= codTipoAseguradoMutualistas.size()
							|| indexipfAnteInssMut <= regimenGeneral.size()
							|| indexipfAnteInssMut <= bajaTitulares.size()
							|| indexipfAnteInssMut <= totalEnvioInssAv.size()
							|| indexipfAnteInssMut <= bajasVinculadosSns.size()
							|| indexipfAnteInssMut <= mutualistasPrivados.size()
							|| indexipfAnteInssMut <= bajaPorDefuncion.size()
							|| indexipfAnteInssMut <= aseguramiento.size()
							|| indexipfAnteInssMut <= indicadorFarmacia.size()
							|| indexipfAnteInssMut <= codigoBadasRepetido.size()
							|| indexipfAnteInssMut <= altasSinNaf.size() || indexipfAnteInssMut <= cambiosIpf.size()
							|| indexipfAnteInssMut <= ipfNuevoExisteInssTit.size())

						row = sheet.getRow(indexipfAnteInssMut++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexipfAnteInssMut++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexIpfNuevoExiteInssMut = 1;

			if (ipfNuevoExiteInssMut.isEmpty()) {
				celda = 333;
			} else {
				for (String a : ipfNuevoExiteInssMut) {

					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexIpfNuevoExiteInssMut <= tipoIdentificador.size()
							|| indexIpfNuevoExiteInssMut <= tipoMovimiento.size()
							|| indexIpfNuevoExiteInssMut <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexIpfNuevoExiteInssMut <= registroIpfNulo.size()
							|| indexIpfNuevoExiteInssMut <= informeAltaCruzado.size()
							|| indexIpfNuevoExiteInssMut <= codTipoAsegurado.size()
							|| indexIpfNuevoExiteInssMut <= titDobleCobertura.size()
							|| indexIpfNuevoExiteInssMut <= beneDobleCobertura.size()
							|| indexIpfNuevoExiteInssMut <= tipoIdentificadorMutualistas.size()
							|| indexIpfNuevoExiteInssMut <= tipoMovimientoMutualista.size()
							|| indexIpfNuevoExiteInssMut <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
							|| indexIpfNuevoExiteInssMut <= registrosIpfNuloMutualistas.size()
							|| indexIpfNuevoExiteInssMut <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexIpfNuevoExiteInssMut <= codTipoAseguradoMutualistas.size()
							|| indexIpfNuevoExiteInssMut <= regimenGeneral.size()
							|| indexIpfNuevoExiteInssMut <= bajaTitulares.size()
							|| indexIpfNuevoExiteInssMut <= totalEnvioInssAv.size()
							|| indexIpfNuevoExiteInssMut <= bajasVinculadosSns.size()
							|| indexIpfNuevoExiteInssMut <= mutualistasPrivados.size()
							|| indexIpfNuevoExiteInssMut <= bajaPorDefuncion.size()
							|| indexIpfNuevoExiteInssMut <= aseguramiento.size()
							|| indexIpfNuevoExiteInssMut <= indicadorFarmacia.size()
							|| indexIpfNuevoExiteInssMut <= codigoBadasRepetido.size()
							|| indexIpfNuevoExiteInssMut <= altasSinNaf.size()
							|| indexIpfNuevoExiteInssMut <= cambiosIpf.size()
							|| indexIpfNuevoExiteInssMut <= ipfNuevoExisteInssTit.size()
							|| indexIpfNuevoExiteInssMut <= ipfAnteriorNoExisteInssMut.size())

						row = sheet.getRow(indexIpfNuevoExiteInssMut++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexIpfNuevoExiteInssMut++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexTitDobleCobertura = 1;

			if (titularesDobleCobertura.isEmpty()) {
				celda = 335;
			} else {
				for (String a : titularesDobleCobertura) {

					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexTitDobleCobertura <= tipoIdentificador.size()
							|| indexTitDobleCobertura <= tipoMovimiento.size()
							|| indexTitDobleCobertura <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexTitDobleCobertura <= registroIpfNulo.size()
							|| indexTitDobleCobertura <= informeAltaCruzado.size()
							|| indexTitDobleCobertura <= codTipoAsegurado.size()
							|| indexTitDobleCobertura <= titDobleCobertura.size()
							|| indexTitDobleCobertura <= beneDobleCobertura.size()
							|| indexTitDobleCobertura <= tipoIdentificadorMutualistas.size()
							|| indexTitDobleCobertura <= tipoMovimientoMutualista.size()
							|| indexTitDobleCobertura <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
							|| indexTitDobleCobertura <= registrosIpfNuloMutualistas.size()
							|| indexTitDobleCobertura <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexTitDobleCobertura <= codTipoAseguradoMutualistas.size()
							|| indexTitDobleCobertura <= regimenGeneral.size()
							|| indexTitDobleCobertura <= bajaTitulares.size()
							|| indexTitDobleCobertura <= totalEnvioInssAv.size()
							|| indexTitDobleCobertura <= bajasVinculadosSns.size()
							|| indexTitDobleCobertura <= mutualistasPrivados.size()
							|| indexTitDobleCobertura <= bajaPorDefuncion.size()
							|| indexTitDobleCobertura <= aseguramiento.size()
							|| indexTitDobleCobertura <= indicadorFarmacia.size()
							|| indexTitDobleCobertura <= codigoBadasRepetido.size()
							|| indexTitDobleCobertura <= altasSinNaf.size()
							|| indexTitDobleCobertura <= cambiosIpf.size()
							|| indexTitDobleCobertura <= ipfNuevoExisteInssTit.size()
							|| indexTitDobleCobertura <= ipfAnteriorNoExisteInssMut.size()
							|| indexTitDobleCobertura <= ipfNuevoExiteInssMut.size())

						row = sheet.getRow(indexTitDobleCobertura++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexTitDobleCobertura++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexBeneDob = 1;

			if (beneficiarioDobleCoberturaMutualistas.isEmpty()) {
				celda = 337;
			} else {
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
							|| indexBeneDob <= ipfAnteriorNoExisteInssMut.size()
							|| indexBeneDob <= ipfNuevoExiteInssMut.size()
							|| indexBeneDob <= titularesDobleCobertura.size())

						row = sheet.getRow(indexBeneDob++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexBeneDob++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexTituIndCoRu = 1;

			if (titularesIndicadorConvenioRural.isEmpty()) {
				celda = 339;
			} else {
				for (String a : titularesIndicadorConvenioRural) {

					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexTituIndCoRu <= tipoIdentificador.size() || indexTituIndCoRu <= tipoMovimiento.size()
							|| indexTituIndCoRu <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexTituIndCoRu <= registroIpfNulo.size()
							|| indexTituIndCoRu <= informeAltaCruzado.size()
							|| indexTituIndCoRu <= codTipoAsegurado.size()
							|| indexTituIndCoRu <= titDobleCobertura.size()
							|| indexTituIndCoRu <= beneDobleCobertura.size()
							|| indexTituIndCoRu <= tipoIdentificadorMutualistas.size()
							|| indexTituIndCoRu <= tipoMovimientoMutualista.size()
							|| indexTituIndCoRu <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
							|| indexTituIndCoRu <= registrosIpfNuloMutualistas.size()
							|| indexTituIndCoRu <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexTituIndCoRu <= codTipoAseguradoMutualistas.size()
							|| indexTituIndCoRu <= regimenGeneral.size() || indexTituIndCoRu <= bajaTitulares.size()
							|| indexTituIndCoRu <= totalEnvioInssAv.size()
							|| indexTituIndCoRu <= bajasVinculadosSns.size()
							|| indexTituIndCoRu <= mutualistasPrivados.size()
							|| indexTituIndCoRu <= bajaPorDefuncion.size() || indexTituIndCoRu <= aseguramiento.size()
							|| indexTituIndCoRu <= indicadorFarmacia.size()
							|| indexTituIndCoRu <= codigoBadasRepetido.size() || indexTituIndCoRu <= altasSinNaf.size()
							|| indexTituIndCoRu <= cambiosIpf.size() || indexTituIndCoRu <= ipfNuevoExisteInssTit.size()
							|| indexTituIndCoRu <= ipfAnteriorNoExisteInssMut.size()
							|| indexTituIndCoRu <= ipfNuevoExiteInssMut.size()
							|| indexTituIndCoRu <= titularesDobleCobertura.size()
							|| indexTituIndCoRu <= beneficiarioDobleCoberturaMutualistas.size())

						row = sheet.getRow(indexTituIndCoRu++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexTituIndCoRu++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexBenIndCoRural = 1;

			if (beneficiadioIndicadorConvenioRural.isEmpty()) {
				celda = 341;
			} else {

				for (String a : beneficiadioIndicadorConvenioRural) {

					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexBenIndCoRural <= tipoIdentificador.size() || indexBenIndCoRural <= tipoMovimiento.size()
							|| indexBenIndCoRural <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexBenIndCoRural <= registroIpfNulo.size()
							|| indexBenIndCoRural <= informeAltaCruzado.size()
							|| indexBenIndCoRural <= codTipoAsegurado.size()
							|| indexBenIndCoRural <= titDobleCobertura.size()
							|| indexBenIndCoRural <= beneDobleCobertura.size()
							|| indexBenIndCoRural <= tipoIdentificadorMutualistas.size()
							|| indexBenIndCoRural <= tipoMovimientoMutualista.size()
							|| indexBenIndCoRural <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
							|| indexBenIndCoRural <= registrosIpfNuloMutualistas.size()
							|| indexBenIndCoRural <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexBenIndCoRural <= codTipoAseguradoMutualistas.size()
							|| indexBenIndCoRural <= regimenGeneral.size() || indexBenIndCoRural <= bajaTitulares.size()
							|| indexBenIndCoRural <= totalEnvioInssAv.size()
							|| indexBenIndCoRural <= bajasVinculadosSns.size()
							|| indexBenIndCoRural <= mutualistasPrivados.size()
							|| indexBenIndCoRural <= bajaPorDefuncion.size()
							|| indexBenIndCoRural <= aseguramiento.size()
							|| indexBenIndCoRural <= indicadorFarmacia.size()
							|| indexBenIndCoRural <= codigoBadasRepetido.size()
							|| indexBenIndCoRural <= altasSinNaf.size() || indexBenIndCoRural <= cambiosIpf.size()
							|| indexBenIndCoRural <= ipfNuevoExisteInssTit.size()
							|| indexBenIndCoRural <= ipfAnteriorNoExisteInssMut.size()
							|| indexBenIndCoRural <= ipfNuevoExiteInssMut.size()
							|| indexBenIndCoRural <= titularesDobleCobertura.size()
							|| indexBenIndCoRural <= beneficiarioDobleCoberturaMutualistas.size()
							|| indexBenIndCoRural <= titularesIndicadorConvenioRural.size())

						row = sheet.getRow(indexBenIndCoRural++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexBenIndCoRural++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexPrivadoRegularPublico = 1;

			if (privadoRegularPublico.isEmpty()) {
				celda = 342;
			} else {
				for (String a : privadoRegularPublico) {

					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexPrivadoRegularPublico <= tipoIdentificador.size()
							|| indexPrivadoRegularPublico <= tipoMovimiento.size()
							|| indexPrivadoRegularPublico <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexPrivadoRegularPublico <= registroIpfNulo.size()
							|| indexPrivadoRegularPublico <= informeAltaCruzado.size()
							|| indexPrivadoRegularPublico <= codTipoAsegurado.size()
							|| indexPrivadoRegularPublico <= titDobleCobertura.size()
							|| indexPrivadoRegularPublico <= beneDobleCobertura.size()
							|| indexPrivadoRegularPublico <= tipoIdentificadorMutualistas.size()
							|| indexPrivadoRegularPublico <= tipoMovimientoMutualista.size()
							|| indexPrivadoRegularPublico <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
							|| indexPrivadoRegularPublico <= registrosIpfNuloMutualistas.size()
							|| indexPrivadoRegularPublico <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexPrivadoRegularPublico <= codTipoAseguradoMutualistas.size()
							|| indexPrivadoRegularPublico <= regimenGeneral.size()
							|| indexPrivadoRegularPublico <= bajaTitulares.size()
							|| indexPrivadoRegularPublico <= totalEnvioInssAv.size()
							|| indexPrivadoRegularPublico <= bajasVinculadosSns.size()
							|| indexPrivadoRegularPublico <= mutualistasPrivados.size()
							|| indexPrivadoRegularPublico <= bajaPorDefuncion.size()
							|| indexPrivadoRegularPublico <= aseguramiento.size()
							|| indexPrivadoRegularPublico <= indicadorFarmacia.size()
							|| indexPrivadoRegularPublico <= codigoBadasRepetido.size()
							|| indexPrivadoRegularPublico <= altasSinNaf.size()
							|| indexPrivadoRegularPublico <= cambiosIpf.size()
							|| indexPrivadoRegularPublico <= ipfNuevoExisteInssTit.size()
							|| indexPrivadoRegularPublico <= ipfAnteriorNoExisteInssMut.size()
							|| indexPrivadoRegularPublico <= ipfNuevoExiteInssMut.size()
							|| indexPrivadoRegularPublico <= titularesDobleCobertura.size()
							|| indexPrivadoRegularPublico <= beneficiarioDobleCoberturaMutualistas.size()
							|| indexPrivadoRegularPublico <= titularesIndicadorConvenioRural.size()
							|| indexPrivadoRegularPublico <= beneficiadioIndicadorConvenioRural.size())

						row = sheet.getRow(indexPrivadoRegularPublico++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexPrivadoRegularPublico++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexPrivadoIrregularPublico = 1;

			if (privadoIrregularPublico.isEmpty()) {
				celda = 343;
			} else {
				for (String a : privadoIrregularPublico) {

					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexPrivadoIrregularPublico <= tipoIdentificador.size()
							|| indexPrivadoIrregularPublico <= tipoMovimiento.size()
							|| indexPrivadoIrregularPublico <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexPrivadoIrregularPublico <= registroIpfNulo.size()
							|| indexPrivadoIrregularPublico <= informeAltaCruzado.size()
							|| indexPrivadoIrregularPublico <= codTipoAsegurado.size()
							|| indexPrivadoIrregularPublico <= titDobleCobertura.size()
							|| indexPrivadoIrregularPublico <= beneDobleCobertura.size()
							|| indexPrivadoIrregularPublico <= tipoIdentificadorMutualistas.size()
							|| indexPrivadoIrregularPublico <= tipoMovimientoMutualista.size()
							|| indexPrivadoIrregularPublico <= protegidoTipAseguramientoAndTipMovimientoMutualistas
									.size()
							|| indexPrivadoIrregularPublico <= registrosIpfNuloMutualistas.size()
							|| indexPrivadoIrregularPublico <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexPrivadoIrregularPublico <= codTipoAseguradoMutualistas.size()
							|| indexPrivadoIrregularPublico <= regimenGeneral.size()
							|| indexPrivadoIrregularPublico <= bajaTitulares.size()
							|| indexPrivadoIrregularPublico <= totalEnvioInssAv.size()
							|| indexPrivadoIrregularPublico <= bajasVinculadosSns.size()
							|| indexPrivadoIrregularPublico <= mutualistasPrivados.size()
							|| indexPrivadoIrregularPublico <= bajaPorDefuncion.size()
							|| indexPrivadoIrregularPublico <= aseguramiento.size()
							|| indexPrivadoIrregularPublico <= indicadorFarmacia.size()
							|| indexPrivadoIrregularPublico <= codigoBadasRepetido.size()
							|| indexPrivadoIrregularPublico <= altasSinNaf.size()
							|| indexPrivadoIrregularPublico <= cambiosIpf.size()
							|| indexPrivadoIrregularPublico <= ipfNuevoExisteInssTit.size()
							|| indexPrivadoIrregularPublico <= ipfAnteriorNoExisteInssMut.size()
							|| indexPrivadoIrregularPublico <= ipfNuevoExiteInssMut.size()
							|| indexPrivadoIrregularPublico <= titularesDobleCobertura.size()
							|| indexPrivadoIrregularPublico <= beneficiarioDobleCoberturaMutualistas.size()
							|| indexPrivadoIrregularPublico <= titularesIndicadorConvenioRural.size()
							|| indexPrivadoIrregularPublico <= beneficiadioIndicadorConvenioRural.size()
							|| indexPrivadoIrregularPublico <= privadoRegularPublico.size())

						row = sheet.getRow(indexPrivadoIrregularPublico++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexPrivadoIrregularPublico++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexPrivadoNoExiste = 1;

			if (privadoNoExiste.isEmpty()) {
				celda = 344;
			} else {
				for (String a : privadoNoExiste) {

					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexPrivadoNoExiste <= tipoIdentificador.size()
							|| indexPrivadoNoExiste <= tipoMovimiento.size()
							|| indexPrivadoNoExiste <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexPrivadoNoExiste <= registroIpfNulo.size()
							|| indexPrivadoNoExiste <= informeAltaCruzado.size()
							|| indexPrivadoNoExiste <= codTipoAsegurado.size()
							|| indexPrivadoNoExiste <= titDobleCobertura.size()
							|| indexPrivadoNoExiste <= beneDobleCobertura.size()
							|| indexPrivadoNoExiste <= tipoIdentificadorMutualistas.size()
							|| indexPrivadoNoExiste <= tipoMovimientoMutualista.size()
							|| indexPrivadoNoExiste <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
							|| indexPrivadoNoExiste <= registrosIpfNuloMutualistas.size()
							|| indexPrivadoNoExiste <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexPrivadoNoExiste <= codTipoAseguradoMutualistas.size()
							|| indexPrivadoNoExiste <= regimenGeneral.size()
							|| indexPrivadoNoExiste <= bajaTitulares.size()
							|| indexPrivadoNoExiste <= totalEnvioInssAv.size()
							|| indexPrivadoNoExiste <= bajasVinculadosSns.size()
							|| indexPrivadoNoExiste <= mutualistasPrivados.size()
							|| indexPrivadoNoExiste <= bajaPorDefuncion.size()
							|| indexPrivadoNoExiste <= aseguramiento.size()
							|| indexPrivadoNoExiste <= indicadorFarmacia.size()
							|| indexPrivadoNoExiste <= codigoBadasRepetido.size()
							|| indexPrivadoNoExiste <= altasSinNaf.size() || indexPrivadoNoExiste <= cambiosIpf.size()
							|| indexPrivadoNoExiste <= ipfNuevoExisteInssTit.size()
							|| indexPrivadoNoExiste <= ipfAnteriorNoExisteInssMut.size()
							|| indexPrivadoNoExiste <= ipfNuevoExiteInssMut.size()
							|| indexPrivadoNoExiste <= titularesDobleCobertura.size()
							|| indexPrivadoNoExiste <= beneficiarioDobleCoberturaMutualistas.size()
							|| indexPrivadoNoExiste <= titularesIndicadorConvenioRural.size()
							|| indexPrivadoNoExiste <= beneficiadioIndicadorConvenioRural.size()
							|| indexPrivadoNoExiste <= privadoRegularPublico.size()
							|| indexPrivadoNoExiste <= privadoIrregularPublico.size())

						row = sheet.getRow(indexPrivadoNoExiste++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexPrivadoNoExiste++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexAltaModifTitDoCo = 1;

			if (altaModificacionTitularesDobleCobertura.isEmpty()) {
				celda = 345;
			} else {
				for (String a : altaModificacionTitularesDobleCobertura) {

					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexAltaModifTitDoCo <= tipoIdentificador.size()
							|| indexAltaModifTitDoCo <= tipoMovimiento.size()
							|| indexAltaModifTitDoCo <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexAltaModifTitDoCo <= registroIpfNulo.size()
							|| indexAltaModifTitDoCo <= informeAltaCruzado.size()
							|| indexAltaModifTitDoCo <= codTipoAsegurado.size()
							|| indexAltaModifTitDoCo <= titDobleCobertura.size()
							|| indexAltaModifTitDoCo <= beneDobleCobertura.size()
							|| indexAltaModifTitDoCo <= tipoIdentificadorMutualistas.size()
							|| indexAltaModifTitDoCo <= tipoMovimientoMutualista.size()
							|| indexAltaModifTitDoCo <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
							|| indexAltaModifTitDoCo <= registrosIpfNuloMutualistas.size()
							|| indexAltaModifTitDoCo <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexAltaModifTitDoCo <= codTipoAseguradoMutualistas.size()
							|| indexAltaModifTitDoCo <= regimenGeneral.size()
							|| indexAltaModifTitDoCo <= bajaTitulares.size()
							|| indexAltaModifTitDoCo <= totalEnvioInssAv.size()
							|| indexAltaModifTitDoCo <= bajasVinculadosSns.size()
							|| indexAltaModifTitDoCo <= mutualistasPrivados.size()
							|| indexAltaModifTitDoCo <= bajaPorDefuncion.size()
							|| indexAltaModifTitDoCo <= aseguramiento.size()
							|| indexAltaModifTitDoCo <= indicadorFarmacia.size()
							|| indexAltaModifTitDoCo <= codigoBadasRepetido.size()
							|| indexAltaModifTitDoCo <= altasSinNaf.size() || indexAltaModifTitDoCo <= cambiosIpf.size()
							|| indexAltaModifTitDoCo <= ipfNuevoExisteInssTit.size()
							|| indexAltaModifTitDoCo <= ipfAnteriorNoExisteInssMut.size()
							|| indexAltaModifTitDoCo <= ipfNuevoExiteInssMut.size()
							|| indexAltaModifTitDoCo <= titularesDobleCobertura.size()
							|| indexAltaModifTitDoCo <= beneficiarioDobleCoberturaMutualistas.size()
							|| indexAltaModifTitDoCo <= titularesIndicadorConvenioRural.size()
							|| indexAltaModifTitDoCo <= beneficiadioIndicadorConvenioRural.size()
							|| indexAltaModifTitDoCo <= privadoRegularPublico.size()
							|| indexAltaModifTitDoCo <= privadoIrregularPublico.size()
							|| indexAltaModifTitDoCo <= privadoNoExiste.size())

						row = sheet.getRow(indexAltaModifTitDoCo++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexAltaModifTitDoCo++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexIrrPrivRePublico = 1;

			if (irregularPrivadoRegularPublico.isEmpty()) {
				celda = 346;
			} else {
				for (String a : irregularPrivadoRegularPublico) {

					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexIrrPrivRePublico <= tipoIdentificador.size()
							|| indexIrrPrivRePublico <= tipoMovimiento.size()
							|| indexIrrPrivRePublico <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexIrrPrivRePublico <= registroIpfNulo.size()
							|| indexIrrPrivRePublico <= informeAltaCruzado.size()
							|| indexIrrPrivRePublico <= codTipoAsegurado.size()
							|| indexIrrPrivRePublico <= titDobleCobertura.size()
							|| indexIrrPrivRePublico <= beneDobleCobertura.size()
							|| indexIrrPrivRePublico <= tipoIdentificadorMutualistas.size()
							|| indexIrrPrivRePublico <= tipoMovimientoMutualista.size()
							|| indexIrrPrivRePublico <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
							|| indexIrrPrivRePublico <= registrosIpfNuloMutualistas.size()
							|| indexIrrPrivRePublico <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexIrrPrivRePublico <= codTipoAseguradoMutualistas.size()
							|| indexIrrPrivRePublico <= regimenGeneral.size()
							|| indexIrrPrivRePublico <= bajaTitulares.size()
							|| indexIrrPrivRePublico <= totalEnvioInssAv.size()
							|| indexIrrPrivRePublico <= bajasVinculadosSns.size()
							|| indexIrrPrivRePublico <= mutualistasPrivados.size()
							|| indexIrrPrivRePublico <= bajaPorDefuncion.size()
							|| indexIrrPrivRePublico <= aseguramiento.size()
							|| indexIrrPrivRePublico <= indicadorFarmacia.size()
							|| indexIrrPrivRePublico <= codigoBadasRepetido.size()
							|| indexIrrPrivRePublico <= altasSinNaf.size() || indexIrrPrivRePublico <= cambiosIpf.size()
							|| indexIrrPrivRePublico <= ipfNuevoExisteInssTit.size()
							|| indexIrrPrivRePublico <= ipfAnteriorNoExisteInssMut.size()
							|| indexIrrPrivRePublico <= ipfNuevoExiteInssMut.size()
							|| indexIrrPrivRePublico <= titularesDobleCobertura.size()
							|| indexIrrPrivRePublico <= beneficiarioDobleCoberturaMutualistas.size()
							|| indexIrrPrivRePublico <= titularesIndicadorConvenioRural.size()
							|| indexIrrPrivRePublico <= beneficiadioIndicadorConvenioRural.size()
							|| indexIrrPrivRePublico <= privadoRegularPublico.size()
							|| indexIrrPrivRePublico <= privadoIrregularPublico.size()
							|| indexIrrPrivRePublico <= privadoNoExiste.size()
							|| indexIrrPrivRePublico <= altaModificacionTitularesDobleCobertura.size())

						row = sheet.getRow(indexIrrPrivRePublico++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexIrrPrivRePublico++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexIrrPrivIrPublico = 1;

			if (irregularPrivadoIrregularPublico.isEmpty()) {
				celda = 347;
			} else {
				for (String a : irregularPrivadoIrregularPublico) {

					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexIrrPrivIrPublico <= tipoIdentificador.size()
							|| indexIrrPrivIrPublico <= tipoMovimiento.size()
							|| indexIrrPrivIrPublico <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexIrrPrivIrPublico <= registroIpfNulo.size()
							|| indexIrrPrivIrPublico <= informeAltaCruzado.size()
							|| indexIrrPrivIrPublico <= codTipoAsegurado.size()
							|| indexIrrPrivIrPublico <= titDobleCobertura.size()
							|| indexIrrPrivIrPublico <= beneDobleCobertura.size()
							|| indexIrrPrivIrPublico <= tipoIdentificadorMutualistas.size()
							|| indexIrrPrivIrPublico <= tipoMovimientoMutualista.size()
							|| indexIrrPrivIrPublico <= protegidoTipAseguramientoAndTipMovimientoMutualistas.size()
							|| indexIrrPrivIrPublico <= registrosIpfNuloMutualistas.size()
							|| indexIrrPrivIrPublico <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexIrrPrivIrPublico <= codTipoAseguradoMutualistas.size()
							|| indexIrrPrivIrPublico <= regimenGeneral.size()
							|| indexIrrPrivIrPublico <= bajaTitulares.size()
							|| indexIrrPrivIrPublico <= totalEnvioInssAv.size()
							|| indexIrrPrivIrPublico <= bajasVinculadosSns.size()
							|| indexIrrPrivIrPublico <= mutualistasPrivados.size()
							|| indexIrrPrivIrPublico <= bajaPorDefuncion.size()
							|| indexIrrPrivIrPublico <= aseguramiento.size()
							|| indexIrrPrivIrPublico <= indicadorFarmacia.size()
							|| indexIrrPrivIrPublico <= codigoBadasRepetido.size()
							|| indexIrrPrivIrPublico <= altasSinNaf.size() || indexIrrPrivIrPublico <= cambiosIpf.size()
							|| indexIrrPrivIrPublico <= ipfNuevoExisteInssTit.size()
							|| indexIrrPrivIrPublico <= ipfAnteriorNoExisteInssMut.size()
							|| indexIrrPrivIrPublico <= ipfNuevoExiteInssMut.size()
							|| indexIrrPrivIrPublico <= titularesDobleCobertura.size()
							|| indexIrrPrivIrPublico <= beneficiarioDobleCoberturaMutualistas.size()
							|| indexIrrPrivIrPublico <= titularesIndicadorConvenioRural.size()
							|| indexIrrPrivIrPublico <= beneficiadioIndicadorConvenioRural.size()
							|| indexIrrPrivIrPublico <= privadoRegularPublico.size()
							|| indexIrrPrivIrPublico <= privadoIrregularPublico.size()
							|| indexIrrPrivIrPublico <= privadoNoExiste.size()
							|| indexIrrPrivIrPublico <= altaModificacionTitularesDobleCobertura.size()
							|| indexIrrPrivIrPublico <= irregularPrivadoRegularPublico.size())

						row = sheet.getRow(indexIrrPrivIrPublico++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexIrrPrivIrPublico++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexIrregularPrivadoNoExiste = 1;

			if (irregularPrivadoNoExiste.isEmpty()) {
				celda = 348;
			} else {
				for (String a : irregularPrivadoNoExiste) {

					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexIrregularPrivadoNoExiste <= tipoIdentificador.size()
							|| indexIrregularPrivadoNoExiste <= tipoMovimiento.size()
							|| indexIrregularPrivadoNoExiste <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexIrregularPrivadoNoExiste <= registroIpfNulo.size()
							|| indexIrregularPrivadoNoExiste <= informeAltaCruzado.size()
							|| indexIrregularPrivadoNoExiste <= codTipoAsegurado.size()
							|| indexIrregularPrivadoNoExiste <= titDobleCobertura.size()
							|| indexIrregularPrivadoNoExiste <= beneDobleCobertura.size()
							|| indexIrregularPrivadoNoExiste <= tipoIdentificadorMutualistas.size()
							|| indexIrregularPrivadoNoExiste <= tipoMovimientoMutualista.size()
							|| indexIrregularPrivadoNoExiste <= protegidoTipAseguramientoAndTipMovimientoMutualistas
									.size()
							|| indexIrregularPrivadoNoExiste <= registrosIpfNuloMutualistas.size()
							|| indexIrregularPrivadoNoExiste <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexIrregularPrivadoNoExiste <= codTipoAseguradoMutualistas.size()
							|| indexIrregularPrivadoNoExiste <= regimenGeneral.size()
							|| indexIrregularPrivadoNoExiste <= bajaTitulares.size()
							|| indexIrregularPrivadoNoExiste <= totalEnvioInssAv.size()
							|| indexIrregularPrivadoNoExiste <= bajasVinculadosSns.size()
							|| indexIrregularPrivadoNoExiste <= mutualistasPrivados.size()
							|| indexIrregularPrivadoNoExiste <= bajaPorDefuncion.size()
							|| indexIrregularPrivadoNoExiste <= aseguramiento.size()
							|| indexIrregularPrivadoNoExiste <= indicadorFarmacia.size()
							|| indexIrregularPrivadoNoExiste <= codigoBadasRepetido.size()
							|| indexIrregularPrivadoNoExiste <= altasSinNaf.size()
							|| indexIrregularPrivadoNoExiste <= cambiosIpf.size()
							|| indexIrregularPrivadoNoExiste <= ipfNuevoExisteInssTit.size()
							|| indexIrregularPrivadoNoExiste <= ipfAnteriorNoExisteInssMut.size()
							|| indexIrregularPrivadoNoExiste <= ipfNuevoExiteInssMut.size()
							|| indexIrregularPrivadoNoExiste <= titularesDobleCobertura.size()
							|| indexIrregularPrivadoNoExiste <= beneficiarioDobleCoberturaMutualistas.size()
							|| indexIrregularPrivadoNoExiste <= titularesIndicadorConvenioRural.size()
							|| indexIrregularPrivadoNoExiste <= beneficiadioIndicadorConvenioRural.size()
							|| indexIrregularPrivadoNoExiste <= privadoRegularPublico.size()
							|| indexIrregularPrivadoNoExiste <= privadoIrregularPublico.size()
							|| indexIrregularPrivadoNoExiste <= privadoNoExiste.size()
							|| indexIrregularPrivadoNoExiste <= altaModificacionTitularesDobleCobertura.size()
							|| indexIrregularPrivadoNoExiste <= irregularPrivadoRegularPublico.size()
							|| indexIrregularPrivadoNoExiste <= irregularPrivadoIrregularPublico.size()
							|| indexIrregularPrivadoNoExiste <= irregularPrivadoIrregularPublico.size())

						row = sheet.getRow(indexIrregularPrivadoNoExiste++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexIrregularPrivadoNoExiste++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexAltModTitsDobCoberturaDos = 1;

			if (altaModificaTitualresDobleCoberturaDos.isEmpty()) {
				celda = 349;
			} else {
				for (String a : altaModificaTitualresDobleCoberturaDos) {

					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexAltModTitsDobCoberturaDos <= tipoIdentificador.size()
							|| indexAltModTitsDobCoberturaDos <= tipoMovimiento.size()
							|| indexAltModTitsDobCoberturaDos <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexAltModTitsDobCoberturaDos <= registroIpfNulo.size()
							|| indexAltModTitsDobCoberturaDos <= informeAltaCruzado.size()
							|| indexAltModTitsDobCoberturaDos <= codTipoAsegurado.size()
							|| indexAltModTitsDobCoberturaDos <= titDobleCobertura.size()
							|| indexAltModTitsDobCoberturaDos <= beneDobleCobertura.size()
							|| indexAltModTitsDobCoberturaDos <= tipoIdentificadorMutualistas.size()
							|| indexAltModTitsDobCoberturaDos <= tipoMovimientoMutualista.size()
							|| indexAltModTitsDobCoberturaDos <= protegidoTipAseguramientoAndTipMovimientoMutualistas
									.size()
							|| indexAltModTitsDobCoberturaDos <= registrosIpfNuloMutualistas.size()
							|| indexAltModTitsDobCoberturaDos <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexAltModTitsDobCoberturaDos <= codTipoAseguradoMutualistas.size()
							|| indexAltModTitsDobCoberturaDos <= regimenGeneral.size()
							|| indexAltModTitsDobCoberturaDos <= bajaTitulares.size()
							|| indexAltModTitsDobCoberturaDos <= totalEnvioInssAv.size()
							|| indexAltModTitsDobCoberturaDos <= bajasVinculadosSns.size()
							|| indexAltModTitsDobCoberturaDos <= mutualistasPrivados.size()
							|| indexAltModTitsDobCoberturaDos <= bajaPorDefuncion.size()
							|| indexAltModTitsDobCoberturaDos <= aseguramiento.size()
							|| indexAltModTitsDobCoberturaDos <= indicadorFarmacia.size()
							|| indexAltModTitsDobCoberturaDos <= codigoBadasRepetido.size()
							|| indexAltModTitsDobCoberturaDos <= altasSinNaf.size()
							|| indexAltModTitsDobCoberturaDos <= cambiosIpf.size()
							|| indexAltModTitsDobCoberturaDos <= ipfNuevoExisteInssTit.size()
							|| indexAltModTitsDobCoberturaDos <= ipfAnteriorNoExisteInssMut.size()
							|| indexAltModTitsDobCoberturaDos <= ipfNuevoExiteInssMut.size()
							|| indexAltModTitsDobCoberturaDos <= titularesDobleCobertura.size()
							|| indexAltModTitsDobCoberturaDos <= beneficiarioDobleCoberturaMutualistas.size()
							|| indexAltModTitsDobCoberturaDos <= titularesIndicadorConvenioRural.size()
							|| indexAltModTitsDobCoberturaDos <= beneficiadioIndicadorConvenioRural.size()
							|| indexAltModTitsDobCoberturaDos <= privadoRegularPublico.size()
							|| indexAltModTitsDobCoberturaDos <= privadoIrregularPublico.size()
							|| indexAltModTitsDobCoberturaDos <= privadoNoExiste.size()
							|| indexAltModTitsDobCoberturaDos <= altaModificacionTitularesDobleCobertura.size()
							|| indexAltModTitsDobCoberturaDos <= irregularPrivadoRegularPublico.size()
							|| indexAltModTitsDobCoberturaDos <= irregularPrivadoIrregularPublico.size()
							|| indexAltModTitsDobCoberturaDos <= irregularPrivadoIrregularPublico.size()
							|| indexAltModTitsDobCoberturaDos <= irregularPrivadoNoExiste.size())

						row = sheet.getRow(indexAltModTitsDobCoberturaDos++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexAltModTitsDobCoberturaDos++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			// ######### QUERY PROBLEMÁTICAS -> listadoRegistroNoExiste y
			// indexlistadoRegistroNoExisteDos

			int indexListadoRegistroNoExiste = 1;

			for (String a : listadoRegistroNoExiste) {

				if (listadoRegistroNoExiste.isEmpty()) {
					celda = 394;
				} else {

					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexListadoRegistroNoExiste <= tipoIdentificador.size()
							|| indexListadoRegistroNoExiste <= tipoMovimiento.size()
							|| indexListadoRegistroNoExiste <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexListadoRegistroNoExiste <= registroIpfNulo.size()
							|| indexListadoRegistroNoExiste <= informeAltaCruzado.size()
							|| indexListadoRegistroNoExiste <= codTipoAsegurado.size()
							|| indexListadoRegistroNoExiste <= titDobleCobertura.size()
							|| indexListadoRegistroNoExiste <= beneDobleCobertura.size()
							|| indexListadoRegistroNoExiste <= tipoIdentificadorMutualistas.size()
							|| indexListadoRegistroNoExiste <= tipoMovimientoMutualista.size()
							|| indexListadoRegistroNoExiste <= protegidoTipAseguramientoAndTipMovimientoMutualistas
									.size()
							|| indexListadoRegistroNoExiste <= registrosIpfNuloMutualistas.size()
							|| indexListadoRegistroNoExiste <= informeAltCruzadoConSegSocialFinalMutualistas.size()
							|| indexListadoRegistroNoExiste <= codTipoAseguradoMutualistas.size()
							|| indexListadoRegistroNoExiste <= regimenGeneral.size()
							|| indexListadoRegistroNoExiste <= bajaTitulares.size()
							|| indexListadoRegistroNoExiste <= totalEnvioInssAv.size()
							|| indexListadoRegistroNoExiste <= bajasVinculadosSns.size()
							|| indexListadoRegistroNoExiste <= mutualistasPrivados.size()
							|| indexListadoRegistroNoExiste <= bajaPorDefuncion.size()
							|| indexListadoRegistroNoExiste <= aseguramiento.size()
							|| indexListadoRegistroNoExiste <= indicadorFarmacia.size()
							|| indexListadoRegistroNoExiste <= codigoBadasRepetido.size()
							|| indexListadoRegistroNoExiste <= altasSinNaf.size()
							|| indexListadoRegistroNoExiste <= cambiosIpf.size()
							|| indexListadoRegistroNoExiste <= ipfNuevoExisteInssTit.size()
							|| indexListadoRegistroNoExiste <= ipfAnteriorNoExisteInssMut.size()
							|| indexListadoRegistroNoExiste <= ipfNuevoExiteInssMut.size()
							|| indexListadoRegistroNoExiste <= titularesDobleCobertura.size()
							|| indexListadoRegistroNoExiste <= beneficiarioDobleCoberturaMutualistas.size()
							|| indexListadoRegistroNoExiste <= titularesIndicadorConvenioRural.size()
							|| indexListadoRegistroNoExiste <= beneficiadioIndicadorConvenioRural.size()
							|| indexListadoRegistroNoExiste <= privadoRegularPublico.size()
							|| indexListadoRegistroNoExiste <= privadoIrregularPublico.size()
							|| indexListadoRegistroNoExiste <= privadoNoExiste.size()
							|| indexListadoRegistroNoExiste <= altaModificacionTitularesDobleCobertura.size()
							|| indexListadoRegistroNoExiste <= irregularPrivadoRegularPublico.size()
							|| indexListadoRegistroNoExiste <= irregularPrivadoIrregularPublico.size()
							|| indexListadoRegistroNoExiste <= irregularPrivadoIrregularPublico.size()
							|| indexListadoRegistroNoExiste <= irregularPrivadoNoExiste.size()
							|| indexListadoRegistroNoExiste <= altaModificaTitualresDobleCoberturaDos.size())

						row = sheet.getRow(indexListadoRegistroNoExiste++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexListadoRegistroNoExiste++);

					for (j = ultimaCelda; j >= separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(j).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(j).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			/*
			 * int indexlistadoRegistroNoExisteDos= 1;
			 * 
			 * if (listadoRegistroNoExisteDos.isEmpty()) { celda = 394; } else { for (String
			 * a : listadoRegistroNoExisteDos) {
			 * 
			 * String[] separar = a.split(",");
			 * 
			 * int i = 0;
			 * 
			 * // Si es menor o igual se hace obtiene las filas ya creadas if
			 * (indexlistadoRegistroNoExisteDos <= tipoIdentificador.size() ||
			 * indexlistadoRegistroNoExisteDos <= tipoMovimiento.size() ||
			 * indexlistadoRegistroNoExisteDos <=
			 * protegidoTipAseguramientoAndTipMovimiento.size() ||
			 * indexlistadoRegistroNoExisteDos <= registroIpfNulo.size() ||
			 * indexlistadoRegistroNoExisteDos <= informeAltaCruzado.size() ||
			 * indexlistadoRegistroNoExisteDos <= codTipoAsegurado.size() ||
			 * indexlistadoRegistroNoExisteDos <= titDobleCobertura.size() ||
			 * indexlistadoRegistroNoExisteDos <= beneDobleCobertura.size() ||
			 * indexlistadoRegistroNoExisteDos <= tipoIdentificadorMutualistas.size() ||
			 * indexlistadoRegistroNoExisteDos <= tipoMovimientoMutualista.size() ||
			 * indexlistadoRegistroNoExisteDos <=
			 * protegidoTipAseguramientoAndTipMovimientoMutualistas.size() ||
			 * indexlistadoRegistroNoExisteDos <= registrosIpfNuloMutualistas.size() ||
			 * indexlistadoRegistroNoExisteDos <=
			 * informeAltCruzadoConSegSocialFinalMutualistas.size() ||
			 * indexlistadoRegistroNoExisteDos <= codTipoAseguradoMutualistas.size() ||
			 * indexlistadoRegistroNoExisteDos <= regimenGeneral.size() ||
			 * indexlistadoRegistroNoExisteDos <= bajaTitulares.size() ||
			 * indexlistadoRegistroNoExisteDos <= totalEnvioInssAv.size() ||
			 * indexlistadoRegistroNoExisteDos <= bajasVinculadosSns.size() ||
			 * indexlistadoRegistroNoExisteDos <= mutualistasPrivados.size() ||
			 * indexlistadoRegistroNoExisteDos <= bajaPorDefuncion.size() ||
			 * indexlistadoRegistroNoExisteDos <= aseguramiento.size() ||
			 * indexlistadoRegistroNoExisteDos <= indicadorFarmacia.size() ||
			 * indexlistadoRegistroNoExisteDos <= codigoBadasRepetido.size() ||
			 * indexlistadoRegistroNoExisteDos <= altasSinNaf.size() ||
			 * indexlistadoRegistroNoExisteDos <= cambiosIpf.size() ||
			 * indexlistadoRegistroNoExisteDos <= ipfNuevoExisteInssTit.size() ||
			 * indexlistadoRegistroNoExisteDos <= ipfAnteriorNoExisteInssMut.size() ||
			 * indexlistadoRegistroNoExisteDos <= ipfNuevoExiteInssMut.size() ||
			 * indexlistadoRegistroNoExisteDos <= titularesDobleCobertura.size() ||
			 * indexlistadoRegistroNoExisteDos <=
			 * beneficiarioDobleCoberturaMutualistas.size() ||
			 * indexlistadoRegistroNoExisteDos <= titularesIndicadorConvenioRural.size() ||
			 * indexlistadoRegistroNoExisteDos <= beneficiadioIndicadorConvenioRural.size()
			 * || indexlistadoRegistroNoExisteDos <= privadoRegularPublico.size() ||
			 * indexlistadoRegistroNoExisteDos <= privadoIrregularPublico.size() ||
			 * indexlistadoRegistroNoExisteDos <= privadoNoExiste.size() ||
			 * indexlistadoRegistroNoExisteDos <=
			 * altaModificacionTitularesDobleCobertura.size() ||
			 * indexlistadoRegistroNoExisteDos <= irregularPrivadoRegularPublico.size() ||
			 * indexlistadoRegistroNoExisteDos <= irregularPrivadoIrregularPublico.size() ||
			 * indexlistadoRegistroNoExisteDos <= irregularPrivadoIrregularPublico.size() ||
			 * indexlistadoRegistroNoExisteDos <= irregularPrivadoNoExiste.size() ||
			 * indexlistadoRegistroNoExisteDos <=
			 * altaModificaTitualresDobleCoberturaDos.size() ||
			 * indexlistadoRegistroNoExisteDos <= listadoRegistroNoExiste.size())
			 * 
			 * row = sheet.getRow(indexlistadoRegistroNoExisteDos++); else // En caso de que
			 * no exista la fila se crea. row =
			 * sheet.createRow(indexlistadoRegistroNoExisteDos++);
			 * 
			 * for (j = ultimaCelda; j > separar.length; j++) { for (i = 0; i <
			 * separar.length; i++) {
			 * 
			 * boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);
			 * 
			 * if (separar[i].compareTo("null") == 0) { separar[i] = ""; } else { if
			 * (isNumeric == true) { if (separar[i].length() <= 10) { Integer n =
			 * Integer.parseInt(separar[i]); row.createCell(j).setCellValue(n); } else {
			 * DecimalFormat df = new DecimalFormat("0"); Long n;
			 * row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i]))); }
			 * } else { row.createCell(j).setCellValue(separar[i]); } } j++; } j = 0; }
			 * celda = ultimaCelda + separar.length; } } ultimaCelda = 394;
			 */

			int indexBajaPorDefuncionPrivadoPublico = 1;

			if (bajaPorDefuncionPrivadoPublico.isEmpty()) {
				celda = 394;
			} else {
				for (String a : bajaPorDefuncionPrivadoPublico) {

					String[] separar = a.split(",");

					int i = 0;

					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexBajaPorDefuncionPrivadoPublico <= tipoIdentificador.size()
							|| indexBajaPorDefuncionPrivadoPublico <= tipoMovimiento.size()
							|| indexBajaPorDefuncionPrivadoPublico <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexBajaPorDefuncionPrivadoPublico <= registroIpfNulo.size()
							|| indexBajaPorDefuncionPrivadoPublico <= informeAltaCruzado.size()
							|| indexBajaPorDefuncionPrivadoPublico <= codTipoAsegurado.size()
							|| indexBajaPorDefuncionPrivadoPublico <= titDobleCobertura.size()
							|| indexBajaPorDefuncionPrivadoPublico <= beneDobleCobertura.size()
							|| indexBajaPorDefuncionPrivadoPublico <= tipoIdentificadorMutualistas.size()
							|| indexBajaPorDefuncionPrivadoPublico <= tipoMovimientoMutualista.size()
							|| indexBajaPorDefuncionPrivadoPublico <= protegidoTipAseguramientoAndTipMovimientoMutualistas
									.size()
							|| indexBajaPorDefuncionPrivadoPublico <= registrosIpfNuloMutualistas.size()
							|| indexBajaPorDefuncionPrivadoPublico <= informeAltCruzadoConSegSocialFinalMutualistas
									.size()
							|| indexBajaPorDefuncionPrivadoPublico <= codTipoAseguradoMutualistas.size()
							|| indexBajaPorDefuncionPrivadoPublico <= regimenGeneral.size()
							|| indexBajaPorDefuncionPrivadoPublico <= bajaTitulares.size()
							|| indexBajaPorDefuncionPrivadoPublico <= totalEnvioInssAv.size()
							|| indexBajaPorDefuncionPrivadoPublico <= bajasVinculadosSns.size()
							|| indexBajaPorDefuncionPrivadoPublico <= mutualistasPrivados.size()
							|| indexBajaPorDefuncionPrivadoPublico <= bajaPorDefuncion.size()
							|| indexBajaPorDefuncionPrivadoPublico <= aseguramiento.size()
							|| indexBajaPorDefuncionPrivadoPublico <= indicadorFarmacia.size()
							|| indexBajaPorDefuncionPrivadoPublico <= codigoBadasRepetido.size()
							|| indexBajaPorDefuncionPrivadoPublico <= altasSinNaf.size()
							|| indexBajaPorDefuncionPrivadoPublico <= cambiosIpf.size()
							|| indexBajaPorDefuncionPrivadoPublico <= ipfNuevoExisteInssTit.size()
							|| indexBajaPorDefuncionPrivadoPublico <= ipfAnteriorNoExisteInssMut.size()
							|| indexBajaPorDefuncionPrivadoPublico <= ipfNuevoExiteInssMut.size()
							|| indexBajaPorDefuncionPrivadoPublico <= titularesDobleCobertura.size()
							|| indexBajaPorDefuncionPrivadoPublico <= beneficiarioDobleCoberturaMutualistas.size()
							|| indexBajaPorDefuncionPrivadoPublico <= titularesIndicadorConvenioRural.size()
							|| indexBajaPorDefuncionPrivadoPublico <= beneficiadioIndicadorConvenioRural.size()
							|| indexBajaPorDefuncionPrivadoPublico <= privadoRegularPublico.size()
							|| indexBajaPorDefuncionPrivadoPublico <= privadoIrregularPublico.size()
							|| indexBajaPorDefuncionPrivadoPublico <= privadoNoExiste.size()
							|| indexBajaPorDefuncionPrivadoPublico <= altaModificacionTitularesDobleCobertura.size()
							|| indexBajaPorDefuncionPrivadoPublico <= irregularPrivadoRegularPublico.size()
							|| indexBajaPorDefuncionPrivadoPublico <= irregularPrivadoIrregularPublico.size()
							|| indexBajaPorDefuncionPrivadoPublico <= irregularPrivadoIrregularPublico.size()
							|| indexBajaPorDefuncionPrivadoPublico <= irregularPrivadoNoExiste.size()
							|| indexBajaPorDefuncionPrivadoPublico <= altaModificaTitualresDobleCoberturaDos.size()
							|| indexBajaPorDefuncionPrivadoPublico <= listadoRegistroNoExiste.size()
							|| indexBajaPorDefuncionPrivadoPublico <= listadoRegistroNoExiste.size()
							|| indexBajaPorDefuncionPrivadoPublico <= listadoRegistroNoExisteDos.size())

						row = sheet.getRow(indexBajaPorDefuncionPrivadoPublico++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexBajaPorDefuncionPrivadoPublico++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(394).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(394).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(394).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
				}
			}
			ultimaCelda = celda;

			int indexBajaPorDefuncionPrivadoNoExiste = 1;

			if (bajaPorDefuncionPrivadoNoExiste.isEmpty()) {
				celda = 395;
			} else {

				for (String a : bajaPorDefuncionPrivadoNoExiste) {
					String[] separar = a.split(",");

					int i = 0;
					// Si es menor o igual se hace obtiene las filas ya creadas
					if (indexBajaPorDefuncionPrivadoNoExiste <= tipoIdentificador.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= tipoMovimiento.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= protegidoTipAseguramientoAndTipMovimiento.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= registroIpfNulo.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= informeAltaCruzado.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= codTipoAsegurado.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= titDobleCobertura.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= beneDobleCobertura.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= tipoIdentificadorMutualistas.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= tipoMovimientoMutualista.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= protegidoTipAseguramientoAndTipMovimientoMutualistas
									.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= registrosIpfNuloMutualistas.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= informeAltCruzadoConSegSocialFinalMutualistas
									.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= codTipoAseguradoMutualistas.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= regimenGeneral.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= bajaTitulares.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= totalEnvioInssAv.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= bajasVinculadosSns.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= mutualistasPrivados.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= bajaPorDefuncion.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= aseguramiento.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= indicadorFarmacia.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= codigoBadasRepetido.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= altasSinNaf.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= cambiosIpf.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= ipfNuevoExisteInssTit.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= ipfAnteriorNoExisteInssMut.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= ipfNuevoExiteInssMut.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= titularesDobleCobertura.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= beneficiarioDobleCoberturaMutualistas.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= titularesIndicadorConvenioRural.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= beneficiadioIndicadorConvenioRural.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= privadoRegularPublico.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= privadoIrregularPublico.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= privadoNoExiste.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= altaModificacionTitularesDobleCobertura.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= irregularPrivadoRegularPublico.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= irregularPrivadoIrregularPublico.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= irregularPrivadoIrregularPublico.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= irregularPrivadoNoExiste.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= altaModificaTitualresDobleCoberturaDos.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= listadoRegistroNoExiste.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= listadoRegistroNoExisteDos.size()
							|| indexBajaPorDefuncionPrivadoNoExiste <= bajaPorDefuncionPrivadoPublico.size())

						row = sheet.getRow(indexBajaPorDefuncionPrivadoNoExiste++);
					else
						// En caso de que no exista la fila se crea.
						row = sheet.createRow(indexBajaPorDefuncionPrivadoNoExiste++);

					for (j = ultimaCelda; j > separar.length; j++) {
						for (i = 0; i < separar.length; i++) {

							boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

							if (separar[i].compareTo("null") == 0) {
								separar[i] = "";
							} else {
								if (isNumeric == true) {
									if (separar[i].length() <= 10) {
										Integer n = Integer.parseInt(separar[i]);
										row.createCell(395).setCellValue(n);
									} else {
										DecimalFormat df = new DecimalFormat("0");
										Long n;
										row.createCell(395).setCellValue(df.format(n = Long.parseLong(separar[i])));
									}
								} else {
									row.createCell(395).setCellValue(separar[i]);
								}
							}
							j++;
						}
						j = 0;
					}
					celda = ultimaCelda + separar.length;
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

	/*
	 * Posible función para automatizar aún más el recorrido de las celdas para
	 * crear el fichero excel - No desarrollado aún. 30/05/2022
	 */

	public void funcionCelda(int ultimaCelda, List<String> datosQuery, Row row) {
		String[] separar = null;

		for (String a : datosQuery) {
			separar = a.split(",");
			for (int j = ultimaCelda; j > separar.length; j++) {
				for (int i = 0; i < separar.length; i++) {

					boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);

					if (separar[i].compareTo("null") == 0) {
						separar[i] = "";
					} else {
						if (isNumeric == true) {
							if (separar[i].length() <= 10) {
								Integer n = Integer.parseInt(separar[i]);
								row.createCell(j).setCellValue(n);
							} else {
								DecimalFormat df = new DecimalFormat("0");
								Long n;
								row.createCell(j).setCellValue(df.format(n = Long.parseLong(separar[i])));
							}
						} else {
							row.createCell(j).setCellValue(separar[i]);
						}
					}
					j++;
				}
				j = 0;
			}
		}

	}

}
