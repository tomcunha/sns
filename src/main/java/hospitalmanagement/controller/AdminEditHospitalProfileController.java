package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Hospital;
import hospitalmanagement.model.people.Doctor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminEditHospitalProfileController extends SceneController {

    private static int hospitalID;
    @FXML
    Button buttonMainMenu, buttonPower, buttonEdit, buttonCancel;
    @FXML
    TextField nameInput;
    @FXML
    TextField phoneNumberInput;
    @FXML
    TextField emailInput;
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
    public void setCancel() throws IOException {
        setScreen(buttonCancel, "AdminFindHospitalScene.fxml");
    }

    public void editHospital() {
        if (buttonEdit.getText().equals("Edit")) {
            enableAll();
            buttonEdit.setText("Save");
            buttonEdit.setStyle("-fx-background-color: #79DD99");
            buttonCancel.setVisible(true);
        } else if (buttonEdit.getText().equals("Save")) {
            Database.modifyTable("UPDATE Hospitals SET name = '" + nameInput.getText() + "' , email = '" + emailInput.getText() + "', phoneNumber = '" + phoneNumberInput.getText() + "', address = '" + addressInput.getText() + "' WHERE hospital_id = " + hospitalID);
            Information.updateHospitals();
            disableAll();
            buttonEdit.setText("Edit");
            buttonEdit.setStyle("-fx-background-color: #6D6D6D");
            buttonCancel.setVisible(false);
        }
    }


    public void setInputs(String name) {
        nameInput.setText(name);
        for (Hospital hospital : Information.getHospitals()) {
            if (hospital.getName().equals(name)) {
                hospitalID = hospital.getId();
                addressInput.setText(hospital.getContact().getAddress());
                phoneNumberInput.setText(hospital.getContact().getPhoneNumber());
                emailInput.setText(hospital.getContact().getEmail());
            }
        }
    }

    public void enableAll() {
        nameInput.setDisable(false);
        phoneNumberInput.setDisable(false);
        emailInput.setDisable(false);
        addressInput.setDisable(false);
    }

    public void disableAll() {
        nameInput.setDisable(true);
        phoneNumberInput.setDisable(true);
        emailInput.setDisable(true);
        addressInput.setDisable(true);
    }

}
