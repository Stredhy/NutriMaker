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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
        String fontPath = PDFBuilder.class.getResource("fonts/ComicShannsMonoNerdFont-Regular.otf").toURI().getPath();
        
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

        
        //Datos del paciente
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

        //Comentarios
        Paragraph comments = new Paragraph("Comentarios:");
        
        doc.add(comments);
        doc.add(new Paragraph(diet.getNote()));

        doc.add(new LineSeparator(new SolidLine(1))); // linea separadora

        //Valores nutrimentales
        
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
        doc.add(setMeals(diet));
        
        doc.close();
   }
    
   private static Table setMeals(Diet diet) throws IOException, URISyntaxException{
        String fontBoldPath = PDFBuilder.class.getResource("fonts/ComicShannsMonoNerdFont-Bold.otf").toURI().getPath();
        PdfFont fontBold = PdfFontFactory.createFont(fontBoldPath, PdfEncodings.IDENTITY_H, EmbeddingStrategy.PREFER_EMBEDDED);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", new Locale("es", "ES"));
        Map<String,Integer> mealsSort = new HashMap<>();
        mealsSort.put("BREAKFAST",1);
        mealsSort.put("SNACK",2);
        mealsSort.put("LUNCH",3);
        mealsSort.put("DINNER",4);
        
        Map<String, List<Meal>> mealsByDay = new LinkedHashMap<>();
        
        List<String> days = Arrays.asList("lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo");

        
        for(String day : days){
            mealsByDay.put(day, new ArrayList<>());
        }
        
        for(Meal meal : diet.getMeals()){
            String day = sdf.format(meal.getDay()).toLowerCase();
            if(mealsByDay.containsKey(day)){
                mealsByDay.get(day).add(meal);
            }
        }
        
        for(String day : days){
            List<Meal> meals = mealsByDay.get(day);
            meals.sort(Comparator.comparing(e -> mealsSort.getOrDefault(e.getMealType(), 99)));
        }
        
        Table table = new Table(new float[]{1,10});
        table.setWidth(UnitValue.createPercentValue(100));
        
        for(String day : days){
            List<Meal> meals = mealsByDay.get(day);
            if(meals.isEmpty()){
                continue;
            }
            Paragraph currentDay = new Paragraph();
            currentDay.add(day.toUpperCase());
            currentDay.setFontSize(12);
            currentDay.setRotationAngle(Math.PI/2);
            currentDay.setFont(fontBold);
            
            Cell dayCell = new Cell().add(currentDay);
            dayCell.setBorderRight(new SolidBorder(1));
            dayCell.setBorderBottom(new SolidBorder(1));
            dayCell.setBorder(Border.NO_BORDER);
            dayCell.setVerticalAlignment(VerticalAlignment.MIDDLE);
            dayCell.setWidth(30);
            dayCell.setPadding(10);
            
            
            Cell mealsCell = new Cell();
            
            for(Meal meal : meals){
                Paragraph type = new Paragraph(meal.getMealType()+ " \uE28D \uE2A5 \uE28D  " + meal.getName()).setFont(fontBold);
                type.setPaddingLeft(50);
                mealsCell.add(type);
                
                mealsCell.add(setIngredients(meal));
                
            }
            mealsCell.setBorderBottom(new SolidBorder(1));
            mealsCell.setBorder(Border.NO_BORDER);
            mealsCell.setPadding(10);
            
            table.addCell(dayCell);
            table.addCell(mealsCell);
        }
        
        return table;
    }
    
    private static Paragraph setIngredients(Meal meal){
        Paragraph paragraph = new Paragraph();
        for(Ingredient ingredient : meal.getIngredients()){
            paragraph.add((ingredient != meal.getIngredients().get(meal.getIngredients().size() - 1))?
                    ingredient.getName() + " \uEA9C " + ingredient.getAmount() + "  \uE29E  " :
                    ingredient.getName() + " \uEA9C " + ingredient.getAmount());
        }
        return paragraph;
    }
}
