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
    Button buttonSave, buttonMainMenu, buttonPower, buttonCancel;
    @FXML
    private TextField specNameInput, priceInput;
    @FXML
    private Text inputTextErrorMessage;



    @FXML
    private void createSpeciality() throws IOException{
        if(validate()){
            try{
                int price = Integer.parseInt(priceInput.getText());
                Database.modifyTable("INSERT INTO Specialities (name, price) VALUES ('" + specNameInput.getText() + "', "+ price +")");
            }
            catch (NumberFormatException ex){
                ex.printStackTrace();
            }
            setScreen(buttonSave, "AdminFindSpecialityScene.fxml");
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
        setScreen(buttonCancel, "AdminFindSpecialityScene.fxml");
    }

    private boolean validate(){
        for (Speciality speciality : Information.getSpecialities()) {
            String inputNameTrimmed = specNameInput.getText().trim();

            if (inputNameTrimmed.replace(" ","").equalsIgnoreCase(speciality.getName().replace(" ",""))) {
                inputTextErrorMessage.setVisible(true);
                inputTextErrorMessage.setText("* That speciality already exists!");
                return false;
            }
            if (inputNameTrimmed.isEmpty() || priceInput.getText().isEmpty()){
                inputTextErrorMessage.setVisible(true);
                inputTextErrorMessage.setText("* Please fill in the fields!");
                return false;
            }
        }
        inputTextErrorMessage.setVisible(false);
        return true;
    }
}
