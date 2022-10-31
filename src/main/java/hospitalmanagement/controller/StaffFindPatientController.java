package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.people.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffFindPatientController extends StaffController {
    @FXML
    private Button buttonAddPatient, buttonEdit, buttonMainMenu, buttonPower,buttonYesPopUp, buttonDelete,buttonNext;
    @FXML
    private TextField ccTextField;

    @FXML
    private Label nrLabel, title;
    @FXML
    private GridPane patientGrid, editPatientScene, popUp;
    @FXML
    HBox firstTextPopUp, secondtTextPopUp;

    @FXML
    PasswordField passwordInputpopUp;

    @FXML
    Text namePatientText, birthDatePatientText, identityNumberPatientText, nameInsuranceText, popUpText, textNext, textStaff;
    private static String patientCC;


    @FXML
    public void setNewPatient() throws IOException {
        setScreen(buttonAddPatient, "StaffNewPatientScene.fxml");
        StaffNewPatientController staffNewPatientController = getFXML().getController();
        staffNewPatientController.initializeComboBox();
        staffNewPatientController.textStaff.setText(LoginMenuController.getEmployee_name());
    }

    @FXML
    public void setButtonSearch(KeyEvent key) {

        if (key.getCode().toString().equals("ENTER")) {

            ccTextField.setStyle("-fx-effect: none");
            patientGrid.setVisible(false);
            nrLabel.setVisible(false);

            for (Patient patient : Information.getPatients()) {
                if (patient.getPatientCC().equals(ccTextField.getText())) {
                    nrLabel.setVisible(false);
                    patientGrid.setVisible(true);
                    namePatientText.setText(patient.getName());
                    birthDatePatientText.setText(patient.getBirthDate().toString());
                    identityNumberPatientText.setText(patient.getPatientCC());

                    if (patient.getInsurance() != null) {
                        nameInsuranceText.setText(patient.getInsurance().getName());
                    } else {
                        nameInsuranceText.setText("No Insurance");
                    }
                    textNext.setVisible(true);
                    buttonNext.setDisable(false);
                    buttonNext.setVisible(true);
                    break;
                } else {
                    nrLabel.setVisible(true);
                    namePatientText.setText("");
                    textNext.setVisible(false);
                    buttonNext.setDisable(false);
                    buttonNext.setVisible(false);
                }
            }
        }
    }

    @FXML
    public void editPatient() {
        try {
            setScreen(buttonEdit, "StaffNewPatientScene.fxml");
            StaffNewPatientController staffNewPatientController = getFXML().getController();
            staffNewPatientController.setCcNumber(identityNumberPatientText.getText());
            staffNewPatientController.initializeComboBox();
            staffNewPatientController.setInputs();
            staffNewPatientController.textStaff.setText(LoginMenuController.getEmployee_name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePerson() {
        popUp.setVisible(true);
        popUpText.setText(nameInsuranceText.getText());
        editPatientScene.setStyle(" -fx-background-color: rgb(192,192,192);opacity:0.8;");
        patientGrid.setStyle(" -fx-background-color: rgb(192,192,192);opacity:0.8;");
    }

    public void confirmDelete(){
        if (buttonYesPopUp.getText().equals("Yes")){
            firstTextPopUp.setVisible(false);
            secondtTextPopUp.setVisible(true);
            passwordInputpopUp.setStyle("-fx-effect: none");
            buttonYesPopUp.setText("OK");
        } else if (buttonYesPopUp.getText().equals("OK")) {
            if (verifyPassword(passwordInputpopUp.getText())) {
                //Database.modifyTable("DELETE FROM Patients WHERE patient_cc = '" + .... + "'");
                setMainMenu();
            } else {
                passwordInputpopUp.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
                passwordInputpopUp.setText("");
            }


        }

    }

    public void cancel(){
        popUp.setVisible(false);
        firstTextPopUp.setVisible(true);
        secondtTextPopUp.setVisible(false);
        editPatientScene.setStyle(" -fx-background-color: default");
        patientGrid.setStyle("-fx-background-color: default");
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

    public void selectAppointment() {
        title.setText("New Appointment");
        buttonEdit.setDisable(true);
        buttonEdit.setVisible(false);
        buttonDelete.setDisable(true);
        buttonDelete.setVisible(false);
        buttonAddPatient.setDisable(true);
        buttonAddPatient.setVisible(false);
    }

    @FXML
    public void nextAppointment() {
        if (!namePatientText.getText().isEmpty()) {
            patientCC = ccTextField.getText();
            try {
                setScreen(buttonNext, "StaffNewAppSelectScene.fxml");
                StaffNewAppSelectController staffNewAppSelectController = getFXML().getController();
                staffNewAppSelectController.textStaff.setText(LoginMenuController.getEmployee_name());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String getPatientCC() {
        return patientCC;
    }

    @FXML
    public void setMainMenu() {setMainMenu(buttonMainMenu);}

    @FXML
    public void setLogout() {setLogout(buttonPower);}
}
