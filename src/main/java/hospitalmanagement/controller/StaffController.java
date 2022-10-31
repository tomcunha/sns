package hospitalmanagement.controller;

import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class StaffController extends SceneController{

    @Override
    public void setMainMenu(Button button) {
        try {
            setScreen(button, "StaffMenuScene.fxml");
            StaffMenuController staffMenuController = getFXML().getController();
            staffMenuController.setTextStaff(LoginMenuController.getEmployee_name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFindPatient(Button button){
        try {
            setScreen(button,"StaffFindPatientScene.fxml");
            StaffFindPatientController staffFindPatientController = getFXML().getController();
            staffFindPatientController.textStaff.setText(LoginMenuController.getEmployee_name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
