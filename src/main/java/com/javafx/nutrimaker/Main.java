package com.javafx.nutrimaker;

import com.javafx.nutrimaker.database.DatabaseClient;
import com.javafx.nutrimaker.models.Diet;
import com.javafx.nutrimaker.repository.DietRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

/*
Usuario de prueba

email: probandoregistro@cactus.moy
contraseña: 123456789
*/

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Loginform.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        DietRepository repo = new DietRepository();

        try {
            int dietId = 1; // ID válido que exista en la base de datos
            Diet diet = repo.getDietObjectById(dietId);
            System.out.println("Dieta obtenida:");
            System.out.println("Calorías: " + diet.getCalories());
            System.out.println("Nota: " + diet.getNote());
            // Y cualquier otro atributo relevante...

        } catch (IOException | ParseException e) {
            System.err.println("Error al obtener la dieta: " + e.getMessage());
        }
    }
}