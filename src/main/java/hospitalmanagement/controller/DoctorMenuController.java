package hospitalmanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class DoctorMenuController extends SceneController {
    @FXML
    Button buttonPower,scheduleButton,patientsManagementButton,diseasesMedSymptButton;
    @FXML
    public void setButtonPower() throws IOException {
        setScreen(buttonPower, "LoginScene.fxml");
    }
    @FXML
    public void setScheduleButton() throws IOException {
        setScreen(scheduleButton, ".fxml");
    }
    @FXML
    public void setPatientsManagementButton() throws IOException {
            setScreen(patientsManagementButton, "StaffFindPatientScene.fxml");
    }
    @FXML
    public void setDiseasesMedSymptButton() throws IOException {
        setScreen(diseasesMedSymptButton, ".fxml");
    }






}
