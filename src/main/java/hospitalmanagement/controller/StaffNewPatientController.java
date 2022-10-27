package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Hospital;
import hospitalmanagement.model.people.Insurance;
import hospitalmanagement.model.people.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffNewPatientController extends SceneController {

    private static String ccNumber = "";

    @FXML
    Button buttonSave;
    @FXML
    Button buttonCancel;
    @FXML
    TextField nameInput;
    @FXML
    TextField ccInput;
    @FXML
    Text warningMessage1;
    @FXML
    Text warningMessage2;
    @FXML
    TextArea addressInput;
    @FXML
    TextField emailInput;
    @FXML
    TextField phoneInput;
    @FXML
    DatePicker dateInput;
    @FXML
    ChoiceBox sexDropdown;
    @FXML
    ComboBox hospitalDropdown;
    @FXML
    ToggleGroup insuranceOption;
    @FXML
    Text warningInsurance;
    @FXML
    RadioButton yesInsurance;
    @FXML
    RadioButton noInsurance;
    @FXML
    ComboBox insuranceDropdown;
    @FXML
    Label insuranceNameLabel;

    @FXML
    private Button buttonMainMenu, buttonPower;

    @FXML
    public void setMainMenu() throws IOException {
        setScreen(buttonMainMenu, "StaffMenuScene.fxml");
    }

    @FXML
    public void setLogout() throws IOException {
        setScreen(buttonPower, "LoginScene.fxml");
    }

    public void savePatient(){
        if(ccInput.isDisable()){
            editPatient();
        }else{
            try {
                createPatient();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void cancelButton(){
        try {
            setScreen(buttonCancel,"StaffMenuScene.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createPatient() throws SQLException {
        if (validateCreate()) {
            Database.modifyTable("INSERT INTO Persons (name,birthDate,sex,phoneNumber,address,email) " + "VALUES ('" + nameInput.getText() + "', '" + dateInput.getValue() + "' ,'" + sexDropdown.getValue().toString().charAt(0) + "', '" + phoneInput.getText() + "', '" + addressInput.getText() + "', '" + emailInput.getText() + "') ");

            ResultSet resultSet = Database.queryTable("SELECT last_insert_id() FROM Persons");
            resultSet.next();
            int last;
            last = Integer.valueOf(resultSet.getString(1));

            Database.modifyTable("INSERT INTO Patients (patientCC,hospital_id,person_id) " + "VALUES ('" + ccInput.getText() + "', '" + getHospital().getId() + "' ,'" + last + "') ");
            if (insuranceDropdown.getValue() != null && insuranceDropdown.isVisible()) {
                Database.modifyTable("UPDATE Patients SET insurance_id = " + getInsurance().getId() +" WHERE Patients.patientCC = '" + ccInput.getText() + "'");
            }

            Information.updatePatients();
            try {
                setScreen(buttonSave, "StaffMenuScene.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void editPatient(){
        if(emptyFields()){
            Database.modifyTable("UPDATE Persons SET name = '" + nameInput.getText() + "', sex = '" + sexDropdown.getValue().toString().charAt(0) + "', address = '" + addressInput.getText() + "', email = '" + emailInput.getText() + "', phoneNumber = '" + phoneInput.getText() + "' WHERE person_id = " + getPersonID());
            Database.modifyTable("UPDATE Patients SET hospital_id = '" + getHospital().getId() + "' WHERE Patients.patientCC = '" + ccInput.getText() + "'");
            if(insuranceOption.getSelectedToggle().equals(noInsurance)) {
                Database.modifyTable("UPDATE Patients SET insurance_id = Default WHERE Patients.patientCC = '" + ccInput.getText() + "'");
            }else if(insuranceOption.getSelectedToggle().equals(yesInsurance)){
                Database.modifyTable("UPDATE Patients SET insurance_id = '" + getInsurance().getId() + "' WHERE Patients.patientCC = '" + ccInput.getText() + "'");
            }
            Information.updatePatients();
            try {
                setScreen(buttonSave, "StaffMenuScene.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void hasInsurance(){
        if (yesInsurance.isArmed()){
            insuranceNameLabel.setVisible(true);
            insuranceDropdown.setVisible(true);
        } else if (noInsurance.isArmed()) {
            insuranceNameLabel.setVisible(false);
            insuranceDropdown.setVisible(false);
        }
    }

    private boolean validateCreate() throws SQLException {
        setAllUnstyled();
        boolean validate = true;
        for (Patient patient : Information.getPatients()) {
            if (patient.getPatientCC().equals(ccInput.getText())) {
                warningMessage1.setVisible(true);
                warningMessage2.setVisible(true);
                validate = false;
                break;
            }
        }
        if(!emptyFields()){
           validate = false;
        }
        return validate;
    }

    private Hospital getHospital() {
        for (Hospital hospital : Information.getHospitals()) {
            if (hospital.getName().equals(hospitalDropdown.getValue())) {
                return hospital;
            }
        }
        return null;
    }

    private Insurance getInsurance() {
        for (Insurance insurance : Information.getInsurances()) {
            if (insurance.getName().equals(insuranceDropdown.getValue())) {
                return insurance;
            }
        }
        return null;
    }

    public void initializeComboBox(){
        initializeComboBoxHospital(hospitalDropdown);
        initializeChoiceBoxSex(sexDropdown);
        initializeComboBoxInsurances(insuranceDropdown);
    }

    private boolean emptyFields(){
        boolean complete = true;
        if(nameInput.getText().isEmpty()){
            complete = false;
            nameInput.setStyle("-fx-effect: dropshadow(one-pass-box,red,15,0,0,0)");
        }
        if(ccInput.getText().isEmpty()){
            complete = false;
            ccInput.setStyle("-fx-effect: dropshadow(one-pass-box,red,15,0,0,0)");
        }
        if(addressInput.getText().isEmpty()){
            complete = false;
            addressInput.setStyle("-fx-effect: dropshadow(one-pass-box,red,15,0,0,0)");
        }
        if (emailInput.getText().isEmpty()){
            complete = false;
            emailInput.setStyle("-fx-effect: dropshadow(one-pass-box,red,15,0,0,0)");
        }
        if(phoneInput.getText().isEmpty()){
            complete = false;
            phoneInput.setStyle("-fx-effect: dropshadow(one-pass-box,red,15,0,0,0)");
        }
        if (dateInput.getValue() == null){
            complete = false;
            dateInput.setStyle("-fx-effect: dropshadow(one-pass-box,red,15,0,0,0)");
        }
        if (sexDropdown.getValue() == null){
            complete = false;
            sexDropdown.setStyle("-fx-effect: dropshadow(one-pass-box,red,15,0,0,0)");
        }
        if (hospitalDropdown.getValue() == null){
            complete = false;
            hospitalDropdown.setStyle("-fx-effect: dropshadow(one-pass-box,red,15,0,0,0)");
        }
        if (insuranceOption.getSelectedToggle() == null){
            complete = false;
            warningInsurance.setVisible(true);
        } else if(insuranceDropdown.getValue() == null && insuranceOption.getSelectedToggle().equals(yesInsurance)){
            complete = false;
            insuranceDropdown.setStyle("-fx-effect: dropshadow(one-pass-box,red,15,0,0,0)");
        }
        return complete;
    }

    private void setAllUnstyled(){
        nameInput.setStyle("-fx-effect: none");
        ccInput.setStyle("-fx-effect: none");
        addressInput.setStyle("-fx-effect: none");
        emailInput.setStyle("-fx-effect: none");
        phoneInput.setStyle("-fx-effect: none");
        dateInput.setStyle("-fx-effect: none");
        sexDropdown.setStyle("-fx-effect: none");
        hospitalDropdown.setStyle("-fx-effect: none");
        insuranceDropdown.setStyle("-fx-effect: none");
        warningMessage1.setVisible(false);
        warningMessage2.setVisible(false);
        warningInsurance.setVisible(false);

        //if(insuranceOption.getSelectedToggle() != null){insuranceDropdown.setStyle("-fx-effect: none");}
    }

    public static void setCcNumber(String index) {
        ccNumber = index;
    }

    private int getPersonID(){
        for (Patient patient : Information.getPatients()) {
            if (patient.getPatientCC().equals(ccNumber)) {
                return patient.getPersonID();
            }
        }
        return 0;
    }

    public void setInputs(){
        for (Patient patient : Information.getPatients()) {
            if (ccNumber.equals(patient.getPatientCC())) {
                nameInput.setText(patient.getName());
                sexDropdown.setValue(patient.getSex().toString());
                dateInput.setValue(patient.getBirthDate()); dateInput.setDisable(true);
                ccInput.setText(ccNumber); ccInput.setDisable(true);
                phoneInput.setText(patient.getContact().getPhoneNumber());
                hospitalDropdown.setValue(patient.getFavoriteHospital().getName());
                emailInput.setText(patient.getContact().getEmail());
                addressInput.setText(patient.getContact().getAddress());
                if (patient.getInsurance() != null) {
                    insuranceDropdown.setValue(patient.getInsurance().getName());
                    insuranceDropdown.setVisible(true);
                    insuranceNameLabel.setVisible(true);
                    insuranceOption.selectToggle(yesInsurance);
                }else{
                    insuranceOption.selectToggle(noInsurance);
                }
            }
        }
    }
}