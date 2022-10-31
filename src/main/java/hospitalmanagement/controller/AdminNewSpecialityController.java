package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Speciality;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class AdminNewSpecialityController extends AdminController {

    @FXML
    Text inputTextErrorMessage, emptyTextErrorMessage, inputSymbolOfError;
    @FXML
    TextField nameInput, priceInput;
    @FXML
    Button buttonSave, buttonCancel, buttonMainMenu, buttonPower;
    private boolean isDuplicate, isEmpty;
    @FXML
    Text textAdmin;

    public void saveNewSpeciality() throws SQLException, IOException {
        if (validation()) {
            if (!nameInput.getText().isEmpty()) {

                Database.modifyTable("INSERT INTO Specialities (name, price) VALUES ('" + nameInput.getText() + "', " + getPrice() + ")");
                Information.updateExams();
                setMainMenu();
            }
        }
    }

    private boolean validation() {
        resetErrors();
        isDuplicate = false;
        isEmpty = false;
        inputTextErrorMessage.setVisible(false);
        inputSymbolOfError.setVisible(false);
        emptyTextErrorMessage.setVisible(false);

        String inputNameTrimmed = nameInput.getText().trim().replace(" ", "");
        String inputPriceTrimmed = priceInput.getText().trim().replace(" ", "");

        for (Speciality speciality : Information.getSpecialities()) {
            if (inputNameTrimmed.equalsIgnoreCase(speciality.getName().replace(" ", ""))) {
                inputTextErrorMessage.setVisible(true);
                inputSymbolOfError.setVisible(true);
                isDuplicate = true;
            }
        }
        if (inputNameTrimmed.isEmpty()) {
            nameInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
            emptyTextErrorMessage.setVisible(true);
            isEmpty = true;
        }
        if (inputPriceTrimmed.isEmpty()) {
            priceInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
            emptyTextErrorMessage.setVisible(true);
            isEmpty = true;
        }
        if (isEmpty || isDuplicate) {
            return false;
        }
        return true;
    }

    public void setCancelButton(){
        setFindSpeciality(buttonCancel);
    }

    private void resetErrors() {
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

    @FXML
    public void setMainMenu() {setMainMenu(buttonMainMenu);}

    @FXML
    public void setLogout() {setLogout(buttonPower);}
}

