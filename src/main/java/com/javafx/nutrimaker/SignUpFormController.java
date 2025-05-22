package com.javafx.nutrimaker;

import static com.javafx.nutrimaker.animations.AnimationPersonalized.setFadeAndScaleAnimation;
import static com.javafx.nutrimaker.animations.AnimationPersonalized.setFadeAnimation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author uh709
 */
public class SignUpFormController implements Initializable {
    static final String REG_EXP_VER_EMAIL= "[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{5,256}";
    static final String REG_EXP_VER_PASS= "{8,16}";
    
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField confPasswordTextField;
    @FXML
    private Button signupButton;
    @FXML
    private Button loginButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFadeAndScaleAnimation(loginButton);
        setFadeAndScaleAnimation(signupButton);
        setFadeAnimation(emailTextField);
        setFadeAnimation(passwordTextField);
        setFadeAnimation(confPasswordTextField);
    }    

     private boolean isEmpty(){
        return emailTextField.getText().isEmpty() && 
                passwordTextField.getText().isEmpty() && 
                confPasswordTextField.getText().isEmpty();
    }
      
    @FXML
    private void signUp(ActionEvent event) throws IOException {
        if(isEmpty()){
            dialog();
        }
    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.show();
    }
    
    public void dialog() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("EmptyInputs.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        
    }
}
