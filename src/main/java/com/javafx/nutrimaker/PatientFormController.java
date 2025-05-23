package com.javafx.nutrimaker;

import java.net.URL;
import static com.javafx.nutrimaker.animations.AnimationPersonalized.*;
import com.javafx.nutrimaker.repository.PatientRepository;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class PatientFormController implements Initializable {
        
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private TextField nameTextField;
    
    @FXML
    private TextField ageTextField;

    @FXML
    private TextField weightTextField;

    @FXML
    private TextField heightTextField;

    @FXML
    private Button saveButton;
    
    @FXML
    void save(ActionEvent event) throws IOException {
        AnchorPane view = new  FXMLLoader().load(getClass().getResource("CreateDiet.fxml"));
        borderPane.setCenter(view);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFadeAnimation(nameTextField);
        setFadeAnimation(ageTextField);
        setFadeAnimation(weightTextField);
        setFadeAnimation(heightTextField);
        setFadeAndScaleAnimation(saveButton);
    }    
    
}
