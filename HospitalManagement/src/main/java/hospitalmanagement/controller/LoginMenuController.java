package hospitalmanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Random;

public class LoginMenuController extends SceneController{

    @FXML
    Button signInButton;

    @FXML
    TextField usernamefield;

    @FXML
    PasswordField passwordfield;

    @FXML
    Text warning;

    @FXML
    public void setSignInButton() throws IOException {
        setScreen(signInButton,"AdminMenuScene.fxml");
        warning.setText("Credentials are wrong!");
    }
}
