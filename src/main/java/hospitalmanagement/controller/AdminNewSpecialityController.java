package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class AdminNewSpecialityController extends SceneController {

    @FXML
    Text inputTextErrorMessage;
    @FXML
    TextField nameInput, priceInput;
    @FXML
    Button buttonSave, buttonCancel, buttonMainMenu, buttonPower;

    public void saveNewSpeciality() throws SQLException, IOException {
        if (validation()) {
            if (!nameInput.getText().isEmpty()) {

                try{
                    int price = Integer.parseInt(priceInput.getText());
                    Database.modifyTable("INSERT INTO Specialities (name, price) VALUES ('" + nameInput.getText() + "', "+ price +")");
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
        ResultSet resultSet = Database.queryTable("SELECT name FROM hospitalManagement.Specialities;");

        while (resultSet.next()) {
            String nameDB = resultSet.getString("name");
            String inputName = nameInput.getText().trim();

            if (inputName.replace(" ","").equalsIgnoreCase(nameDB.replace(" ",""))) {
                inputTextErrorMessage.setVisible(true);
                inputTextErrorMessage.setText("* That speciality already exists!");
                return false;
            }
            if(nameInput.getText().isEmpty() || priceInput.getText().isEmpty()){
                inputTextErrorMessage.setVisible(true);
                inputTextErrorMessage.setText("* Please fill in the field!");
            }
        }
        inputTextErrorMessage.setVisible(false);
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
}
