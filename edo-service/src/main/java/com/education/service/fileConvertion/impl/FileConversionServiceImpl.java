package com.education.service.fileConvertion.impl;

import com.education.service.fileConvertion.FileConversionService;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import lombok.RequiredArgsConstructor;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * Представляет реализацию конвертации файлов в формат pdf
 *
 * @author Дарья Лукьянова
 */


@Service
@RequiredArgsConstructor
public class FileConversionServiceImpl implements FileConversionService {

    @Override
    public void convertFile(MultipartFile multipartFile) {
        String filename = multipartFile.getOriginalFilename();
        String extension = filename.substring(filename.indexOf(".") + 1);
//        конвертация файлов с расширениями doc, docx
        if (extension.equals("doc") || extension.equals("docx")) {

            try {
                InputStream inputStream = multipartFile.getInputStream();
                WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(inputStream);
                String outputFilePath = "C:/Users/Dasha/Desktop/new.pdf";
                FileOutputStream os = new FileOutputStream(outputFilePath);
                Docx4J.toPDF(wordMLPackage, os);
                os.flush();
                os.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }
//            конвертация файлов с расширениями jpg, png
        } else if (extension.equals("jpg") || extension.equals("png")) {
            String dest = "C:/Users/Dasha/Desktop/IMAGE.pdf";
            try {
                PdfWriter pdfWriter = new PdfWriter(dest);
                PdfDocument pdfDocument = new PdfDocument(pdfWriter);
                Document document = new Document(pdfDocument);
                ImageData data = ImageDataFactory.create(multipartFile.getInputStream().readAllBytes());
                Image image = new Image(data);
                document.add(image);
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
