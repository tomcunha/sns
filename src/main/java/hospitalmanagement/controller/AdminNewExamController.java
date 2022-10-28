package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminNewExamController extends SceneController {

    @FXML
    Text inputTextErrorMessage;
    @FXML
    TextField examNameInput, priceInput;
    @FXML
    Button buttonSave, buttonCancel, buttonMainMenu, buttonPower;

    public void saveNewExam() throws SQLException, IOException {
        if (validation()) {
            if (!examNameInput.getText().isEmpty()) {

                try{
                    int price = Integer.parseInt(priceInput.getText());
                    Database.modifyTable("INSERT INTO Exams (name, price) VALUES ('" + examNameInput.getText() + "', "+ price +")");
                }
                catch (NumberFormatException ex){
                    ex.printStackTrace();
                }


                Information.updateExams();
                setScreen(buttonSave, "AdminMenuScene.fxml");

            }else {
            }

        }
    }

    private boolean validation() throws SQLException {
        ResultSet resultSet = Database.queryTable("SELECT name FROM hospitalManagement.Exams;");

        while (resultSet.next()) {
            String nameDB = resultSet.getString("name");
            String inputName = examNameInput.getText().trim();

            if (inputName.replace(" ","").equalsIgnoreCase(nameDB.replace(" ",""))) {
                inputTextErrorMessage.setVisible(true);
                inputTextErrorMessage.setText("* That exam already exists!");
                return false;
            }
            if(examNameInput.getText().isEmpty() || priceInput.getText().isEmpty()){
                inputTextErrorMessage.setVisible(true);
                inputTextErrorMessage.setText("* Please fill in the field!");
            }
        }
        inputTextErrorMessage.setVisible(false);
        return true;
    }

    public void setCancelButton() throws IOException {
        setScreen(buttonCancel, "AdminFindExamScene.fxml");
    }

    @FXML
    public void setMainMenu() throws IOException {
        setScreen(buttonMainMenu, "AdminMenuScene.fxml");
    }

    @FXML
    public void setLogout() throws IOException {
        setScreen(buttonPower, "LoginScene.fxml");
    }
}
