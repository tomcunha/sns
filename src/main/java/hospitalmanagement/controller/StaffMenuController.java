package hospitalmanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class StaffMenuController extends SceneController{

    @FXML
    Button buttonPower, patientsManagementButton, newAppointmentButton;

    @FXML
    public void setButtonPower(){
        try {
            setScreen(buttonPower, "LoginScene.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void setPatientsManagement(){
        try {
            setScreen(patientsManagementButton, "StaffFindPatientScene.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void setNewAppointmentButton(){
        try {
            setScreen(newAppointmentButton,"StaffNewAppSelectScene.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
