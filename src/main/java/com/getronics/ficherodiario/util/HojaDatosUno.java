package com.getronics.ficherodiario.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HojaDatosUno {

	private static final String EXCEL_FILE_PATH = "C:/Log/datos1.xlsx";
	private Logger logHojaDatosUno = LoggerFactory.getLogger(this.getClass());
	 
	public void crearHojaExcel(List<String> datosUno) {
		
		final String[] header = {"COD_TIPO_ASEGURADO","TIPO_MOVIMIENTO","IPF","DNI_NIE","PASAPORTE","NAF",
				 "NAF_SEC1","NAF_SEC2","NAF_SEC3","NAF_SEC4",
				 "NAF_SEC5","NAF_SEC6","NAF_SEC7","NAF_SEC8","NAF_SEC9","INDICATIVO_NOMBRE",
				 "APELLIDOS_NOMBRE","APELLIDO1","APELLIDO2",
				 "NOMBRE	NACIONALIDAD","FECHA_NACIMIENTO","SEXO","INDICATIVO_DOMICILIO",
				 "DOMICILIO","TIPO_ASEGURAMIENTO",
				 "COD_INDICADOR_DE_FARMACIA","COD_SUBINDICADOR_DE_FARMACIA","COD_SITUACION",
				 "FECHA_EFECTO_SITUACION","COD_TIPO_BENEFICIARIO",
				 "IPF_TITULAR","NAF_TITULAR","NUMERO_SECUENCIA","FECHA_NACIMIENTO_RAW",
				 "IPF_ANTERIOR","COD_USUARIO_SNS","CODIGO_BADAS",
				 "MOTIVO_BAJA","PROTEGIDA","INDICADOR_DOBLE_COBERTURA","CIP_MUTUALISTA",
				 "CIP_MUTUALISTA_TITULAR","INDICADOR_CONVENIO_RURAL", "PRESTADORA_PRIVADA"};
		
		Workbook workbook = new XSSFWorkbook();

		// SE INDICA EL NOMBRE DE LA PÁGINA DEL EXCEL
		Sheet sheet = workbook.createSheet("DATOS");

		// Se crea la fila de la cabecera
		Row row = sheet.createRow(0);
		
		// Se crea la variable de estilo para las celdas de la cabecera
		CellStyle headerStyle = null;
		
		// Se crea el estilo de la fuente
		XSSFFont font = (XSSFFont) workbook.createFont();
		font.setFontHeight(11);
		font.setFontName("Calibri");
		font.setBold(true);
        font.setItalic(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        
        // Se crea el estilo de la celda
        headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE .getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		 
        // Si se quisiera alinear
        // style.setAlignment(HorizontalAlignment.CENTER);
        
        // Se crea el estilo del resto de celdas
        CellStyle celdaStyle = null;
        celdaStyle = workbook.createCellStyle();
         
        celdaStyle.setBorderTop(BorderStyle.THIN);
        celdaStyle.setBorderBottom(BorderStyle.THIN);
        celdaStyle.setBorderLeft(BorderStyle.THIN);
        celdaStyle.setBorderRight(BorderStyle.THIN);
        
        // Damos a la fuente el estilo
        headerStyle.setFont(font);
  
		// Se rellena la fila con los campos de la cabecera.
		for (int i = 0; i < header.length; i++) {
			sheet.setColumnWidth(i, 20 * 256);
			Cell cell = row.createCell(i);
			
			// Rellenamos la celda con la parte de la cabecera correspondiente
			cell.setCellValue(header[i]);
			
			// Damos el estilo a la celda
			cell.setCellStyle(headerStyle);
		}
		
		int rowNum = 1;

		for (String a : datosUno) {
			String[] separar = a.split(",");
			row = sheet.createRow(rowNum++);
			for (int i = 0; i < header.length; i++) {
				Cell celda = row.createCell(i);
				if (separar[i].compareTo("null") == 0) {	
					celda.setCellValue("");
				} else {
					celda.setCellValue(separar[i]);
				}
				celda.setCellStyle(celdaStyle);
			}
		}
		
		// Definimos un grupo de celdas y les pintamos los bordes
        CellRangeAddress region = new CellRangeAddress(0, datosUno.size(), 0, header.length-1);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, region, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, region, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, region, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, region, sheet);
        
        RegionUtil.setTopBorderColor(IndexedColors.BLACK.index, region, sheet);
        RegionUtil.setBottomBorderColor(IndexedColors.BLACK.index, region, sheet);
        RegionUtil.setLeftBorderColor(IndexedColors.BLACK.index, region, sheet);
        RegionUtil.setRightBorderColor(IndexedColors.BLACK.index, region, sheet);
        
		FileOutputStream outputStream = null;

		try {
			outputStream = new FileOutputStream(EXCEL_FILE_PATH);
			workbook.write(outputStream);
		} catch (IOException e) {
			logHojaDatosUno.error(e.getMessage(), e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
					workbook.close();
				} catch (IOException e) {
					logHojaDatosUno.error(e.getMessage(), e);
				}
			}
		}
	}
}
