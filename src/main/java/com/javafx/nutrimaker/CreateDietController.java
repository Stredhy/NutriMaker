/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.javafx.nutrimaker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static com.javafx.nutrimaker.animations.AnimationPersonalized.*;
import com.javafx.nutrimaker.repository.DietRepository;
import com.javafx.nutrimaker.DialogController;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author mimoe
 */
public class CreateDietController implements Initializable {

    @FXML
    private Button returnButton;
    @FXML
    private TextField calTextField;
    @FXML
    private ChoiceBox<String> quantityFoodDistribution;
    @FXML
    private ListView<String> selectFreeDay;
    @FXML
    public void dialog(String warningMessage) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("DialogInputs.fxml"));
        Parent parent = loader.load();
        DialogController dialog = loader.getController();
        dialog.setText(warningMessage);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    private boolean checkInputs() throws IOException {
        int cantCalories = Integer.parseInt(calTextField.getText());
        if (calTextField.getText().isEmpty() || selectFreeDay.getSelectionModel().getSelectedItems().isEmpty() || quantityFoodDistribution.getValue() == null) {
            dialog("No pueden existir entradas vacías");
            return false;
        }
        if (!(0 < cantCalories && cantCalories <= 6000)) {
            dialog("Rango de calorías permitido 0 - 6000");
            return false;
        }
        return true;
    }
    
    private void setUnselectedItems() {
        ObservableList<String> all = selectFreeDay.getItems();
        ObservableList<String> selected = selectFreeDay.getSelectionModel().getSelectedItems();
        //List<String> unSelected = new ArrayList<String>(all); 
        unSelected.remove(selected);

}
    
    }
    @FXML
    private void save(ActionEvent event) throws IOException {
        if (checkInputs()) {
            //creardieta;
        }
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("DietStorage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Almacenamiento de Dietas");
        stage.show();*/
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] days = {"Lunes", "Martes", "Miercoles", "Jueves", "Virenes", "Sabado", "Domingo"};
        selectFreeDay.getItems().addAll(days);
        // TODO
    }    

    
    
}
