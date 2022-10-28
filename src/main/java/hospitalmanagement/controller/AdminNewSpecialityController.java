package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Speciality;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminNewSpecialityController extends SceneController {

    @FXML
    Text inputTextErrorMessage, emptyTextErrorMessage, inputSymbolOfError;
    @FXML
    TextField nameInput, priceInput;
    @FXML
    Button buttonSave, buttonCancel, buttonMainMenu, buttonPower;
    private boolean isDuplicate, isEmpty;

    public void saveNewSpeciality() throws SQLException, IOException {
        if (validation()) {
            if (!nameInput.getText().isEmpty()) {

                Database.modifyTable("INSERT INTO Specialities (name, price) VALUES ('" + nameInput.getText() + "', " + getPrice() + ")");
                Information.updateExams();
                setScreen(buttonSave, "AdminMenuScene.fxml");
            }
        }
    }

    private boolean validation() throws SQLException {
        // ResultSet resultSet = Database.queryTable("SELECT name FROM hospitalManagement.Specialities;");
        // while (resultSet.next()) {fred
        //   String nameDB = resultSet.getString("name");
        // String inputName = nameInput.getText().trim();
        resetErrors();
        isDuplicate = false;
        isEmpty = false;
        inputTextErrorMessage.setVisible(false);
        inputSymbolOfError.setVisible(false);
        emptyTextErrorMessage.setVisible(false);

        String inputNameTrimmed = nameInput.getText().trim();

        for (Speciality speciality : Information.getSpecialities()) {
            if (inputNameTrimmed.replace(" ", "").equalsIgnoreCase(speciality.getName().replace(" ", ""))) {
                inputTextErrorMessage.setVisible(true);
                inputSymbolOfError.setVisible(true);
                isDuplicate = true;
            }
        }
        if (inputNameTrimmed.isEmpty() || priceInput.getText().isEmpty()) {
            emptyTextErrorMessage.setVisible(true);
            isEmpty = true;
        }
        if (nameInput.getText().isEmpty())
            nameInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
        if (priceInput.getText().isEmpty())
            priceInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");

        if (isEmpty || isDuplicate) {
            return false;
        }
        return true;
    }


    public void setCancelButton() throws IOException {
        setScreen(buttonCancel, "AdminFindSpecialityScene.fxml");
    }

    @FXML
    public void setMainMenu() throws IOException {
        setScreen(buttonMainMenu, "AdminMenuScene.fxml");
    }

    @FXML
    public void setLogout() throws IOException {
        setScreen(buttonPower, "LoginScene.fxml");
    }

    private void resetErrors() {
        System.out.println("reset errors called");

        nameInput.setStyle("-fx-effect: none");
        priceInput.setStyle("-fx-effect: none");
    }

    private int getPrice() {
        try {
            int price = Integer.parseInt(priceInput.getText());
            return price;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

