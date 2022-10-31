package hospitalmanagement.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class AdminMenuController extends AdminController {

    @FXML
    Button buttonPower,doctorsManagementButton,examsManagementButton, hospitalsManagementButton,specialitiesManagementButton;

    @FXML
    Text textAdmin;

    @FXML
    public void setDoctorManagementButton() throws IOException {
        setScreen(doctorsManagementButton, "AdminFindDoctorScene.fxml");

    }
    @FXML
    public void setExamsManagementButton() throws IOException {
        setScreen(examsManagementButton, "AdminFindExamScene.fxml");
    }

    @FXML
    public void setHospitalsManagementButton() throws IOException {
        setScreen(hospitalsManagementButton, "AdminFindHospitalScene.fxml");
    }

    @FXML
    public void setSpecialitiesManagementButton() throws IOException {
        setScreen(specialitiesManagementButton, "AdminFindSpecialityScene.fxml");
    }

    @FXML
    public void setLogout() {setLogout(buttonPower);}

    public void setTextAdmin(String string) {
        textAdmin.setText(string);
    }
}
