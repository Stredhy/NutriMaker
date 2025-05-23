package com.javafx.nutrimaker;

import static com.javafx.nutrimaker.animations.AnimationPersonalized.*;
import java.net.URL;
import com.javafx.nutrimaker.repository.PatientRepository;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PatientFormController implements Initializable {
     
    static final String REG_EXP_POSIT_FLOAT = "\\d+(\\.\\d*)?";
    static final String REG_EXP_POSIT_INT = "\\d+";
    
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
    private void save(ActionEvent event) throws IOException {
        checkInputs();
    }
    
    //1 vacias, 2 formatos, 
    public void dialog(String archiveRoute) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(archiveRoute));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    
    private boolean isPositive(TextField textField) {
        return textField.getText().matches(REG_EXP_POSIT_INT);
    }
    
    public boolean checkInputs() throws IOException {
        if (nameTextField.getText().isEmpty() || ageTextField.getText().isEmpty() || weightTextField.getText().isEmpty() || heightTextField.getText().isEmpty()) {
            dialog("EmptyInputs.fxml");
            return false;
        }
        if (!isPositive(ageTextField) || !isPositive(weightTextField) || !isPositive(heightTextField)) {
            dialog("WrongNumberInputs.fxml");
            return false;
        }
        return true;
    }
    
    private void createDiet(ActionEvent event) throws IOException {
        
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
