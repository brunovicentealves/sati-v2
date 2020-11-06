package com.br.sati.Util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class GerarPdfReport {

    private static final Logger logger = LoggerFactory.getLogger(GerarPdfReport.class);

    public static ByteArrayInputStream gerarPdfContrato (){
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("sdf"));
            document.add(new Paragraph("paragrafoUm,f"));
            document.add(new Paragraph("paragrafoDois,f"));

            document.close();

        }catch(DocumentException ex ){

            logger.error("Error occurred: {0}", ex.toString());
        }

        return  new ByteArrayInputStream(out.toByteArray());
    }

}
