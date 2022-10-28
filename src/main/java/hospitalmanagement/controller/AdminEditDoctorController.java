package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Hospital;
import hospitalmanagement.model.people.Doctor;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AdminEditDoctorController extends SceneController {
    private static String medicalLicense;

    @FXML
    GridPane editDoctorScene, popUp;
    @FXML
    ChoiceBox sexDropdown;
    @FXML
    DatePicker datePicker;
    @FXML
    TextField nameInput, medicalLicenseInput, phoneNumberInput, emailInput, usernameInput, passwordInput;
    @FXML
    ComboBox specialityDropdown,  hospitalDropdown;
    @FXML
    TextArea addressInput;
    @FXML
    Button buttonEdit, buttonDelete, buttonMainMenu, buttonPower, buttonYesPopUp;

    @FXML
    HBox firstTextPopUp, secondtTextPopUp;

    @FXML
    PasswordField passwordInputpopUp;

    @FXML
    Text popUpText;


    @FXML
    public void setMainMenu() throws IOException {
        setScreen(buttonMainMenu, "AdminMenuScene.fxml");
    }

    @FXML
    public void setLogout() throws IOException {
        setScreen(buttonPower, "LoginScene.fxml");
    }

    public void editPersonAttributes() {
        if (buttonEdit.getText().equals("Edit")) {
            enableAll();
            buttonEdit.setText("Save");
            buttonEdit.setStyle("-fx-background-color: #79DD99");
            buttonDelete.setText("Cancel");
            buttonDelete.setStyle("-fx-background-color: linear-gradient(#d0d0d0, #aeb5b6)");
        } else if (buttonEdit.getText().equals("Save")) {
            Database.modifyTable("UPDATE Persons SET name = '" + nameInput.getText() + "', birthDate = '" + datePicker.getValue() + "', sex = '" + sexDropdown.getValue().toString().charAt(0) + "', email = '" + emailInput.getText() + "', phoneNumber = '" + phoneNumberInput.getText() + "', address = '" + addressInput.getText() + "' WHERE person_id = " + getPersonID());
            Database.modifyTable("UPDATE Doctors SET hospital_id = '" + getHospital().getId() + "' WHERE medicalLicense = " + medicalLicense);
            Information.updateDoctors();
            disableAll();
            buttonEdit.setText("Edit");
            buttonEdit.setStyle("-fx-background-color: #6D6D6D");
            buttonDelete.setText("Delete");
            buttonDelete.setStyle("-fx-background-color: #CF5F5F");
        }
    }

    public void deletePerson() throws IOException {
        if (buttonDelete.getText().equals("Delete")) {
            delete();
        } else if (buttonDelete.getText().equals("Cancel")) {
            disableAll();
            setInputs();
            buttonEdit.setText("Edit");
            buttonEdit.setStyle("-fx-background-color: #6D6D6D");
            buttonDelete.setText("Delete");
            buttonDelete.setStyle("-fx-background-color: #CF5F5F");
        }
    }


    public void enableAll() {
        nameInput.setDisable(false);
        sexDropdown.setDisable(false);
        phoneNumberInput.setDisable(false);
        hospitalDropdown.setDisable(false);
        emailInput.setDisable(false);
        addressInput.setDisable(false);
    }

    public void disableAll() {
        nameInput.setDisable(true);
        sexDropdown.setDisable(true);
        phoneNumberInput.setDisable(true);
        hospitalDropdown.setDisable(true);
        emailInput.setDisable(true);
        addressInput.setDisable(true);
    }

    public static void setMedicalLicense(String index) {
        medicalLicense = index;
    }

    public void setInputs() {
        for (Doctor doctor : Information.getDoctors()) {
            if (medicalLicense.equals(doctor.getMedicalLicense())) {
                nameInput.setText(doctor.getName());
                sexDropdown.setValue(doctor.getSex().toString());
                datePicker.setValue(doctor.getBirthDate());
                medicalLicenseInput.setText(medicalLicense);
                phoneNumberInput.setText(doctor.getContact().getPhoneNumber());
                specialityDropdown.setValue(doctor.getSpecialty().toString());
                hospitalDropdown.setValue(doctor.getWorkingHospital().getName());
                emailInput.setText(doctor.getContact().getEmail());
                addressInput.setText(doctor.getContact().getAddress());
                usernameInput.setText(doctor.getUsername());
                passwordInput.setText(doctor.getPassword());
            }
        }
    }

    private int getPersonID() {
        for (Doctor doctor : Information.getDoctors()) {
            if (doctor.getMedicalLicense().equals(medicalLicense)) {
                return doctor.getPersonID();
            }
        }
        return 0;
    }

    private Hospital getHospital() {
        for (Hospital hospital : Information.getHospitals()) {
            if (hospital.getName().equals(hospitalDropdown.getValue())) {
                return hospital;
            }
        }
        return null;
    }

    private void delete() {
        popUp.setVisible(true);
        popUpText.setText(nameInput.getText());
        editDoctorScene.setStyle(" -fx-background-color: rgb(192,192,192);opacity:0.8;");
    }

    public void confirmDelete(){
        if (buttonYesPopUp.getText().equals("Yes")){
            firstTextPopUp.setVisible(false);
            secondtTextPopUp.setVisible(true);
            passwordInputpopUp.setStyle("-fx-effect: none");
            buttonYesPopUp.setText("OK");
        } else if (buttonYesPopUp.getText().equals("OK")) {
            if (verifyPassword(passwordInputpopUp.getText())) {
                //Database.modifyTable("DELETE FROM Doctors WHERE medicalLicense = '" + medicalLicense + "'");

                try {
                    setScreen(buttonYesPopUp, "AdminMenuScene.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                passwordInputpopUp.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
                passwordInputpopUp.setText("");
            }


        }

    }

    public void cancel(){
        popUp.setVisible(false);
        firstTextPopUp.setVisible(true);
        secondtTextPopUp.setVisible(false);
        editDoctorScene.setStyle(" -fx-background-color: default");
    }

    public boolean verifyPassword(String insertedPass) {

        int employee_id = LoginMenuController.getEmployee_id();

        ResultSet rs = Database.queryTable("SELECT (password) FROM Employees WHERE employee_id=" + employee_id);

        try {
            if (rs.next()) {
                return insertedPass.equals(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }



    public void initializeComboBox() {
        initializeComboBoxHospital(hospitalDropdown);
        initializeComboBoxSpeciality(specialityDropdown);
        initializeChoiceBoxSex(sexDropdown);
    }
}
