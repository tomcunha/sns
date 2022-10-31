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

public class AdminEditSpecialityController extends AdminController {

    private static int specialityID;
    private boolean isDuplicate, isEmpty;
    @FXML
    Button buttonSave, buttonCancel, buttonMainMenu, buttonPower;
    @FXML
    TextField nameInput, priceInput;
    @FXML
    Text inputTextErrorMessage, emptyTextErrorMessage, inputSymbolOfError;


    @FXML
    public void setCancel() throws IOException {
        setScreen(buttonCancel, "AdminFindSpecialityScene.fxml");
    }

    public void editSpeciality() throws IOException, SQLException {
        if (validation()) {
            Database.modifyTable("UPDATE Specialities SET name = '" + nameInput.getText() + "', price = " + getPrice() + " WHERE speciality_id = " + specialityID);

            Information.updateSpecialities();
            setScreen(buttonSave, "AdminFindSpecialityScene.fxml");
        }
    }

    public void setInputs(String name) {
        nameInput.setText(name);
        for (Speciality speciality : Information.getSpecialities()) {
            if (speciality.getName().equals(name)) {
                specialityID = speciality.getId();
                priceInput.setText(String.valueOf(speciality.getPrice()));
                break;
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
        if (inputNameTrimmed.isEmpty() || inputPriceTrimmed.isEmpty()) {
            emptyTextErrorMessage.setVisible(true);
            isEmpty = true;
        }
        if (inputNameTrimmed.isEmpty())
            nameInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");
        if (inputPriceTrimmed.isEmpty())
            priceInput.setStyle("-fx-effect: dropshadow( one-pass-box, red, 15,0,0,0)");

        if (isEmpty || isDuplicate) {
            return false;
        }
        return true;
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

    @FXML
    public void setMainMenu() {setMainMenu(buttonMainMenu);}

    @FXML
    public void setLogout() {setLogout(buttonPower);}
}
