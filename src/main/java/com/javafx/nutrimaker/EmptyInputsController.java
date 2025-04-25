package com.javafx.nutrimaker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.net.URL;
import java.util.ResourceBundle;

public class EmptyInputsController implements Initializable {
    @FXML
    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //db connection
    }
}
