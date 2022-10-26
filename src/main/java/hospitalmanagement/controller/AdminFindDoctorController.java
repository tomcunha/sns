package hospitalmanagement.controller;

import hospitalmanagement.Database;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static hospitalmanagement.controller.AdminEditDoctorProfileController.setMedicalLicense;

public class AdminFindDoctorController extends SceneController {

    @FXML
    private Button buttonMainMenu, buttonPower;

    ObservableList<ObservableList> listDoctors = FXCollections.observableArrayList();
    private ResultSet resultSet;

    @FXML
    private TableView tableDoctors;

    @FXML
    private TableColumn doctorLicense, doctorName, doctorHospital, doctorSpeciality;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button buttonAddDoctor;

    @FXML
    public void setMouseClicked() throws IOException {


        String row = tableDoctors.getSelectionModel().getSelectedItems().get(0).toString();
        row = row.replaceAll("\\D+", "");
        AdminEditDoctorProfileController.setMedicalLicense(row);

        setScreen(buttonPower, "AdminEditDoctorProfileScene.fxml");
        AdminEditDoctorProfileController adminEditDoctorProfileController = getFXML().getController();
        adminEditDoctorProfileController.setInputs();
        adminEditDoctorProfileController.initializeComboBox();

    }

    @FXML
    public void setMainMenu() throws IOException {
        setScreen(buttonMainMenu, "AdminMenuScene.fxml");
    }

    @FXML
    public void setLogout() throws IOException {
        setScreen(buttonPower, "LoginScene.fxml");
    }

    @FXML
    public void setNewDoctor() throws IOException, SQLException {
        setScreen(buttonAddDoctor, "AdminNewDoctorProfileScene.fxml");
        AdminNewDoctorProfileController adminNewDoctorProfileController = getFXML().getController();
        adminNewDoctorProfileController.initializeComboBox();
    }

    @FXML
    public void setButtonSearch() throws SQLException {
        tableDoctors.getItems().clear();

        buttonAddDoctor.setVisible(false);

        initiateCols();

        loadData(nameTextField.getText());

        tableDoctors.setVisible(true);
    }


    private void loadData(String name) throws SQLException {

        listDoctors.removeAll(listDoctors);

        resultSet = Database.queryTable("SELECT medicalLicense as 'MedicalLicense', Persons.name  as 'Name', Hospitals.name as 'Hospital', Specialities.name as 'Speciality'\n" +
                "FROM Doctors \n" +
                "JOIN Employees ON Doctors.employee_id = Employees.employee_id \n" +
                "JOIN Persons ON Employees.person_id = Persons.person_id\n" +
                "JOIN Specialities ON Doctors.speciality_id = Specialities.speciality_id\n" +
                "JOIN Hospitals ON Doctors.hospital_id = Hospitals.hospital_id\n" +
                "WHERE Persons.name LIKE '%" + name + "%';");

        while (resultSet.next()) {

            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                //Iterate Column
                row.add(resultSet.getString(i));
            }
            listDoctors.add(row);

        }

        tableDoctors.getItems().addAll(listDoctors);
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


}
