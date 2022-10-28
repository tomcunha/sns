package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Exam;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminEditExamController extends SceneController {

    private static int examId;
    @FXML
    Button buttonSave, buttonCancel, buttonMainMenu, buttonPower;
    @FXML
    TextField nameInput, priceInput;


    @FXML
    public void setMainMenu() {
        try {
            setScreen(buttonMainMenu, "AdminMenuScene.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void setLogout() {
        try {
            setScreen(buttonPower, "LoginScene.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void setCancel() throws IOException {
        setScreen(buttonCancel, "AdminFindExamScene.fxml");
    }

    public void editExam() throws IOException {
        try{
            int price = Integer.parseInt(priceInput.getText());
            Database.modifyTable("UPDATE Exams SET name = '" + nameInput.getText() + "', price = "+ price + " WHERE exam_id = " + examId);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }

        Information.updateExams();
        setScreen(buttonSave,"AdminFindExamScene.fxml" );
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


}
