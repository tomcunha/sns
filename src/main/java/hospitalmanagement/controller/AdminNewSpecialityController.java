package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Speciality;
import hospitalmanagement.model.people.Doctor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminNewSpecialityController extends SceneController {

    @FXML
    Button buttonSave;
    @FXML
    private Button buttonMainMenu;
    @FXML
    private Button buttonPower;
    @FXML
    private Button buttonCancel;
    @FXML
    private TextField specNameInput;
    @FXML
    private Text inputErrorMessage;



    @FXML
    private void createSpeciality() throws IOException{
        if(validate()){
            Database.modifyTable("INSERT INTO Specialities (name) VALUES ('" + specNameInput.getText() + "')");
            setScreen(buttonSave, "AdminFindSpecialitiesScene.fxml");
            Information.updateSpecialities();
        } else {
        }
}
    @FXML
    public void setMainMenu() throws IOException {
        setScreen(buttonMainMenu, "AdminMenuScene.fxml");
    }

    @FXML
    public void setLogout() throws IOException {
        setScreen(buttonPower, "LoginScene.fxml");
    }
    @FXML
    public void cancel() throws IOException {
        setScreen(buttonCancel, "AdminFindSpecialitiesScene.fxml");
    }

    private boolean validate(){
        for (Speciality speciality : Information.getSpecialities()) {
            if (speciality.getName().equalsIgnoreCase(specNameInput.getText())) {
                inputErrorMessage.setVisible(true);
                inputErrorMessage.setText("* That speciality already exists!");
                return false;
            }
            if (specNameInput.getText().isEmpty()){
                inputErrorMessage.setVisible(true);
                inputErrorMessage.setText("* Please fill in the field!");
                return false;
            }
        }
        inputErrorMessage.setVisible(false);
        return true;
    }
}