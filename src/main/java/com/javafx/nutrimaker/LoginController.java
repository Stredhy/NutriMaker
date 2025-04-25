package com.javafx.nutrimaker;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    static final String REG_EXP_VER_EMAIL= "[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{5,256}";
    static final String REG_EXP_VER_PASS= "{8,16}";
    @FXML




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //db connection
    }
}
