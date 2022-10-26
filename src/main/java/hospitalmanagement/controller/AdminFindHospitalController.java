package hospitalmanagement.controller;

import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Hospital;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;

public class AdminFindHospitalController extends SceneController {

    @FXML
    private Button buttonMainMenu, buttonPower;


    ObservableList<ObservableList<String>> listHospitals = FXCollections.observableArrayList();

    @FXML
    private TableView<ObservableList<String>> tableHospitals;

    @FXML
    private TableColumn hospitalName, hospitalPhoneNumber, hospitalEmail;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button buttonAddHospital;


    @FXML
    public void setMainMenu() throws IOException {
        setScreen(buttonMainMenu, "AdminMenuScene.fxml");
    }

    @FXML
    public void setLogout() throws IOException {
        setScreen(buttonPower, "LoginScene.fxml");
    }


    @FXML
    private void loadData() {
        listHospitals.clear();
        tableHospitals.getItems().clear();

        String name = nameTextField.getText();

        if (!name.isBlank()) {
            for (Hospital hospital : Information.getHospitals()) {

                String hName = hospital.getName().toUpperCase();
                name = name.toUpperCase();

                if (hName.contains(name)) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    row.add(hospital.getName());
                    row.add(hospital.getContact().getPhoneNumber());
                    row.add(hospital.getContact().getEmail());
                    listHospitals.add(row);
                }
            }
        }

        tableHospitals.getItems().addAll(listHospitals);

        if (listHospitals.size() != 0) {
            tableHospitals.setVisible(true);
        }

        try {
            initiateCols();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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


}
