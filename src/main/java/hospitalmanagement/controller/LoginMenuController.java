package hospitalmanagement.controller;

import hospitalmanagement.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    @FXML
    public void setSignInButton() throws IOException {

        List<String> users = new ArrayList<>();
        List<String> passwords = new ArrayList<>();
        List<Integer> types = new ArrayList<>();

        ResultSet resultSet = Database.queryTable("SELECT * FROM Employees");
        try {
            while (resultSet.next()) {
                users.add(resultSet.getString("user"));
                passwords.add(resultSet.getString("password"));
                types.add(resultSet.getInt("type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (int i=0; i<users.size();i++){
            if (users.get(i).equals(usernamefield.getText()) && passwords.get(i).equals(passwordfield.getText())){
                switch (types.get(i)){
                    case 1:

                        break;
                    case 2:
                        setScreen(signInButton, "Example.fxml");
                        break;
                    case 3:
                        setScreen(signInButton, "AdminMenuScene.fxml");
                        break;
                }
                break;
            }
            else {
                textWarning.setText("Login failed: Your user ID or password is incorrect.");
            }
        }
    }
}
