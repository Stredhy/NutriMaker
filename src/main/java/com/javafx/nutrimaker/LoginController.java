package com.javafx.nutrimaker;

import static com.javafx.nutrimaker.animations.AnimationPersonalized.*;
import com.javafx.nutrimaker.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class LoginController implements Initializable {
    static final String REG_EXP_VER_EMAIL= "[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{5,256}";
    static final String REG_EXP_VER_PASS= "{8,16}";
    public User user;
    
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private Button signupButton;
    @FXML
    private Button loginButton;

    private boolean isEmpty() {
        return passwordTextField.getText().isEmpty() || emailTextField.getText().isEmpty();
    }

    private void dietStorage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DietStorage.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Almacenamiento de Dietas");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFadeAndScaleAnimation(loginButton);
        setFadeAndScaleAnimation(signupButton);
        setFadeAnimation(emailTextField);
        setFadeAnimation(passwordTextField);
        //db connection
    }

    /*public boolean checkPassword(String password) {

    }

    public boolean checkEmail(String email) {
        
    }

    public boolean verifyPassword(String password) {}

    public boolean verifyEmail(String email) {}*/

    public void dialog() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("EmptyInputs.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        
    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        if(isEmpty()){
            dialog();
        }

    }

    @FXML
    public void signUp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUpForm.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Register");
        stage.show();
    }
}
