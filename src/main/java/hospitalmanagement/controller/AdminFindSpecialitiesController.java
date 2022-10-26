package hospitalmanagement.controller;

import hospitalmanagement.Database;
import hospitalmanagement.Information;
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

    ObservableList <ObservableList> listSpecialities = FXCollections.observableArrayList();

    @FXML
    private Button buttonMainMenu, buttonPower, buttonAddSpeciality;
    @FXML
    private TableView<ObservableList> tableSpecialities;
    @FXML
    TableColumn<ObservableList, String> specialityColumn;
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
    public void setButtonSearch() throws SQLException {
        tableSpecialities.getItems().clear();

        buttonAddSpeciality.setVisible(false);

        loadData(nameTextField.getText());

        tableSpecialities.setVisible(true);
    }

    @FXML
    public void setNewSpeciality() throws IOException, SQLException {
        System.out.println("chamou setNewSpeciality");
        setScreen(buttonAddSpeciality, "AdminNewSpecialityScene.fxml");
        AdminNewDoctorProfileController adminNewDoctorProfileController = getFXML().getController();
        adminNewDoctorProfileController.initializeComboBox();
    }


    private void loadData(String name) {
        specialityColumn.setCellValueFactory(new PropertyValueFactory<>("name"));


        for (Speciality speciality : Information.getSpecialities()) {
            //if(speciality.toString().equalsIgnoreCase("%" + name + "%" ))
            listSpecialities.add(speciality);
        }


        tableSpecialities.getItems().addAll(listSpecialities);
        System.out.println(tableSpecialities.getItems());

        specialityColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(0).toString());
            }
        });
    }
//        specialitiesList.removeAll();
//        List<String> listSpecialities = new ArrayList<>();
//        for (Speciality speciality : Information.getSpecialities()) {
//            if(speciality.toString().equalsIgnoreCase("%" + name + "%" ))
//            listSpecialities.add(speciality.getName());
//            tableSpecialities.getItems().add(speciality.getName());
//        }
//        tableSpecialities.getItems().addAll(specialitiesList)
//        tableSpecialities.getItems().addAll(listSpecialities);
//        System.out.println(tableSpecialities.getItems());
}




