package com.getronics.ficherodiario.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.getronics.ficherodiario.models.entity.CrucesUrgentes;

@Component("ejecucionFicheros.xlsx")
public class HojaTresExcel extends AbstractXlsxView{

	private Logger logDatosTres = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"datosTres.xlsx\"");
		
		logDatosTres.info("Iniciando la clase HojaTresExcel");
		
		//CrucesUrgentes datos = (CrucesUrgentes) model.get("crucesUrgentes");
		
		final String[] header = { "COD_ESTADO", "COD_USUARIO_SNS", "DNI_NIE", "COD_USUARIO_SNS_TITULAR" };

		// SE INDICA EL NOMBRE DE LA PÁGINA DEL EXCEL
		Sheet sheet = workbook.createSheet("DATOS");
		
	

		
	}

}
