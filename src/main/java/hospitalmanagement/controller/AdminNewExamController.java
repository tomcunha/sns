package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminNewExamController extends SceneController {
    @FXML
    TextField nameInput;

    public void saveNewExam() throws SQLException {
        if (validation()){
            System.out.println("Cheguei");
            Database.modifyTable("INSERT INTO hospitalManagement.Exams (name) VALUES ('" + nameInput.getText() + "')");
            Information.updateExams();
        }

    }

    private boolean validation() throws SQLException {
        ResultSet resultSet = Database.queryTable("SELECT name FROM hospitalManagement.Exams;");
        while (resultSet.next()){
            if (resultSet.getMetaData().toString().equals(nameInput.getText())) {
                System.out.println("Cheguei Primeiro");
                System.out.println(resultSet.next());
                return false;
            }

        }
        System.out.println(resultSet.getFetchSize());
        return true;
    }
}
