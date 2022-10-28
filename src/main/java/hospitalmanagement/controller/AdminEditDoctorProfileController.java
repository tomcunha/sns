package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Hospital;
import hospitalmanagement.model.people.Doctor;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AdminEditDoctorProfileController extends SceneController {
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

    @FXML
    Button buttonMainMenu, buttonPower;

    @FXML
    TextField usernameInput;

    @FXML
    TextField passwordInput;

    @FXML
    public void setMainMenu() throws IOException {
        setScreen(buttonMainMenu, "AdminMenuScene.fxml");
    }

    @FXML
    public void setLogout() throws IOException {
        setScreen(buttonPower, "LoginScene.fxml");
    }

    public void editPersonAttributes() {
        if (buttonEdit.getText().equals("Edit")) {
            enableAll();
            buttonEdit.setText("Save");
            buttonEdit.setStyle("-fx-background-color: #79DD99");
            buttonDelete.setText("Cancel");
            buttonDelete.setStyle("-fx-background-color: linear-gradient(#d0d0d0, #aeb5b6)");
        } else if (buttonEdit.getText().equals("Save")) {
            Database.modifyTable("UPDATE Persons SET name = '" + nameInput.getText() + "', birthDate = '" + datePicker.getValue() + "', sex = '" + sexDropdown.getValue().toString().charAt(0) + "', email = '" + emailInput.getText() + "', phoneNumber = '" + phoneNumberInput.getText() + "', address = '" + addressInput.getText() + "' WHERE person_id = " + getPersonID());
            Database.modifyTable("UPDATE Doctors SET hospital_id = '" + getHospital().getId() + "' WHERE medicalLicense = " + medicalLicense);
            Information.updateDoctors();
            disableAll();
            buttonEdit.setText("Edit");
            buttonEdit.setStyle("-fx-background-color: #6D6D6D");
            buttonDelete.setText("Delete");
            buttonDelete.setStyle("-fx-background-color: #CF5F5F");
        }
    }

    public void deletePerson() throws IOException {
        if (buttonDelete.getText().equals("Delete")) {
            delete();
        } else if (buttonDelete.getText().equals("Cancel")) {
            disableAll();
            setInputs();
            buttonEdit.setText("Edit");
            buttonEdit.setStyle("-fx-background-color: #6D6D6D");
            buttonDelete.setText("Delete");
            buttonDelete.setStyle("-fx-background-color: #CF5F5F");
        }
    }


    public void enableAll() {
        nameInput.setDisable(false);
        sexDropdown.setDisable(false);
        phoneNumberInput.setDisable(false);
        hospitalDropdown.setDisable(false);
        emailInput.setDisable(false);
        addressInput.setDisable(false);
    }

    public void disableAll() {
        nameInput.setDisable(true);
        sexDropdown.setDisable(true);
        phoneNumberInput.setDisable(true);
        hospitalDropdown.setDisable(true);
        emailInput.setDisable(true);
        addressInput.setDisable(true);
    }

    public static void setMedicalLicense(String index) {
        medicalLicense = index;
    }

    public void setInputs() {
        for (Doctor doctor : Information.getDoctors()) {
            if (medicalLicense.equals(doctor.getMedicalLicense())) {
                nameInput.setText(doctor.getName());
                sexDropdown.setValue(doctor.getSex().toString());
                datePicker.setValue(doctor.getBirthDate());
                medicalLicenseInput.setText(medicalLicense);
                phoneNumberInput.setText(doctor.getContact().getPhoneNumber());
                specialityDropdown.setValue(doctor.getSpecialty().toString());
                hospitalDropdown.setValue(doctor.getWorkingHospital().getName());
                emailInput.setText(doctor.getContact().getEmail());
                addressInput.setText(doctor.getContact().getAddress());
                usernameInput.setText(doctor.getUsername());
                passwordInput.setText(doctor.getPassword());
            }
        }
    }

    private int getPersonID() {
        for (Doctor doctor : Information.getDoctors()) {
            if (doctor.getMedicalLicense().equals(medicalLicense)) {
                return doctor.getPersonID();
            }
        }
        return 0;
    }

    private Hospital getHospital() {
        for (Hospital hospital : Information.getHospitals()) {
            if (hospital.getName().equals(hospitalDropdown.getValue())) {
                return hospital;
            }
        }
        return null;
    }

    private void delete() throws IOException {
        Stage backStage = (Stage) editDoctorScene.getScene().getWindow();
        BoxBlur blur = new BoxBlur(5, 5, 5);
        editDoctorScene.setEffect(blur);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(backStage);
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
                //VERIFY THE PASSWORDS
                //Database.modifyTable("DELETE FROM Doctors WHERE medicalLicense = '" + medicalLicense + "'");

            }
            setScreen(buttonDelete, "AdminMenuScene.fxml");
        } else {
            editDoctorScene.setEffect(null);
        }
    }

    public void initializeComboBox() {
        initializeComboBoxHospital(hospitalDropdown);
        initializeComboBoxSpeciality(specialityDropdown);
        initializeChoiceBoxSex(sexDropdown);
    }
}
