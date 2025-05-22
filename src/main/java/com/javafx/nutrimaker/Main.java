package com.javafx.nutrimaker;

import com.javafx.nutrimaker.models.DietSummary;
import com.javafx.nutrimaker.repository.DietRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;

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

        DietRepository dietRepository = new DietRepository();

        try {
            // Obtener la primera página con 10 dietas
            List<DietSummary> diets = dietRepository.getDiets(0, 10);

            // Imprimir información básica de cada dieta
            for (DietSummary diet : diets) {
                System.out.println("ID: " + diet.getDietId());
                System.out.println("Paciente: " + diet.getPatientName());
                System.out.println("Peso: " + diet.getWeight());
                System.out.println("Altura: " + diet.getHeight());
                System.out.println("Fecha creación: " + diet.getCreationDate());
                System.out.println("-----------------------------");
            }

        } catch (IOException e) {
            System.err.println("Error al obtener dietas: " + e.getMessage());
        }
    }
}