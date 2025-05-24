/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.javafx.nutrimaker;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mimoe
 */
public class AskDialogController implements Initializable {

    private boolean selection;
    private Stage stage;
    
    @FXML
    private Label textField;
    @FXML
    private Button acceptBtn;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onAccept(ActionEvent event) {
        setSelection(true);
        stage.close();
    }

    @FXML
    private void onCancel(ActionEvent event) {
        setSelection(false);
        stage.close();
    }

    public void setTextField(Label textField) {
        this.textField = textField;
    }

    public void setAcceptBtn(Button acceptBtn) {
        this.acceptBtn = acceptBtn;
    }

    public void setCancelBtn(Button cancelBtn) {
        this.cancelBtn = cancelBtn;
    }

    public Label getTextField() {
        return textField;
    }

    public Button getAcceptBtn() {
        return acceptBtn;
    }

    public Button getCancelBtn() {
        return cancelBtn;
    }

    public boolean isSelection() {
        return selection;
    }

    public void setSelection(boolean selection) {
        this.selection = selection;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
}
