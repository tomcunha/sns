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
        AdminFindDoctorController adminFindDoctorController = getFXML().getController();
        adminFindDoctorController.textAdmin.setText(LoginMenuController.getEmployee_name());
    }
    @FXML
    public void setExamsManagementButton() throws IOException {
        setScreen(examsManagementButton, "AdminFindExamScene.fxml");
        AdminFindExamController adminFindExamController = getFXML().getController();
        adminFindExamController.textAdmin.setText(LoginMenuController.getEmployee_name());
    }

    @FXML
    public void setHospitalsManagementButton() throws IOException {
        setScreen(hospitalsManagementButton, "AdminFindHospitalScene.fxml");
        AdminFindHospitalController adminFindHospitalController = getFXML().getController();
        adminFindHospitalController.textAdmin.setText(LoginMenuController.getEmployee_name());
    }

    @FXML
    public void setSpecialitiesManagementButton() throws IOException {
        setScreen(specialitiesManagementButton, "AdminFindSpecialityScene.fxml");
        AdminFindSpecialityController adminFindSpecialityController = getFXML().getController();
        adminFindSpecialityController.textAdmin.setText(LoginMenuController.getEmployee_name());
    }

    @FXML
    public void setLogout() {setLogout(buttonPower);}

    public void setTextAdmin(String string) {
        textAdmin.setText(string);
    }
}
