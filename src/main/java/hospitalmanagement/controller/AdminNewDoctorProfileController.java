package hospitalmanagement.controller;

import hospitalmanagement.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.Text;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class AdminEditDoctorProfileController {

    @FXML
    TextField nameInput;
    @FXML
    ChoiceBox sexDropdown;
    @FXML
    DatePicker datePicker;
    @FXML
    TextField medicalLicenseInput;

    @FXML
    TextField phoneNumberInput;
    @FXML
    ComboBox specialityDropdown;
    @FXML
    ComboBox hospitalDropdown;
    @FXML
    TextField emailInput;
    @FXML
    TextArea addressInput;


    public void savePersonAtributes() {
        Database.modifyTable("INSERT INTO hospitalManagement.Persons (name,birthDate,sex,phoneNumber,address,email) " +
                "VALUES ('"+nameInput.getText()+"', '"+datePicker.getValue()+"', 'F', '"+phoneNumberInput.getText()+"', '"+addressInput.getText()+"', '"+emailInput.getText()+"') ");

        Database.modifyTable("INSERT INTO hospitalManagement.Employees (user,password,type,person_id) " +
                "VALUES ('new_user', 'new_password', '3', '"+LAST_INSERT_ID()+"') ");



    }

}
