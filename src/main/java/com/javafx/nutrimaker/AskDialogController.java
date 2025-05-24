
package com.javafx.nutrimaker;

import static com.javafx.nutrimaker.animations.AnimationPersonalized.setFadeAndScaleAnimation;
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
    private Button acceptBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label customLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFadeAndScaleAnimation(cancelBtn);
        setFadeAndScaleAnimation(acceptBtn);
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

    public void setLabelText(String text) {
        this.customLabel.setText(text);
    }

    public void setAcceptBtn(Button acceptBtn) {
        this.acceptBtn = acceptBtn;
    }

    public void setCancelBtn(Button cancelBtn) {
        this.cancelBtn = cancelBtn;
    }

    public Label getTextField() {
        return customLabel;
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
