package com.education.service.fileConvertion.impl;

import com.education.service.fileConvertion.FileConversionService;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import lombok.AllArgsConstructor;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.jvnet.hk2.annotations.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@AllArgsConstructor
public class FileConversionServiceImpl implements FileConversionService {

    @Override
    public void convertFile(String extension) {
        if (extension.equals("doc") || extension.equals("docx")) {

            try {
                InputStream templateInputStream = new FileInputStream("C:/Users/Dasha/Desktop/123.doc");
                WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);

                String outputfilepath = "C:/Users/Dasha/Desktop/new.pdf";
                FileOutputStream os = new FileOutputStream(outputfilepath);
                Docx4J.toPDF(wordMLPackage, os);
                os.flush();
                os.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } else if (extension.equals("jpg") || extension.equals("png")) {
            String dest = "C:/Users/Dasha/Desktop/IMAGE.pdf";
            String imFile = "C:/Users/Dasha/Desktop/Externalizable.png";
            try {
                PdfWriter pdfWriter = new PdfWriter(dest);
                PdfDocument pdfDocument = new PdfDocument(pdfWriter);
                Document document = new Document(pdfDocument);
                ImageData data = ImageDataFactory.create(imFile);
                Image image = new Image(data);
                document.add(image);
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
