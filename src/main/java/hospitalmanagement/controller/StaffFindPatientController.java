package hospitalmanagement.controller;

import hospitalmanagement.Information;
import hospitalmanagement.model.people.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StaffFindPatientController extends SceneController{
    @FXML
    private Button buttonAddPatient, buttonEdit, buttonDelete;

    @FXML
    private TextField ccTextField;

    @FXML
    private Label nrLabel;

    @FXML
    private GridPane patientGrid;

    @FXML
    private Text namePatientText, birthDatePatientText, identityNumberPatientText, nameInsuranceText;

    @FXML
    public void setNewPatient() throws IOException {
        setScreen(buttonAddPatient, "StaffNewPatientScene.fxml");
        StaffNewPatientController staffNewPatientController = getFXML().getController();
        staffNewPatientController.initializeComboBox();
    }

    @FXML
    public void setButtonSearch(){
        patientGrid.setVisible(false);
        nrLabel.setVisible(false);

        for (Patient patient: Information.getPatients()){
            if (patient.getPatientCC().equals(ccTextField.getText())){
                nrLabel.setVisible(false);
                patientGrid.setVisible(true);
                namePatientText.setText(patient.getName());
                birthDatePatientText.setText(patient.getBirthDate().toString());
                identityNumberPatientText.setText(patient.getPatientCC());
                if(patient.getInsurance() != null) {
                    nameInsuranceText.setText(patient.getInsurance().getName());
                }else {
                    nameInsuranceText.setText("No Insurance");
                }
                break;
               }
            else {
                nrLabel.setVisible(true);
                break;
            }
        }
    }

    @FXML
    public void editPatient(){
        try {
            setScreen(buttonEdit, "StaffNewPatientScene.fxml");
            StaffNewPatientController staffNewPatientController = getFXML().getController();
            staffNewPatientController.setCcNumber(identityNumberPatientText.getText());
            staffNewPatientController.initializeComboBox();
            staffNewPatientController.setInputs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
