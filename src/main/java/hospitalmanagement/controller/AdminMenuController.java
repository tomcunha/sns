package hospitalmanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminMenuController extends SceneController {

    @FXML
    Button buttonPower;

    @FXML
    Button doctorsManagementButton;
    @FXML
    Button examsManagementButton;
    @FXML
    Button hospitalsManagementButton;
    @FXML
    Button specialitiesManagementButton;

    @FXML
    public void setButtonPower() throws IOException {
        setScreen(buttonPower, "LoginScene.fxml");

    }

    @FXML
    public void setExamsManagementButton() throws IOException {
        setScreen(examsManagementButton, "LoginScene.fxml");
    }
    @FXML
    public void setHospitalsManagementButton() throws IOException {
        setScreen(hospitalsManagementButton, "LoginScene.fxml");
    }
    @FXML
    public void setDoctorManagementButton() throws IOException {
        setScreen(doctorsManagementButton, "DoctorMenuScene.fxml");
    }
    @FXML
    public void setSpecialitiesManagementButton() throws IOException {
        setScreen(specialitiesManagementButton, "LoginScene.fxml");
    }
}