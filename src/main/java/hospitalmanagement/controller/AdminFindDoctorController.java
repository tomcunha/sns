package hospitalmanagement.controller;

import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Hospital;
import hospitalmanagement.model.people.Doctor;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static hospitalmanagement.controller.AdminEditDoctorProfileController.setMedicalLicense;

public class AdminFindDoctorController extends SceneController {

    @FXML
    private Button buttonMainMenu, buttonPower;

    ObservableList<ObservableList> listDoctors = FXCollections.observableArrayList();

    @FXML
    private TableView tableDoctors;

    @FXML
    private TableColumn doctorLicense, doctorName, doctorHospital, doctorSpeciality;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button buttonAddDoctor;

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

    @FXML
    public void selectDoctors(MouseEvent mouseEvent) {

        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                String row = tableDoctors.getSelectionModel().getSelectedItems().get(0).toString();
                row = row.replaceAll("\\D+", "");

                try {
                    setScreen(buttonPower, "AdminEditDoctorProfileScene.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                AdminEditDoctorProfileController adminEditDoctorProfileController = getFXML().getController();
                adminEditDoctorProfileController.setMedicalLicense(row);
                adminEditDoctorProfileController.setInputs();
                adminEditDoctorProfileController.initializeComboBox();
            }
        }
    }

    public void clearInfo() {
        listDoctors.clear();
        tableDoctors.getItems().clear();
    }

    public void startTable() {
        if (listDoctors.size() != 0) {
            tableDoctors.setVisible(true);
        }

        try {
            initiateCols();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void showAllDoctors() {

        if (nameTextField.getText().isBlank()) {

            clearInfo();

            for (Doctor doctor : Information.getDoctors()) {
                loadDoctorInfo(doctor);
            }
            tableDoctors.getItems().addAll(listDoctors);

            startTable();
        }
    }

    public void loadDoctorInfo(Doctor doctor) {
        ObservableList<String> row = FXCollections.observableArrayList();
        row.add(doctor.getMedicalLicense());
        row.add(doctor.getName());
        row.add(doctor.getWorkingHospital().getName());
        row.add(doctor.getSpecialty().getName());
        listDoctors.add(row);
    }

    @FXML
    private void loadData(KeyEvent key) {
        String name = nameTextField.getText();

        if (!name.isBlank()) {

            clearInfo();

            for (Doctor doctor : Information.getDoctors()) {

                String hName = doctor.getName().toUpperCase();
                name = name.toUpperCase();

                if (hName.contains(name)) {
                    loadDoctorInfo(doctor);
                }
            }

            tableDoctors.getItems().addAll(listDoctors);
            startTable();

        } else if (key.getCode().toString().equals("BACK_SPACE")) {
            showAllDoctors();
        }

    }

    private void initiateCols() throws SQLException {

        doctorLicense.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(0).toString());
            }
        });

        doctorName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(1).toString());
            }
        });

        doctorHospital.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(2).toString());
            }
        });

        doctorSpeciality.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(3).toString());
            }
        });
    }

    @FXML
    public void createNewDoctor() {
        try {
            setScreen(buttonAddDoctor, "AdminNewDoctorProfileScene.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
