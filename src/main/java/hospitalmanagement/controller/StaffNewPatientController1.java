package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Hospital;
import hospitalmanagement.model.people.Insurance;
import hospitalmanagement.model.people.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffNewPatientController1 extends SceneController {

    @FXML
    TextField nameInput;
    @FXML
    TextField ccInput;
    @FXML
    TextField addressInput;
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
    RadioButton insuranceButton;
    @FXML
    ComboBox insuranceDropdown;
    @FXML
    Text inputSymbolOfErrorCC;
    @FXML
    Text inputTextErrorMessage;

    public void createPatient() throws SQLException {
        if (validate()) {
            Database.modifyTable("INSERT INTO Persons (name,birthDate,sex,phoneNumber,address,email) " + "VALUES ('" + nameInput.getText() + "', '" + dateInput.getValue() + "' ,'" + sexDropdown.getValue().toString().charAt(0) + "', '" + phoneInput.getText() + "', '" + addressInput.getText() + "', '" + emailInput.getText() + "') ");

            ResultSet resultSet = Database.queryTable("SELECT last_insert_id() FROM Persons");
            resultSet.next();
            int last;
            last = Integer.valueOf(resultSet.getString(1));

            Database.modifyTable("INSERT INTO Patients (patientCC,hospital_id,person_id) " + "VALUES ('" + ccInput.getText() + "', '" + getHospital().getId() + "' ,'" + last + "') ");
            if (true/* check */){Database.modifyTable("INSERT INTO Patients (insurance_id) " + "VALUES ('" + getInsurance().getId() + "') ");}

            Information.updatePatients();
        }else {
            inputSymbolOfErrorCC.setVisible(true);
            inputTextErrorMessage.setVisible(true);
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
    }
}


