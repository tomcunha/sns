package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.people.Doctor;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AdminEditDoctorProfileController {
    private static String medicalLicense;

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
            buttonDelete.setText("Cancel");
        } else if (buttonEdit.getText().equals("Save")) {

            Database.modifyTable(
                    "UPDATE Persons SET " +
                                "name = '" + nameInput.getText() +
                                "', birthDate = '" + datePicker.getValue() +
                                "', sex = '" + sexDropdown.getValue().toString() +
                                "', phoneNumber = '" + phoneNumberInput.getText() +
                                "', address = '" + addressInput.getText() +
                                "', email = '" + emailInput.getText() +
                             "' WHERE person_id = " + getPersonID());
            Database.modifyTable(
                    "UPDATE Doctors SET hospital_id =");
            disableAll();
            buttonEdit.setText("Edit");
        }
    }

    public void deletePerson(){
        if(buttonDelete.getText().equals("Delete")){

        }else if(buttonDelete.getText().equals("Cancel")){
            disableAll();
            setInputs();
            buttonDelete.setText("Delete");
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

    public static void setMedicalLicense(String index) {
        medicalLicense = index;
    }

    public void setInputs(){
        for (Doctor doctor: Information.getDoctors()){
            if(medicalLicense.equals(doctor.getMedicalLicense())){
                nameInput.setText(doctor.getName());
                sexDropdown.setValue(doctor.getSex().toString());
                datePicker.setValue(doctor.getBirthDate());
                medicalLicenseInput.setText(medicalLicense);
                phoneNumberInput.setText(doctor.getContacts().getPhoneNumber());
                specialityDropdown.setValue(doctor.getMedicalLicense());
                hospitalDropdown.setValue(doctor.getWorkingHospital());
                emailInput.setText(doctor.getContacts().getEmail());
                addressInput.setText(doctor.getContacts().getAddress());
            }
        }
    }

    private int getPersonID(){
        for (Doctor doctor:Information.getDoctors()){
            if (doctor.getMedicalLicense().equals(medicalLicense)) {
                return doctor.getId();
            }
        }
        return 0;
    }
}
