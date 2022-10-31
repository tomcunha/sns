package hospitalmanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class StaffNewAppSelectController extends StaffController{

    @FXML
    private Button buttonNewExam, buttonNewApp;

    @FXML
    private Button buttonMainMenu, buttonPower;
    @FXML
    Text textStaff;

    @FXML
    public void setNewExamButton(){
        try {
            setScreen(buttonNewExam,"StaffNewExamScene.fxml");
            StaffNewExamController staffNewExamController = getFXML().getController();
            staffNewExamController.initializeComboBox();
            staffNewExamController.textStaff.setText(LoginMenuController.getEmployee_name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML void setNewAppButton(){
        try {
            setScreen(buttonNewApp,"StaffNewAppointment.fxml");
            StaffNewAppointmentController staffNewAppointmentController = getFXML().getController();
            staffNewAppointmentController.initializeComboBox();
            staffNewAppointmentController.textStaff.setText(LoginMenuController.getEmployee_name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void setMainMenu() {setMainMenu(buttonMainMenu);}

    @FXML
    public void setLogout() {setLogout(buttonPower);}
}
