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
}
