package hospitalmanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class StaffMenuController extends SceneController{

    @FXML
    Button buttonPower;
    @FXML
    Button patientsManagementButton;

    @FXML
    public void setButtonPower() throws IOException {
        setScreen(buttonPower, "LoginScene.fxml");
    }

    @FXML
    public void setPatientsManagement() throws IOException {
        setScreen(patientsManagementButton, "StaffFindPatientScene.fxml");
    }
}
