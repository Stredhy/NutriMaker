package com.javafx.nutrimaker;

import com.javafx.nutrimaker.models.Diet;
import com.javafx.nutrimaker.models.DietSummary;
import com.javafx.nutrimaker.repository.DietRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.javafx.nutrimaker.repository.DietRepository;
import com.javafx.nutrimaker.models.Diet;


import java.io.IOException;
import java.text.ParseException;
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
    }
}