package com.wildcodeschool.festivalorleansjoue.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.wildcodeschool.festivalorleansjoue.services.AppExceptionService;

@Service
public class PdfGeneratorUtil {
	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private String baseDir;
	
	@Value("${app.uploadInvoice.dir}")
	private String uploadDir;

	public String createPdf(String templateName, Map<String, Object> map, String societyName) throws Exception {
		Assert.notNull(templateName, "The templateName can not be null");
		Context ctx = new Context();
		if (map != null) {
			Iterator<Entry<String, Object>> itMap = map.entrySet().iterator();
			while (itMap.hasNext()) {

				Map.Entry<String, Object> entry = itMap.next();
				ctx.setVariable(entry.getKey(), entry.getValue());
			}
		}

		String processedHtml = templateEngine.process(templateName, ctx);
		FileOutputStream os = null;
		SimpleDateFormat formater = null;
		Date today = new Date();
		formater = new SimpleDateFormat("dd-MM-yy_HH-mm-ss");
		String date = formater.format(today);
		String fileName = societyName + "_" + date;
		fileName = fileName.replaceAll("[^\\w]","");

		try {
			final File outputFile = new File(baseDir + uploadDir + File.separator + fileName + ".pdf");
			outputFile.createNewFile();
			os = new FileOutputStream(outputFile);
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(processedHtml);
			renderer.layout();
			renderer.createPDF(os, false);
			renderer.finishPDF();
		} catch (Exception e){
			e.printStackTrace();
			throw new AppExceptionService("Echec de la génération du fichier PDF");
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new AppExceptionService("Erreur lors de la fermeture du process (génération fichier PDF)");
				}
			}
		}
		return uploadDir + File.separator + fileName + ".pdf";
	}
}
