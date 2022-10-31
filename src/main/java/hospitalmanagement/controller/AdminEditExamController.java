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

public class AdminEditExamController extends AdminController {

    private static int examId;
    @FXML
    Button buttonSave, buttonCancel, buttonMainMenu, buttonPower;
    @FXML
    TextField nameInput, priceInput;
    @FXML
    Text inputTextErrorMessage, emptyTextErrorMessage, inputSymbolOfError;
    private boolean isDuplicate, isEmpty;


    @FXML
    public void setCancel() throws IOException {
        setScreen(buttonCancel, "AdminFindExamScene.fxml");
    }


    public void editExam() throws IOException, SQLException {
        if (validation()) {
            Database.modifyTable("UPDATE Exams SET name = '" + nameInput.getText() + "', price = " + getPrice() + " WHERE exam_id = " + examId);

            Information.updateExams();
            setScreen(buttonSave, "AdminFindExamScene.fxml");
        }
    }

    public void setInputs(String name) {
        nameInput.setText(name);
        for (Exam exam : Information.getExams()) {
            if (exam.getName().equals(name)) {
                examId = exam.getId();
                priceInput.setText(String.valueOf(exam.getPrice()));
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

        for (Exam exam : Information.getExams()) {
            if (inputNameTrimmed.equalsIgnoreCase(exam.getName().replace(" ", ""))) {
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
    public void setMainMenu() {
        setMainMenu(buttonMainMenu);
    }

    @FXML
    public void setLogout() {setLogout(buttonPower);}

}
