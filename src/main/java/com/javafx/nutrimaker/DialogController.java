package com.javafx.nutrimaker;

import static com.javafx.nutrimaker.animations.AnimationPersonalized.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DialogController implements Initializable {

    @FXML
    private Label warningLabel;
    
    @FXML
    private Button closeButton;
    @FXML
    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public  void setText(String labelString){
        warningLabel.setText(labelString);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFadeAndScaleAnimation(closeButton);
        //db connection
    }
}
