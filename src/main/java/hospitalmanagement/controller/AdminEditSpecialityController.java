package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Speciality;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminEditSpecialityController extends SceneController {

    private static int specialityID;
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
        setScreen(buttonCancel, "AdminFindSpecialityScene.fxml");
    }

    public void editSpeciality() throws IOException {
        try{
            int price = Integer.parseInt(priceInput.getText());
            Database.modifyTable("UPDATE Specialities SET name = '" + nameInput.getText() + "', price = " + price + " WHERE exam_id = " + specialityID);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }


        Information.updateSpecialities();
        setScreen(buttonSave,"AdminFindSpecialityScene.fxml" );
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


}
