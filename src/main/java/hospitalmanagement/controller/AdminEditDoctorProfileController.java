package hospitalmanagement.controller;

import hospitalmanagement.Database;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;

public class AdminEditDoctorProfileController {
    private static int medicalLicense;

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
    Button buttonEdit;
    @FXML
    Button buttonDelete;


    public void editPersonAtributes() {
        if(buttonEdit.getText().equals("Edit")){
            enableAll();
            buttonEdit.setText("Save");
        } else if (buttonEdit.getText().equals("Save")) {
            Database.modifyTable("UPDATE hospitalManagement.Persons SET name = '" + nameInput.getText() + "', birthDate = '" + datePicker.getValue() + "', sex = 'F', phoneNumber = '" + phoneNumberInput.getText() + "', address = '" + addressInput.getText() + "', email = '" + emailInput.getText() + "' WHERE person_id = 12;");
            disableAll();
            buttonEdit.setText("Edit");
        }
    }


    public void enableAll(){
            nameInput.setDisable(false);
            sexDropdown.setDisable(false);
            //datePicker.setDisable(false);
            //medicalLicenseInput.setDisable(false);
            phoneNumberInput.setDisable(false);
            specialityDropdown.setDisable(false);
            hospitalDropdown.setDisable(false);
            emailInput.setDisable(false);
            addressInput.setDisable(false);
    }

    public void disableAll(){
        nameInput.setDisable(true);
        sexDropdown.setDisable(true);
        //datePicker.setDisable(true);
        //medicalLicenseInput.setDisable(true);
        phoneNumberInput.setDisable(true);
        specialityDropdown.setDisable(true);
        hospitalDropdown.setDisable(true);
        emailInput.setDisable(true);
        addressInput.setDisable(true);
    }

    public static void setMedicalLicense(int index) {
        medicalLicense = index;
    }

    public void setNameInput(){
    }
}
