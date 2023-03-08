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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

/**
 * Представляет реализацию конвертации файлов в формат pdf
 *
 * @author Дарья Лукьянова
 */


@Service
@RequiredArgsConstructor
public class FileConversionServiceImpl implements FileConversionService {

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    @Override
    public void convertFile(MultipartFile multipartFile) {
        Path dir; //создаем директорию для сохранения файлов;
        String paths; //создаем путь для сохранения сконвертированных файлов
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        String extension = getFileExtension(file); //определяем расширение файла
        try {
            dir = Files.createDirectories(Paths.get("convertedFiles"));
            paths = dir + File.separator + "new.pdf";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        конвертация файлов с расширениями doc, docx
        if (extension.equals("doc") || extension.equals("docx")) {
            try (BufferedInputStream buffIs = new BufferedInputStream(
                    new ByteArrayInputStream(multipartFile.getBytes()));
                 FileOutputStream os = new FileOutputStream(paths)) {
                WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(buffIs);
                Docx4J.toPDF(wordMLPackage, os);
                os.flush();
            } catch (Throwable e) {
                e.printStackTrace();
            }
//            конвертация файлов с расширениями jpg, png
        } else if (extension.equals("jpg") || extension.equals("png")) {
            try (BufferedInputStream buffIs = new BufferedInputStream(
                    new ByteArrayInputStream(multipartFile.getBytes()))) {
                PdfWriter pdfWriter = new PdfWriter(paths);
                PdfDocument pdfDocument = new PdfDocument(pdfWriter);
                Document document = new Document(pdfDocument);
                ImageData data = ImageDataFactory.create(buffIs.readAllBytes());
                Image image = new Image(data);
                document.add(image);
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
