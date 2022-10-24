package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Hospital;
import hospitalmanagement.model.people.Doctor;
import hospitalmanagement.utility.SpecialitiesUtil;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class AdminEditDoctorProfileController {
    private static String medicalLicense;

    @FXML
    GridPane editDoctorScene;
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
                    "UPDATE Persons SET name = '" + nameInput.getText() + "', birthDate = '" + datePicker.getValue() + "', sex = '" + sexDropdown.getValue().toString() + "', phoneNumber = '" + phoneNumberInput.getText() + "', address = '" + addressInput.getText() + "', email = '" + emailInput.getText() + "' WHERE person_id = " + getPersonID());
//            Database.modifyTable(
//                    "UPDATE Doctors SET hospital_id = '" + getHospital().getId() + "' WHERE medicalLicence = " + medicalLicense);
            disableAll();
            buttonEdit.setText("Edit");
            buttonDelete.setText("Delete");
        }
    }

    public void deletePerson(){
        if(buttonDelete.getText().equals("Delete")){
            delete();
        }else if(buttonDelete.getText().equals("Cancel")){
            disableAll();
            setInputs();
            buttonDelete.setText("Delete");
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
                phoneNumberInput.setText(doctor.getContact().getPhoneNumber());
                specialityDropdown.setValue(doctor.getSpecialty().toString());
                hospitalDropdown.setValue(doctor.getWorkingHospital().getName());
                emailInput.setText(doctor.getContact().getEmail());
                addressInput.setText(doctor.getContact().getAddress());
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

    private Hospital getHospital(){
        for (Hospital hospital:Information.getHospitals()){
            if (hospital.getName().equals(hospitalDropdown.getValue())){
                return hospital;
            }
        }
        return null;
    }

    private void delete(){
            Stage backStage = (Stage) editDoctorScene.getScene().getWindow();
            BoxBlur blur = new BoxBlur(5, 5, 5);
            editDoctorScene.setEffect(blur);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(backStage);
            //alert.setTitle("Confirmation Dialog with Custom Actions");
            alert.setHeaderText("Are you sure that you want to delete this Doctor?\nLicense nº " + medicalLicense + "?");
            alert.setContentText("Choose your option.");

            ButtonType buttonTypeYes = new ButtonType("YES");
            ButtonType buttonTypeCancel = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);


            Optional<ButtonType> yesOrCancelButton = alert.showAndWait();
            if (yesOrCancelButton.get() == buttonTypeYes) {

                Dialog<String> dialog = new Dialog<>();
                dialog.initOwner(backStage);
                dialog.setTitle("Delete Doctor Info");
                dialog.setHeaderText("Are you quite sure? This will delete all data present in the programme.");
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);


                PasswordField pwd = new PasswordField();
                HBox content = new HBox();
                content.setAlignment(Pos.CENTER_LEFT);
                content.setSpacing(10);
                content.getChildren().addAll(new Label("Please enter your password to confirm:"), pwd);
                dialog.getDialogPane().setContent(content);
                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == ButtonType.OK) {
                        return pwd.getText();
                    }
                    return null;
                });
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    System.out.println(result.get());
                }
                editDoctorScene.setEffect(null);
            } else {editDoctorScene.setEffect(null);}
        }
}
