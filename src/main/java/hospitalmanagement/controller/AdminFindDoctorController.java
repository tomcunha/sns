package hospitalmanagement.controller;

import hospitalmanagement.Information;
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
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;

public class AdminFindDoctorController extends AdminController {

    @FXML
    private Button buttonMainMenu, buttonPower, buttonAddDoctor;

    ObservableList<ObservableList> listDoctors = FXCollections.observableArrayList();

    @FXML
    private TableView tableDoctors;

    @FXML
    private TableColumn doctorLicense, doctorName, doctorHospital, doctorSpeciality;

    @FXML
    private TextField nameTextField;
    @FXML
    Text textAdmin;



    @FXML
    public void selectDoctors(MouseEvent mouseEvent) {

        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                String row = tableDoctors.getSelectionModel().getSelectedItems().get(0).toString();
                row = row.replaceAll("\\D+", "");

                try {
                    setScreen(buttonPower, "AdminEditDoctorScene.fxml");
                    AdminEditDoctorController adminEditDoctorController = getFXML().getController();
                    adminEditDoctorController.textAdmin.setText(LoginMenuController.getEmployee_name());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                AdminEditDoctorController adminEditDoctorController = getFXML().getController();
                adminEditDoctorController.setMedicalLicense(row);
                adminEditDoctorController.setInputs();
                adminEditDoctorController.initializeComboBox();
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
            setScreen(buttonAddDoctor, "AdminNewDoctorScene.fxml");
            AdminNewDoctorController adminNewDoctorController = getFXML().getController();
            adminNewDoctorController.textAdmin.setText(LoginMenuController.getEmployee_name());
            adminNewDoctorController.initializeComboBox();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void setMainMenu() {setMainMenu(buttonMainMenu);}

    @FXML
    public void setLogout() {setLogout(buttonPower);}

}
