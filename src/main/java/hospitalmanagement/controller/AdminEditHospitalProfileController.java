package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Hospital;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AdminEditHospitalProfileController extends SceneController {

    @FXML
    Button buttonMainMenu, buttonPower, buttonEdit, buttonDelete,buttonCancel;
    @FXML
    TextField nameInput;
    @FXML
    TextField phoneNumberInput;
    @FXML
    TextField emailInput;
    @FXML
    TextArea addressInput;
    @FXML
    GridPane editHospitalScene;

    private String hospitalName;
    private int hospital_id;

    @FXML
    public void setMainMenu() {
        try {
            setScreen(buttonMainMenu, "AdminMenuScene.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void setLogout() {
        try {
            setScreen(buttonPower, "LoginScene.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCancel() {
        try{
            setScreen(buttonCancel, "AdminFindHospitalScene.fxml");
        }catch (IOException e){
            throw  new RuntimeException(e);
        }
    }

    @FXML
    public void editHospital() {
        resetErrors();
        if (buttonEdit.getText().equals("Edit")) {
            enableAll();
            buttonEdit.setText("Save");
            buttonEdit.setStyle("-fx-background-color: #79DD99");
            buttonDelete.setText("Cancel");
            buttonDelete.setStyle("-fx-background-color: linear-gradient(#d0d0d0, #aeb5b6)");
        } else if (updateTable()) {
            disableAll();
            buttonEdit.setText("Edit");
            buttonEdit.setStyle("-fx-background-color: #6D6D6D");
            buttonDelete.setText("Delete");
            buttonDelete.setStyle("-fx-background-color: #CF5F5F");
        }
    }

    @FXML
    public void deleteHospital() {
        resetErrors();
        if (buttonDelete.getText().equals("Delete")) {
            try {
                delete();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (buttonDelete.getText().equals("Cancel")) {
            disableAll();
            setInputs(hospitalName);
            buttonEdit.setText("Edit");
            buttonEdit.setStyle("-fx-background-color: #6D6D6D");
            buttonDelete.setText("Delete");
            buttonDelete.setStyle("-fx-background-color: #CF5F5F");
        }
    }

    public void setInputs(String name) {
        nameInput.setText(name);
        hospitalName = name;
        for (Hospital hospital : Information.getHospitals()) {
            if (hospital.getName().equals(name)) {
                hospital_id = hospital.getId();
                addressInput.setText(hospital.getContact().getAddress());
                phoneNumberInput.setText(hospital.getContact().getPhoneNumber());
                emailInput.setText(hospital.getContact().getEmail());
            }
        }
    }

    public void enableAll() {
        nameInput.setDisable(false);
        phoneNumberInput.setDisable(false);
        emailInput.setDisable(false);
        addressInput.setDisable(false);
    }

    public void disableAll() {
        nameInput.setDisable(true);
        phoneNumberInput.setDisable(true);
        emailInput.setDisable(true);
        addressInput.setDisable(true);
    }

    public void resetErrors() {
        nameInput.setStyle("-fx-effect: none");
        addressInput.setStyle("-fx-effect: none");
        phoneNumberInput.setStyle("-fx-effect: none");
        emailInput.setStyle("-fx-effect: none");
    }

    public boolean updateTable() {

        String name = nameInput.getText().trim();
        String address = addressInput.getText().trim();
        String phoneNumber = phoneNumberInput.getText().replace(" ", "");
        String email = emailInput.getText().trim();

        if (validate(name, address, phoneNumber, email)) {
            String instruction = "UPDATE Hospitals " +
                    "SET name = '" + name +
                    "', address = '" + address +
                    "', phoneNumber ='" + phoneNumber +
                    "', email='" + email +
                    "' WHERE hospital_id =" + hospital_id;

            Database.modifyTable(instruction);
            Information.updateHospitals();
            return true;
        }
        return false;
    }

    public boolean validate(String name, String address, String phoneNumber, String email) {
        boolean validation = true;
        for (Hospital hospital : Information.getHospitals()) {

            if (hospital.getId() != hospital_id) {
                validation = AdminNewHospitalProfileController.validateHospital(name, address, phoneNumber, email, validation, hospital, nameInput, addressInput, phoneNumberInput, emailInput);
            }
        }
        return validation;
    }

    private void delete() throws IOException {

        Stage backStage = (Stage) editHospitalScene.getScene().getWindow();
        BoxBlur blur = new BoxBlur(5, 5, 5);
        editHospitalScene.setEffect(blur);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(backStage);
        alert.setHeaderText("Are you sure that you want to delete this Hospital?\nName: " + nameInput.getText() + "?");
        alert.setContentText("Choose your option.");

        ButtonType buttonTypeYes = new ButtonType("YES");
        ButtonType buttonTypeCancel = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

        Optional<ButtonType> yesOrCancelButton = alert.showAndWait();
        if (yesOrCancelButton.isPresent()){
            if (yesOrCancelButton.get() == buttonTypeYes) {

                Dialog<String> dialog = new Dialog<>();
                dialog.initOwner(backStage);
                dialog.setTitle("Delete Hospital Profile");
                dialog.setHeaderText("Are you quite sure? This will delete all data present in the programme.");
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                PasswordField pwd = new PasswordField();
                HBox content = new HBox();
                content.setAlignment(Pos.CENTER_LEFT);
                content.setSpacing(10);
                content.getChildren().addAll(new Label("Please enter your password to confirm:"), pwd);
                dialog.getDialogPane().setContent(content);
                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == ButtonType.OK) {
                        return pwd.getText();
                    }
                    return null;
                });

                Optional<String> result = dialog.showAndWait();

                if (verifyPassword(result.orElseThrow())) {
                    Database.modifyTable("DELETE FROM Hospitals WHERE hospital_id = " + hospital_id);
                    setScreen(buttonDelete, "AdminMenuScene.fxml");
                    Information.updateHospitals();
                } else {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initOwner(backStage);
                    alert.setHeaderText("Wrong password ...");

                    ButtonType buttonOk = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(buttonOk);
                    alert.showAndWait();
                }
            }
        }
        editHospitalScene.setEffect(null);
    }

    public boolean verifyPassword(String insertedPass) {

        int employee_id = LoginMenuController.getEmployee_id();

        ResultSet rs = Database.queryTable("SELECT (password) FROM Employees WHERE employee_id=" + employee_id);

        try {
            if (rs.next()) {
                return insertedPass.equals(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

}
