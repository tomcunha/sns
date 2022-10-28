package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Exam;
import hospitalmanagement.model.medicalLists.Hospital;
import hospitalmanagement.model.medicalLists.Speciality;
import hospitalmanagement.utility.SexUtil;
import hospitalmanagement.utility.SpecialitiesUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static hospitalmanagement.controller.AdminEditDoctorProfileController.setMedicalLicense;

public class AdminFindSpecialitiesController extends SceneController {

    ObservableList<ObservableList<String>> listSpecialities = FXCollections.observableArrayList();

    @FXML
    private Button buttonMainMenu, buttonPower, buttonAddSpeciality, buttonSearch;
    @FXML
    private TableView<ObservableList<String>> tableSpecialities;
    @FXML
    private TableColumn specialityNameColumn, specialityPriceColumn;
    @FXML
    private TextField nameTextField;


    @FXML
    public void setMainMenu() throws IOException {
        setScreen(buttonMainMenu, "AdminMenuScene.fxml");
    }

    @FXML
    public void setLogout() throws IOException {
        setScreen(buttonPower, "LoginScene.fxml");
    }

    @FXML
    public void setNewSpeciality() throws IOException {
        setScreen(buttonAddSpeciality, "AdminNewSpecialityScene.fxml");
    }

    public void showAllSpecialities() {
        if (nameTextField.getText().isBlank()) {
            for (Speciality speciality : Information.getSpecialities()) {
                loadSpecialitiesInfo(speciality);
            }
            tableSpecialities.getItems().addAll(listSpecialities);
            tableSpecialities.setVisible(true);
            initColumn();
        }
    }
    @FXML
    public void setMouseClicked() throws IOException {

        String row = tableSpecialities.getSelectionModel().getSelectedItems().get(0).toString();
        row = row.replaceAll("\\D+", "");
        AdminEditDoctorProfileController.setMedicalLicense(row);
        System.out.println("mudaria de ecra");
        setScreen(buttonPower, "AdminEditSpecialitiesScene.fxml");
        AdminEditDoctorProfileController adminEditDoctorProfileController = getFXML().getController();
        adminEditDoctorProfileController.setInputs();
        adminEditDoctorProfileController.initializeComboBox();
    }

    public void loadSpecialitiesInfo(Speciality speciality) {
        ObservableList<String> row = FXCollections.observableArrayList();
        row.add(speciality.getName());
        row.add(speciality.toString());
        listSpecialities.add(row);
    }

    @FXML
    private void loadData() {
        tableSpecialities.getItems().clear();
        listSpecialities.removeAll(listSpecialities);

        String name = nameTextField.getText();
        for (Speciality specialityName : Information.getSpecialities()) {
            if (specialityName.getName().toLowerCase().contains(name.toLowerCase()))
                loadSpecialitiesInfo(specialityName);
        }
        tableSpecialities.getItems().setAll(listSpecialities);
        tableSpecialities.setVisible(true);
        initColumn();
    }

    private void initColumn() {
        specialityNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(0).toString());
            }
        });

        specialityPriceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(1).toString());
            }
        });

    }
}




