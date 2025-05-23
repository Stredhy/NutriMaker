/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javafx.nutrimaker;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfFontFactory.EmbeddingStrategy;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TabAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.javafx.nutrimaker.models.Diet;
import com.javafx.nutrimaker.models.Ingredient;
import com.javafx.nutrimaker.models.Meal;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
/**
 *
 * @author mimoe
 */
public class PDFBuilder {
    private static final String PATH = System.getProperty("user.home") + "/Documents/diet.pdf";
    private static final URL LOGO_PATH = PDFBuilder.class.getResource("images/nutrimakerLogo.png");

    public PDFBuilder(PDFBuilder pdf){}
    
    public PDFBuilder(Diet diet){}
    
    public static void exportPdf(Diet diet) throws FileNotFoundException, IOException, MalformedURLException, URISyntaxException{
        generatePdf(diet);
        Desktop.getDesktop().open(new File(PATH));
    }
    
    private static void generatePdf(Diet diet) throws FileNotFoundException, MalformedURLException, IOException, URISyntaxException{         
        String fontPath = PDFBuilder.class.getResource("fonts/ComicRelief-Regular.ttf").toURI().getPath();
        String fontBoldPath = PDFBuilder.class.getResource("fonts/ComicRelief-Bold.ttf").toURI().getPath();
        ImageData imageData = ImageDataFactory.create(LOGO_PATH);
        Image img = new Image(imageData);
        img.scaleToFit(200,200);

        PdfWriter pdfWriter = new PdfWriter(PATH);
        PdfDocument pdfDoc = new PdfDocument(pdfWriter);
        pdfDoc.addNewPage();

        // Obtener dimensiones de la pagina
        float x = pdfDoc.getFirstPage().getPageSize().getRight();
        float y = pdfDoc.getFirstPage().getPageSize().getTop();

        img.setFixedPosition(x - 160, y - 140);

        Document doc = new Document(pdfDoc,PageSize.A4);
        doc.add(img);
        PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H, EmbeddingStrategy.PREFER_EMBEDDED);
        doc.setFont(font);
        doc.setFontSize(10);  

        Paragraph namePara = new Paragraph("Nombre: " + diet.getPatient().getName()).setFontSize(12);
        Paragraph infoPatient = new Paragraph();

        infoPatient.addTabStops(new TabStop(100, TabAlignment.LEFT));

        infoPatient.addTabStops(new TabStop(185, TabAlignment.LEFT));
        infoPatient.addTabStops(new TabStop(305, TabAlignment.LEFT));

        infoPatient.add(new Tab()); 
        infoPatient.add("Edad: " + diet.getPatient().getAge());
        infoPatient.add(new Tab());
        infoPatient.add("Estatura: " + diet.getPatient().getHeight() + " m");
        infoPatient.add(new Tab());        
        infoPatient.add("Peso: " + diet.getPatient().getWeight() + " kg");

        for(Paragraph paragraph : new Paragraph[]{namePara,infoPatient}){
            doc.add(paragraph);
        }
        doc.add(new LineSeparator(new SolidLine(1))); // linea separadora

        Paragraph comments = new Paragraph("Comentarios:");
        // Paragraph comments = new Paragraph(diet.getComments());
        doc.add(comments);

        doc.add(new LineSeparator(new SolidLine(1))); // linea separadora

        Paragraph nutriValues = new Paragraph();
       
        nutriValues.add("Calorias: " + diet.getCalories() + " Kcal    ");
        nutriValues.add("Proteinas: " + diet.getProtein() + " g    ");
        nutriValues.add("Grasas: " + diet.getFats() + " g    ");
        nutriValues.add("Calcio: " + diet.getCalcium() + " g    ");
        nutriValues.add("Sodio: " + diet.getSodium() + " g    ");
        nutriValues.add("Hierro: " + diet.getIron() + " g");
        
        doc.add(nutriValues);
        
        doc.add(new LineSeparator(new SolidLine(1))); // linea separadora
                
        //settear dietas
        for(Meal meal : diet.getMeals()){
            Table table = new Table(new float[]{1,10});
            table.setWidth(UnitValue.createPercentValue(100));
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE", new Locale("es", "ES"));

            Paragraph day = new Paragraph(sdf.format(meal.getDay())).setFontSize(12);

            day.setRotationAngle(Math.PI/2);
            
            Cell dayCell = new Cell().add(day);
            dayCell.setBorder(Border.NO_BORDER);    
            dayCell.setBorderRight(new SolidBorder(1));
            dayCell.setVerticalAlignment(VerticalAlignment.MIDDLE);
            
            Cell mealCell = new Cell();
            for(Paragraph paragraph : setMeals(meal)){
                mealCell.add(paragraph);
            }
            table.addCell(dayCell);
            table.addCell(mealCell);
        }
        doc.close();
   }
    
   private static Iterable<Paragraph> setMeals(Meal meal){
        List<Paragraph> ingredients=null;
        Paragraph typeMeal = new Paragraph("            " + meal.getMealType());
        ingredients.add(typeMeal);
        for(Ingredient ing : meal.getIngredients()){
            Paragraph ingredient = new Paragraph((ing != meal.getIngredients().getLast())?
                    ing.getName()+ " - " + ing.getAmount() + " | ":
                    ing.getName()+ " - " + ing.getAmount()
            );
            ingredients.add(typeMeal);
        }
        return ingredients;
   }
   
}
