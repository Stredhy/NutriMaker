package com.javafx.nutrimaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DietStorageView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Test!");
        stage.setScene(scene);
        stage.show();
        //Alan Herrera
        //Eric Aguilar
    }

    public static void main(String[] args) {
        launch();
    }
}