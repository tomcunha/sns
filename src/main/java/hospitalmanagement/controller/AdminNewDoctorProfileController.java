package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.StartApplication;
import hospitalmanagement.model.medicalLists.Hospital;
import hospitalmanagement.model.medicalLists.Speciality;
import hospitalmanagement.model.people.Doctor;
import hospitalmanagement.utility.SexUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AdminNewDoctorProfileController extends SceneController{

    @FXML
    Button buttonSave;
    @FXML
    TextField nameInput;
    @FXML
    ChoiceBox sexDropdown;
    @FXML
    DatePicker datePicker;
    @FXML
    TextField medicalLicenseInput;
    @FXML
    TextField phoneNumberInput;
    @FXML
    ComboBox specialityDropdown;
    @FXML
    ComboBox hospitalDropdown;
    @FXML
    TextField emailInput;
    @FXML
    TextArea addressInput;
    @FXML
    TextField usernameInput;
    @FXML
    TextField passwordInput;
    @FXML
    Text inputTextErrorMessage;
    @FXML
    Text inputSymbolOfErrorMl;
    @FXML
    Text inputSymbolOfErrorUser;

    @FXML
    private Button buttonMainMenu, buttonPower;


    @FXML
    public void setMainMenu() throws IOException {
        setScreen(buttonMainMenu, "AdminMenuScene.fxml");
    }

    @FXML
    public void setLogout() throws IOException {
        setScreen(buttonPower, "LoginScene.fxml");
    }

    public void createDoctor() throws SQLException, IOException {

        if (validate()) {

            Database.modifyTable("INSERT INTO Persons (name,birthDate,sex,phoneNumber,address,email) " + "VALUES ('" + nameInput.getText() + "', '" + datePicker.getValue() + "' ,'" + sexDropdown.getValue().toString().charAt(0) + "', '" + phoneNumberInput.getText() + "', '" + addressInput.getText() + "', '" + emailInput.getText() + "') ");

            ResultSet resultSet = Database.queryTable("SELECT person_id" + " FROM hospitalManagement.Persons" + " WHERE name ='" + nameInput.getText() + "' AND phoneNumber ='" + phoneNumberInput.getText() + "' AND address ='" + addressInput.getText() + "' AND email='" + emailInput.getText() + "' ");

            int personId = 0;
            while (resultSet.next()) {
                personId = resultSet.getInt("person_id");
            }


            Database.modifyTable("INSERT INTO hospitalManagement.Employees (user,password,type,person_id) " + "VALUES ('" + usernameInput.getText() + "', '" + passwordInput.getText() + "', '2', '" + personId + "' ) ");

            resultSet = Database.queryTable("SELECT employee_id" + " FROM hospitalManagement.Employees" + " WHERE person_id ='" + personId + "'");

            int employeeId = 0;
            while (resultSet.next()) {
                employeeId = resultSet.getInt("employee_id");
            }


            resultSet = Database.queryTable("SELECT hospital_id" + " FROM hospitalManagement.Hospitals" + " WHERE name ='" + hospitalDropdown.getValue() + "' ");

            int hospital_id = 0;
            while (resultSet.next()) {
                hospital_id = resultSet.getInt("hospital_id");
            }


            resultSet = Database.queryTable("SELECT speciality_id" + " FROM hospitalManagement.Specialities" + " WHERE name ='" + specialityDropdown.getValue() + "' ");

            int speciality_id = 0;
            while (resultSet.next()) {
                speciality_id = resultSet.getInt("speciality_id");
            }


            Database.modifyTable("INSERT INTO hospitalManagement.Doctors (medicalLicense,speciality_id,hospital_id,employee_id) " + "VALUES ('" + medicalLicenseInput.getText() + "', " + speciality_id + ", " + hospital_id + " ,  '" + employeeId + "' ) ");

            Information.updateDoctors();
            setScreen(buttonSave, "AdminMenuScene.fxml");

        } else {
            inputSymbolOfErrorMl.setVisible(true);
            inputSymbolOfErrorUser.setVisible(true);
            inputTextErrorMessage.setVisible(true);
        }
    }

    private boolean validate() throws SQLException {
        System.out.println("entrei");
        System.out.println(medicalLicenseInput.getText());
        ResultSet resultSet = Database.queryTable("SELECT user" + " FROM hospitalManagement.Employees" + " WHERE user ='" + usernameInput.getText() + "' ");
        for (Doctor doctor : Information.getDoctors()) {
            if (doctor.getMedicalLicense().equals(medicalLicenseInput.getText()) || resultSet.next()) {
                return false;
            }
        }
        inputSymbolOfErrorMl.setVisible(false);
        inputSymbolOfErrorUser.setVisible(false);
        inputTextErrorMessage.setVisible(false);
        return true;
    }

    public void initializeComboBox(){
        initializeComboBoxHospital(hospitalDropdown);
        initializeComboBoxSpeciality(specialityDropdown);
        initializeChoiceBoxSex(sexDropdown);
    }

}
