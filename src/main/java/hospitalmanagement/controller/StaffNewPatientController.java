package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Hospital;
import hospitalmanagement.model.people.Insurance;
import hospitalmanagement.model.people.Patient;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffNewPatientController extends SceneController {

    @FXML
    Button buttonSave;
    @FXML
    TextField nameInput;
    @FXML
    TextField ccInput;
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
    RadioButton yesInsurance;
    @FXML
    RadioButton noInsurance;
    @FXML
    ComboBox insuranceDropdown;
    @FXML
    Label insuranceNameLabel;

    public void createPatient() throws SQLException {
        if (validate()) {
            Database.modifyTable("INSERT INTO Persons (name,birthDate,sex,phoneNumber,address,email) " + "VALUES ('" + nameInput.getText() + "', '" + dateInput.getValue() + "' ,'" + sexDropdown.getValue().toString().charAt(0) + "', '" + phoneInput.getText() + "', '" + addressInput.getText() + "', '" + emailInput.getText() + "') ");

            ResultSet resultSet = Database.queryTable("SELECT last_insert_id() FROM Persons");
            resultSet.next();
            int last;
            last = Integer.valueOf(resultSet.getString(1));

            Database.modifyTable("INSERT INTO Patients (patientCC,hospital_id,person_id) " + "VALUES ('" + ccInput.getText() + "', '" + getHospital().getId() + "' ,'" + last + "') ");
            if (insuranceDropdown.getValue() != null) {
                System.out.println("Chegueiiii");
                Database.modifyTable("UPDATE Patients SET insurance_id = " + getInsurance().getId() +" WHERE Patients.patientCC = '" + ccInput.getText() + "'");
            }

            Information.updatePatients();
            try {
                setScreen(buttonSave, "StaffMenuScene.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Ja existe com este CC");
//            inputSymbolOfErrorCC.setVisible(true);
//            inputTextErrorMessage.setVisible(true);
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

    private boolean validate() throws SQLException {
        for (Patient patient : Information.getPatients()) {
            if (patient.getPatientCC().equals(ccInput.getText())) {
                return false;
            }
        }
        return true;
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
}