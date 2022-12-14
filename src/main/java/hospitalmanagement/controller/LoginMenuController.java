package hospitalmanagement.controller;

import hospitalmanagement.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginMenuController extends SceneController {

    @FXML
    Button signInButton;

    @FXML
    TextField usernamefield;

    @FXML
    PasswordField passwordfield;

    @FXML
    Text textWarning;

    private static int employee_id;

    @FXML
    public void enterPressed(KeyEvent key) {
        if (key.getCode().toString().equals("ENTER")) {
            try {
                setSignInButton();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void setSignInButton() throws IOException {

        List<Integer> ids = new ArrayList<>();
        List<String> users = new ArrayList<>();
        List<String> passwords = new ArrayList<>();
        List<Integer> types = new ArrayList<>();

        ResultSet resultSet = Database.queryTable("SELECT * FROM Employees");
        try {
            while (resultSet.next()) {
                ids.add(resultSet.getInt("employee_id"));
                users.add(resultSet.getString("user"));
                passwords.add(resultSet.getString("password"));
                types.add(resultSet.getInt("type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(usernamefield.getText()) && passwords.get(i).equals(passwordfield.getText())) {
                employee_id = ids.get(i);
                switch (types.get(i)) {
                    case 1:
                        setScreen(signInButton, "StaffMenuScene.fxml");
                        StaffMenuController staffMenuController = getFXML().getController();
                        staffMenuController.setTextStaff(getEmployee_name());
                        break;
                    case 2:
                        setScreen(signInButton, "DoctorMenuScene.fxml");
                        DoctorMenuController doctorMenuController = getFXML().getController();
                        doctorMenuController.setTextDoctor(getEmployee_name());
                        break;
                    case 3:
                        setScreen(signInButton, "AdminMenuScene.fxml");
                        AdminMenuController adminMenuController = getFXML().getController();
                        adminMenuController.setTextAdmin(getEmployee_name());
                        break;
                }
                break;
            }
        }
        textWarning.setText("Login failed: Your user ID or password is incorrect.");
    }

    public static int getEmployee_id() {
        return employee_id;
    }

    public static String getEmployee_name(){
        ResultSet resultSet = Database.queryTable("SELECT name FROM Persons INNER JOIN Employees ON Persons.person_id = Employees.person_id WHERE employee_id = " + getEmployee_id());
        try {
            if(resultSet.next()){
                return resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void setMainMenu(Button button) {}
}
