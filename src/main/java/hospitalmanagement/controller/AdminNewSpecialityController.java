package hospitalmanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminNewSpecialityController extends SceneController {

    @FXML
    Button buttonSave;
    @FXML
    private Button buttonMainMenu;
    @FXML
    private Button buttonPower;


    @FXML
    private void createSpeciality() throws IOException {
    setScreen(buttonSave, "AdminMenuScene.fxml");
}
    @FXML
    public void setMainMenu() throws IOException {
        setScreen(buttonMainMenu, "AdminMenuScene.fxml");
    }

    @FXML
    public void setLogout() throws IOException {
        setScreen(buttonPower, "LoginScene.fxml");
    }
}
