package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Exam;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminEditExamsController extends SceneController {

    private static Integer examId;
    @FXML
    Button buttonCancel;
    @FXML
    Button buttonSave;
    @FXML
    TextField nameInput;


    public void saveNewExam() throws IOException {
        nameInput.setDisable(false);
        Database.modifyTable("UPDATE hospitalManagement.Exams SET name = '" + nameInput.getText() + "' WHERE exam_id = " + getExamId());
        Information.updateExams();
        nameInput.setDisable(true);
        setScreen(buttonSave,"AdminFindExams.fxml" );
    }

    public static void setExamId(Integer index) {
        examId = index;
    }

    public void setNameInput() {
        for (Exam exam : Information.getExams()) {
            if (examId.equals(exam.getId())) {
                nameInput.setText(exam.getName());
            }
        }
    }

    public int getExamId() {
        for (Exam exam : Information.getExams()) {
            if (examId.equals(exam.getId())) {
                return exam.getId();
            }
        }
        return 0;
    }


    public void setCancelButton() throws IOException {
        setScreen(buttonCancel, "AdminFindExams.fxml");
    }
}
