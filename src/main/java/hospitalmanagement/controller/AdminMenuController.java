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
    public void setExamsManagementButton() throws IOException {
        setScreen(examsManagementButton, "LoginScene.fxml");
    }

    @FXML
    public void setHospitalsManagementButton() throws IOException {
        setScreen(hospitalsManagementButton, "LoginScene.fxml");
    }

    @FXML
    public void setDoctorManagementButton() throws IOException {
        List<String> name = new ArrayList<>();
        List<String> medicalLicense = new ArrayList<>();
        List<Integer> medical_id = new ArrayList<>();
        List<LocalDate> birthdate = new ArrayList<>();
        List<String> gender = new ArrayList<>();
        List<String> phoneNumber = new ArrayList<>();
        List<String> address = new ArrayList<>();
        List<String> email = new ArrayList<>();
        List<Integer> hospital_id = new ArrayList<>();


        ResultSet resultSet = Database.queryTable("SELECT * FROM Doctors JOIN Persons WHERE idDoctors = person_id");


        try {
            while (resultSet.next()) {

                medical_id.add(resultSet.getInt("idDoctors"));
                medicalLicense.add(resultSet.getString("medicalLicense"));
                name.add(resultSet.getString("name"));
                birthdate.add(resultSet.getDate("birthdate").toLocalDate());
                gender.add(resultSet.getString("sex"));
                phoneNumber.add(resultSet.getString("phoneNumber"));
                address.add(resultSet.getString("address"));
                email.add(resultSet.getString("email"));
                hospital_id.add(resultSet.getInt("hospital_id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        String name1 = "Débora";
        String name2 = "Débora XXXX";

        System.out.println(name1.compareTo(name2));


        setScreen(doctorsManagementButton, "DoctorMenuScene.fxml");

    }

    @FXML
    public void setSpecialitiesManagementButton() throws IOException {
        setScreen(specialitiesManagementButton, "LoginScene.fxml");
    }
}
