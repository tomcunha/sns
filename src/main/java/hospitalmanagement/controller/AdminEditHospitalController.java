package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Hospital;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminEditHospitalController extends AdminController {

    @FXML
    Button buttonMainMenu, buttonPower, buttonEdit, buttonDelete,buttonCancel, buttonYesPopUp;
    @FXML
    TextField nameInput, phoneNumberInput, emailInput;
    @FXML
    TextArea addressInput;
    private String hospitalName;
    private int hospital_id;

    @FXML
    GridPane editHospitalScene, popUp;
    @FXML
    HBox firstTextPopUp, secondTextPopUp;

    @FXML
    PasswordField passwordInputPopUp;

    @FXML
    Text popUpText;

    public void setCancel() {
        try{
            setScreen(buttonCancel, "AdminFindHospitalScene.fxml");
        }catch (IOException e){
            throw  new RuntimeException(e);
        }
    }

    @FXML
    public void editHospital() {
        resetErrors();
        if (buttonEdit.getText().equals("Edit")) {
            enableAll();
            buttonEdit.setText("Save");
            buttonEdit.setStyle("-fx-background-color: #79DD99");
            buttonDelete.setText("Cancel");
            buttonDelete.setStyle("-fx-background-color: linear-gradient(#d0d0d0, #aeb5b6)");
        } else if (updateTable()) {
            disableAll();
            buttonEdit.setText("Edit");
            buttonEdit.setStyle("-fx-background-color: #6D6D6D");
            buttonDelete.setText("Delete");
            buttonDelete.setStyle("-fx-background-color: #CF5F5F");
        }
    }

    @FXML
    public void deleteHospital() {
        resetErrors();
        if (buttonDelete.getText().equals("Delete")) {
            delete();
        } else if (buttonDelete.getText().equals("Cancel")) {
            disableAll();
            setInputs(hospitalName);
            buttonEdit.setText("Edit");
            buttonEdit.setStyle("-fx-background-color: #6D6D6D");
            buttonDelete.setText("Delete");
            buttonDelete.setStyle("-fx-background-color: #CF5F5F");
        }
    }

    public void setInputs(String name) {
        nameInput.setText(name);
        hospitalName = name;
        for (Hospital hospital : Information.getHospitals()) {
            if (hospital.getName().equals(name)) {
                hospital_id = hospital.getId();
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

    public void resetErrors() {
        nameInput.setStyle("-fx-effect: none");
        addressInput.setStyle("-fx-effect: none");
        phoneNumberInput.setStyle("-fx-effect: none");
        emailInput.setStyle("-fx-effect: none");
    }

    public boolean updateTable() {

        String name = nameInput.getText().trim();
        String address = addressInput.getText().trim();
        String phoneNumber = phoneNumberInput.getText().replace(" ", "");
        String email = emailInput.getText().trim();

        if (validate(name, address, phoneNumber, email)) {
            String instruction = "UPDATE Hospitals " +
                    "SET name = '" + name +
                    "', address = '" + address +
                    "', phoneNumber ='" + phoneNumber +
                    "', email='" + email +
                    "' WHERE hospital_id =" + hospital_id;

            Database.modifyTable(instruction);
            Information.updateHospitals();
            return true;
        }
        return false;
    }

    public boolean validate(String name, String address, String phoneNumber, String email) {
        boolean validation = true;
        for (Hospital hospital : Information.getHospitals()) {

            if (hospital.getId() != hospital_id) {
                validation = AdminNewHospitalController.validateHospital(name, address, phoneNumber, email, validation, hospital, nameInput, addressInput, phoneNumberInput, emailInput);
            }
        }
        return validation;
    }

    private void delete() {
        popUp.setVisible(true);
        popUpText.setText(nameInput.getText());
        editHospitalScene.setStyle(" -fx-background-color: rgb(192,192,192);opacity:0.8;");
    }

    public void confirmDelete(){
        if (buttonYesPopUp.getText().equals("Yes")){
            firstTextPopUp.setVisible(false);
            secondTextPopUp.setVisible(true);
            passwordInputPopUp.setStyle("-fx-effect: none");
            buttonYesPopUp.setText("OK");
        } else if (buttonYesPopUp.getText().equals("OK")) {
            if (verifyPassword(passwordInputPopUp.getText())) {
                //Database.modifyTable("DELETE FROM Doctors WHERE medicalLicense = '" + medicalLicense + "'");

                try {
                    setScreen(buttonYesPopUp, "AdminMenuScene.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                passwordInputPopUp.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
                passwordInputPopUp.setText("");
            }


        }

    }

    public void cancel(){
        popUp.setVisible(false);
        firstTextPopUp.setVisible(true);
        secondTextPopUp.setVisible(false);
        editHospitalScene.setStyle(" -fx-background-color: default");
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

    @FXML
    public void setMainMenu() {setMainMenu(buttonMainMenu);}

    @FXML
    public void setLogout() {setLogout(buttonPower);}


}
