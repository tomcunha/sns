package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Speciality;
import hospitalmanagement.model.people.Doctor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminNewSpecialityController extends SceneController {

    @FXML
    Button buttonSave;
    @FXML
    private Button buttonMainMenu;
    @FXML
    private Button buttonPower;
    @FXML
    private Button buttonCancel;
    @FXML
    private TextField specNameInput, specPriceInput;
    @FXML
    private Text inputTextErrorMessage, emptyTextErrorMessage, inputSymbolOfError;
    private boolean isEmpty, isDuplicate;

    @FXML
    private void createSpeciality() throws IOException {
        System.out.println("button clicked called");

        if (validate()) {
            Database.modifyTable("INSERT INTO Specialities (name, price) VALUES ('" + specNameInput.getText() + "','" + specPriceInput.getText() + "' )");
            setScreen(buttonSave, "AdminFindSpecialitiesScene.fxml");
            Information.updateSpecialities();
        }
    }

    @FXML
    public void setMainMenu() throws IOException {
        setScreen(buttonMainMenu, "AdminMenuScene.fxml");
    }

    @FXML
    public void setLogout() throws IOException {
        setScreen(buttonPower, "LoginScene.fxml");
    }
    @FXML
    public void cancel() throws IOException {
        setScreen(buttonCancel, "AdminFindSpecialitiesScene.fxml");
    }

    private boolean validate() {
        System.out.println("validate called");
        resetErrors();
        isDuplicate = false;
        isEmpty = false;
        inputTextErrorMessage.setVisible(false);
        inputSymbolOfError.setVisible(false);
        emptyTextErrorMessage.setVisible(false);
        System.out.println("error messages visible FALSE");

        String inputNameTrimmed = specNameInput.getText().trim();

        for (Speciality speciality : Information.getSpecialities()) {
            if (inputNameTrimmed.replace(" ", "").equalsIgnoreCase(speciality.getName().replace(" ", ""))) {
                inputTextErrorMessage.setVisible(true);
                inputSymbolOfError.setVisible(true);
                isDuplicate = true;
            }
        }
        if (inputNameTrimmed.isEmpty() || specPriceInput.getText().isEmpty()) {
            emptyTextErrorMessage.setVisible(true);
            isEmpty = true;
        }

        if (specNameInput.getText().isEmpty())
            specNameInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
        if (specPriceInput.getText().isEmpty())
            specPriceInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");

        if (isEmpty || isDuplicate) {
            return false;
        }
        return true;
    }

    private void resetErrors() {
        System.out.println("reset errors called");

        specNameInput.setStyle("-fx-effect: none");
        specPriceInput.setStyle("-fx-effect: none");
    }
}
