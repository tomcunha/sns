package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Hospital;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminNewHospitalController extends SceneController {

    @FXML
    Button buttonSave, buttonMainMenu, buttonPower, buttonCancel;

    @FXML
    TextField nameInput, phoneNumberInput, emailInput;

    @FXML
    TextArea addressInput;

    @FXML
    public void setMainMenu() {
        try {
            setScreen(buttonMainMenu, "AdminMenuScene.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void setLogout() {
        try {
            setScreen(buttonPower, "LoginScene.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void cancelCreation() {
        try {
            setScreen(buttonCancel, "AdminFindHospitalScene.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createHospital() {

        resetErrors();

        String name = nameInput.getText().trim();
        String address = addressInput.getText().trim();
        String phoneNumber = phoneNumberInput.getText().replace(" ", "");
        String email = emailInput.getText().trim();

        if (validate(name, address, phoneNumber, email)) {
            String instruction = "INSERT INTO Hospitals \n" +
                    "VALUES (DEFAULT, '" + name +
                    "','" + address +
                    "', '" + phoneNumber +
                    "','" + email + "')";

            Database.modifyTable(instruction);
            Information.updateHospitals();
            setMainMenu();
        }
    }

    public boolean validate(String name, String address, String phoneNumber, String email) {
        boolean validation = true;
        for (Hospital hospital : Information.getHospitals()) {
            validation = validateHospital(name, address, phoneNumber, email, validation, hospital, nameInput, addressInput, phoneNumberInput, emailInput);
        }
        return validation;
    }

    public void resetErrors() {
        nameInput.setStyle("-fx-effect: none");
        addressInput.setStyle("-fx-effect: none");
        phoneNumberInput.setStyle("-fx-effect: none");
        emailInput.setStyle("-fx-effect: none");
    }

    static boolean validateHospital(String name, String address, String phoneNumber, String email, boolean validation, Hospital hospital, TextField nameInput, TextArea addressInput, TextField phoneNumberInput, TextField emailInput) {
        if (hospital.getName().equals(name) || name.length() == 0) {
            nameInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
            validation = false;
        }
        if (hospital.getContact().getAddress().equals(address) || address.length() == 0) {
            addressInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
            validation = false;
        }
        if (hospital.getContact().getPhoneNumber().equals(phoneNumber) || phoneNumber.length() != 9) {
            phoneNumberInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
            validation = false;
        }
        if (hospital.getContact().getEmail().equals(email) || !email.contains("@")) {
            emailInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
            validation = false;
        }
        return validation;
    }

}
