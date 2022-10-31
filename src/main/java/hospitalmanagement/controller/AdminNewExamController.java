package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Exam;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class AdminNewExamController extends AdminController {

    @FXML
    Text inputTextErrorMessage, emptyTextErrorMessage, inputSymbolOfError;
    @FXML
    TextField nameInput, priceInput;
    @FXML
    Button buttonSave, buttonCancel, buttonMainMenu, buttonPower;
    @FXML
    Text textAdmin;
    private boolean isDuplicate, isEmpty;


    public void saveNewExam() throws SQLException {
        if (validation()) {
            if (!nameInput.getText().isEmpty()) {
                Database.modifyTable("INSERT INTO Exams (name, price) VALUES ('" + nameInput.getText() + "', " + getPrice() + ")");

                Information.updateExams();
                try {
                    setScreen(buttonSave, "AdminMenuScene.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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

        for (Exam exam : Information.getExams()) {
            if (inputNameTrimmed.equalsIgnoreCase(exam.getName().replace(" ", ""))) {
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

    public void setCancelButton() throws IOException {
        setScreen(buttonCancel, "AdminFindExamScene.fxml");
    }

    @FXML
    private int getPrice() {
        try {
            int price = Integer.parseInt(priceInput.getText());
            return price;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    private void resetErrors() {
        System.out.println("reset errors called");

        nameInput.setStyle("-fx-effect: none");
        priceInput.setStyle("-fx-effect: none");
    }

    @FXML
    public void setMainMenu() {setMainMenu(buttonMainMenu);}

    @FXML
    public void setLogout() {setLogout(buttonPower);}
}
