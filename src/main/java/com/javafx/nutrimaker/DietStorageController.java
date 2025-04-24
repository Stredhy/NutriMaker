/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.javafx.nutrimaker;

import com.javafx.nutrimaker.models.Diet;
import com.javafx.nutrimaker.models.Patient;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author mimoe
 */
public class DietStorageController implements Initializable {

    @FXML
    private TableView<Diet> dietsTable;
    @FXML
    private TableColumn<Diet, String> numCol;
    @FXML
    private TableColumn<Patient, String> patientCol;
    @FXML
    private TableColumn<Patient, String> weightCol;
    @FXML
    private TableColumn<Patient, String> heightCol;
    @FXML
    private TableColumn<Diet, String> dateCol;
    @FXML
    private TableColumn<Patient, String> actionsCol;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showDietList();
    }    

    @FXML
    private void createDiet(ActionEvent event) {
    }

    private void showDietList() {
        int count=5;
        numCol.setCellValueFactory(new PropertyValueFactory<>(Integer.toString(count)));
        patientCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        heightCol.setCellValueFactory(new PropertyValueFactory<>("height"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
    }
    
    
    private void exportToPDF(ActionEvent event){
        
    }
    
    private void copyDiet(ActionEvent event){
    
    }
    
    private void modifyDiet(ActionEvent event){
    
    }
    
    private void deleteDiet(ActionEvent event){
    
    }
    
    @FXML
    private void next(ActionEvent event){
    
    }
    
    @FXML
    private void prev(ActionEvent event){
        
    }
    
}

