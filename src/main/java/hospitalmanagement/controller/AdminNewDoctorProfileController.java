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


public class AdminNewDoctorProfileController extends SceneController {

    @FXML
    Button buttonSave, buttonMainMenu, buttonPower;
    @FXML
    TextField nameInput, medicalLicenseInput, phoneNumberInput, emailInput, usernameInput, passwordInput;
    @FXML
    ChoiceBox sexDropdown;
    @FXML
    DatePicker datePicker;
    @FXML
    ComboBox specialityDropdown, hospitalDropdown;
    @FXML
    TextArea addressInput;

    @FXML
    Text inputTextErrorMessage, inputSymbolOfErrorMl, inputSymbolOfErrorUser, emptyTextErrorMessage;
    boolean isDuplicate, isEmpty;


    @FXML
    public void setMainMenu() throws IOException {
        System.out.println("button clickec");
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
        }
    }

    private boolean validate() throws SQLException {
        resetErrors();
        isDuplicate = false;
        isEmpty = false;
        inputSymbolOfErrorUser.setVisible(false);
        inputSymbolOfErrorMl.setVisible(false);
        inputTextErrorMessage.setVisible(false);
        emptyTextErrorMessage.setVisible(false);

        ResultSet resultSet = Database.queryTable("SELECT user" + " FROM hospitalManagement.Employees" + " WHERE user ='" + usernameInput.getText() + "' ");
        if (resultSet.next()) {
            inputSymbolOfErrorUser.setVisible(true);
            inputTextErrorMessage.setVisible(true);
            isDuplicate = true;
        }

        for (Doctor doctor : Information.getDoctors()) {
            if (doctor.getMedicalLicense().equals(medicalLicenseInput.getText())) {
                isDuplicate = true;
                inputTextErrorMessage.setVisible(true);
                inputSymbolOfErrorMl.setVisible(true);
            }
        }

        if (nameInput.getText().isEmpty() || medicalLicenseInput.getText().isEmpty() || emailInput.getText().isEmpty() || datePicker.getValue() == null || usernameInput.getText().isEmpty() ||
                passwordInput.getText().isEmpty() || addressInput.getText().isEmpty() || phoneNumberInput.getText().isEmpty() || sexDropdown.getValue() == null || specialityDropdown.getValue() == null || hospitalDropdown.getValue() == null) {
            isEmpty = true;
            emptyTextErrorMessage.setVisible(true);
            if (nameInput.getText().isEmpty())
                nameInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
            if (medicalLicenseInput.getText().isEmpty())
                medicalLicenseInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
            if (emailInput.getText().isEmpty())
                emailInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
            if (datePicker.getValue() == null)
                datePicker.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
            if (usernameInput.getText().isEmpty())
                usernameInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
            if (passwordInput.getText().isEmpty())
                passwordInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
            if (addressInput.getText().isEmpty())
                addressInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
            if (phoneNumberInput.getText().isEmpty())
                phoneNumberInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
            if (specialityDropdown.getValue() == null)
                specialityDropdown.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
            if (hospitalDropdown.getValue() == null)
                hospitalDropdown.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
            if (sexDropdown.getValue() == null)
                sexDropdown.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");

        }
        if (isDuplicate || isEmpty) {
            return false;
        }

        return true;
    }

    public void resetErrors() {
        nameInput.setStyle("-fx-effect: none");
        medicalLicenseInput.setStyle("-fx-effect: none");
        emailInput.setStyle("-fx-effect: none");
        datePicker.setStyle("-fx-effect: none");
        usernameInput.setStyle("-fx-effect: none");
        passwordInput.setStyle("-fx-effect: none");
        addressInput.setStyle("-fx-effect: none");
        phoneNumberInput.setStyle("-fx-effect: none");
        specialityDropdown.setStyle("-fx-effect: none");
        hospitalDropdown.setStyle("-fx-effect: none");
        sexDropdown.setStyle("-fx-effect: none");

    }

    public void initializeComboBox() {
        initializeComboBoxHospital(hospitalDropdown);
        initializeComboBoxSpeciality(specialityDropdown);
        initializeChoiceBoxSex(sexDropdown);
    }

}
