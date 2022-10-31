package hospitalmanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class StaffMenuController extends StaffController{

    @FXML
    Button buttonPower, patientsManagementButton, newAppointmentButton;
    @FXML
    Text textStaff;

    @FXML
    public void setPatientsManagement() {
        try {
            setScreen(patientsManagementButton, "StaffFindPatientScene.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void newAppointment() {
        try {
            setScreen(newAppointmentButton, "StaffFindPatientScene.fxml");
            StaffFindPatientController findPatient = getFXML().getController();
            findPatient.selectAppointment();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void setLogout() {setLogout(buttonPower);}

    public void setTextStaff(String string) {
        textStaff.setText(string);
    }
}
