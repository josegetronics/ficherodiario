package com.getronics.ficherodiario.util;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.getronics.ficherodiario.models.entity.CrucesUrgentes;

@Component("hojaCuatro.xlsx")
public class HojaDatosCuatroExcel extends AbstractXlsxView {

	private Logger logDatosCuatro = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"datos4.xlsx\"");

		logDatosCuatro.info("Iniciando la clase buildExcelDocument");

		List<String> datosCuatro = (List<String>) model.get("crucesUrgentes");

		final String[] header = { "COD_TIPO_ASEGURADO", "TIPO_MOVIMIENTO", "IPF", "DNI_NIE", "PASAPORTE", "NAF",
				"NAF_SEC1", "NAF_SEC2", "NAF_SEC3", "NAF_SEC4", "NAF_SEC5", "NAF_SEC6", "NAF_SEC7", "NAF_SEC8",
				"NAF_SEC9", "INDICATIVO_NOMBRE", "APELLIDOS_NOMBRE", "APELLIDO1", "APELLIDO2", "NOMBRE", "NACIONALIDAD",
				"FECHA_NACIMIENTO", "SEXO", "INDICATIVO_DOMICILIO", "DOMICILIO", "TIPO_ASEGURAMIENTO",
				"COD_INDICADOR_DE_FARMACIA", "COD_SUBINDICADOR_DE_FARMACIA", "COD_SITUACION", "FECHA_EFECTO_SITUACION",
				"COD_TIPO_BENEFICIARIO", "IPF_TITULAR", "NAF_TITULAR", "NUMERO_SECUENCIA", "FECHA_NACIMIENTO_RAW",
				"IPF_ANTERIOR", "COD_USUARIO_SNS", "CODIGO_BADAS", "MOTIVO_BAJA", "PROTEGIDA",
				"INDICADOR_DOBLE_COBERTURA", "CIP_MUTUALISTA", "CIP_MUTUALISTA_TITULAR", "INDICADOR_CONVENIO_RURAL",
				"PRESTADORA_PRIVADA" };

		// SE INDICA EL NOMBRE DE LA PÁGINA DEL EXCEL
		Sheet sheet = workbook.createSheet("DATOS");

		/*
		 * ###################################################### INICIO - ESTILO PARA
		 * LAS CELDA DE LA CABECERA Y CUERPO
		 * #####################################################
		 */

		// Formato en la cabecera.
		CellStyle headerStyle = null;

		// Se crea el estilo de la fuente
		XSSFFont font = (XSSFFont) workbook.createFont();

		font.setFontHeight(11);
		font.setFontName("Calibri");
		font.setBold(true);
		font.setItalic(true);
		font.setColor(IndexedColors.WHITE.getIndex());

		// Se crea el estilo de la celda
		String colorRgb= "1F4E78";
		byte[] rgb= Hex.decodeHex(colorRgb);
		
		XSSFColor colorCabecera = new XSSFColor(rgb,null);
		
		headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// Se crea el estilo del resto de celdas
		CellStyle celdaStyle = null;
		celdaStyle = workbook.createCellStyle();

		celdaStyle.setBorderTop(BorderStyle.THIN);
		celdaStyle.setBorderBottom(BorderStyle.THIN);
		celdaStyle.setBorderLeft(BorderStyle.THIN);
		celdaStyle.setBorderRight(BorderStyle.THIN);

		// Damos a la fuente el estilo
		headerStyle.setFont(font);

		/*
		 * ###################################################### FIN - ESTILO PARA LAS
		 * CELDA DE LA CABECERA Y CUERPO
		 * #####################################################
		 */

		// Creamos la fila del encabezado.
		Row row = sheet.createRow(0);
		Cell cell;

		for (int i = 0; i < header.length; i++) {
			sheet.setColumnWidth(i, 20 * 276);
			cell = row.createCell(i);
			// Se llena la cabecera con los datos.
			cell.setCellValue(header[i]);

			// Se le añade el estilo creado.
			cell.setCellStyle(headerStyle);
		}

		int rowNum = 1;

		// rellenamos el body
		for (String a : datosCuatro) {

			String[] separar = a.split(",");
			row = sheet.createRow(rowNum++);
			for (int i = 0; i < separar.length; i++) {
				Cell celdaBody = row.createCell(i);
				boolean isNumeric = separar[i].chars().allMatch(Character::isDigit);
				if (separar[i].compareTo("null") == 0) {
					separar[i] = "";
				} else {
					if (isNumeric == true) {
						if (separar[i].length() <= 10) {
							Integer n = Integer.parseInt(separar[i]);
							celdaBody.setCellValue(n);
						} else {
							DecimalFormat df = new DecimalFormat("0");
							Long n;
							celdaBody.setCellValue(df.format(n = Long.parseLong(separar[i])));
						}
					} else {
						celdaBody.setCellValue(separar[i]);

					}
				}
				celdaBody.setCellStyle(celdaStyle);
			}
		}

		CellRangeAddress region = new CellRangeAddress(0, datosCuatro.size(), 0, header.length - 1);
		RegionUtil.setBorderTop(BorderStyle.MEDIUM, region, sheet);
		RegionUtil.setBorderBottom(BorderStyle.MEDIUM, region, sheet);
		RegionUtil.setBorderLeft(BorderStyle.MEDIUM, region, sheet);
		RegionUtil.setBorderRight(BorderStyle.MEDIUM, region, sheet);

		RegionUtil.setTopBorderColor(IndexedColors.BLACK.index, region, sheet);
		RegionUtil.setBottomBorderColor(IndexedColors.BLACK.index, region, sheet);
		RegionUtil.setLeftBorderColor(IndexedColors.BLACK.index, region, sheet);
		RegionUtil.setRightBorderColor(IndexedColors.BLACK.index, region, sheet);

	}

}
