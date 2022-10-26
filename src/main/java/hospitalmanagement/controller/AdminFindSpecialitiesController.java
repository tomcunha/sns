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

    ObservableList<Speciality> listSpecialities = FXCollections.observableArrayList();

    @FXML
    private Button buttonMainMenu, buttonPower, buttonAddSpeciality, buttonSearch;
    @FXML
    private TableView<Speciality> tableSpecialities;
    @FXML
    private TableColumn<Speciality, String> specialityColumn;
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
    public void setButtonSearch() {
        tableSpecialities.getItems().clear();

        buttonSearch.setVisible(false);
        initColumn();
        loadData();

        tableSpecialities.setVisible(true);
    }

    @FXML
    private void loadData() {
        listSpecialities.clear();
        tableSpecialities.getItems().clear();

        String name =  nameTextField.getText();
        ObservableList<Speciality> filterList = FXCollections.observableArrayList();
        for (Speciality specialityName : Information.getSpecialities()) {
            if (specialityName.getName().toLowerCase().contains(name.toLowerCase()))
                listSpecialities.add(specialityName);
        }
        tableSpecialities.getItems().setAll(listSpecialities);
        if(listSpecialities.size() != 0)
            tableSpecialities.setVisible(true);
        initColumn();
    }

    private void initColumn() {
        specialityColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Speciality, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Speciality, String> specialityStringCellDataFeatures) {
                return specialityStringCellDataFeatures.getValue().nameProperty();
            }
        });

    }
}




