package hospitalmanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class StaffNewAppSelectController extends SceneController{

    @FXML
    private Button buttonNewExam, buttonNewApp;

    @FXML
    private Button buttonMainMenu, buttonPower;

    @FXML
    public void setMainMenu() throws IOException {
        setScreen(buttonMainMenu, "StaffMenuScene.fxml");
    }

    @FXML
    public void setLogout() throws IOException {
        setScreen(buttonPower, "LoginScene.fxml");
    }

    @FXML
    public void setNewExamButton(){
        try {
            setScreen(buttonNewExam,"LoginScene.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML void setNewAppButton(){
        try {
            setScreen(buttonNewApp,"StaffNewAppointment.fxml");
            StaffNewAppointmentController staffNewAppointmentController = getFXML().getController();
            staffNewAppointmentController.initializeComboBox();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
