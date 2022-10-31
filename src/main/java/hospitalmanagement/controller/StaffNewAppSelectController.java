package hospitalmanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class StaffNewAppSelectController extends StaffController{

    @FXML
    private Button buttonNewExam, buttonNewApp;

    @FXML
    private Button buttonMainMenu, buttonPower;

    @FXML
    public void setNewExamButton(){
        try {
            setScreen(buttonNewExam,"StaffNewExamScene.fxml");
            StaffNewExamController staffNewExamController = getFXML().getController();
            staffNewExamController.initializeComboBox();
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

    @FXML
    public void setMainMenu() {setMainMenu(buttonMainMenu);}

    @FXML
    public void setLogout() {setLogout(buttonPower);}
}
