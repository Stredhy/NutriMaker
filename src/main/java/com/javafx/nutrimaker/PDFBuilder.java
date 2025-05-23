/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javafx.nutrimaker;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.javafx.nutrimaker.models.Diet;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
/**
 *
 * @author mimoe
 */
public class PDFBuilder {
    private static final String PATH = System.getProperty("user.home") + "/Documents/diet.pdf";
    private static final URL LOGO_PATH = PDFBuilder.class.getResource("images/nutrimakerLogo.png");

    public PDFBuilder(PDFBuilder pdf){}
    
    public PDFBuilder(Diet diet){}
    
    public static void exportPdf(Diet diet) throws FileNotFoundException, IOException{
        setElements(diet);
        Desktop.getDesktop().open(new File(PATH));
    }
    
   private static void setElements(Diet diet) throws FileNotFoundException, MalformedURLException{
       String patientName = diet.getPatient().getName();
       int patientAge = diet.getPatient().getAge();
       Double patientWeight = diet.getPatient().getWeight();
       Double patientHeight = diet.getPatient().getHeight();            
       ImageData imageData = ImageDataFactory.create(LOGO_PATH);
       Image img = new Image(imageData);
       img.scaleToFit(200,200);

       PdfWriter pdfWriter = new PdfWriter(PATH);
       PdfDocument pdfDoc = new PdfDocument(pdfWriter);
       pdfDoc.addNewPage();
       
       float x = pdfDoc.getFirstPage().getPageSize().getRight();
       float y = pdfDoc.getFirstPage().getPageSize().getTop();
       
       img.setFixedPosition(x - 160, y - 140);
       
       Document doc = new Document(pdfDoc,PageSize.A4);
       
       Paragraph namePara = new Paragraph("Nombre: " + patientName);
       
       Paragraph infoPatient = new Paragraph("Edad: " + patientAge + 
                            "Peso: " + patientWeight + " kg"+
                            "Estatura: " + patientHeight + " m");
       
       Paragraph[] paragraphs = {namePara,infoPatient};
       
       for(Paragraph paragraph : paragraphs){
           doc.add(paragraph);
       }
       
       doc.add(img);
       doc.close();
   }
    
}
