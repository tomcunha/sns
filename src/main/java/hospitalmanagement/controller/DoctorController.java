package hospitalmanagement.controller;

import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class DoctorController extends SceneController{

    @Override
    public void setMainMenu(Button button) {
        try {
            setScreen(button, "DoctorMenuScene.fxml");
            DoctorMenuController doctorMenuController = getFXML().getController();
            doctorMenuController.setTextDoctor(LoginMenuController.getEmployee_name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
