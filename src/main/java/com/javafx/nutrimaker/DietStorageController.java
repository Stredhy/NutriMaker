/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.javafx.nutrimaker;

import com.javafx.nutrimaker.models.Diet;
import com.javafx.nutrimaker.models.Patient;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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
    private TableColumn<Diet, Void> actionsCol;
    @FXML
    private Button createButton;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showDietList();
    }    

    @FXML
    private void createDiet(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateDiet.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Creat Dieta");
        stage.show();
    }
    
    @FXML
    private void next(MouseEvent event){
    
    }
    
    @FXML
    private void prev(MouseEvent event){
        
    }

    private void showDietList() {
        int count=5;
        numCol.setCellValueFactory(new PropertyValueFactory<>(Integer.toString(count)));
        patientCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        heightCol.setCellValueFactory(new PropertyValueFactory<>("height"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        actionsCol.setCellFactory(col ->  new TableCell<Diet,Void>(){
            private final HBox actions = new HBox(10);;
            private final ImageView pdf = new ImageView(DietStorageController.class.getResource("images/pdf.png").toExternalForm());
            private final ImageView clone = new ImageView(DietStorageController.class.getResource("images/clone.png").toExternalForm()); 
            private final ImageView edit = new ImageView(DietStorageController.class.getResource("images/edit.png").toExternalForm());
            private final ImageView delete = new ImageView(DietStorageController.class.getResource("images/delete.png").toExternalForm());
            
            {
                for(ImageView icon: new ImageView[]{pdf,clone,edit,delete}){
                    icon.setFitHeight(34);
                    icon.setFitWidth(34);
                    icon.setCursor(Cursor.HAND);
                }

                pdf.setOnMouseClicked(event -> exportToPDF());

                clone.setOnMouseClicked(event -> copyDiet());

                edit.setOnMouseClicked(event -> modifyDiet());

                delete.setOnMouseClicked(event -> deleteDiet());
            }
            
            @Override   
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    //Debug
                    System.out.println("Error de carga de graficos");
                } else {
                    actions.getChildren().addAll(pdf,clone,edit,delete);
                    actions.setAlignment(Pos.CENTER);
                    actions.setPadding(new Insets(5));
                    
                    setGraphic(actions);
                }
            }
        });
    }
    
    private void exportToPDF(){
        
    }
    
    private void copyDiet(){
    
    }
    
    private void modifyDiet(){
    
    }
    
    private void deleteDiet(){
    
    }
}