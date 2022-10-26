package hospitalmanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;

public class StaffFindPatientController extends SceneController{

    @FXML
    private Button buttonAddPatient;

    @FXML
    public void setNewPatient() throws IOException, SQLException {
        setScreen(buttonAddPatient, "StaffNewPatientScene.fxml");
        StaffNewPatientController staffNewPatientController = getFXML().getController();
        staffNewPatientController.initializeComboBox();
    }

}
