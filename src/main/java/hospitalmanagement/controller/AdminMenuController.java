package hospitalmanagement.controller;


import hospitalmanagement.Database;
import hospitalmanagement.model.people.Doctor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
    public void setDoctorManagementButton() throws IOException {
        setScreen(doctorsManagementButton, "AdminFindDoctorScene.fxml");

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
    public void setSpecialitiesManagementButton() throws IOException {
        setScreen(specialitiesManagementButton, "LoginScene.fxml");
    }
}
