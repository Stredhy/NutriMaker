package com.javafx.nutrimaker;

import com.javafx.nutrimaker.repository.DietRepository;
import com.javafx.nutrimaker.repository.MealRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/*
Usuario de prueba

email: probandoregistro@cactus.moy
contraseña: 123456789
*/

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        // Crear una instancia de la clase que contiene createNewDiet
        MealRepository dietService = new MealRepository();

        // Parámetros de ejemplo
        double caloriasTotales = 2000.0;
        int comidasPorDia = 4; // entre 1 y 6
        String diaDescanso = "SUNDAY"; // Debe coincidir con el formato de DayOfWeek.toString()
        int userId = 25;
        int patientId = 3;

        // Ejecutar creación de la dieta
        boolean exito = dietService.createNewDiet(caloriasTotales, comidasPorDia, diaDescanso, userId, patientId);

        // Mostrar resultado
        if (exito) {
            System.out.println("Dieta creada exitosamente.");
        } else {
            System.out.println("Falló la creación de la dieta.");
        }
    }
}