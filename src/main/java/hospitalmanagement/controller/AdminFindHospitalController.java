package hospitalmanagement.controller;

import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Hospital;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;

public class AdminFindHospitalController extends AdminController {

    @FXML
    private Button buttonMainMenu, buttonPower, buttonAddHospital;

    ObservableList<ObservableList<String>> listHospitals = FXCollections.observableArrayList();

    @FXML
    private TableView<ObservableList<String>> tableHospitals;

    @FXML
    private TableColumn hospitalName, hospitalPhoneNumber, hospitalEmail;

    @FXML
    private TextField nameTextField;
    @FXML
    Text textAdmin;




    @FXML
    public void selectHospital(MouseEvent mouseEvent) {

        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                String row = tableHospitals.getSelectionModel().getSelectedItems().get(0).toString();
                String[] row1 = row.split(",");
                String hospitalName = row1[0].replace("[", "").trim();

                try {
                    setScreen(buttonPower, "AdminEditHospitalScene.fxml");
                    AdminEditHospitalController adminEditHospitalController = getFXML().getController();
                    adminEditHospitalController.textAdmin.setText(LoginMenuController.getEmployee_name());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                AdminEditHospitalController editHospital = getFXML().getController();
                editHospital.setInputs(hospitalName);
            }
        }
    }

    public void clearInfo() {
        listHospitals.clear();
        tableHospitals.getItems().clear();
    }

    public void startTable() {
        if (listHospitals.size() != 0) {
            tableHospitals.setVisible(true);
        }

        try {
            initiateCols();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void showAllHospitals() {

        if (nameTextField.getText().isBlank()) {

            clearInfo();

            for (Hospital hospital : Information.getHospitals()) {
                loadHospitalInfo(hospital);
            }
            tableHospitals.getItems().addAll(listHospitals);

            startTable();
        }
    }

    public void loadHospitalInfo(Hospital hospital) {
        ObservableList<String> row = FXCollections.observableArrayList();
        row.add(hospital.getName());
        row.add(hospital.getContact().getPhoneNumber());
        row.add(hospital.getContact().getEmail());
        listHospitals.add(row);
    }

    @FXML
    private void loadData(KeyEvent key) {
        String name = nameTextField.getText();

        if (!name.isBlank()) {

            clearInfo();

            for (Hospital hospital : Information.getHospitals()) {

                String hName = hospital.getName().toUpperCase();
                name = name.toUpperCase();

                if (hName.contains(name)) {
                    loadHospitalInfo(hospital);
                }
            }

            tableHospitals.getItems().addAll(listHospitals);
            startTable();

        } else if (key.getCode().toString().equals("BACK_SPACE")) {
            showAllHospitals();
        }

    }

    private void initiateCols() throws SQLException {

        hospitalName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(0).toString());
            }
        });

        hospitalPhoneNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(1).toString());
            }
        });

        hospitalEmail.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(2).toString());
            }
        });
    }

    @FXML
    public void createNewHospital() {
        try {
            setScreen(buttonAddHospital, "AdminNewHospitalScene.fxml");
            AdminNewHospitalController adminNewHospitalController = getFXML().getController();
            adminNewHospitalController.textAdmin.setText(LoginMenuController.getEmployee_name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void setMainMenu() {setMainMenu(buttonMainMenu);}

    @FXML
    public void setLogout() {setLogout(buttonPower);}

}
