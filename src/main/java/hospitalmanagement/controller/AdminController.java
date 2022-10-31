package hospitalmanagement.controller;

import javafx.scene.control.Button;
import java.io.IOException;

public class AdminController extends SceneController{

    @Override
    public void setMainMenu(Button button) {
        try {
            setScreen(button, "AdminMenuScene.fxml");
            AdminMenuController adminMenuController = getFXML().getController();
            adminMenuController.setTextAdmin(LoginMenuController.getEmployee_name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setFindSpeciality(Button button){
        try {
            setScreen(button, "AdminFindSpecialityScene.fxml");
            AdminFindSpecialityController adminFindSpecialityController = getFXML().getController();
            adminFindSpecialityController.textAdmin.setText(LoginMenuController.getEmployee_name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setFindExam(Button button){
        try {
            setScreen(button,"AdminFindExamScene.fxml");
            AdminFindExamController adminFindExamController = getFXML().getController();
            adminFindExamController.textAdmin.setText(LoginMenuController.getEmployee_name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
