package hospitalmanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class DoctorMenuController extends DoctorController {
    @FXML
    Button buttonPower,scheduleButton,patientsManagementButton,diseasesMedSymptButton;
    @FXML
    public void setScheduleButton(){
        try {
            setScreen(scheduleButton, ".fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void setPatientsManagementButton(){
        try {
            setScreen(patientsManagementButton, "StaffFindPatientScene.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void setDiseasesMedSymptButton(){
        try {
            setScreen(diseasesMedSymptButton, ".fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void setLogout() {setLogout(buttonPower);}
}
