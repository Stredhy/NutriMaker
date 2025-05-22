/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.javafx.nutrimaker;

import static com.javafx.nutrimaker.animations.AnimationPersonalized.*;
import com.javafx.nutrimaker.models.DietSummary;
import com.javafx.nutrimaker.models.User;
import com.javafx.nutrimaker.repository.DietRepository;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mimoe
 */
public class DietStorageController implements Initializable {

    private final int LIMIT = 10;
    private int count = 0;    
    private int userId = User.getUser().getId();
    
    @FXML
    private TableView<DietSummary> dietsTable;
    @FXML
    private TableColumn<DietSummary, String> numCol;
    @FXML
    private TableColumn<DietSummary, String> patientCol;
    @FXML
    private TableColumn<DietSummary, String> weightCol;
    @FXML
    private TableColumn<DietSummary, String> heightCol;
    @FXML
    private TableColumn<DietSummary, String> dateCol;
    @FXML
    private TableColumn<DietSummary, String> actionsCol;
    @FXML
    private Button createButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button prevButton;
    
    private ObservableList<DietSummary> dietsList = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFadeAndScaleAnimation(createButton);
        setFadeAndScaleAnimation(nextButton);
        setFadeAndScaleAnimation(prevButton);
        dietsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_LAST_COLUMN);
        dietsTable.setSelectionModel(null);
        try {
            showDietList();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
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
    private void next(ActionEvent event) throws IOException{
        count+=10;
        showDietList();
    }
    
    @FXML
    private void prev(ActionEvent event) throws IOException{
        count-=10;
        if (count < 0){
            count+=10;
        }
        showDietList();
    }

    private void showDietList() throws IOException {
        refreshButtons();
        refreshTable();
        numCol.setCellValueFactory(new PropertyValueFactory<>("dietId"));
        patientCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        heightCol.setCellValueFactory(new PropertyValueFactory<>("height"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        actionsCol.setCellFactory(col ->  new TableCell<DietSummary,String>(){
            private final HBox actions = new HBox(5);
            private final ImageView pdfIcon = new ImageView(
                    DietStorageController.class.getResource("images/pdf.png").toExternalForm());
            private final ImageView cloneIcon = new ImageView(
                    DietStorageController.class.getResource("images/clone.png").toExternalForm()); 
            private final ImageView editIcon = new ImageView(
                    DietStorageController.class.getResource("images/edit.png").toExternalForm());
            private final ImageView deleteIcon = new ImageView(
                    DietStorageController.class.getResource("images/delete.png").toExternalForm());
            private final DropShadow shadow = new DropShadow(5, 2, 2, Color.rgb(0, 0, 0, 0.4));
            private final Button pdf = new Button("",pdfIcon);
            private final Button clone = new Button("",cloneIcon);
            private final Button edit = new Button("",editIcon);
            private final Button delete = new Button("",deleteIcon);
            {
                for(ImageView icon: new ImageView[]{pdfIcon,cloneIcon,editIcon,deleteIcon}){
                    icon.setEffect(shadow);
                    icon.setFitHeight(30);
                    icon.setFitWidth(30);
                }
                for(Button btn: new Button[]{pdf,clone,edit,delete}){
                    btn.setPrefSize(30, 30);
                    btn.setStyle("-fx-background-color: none; -fx-border-color: none;");
                    btn.setCursor(Cursor.HAND);
                    setFadeAndScaleAnimation(btn);
                }
                pdf.setOnMouseClicked(event -> exportToPDF());
                clone.setOnMouseClicked(event -> copyDiet());
                edit.setOnMouseClicked(event -> modifyDiet());
                delete.setOnMouseClicked(event -> deleteDiet());
            }
            
            @Override   
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
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
        System.out.println("Export to Pdf");
    }
    
    private void copyDiet(){
        System.out.println("Copy Diet");
    }
    
    private void modifyDiet(){
        System.out.println("Modify Diet");
    }
    
    private void deleteDiet(){
        System.out.println("Delete Diet");
    }
    
    private void refreshButtons() throws IOException{
        DietRepository dietRepo = new DietRepository();        
        prevButton.setVisible( count > 0);        
        nextButton.setVisible((count + LIMIT) < dietRepo.getTotalDietsCount());
    }
    
    private void refreshTable(){
        dietsList.clear();
        DietRepository dietRepo = new DietRepository();
        try{
            List<DietSummary> diets = dietRepo.getDiets(count, LIMIT);
            for(DietSummary diet : diets){
                dietsList.add(new DietSummary(diet.getDietId(),diet.getPatientName(),
                diet.getWeight(),diet.getHeight(),diet.getCreationDate()));
                dietsTable.setItems(dietsList);
            }            
        }catch(IOException e){
            System.out.println(e.getMessage());
        }        
    }
}