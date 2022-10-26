package hospitalmanagement.controller;

import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Hospital;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminEditHospitalProfileController extends SceneController {

    @FXML
    Button buttonMainMenu, buttonPower;
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

    public void editPerson() {
        enableAll();
    }

    public void deletePerson() {
        disableAll();
    }

    public void setInputs(String name) {
        nameInput.setText(name);
        for (Hospital hospital : Information.getHospitals()) {
            if (hospital.getName().equals(name)) {
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
