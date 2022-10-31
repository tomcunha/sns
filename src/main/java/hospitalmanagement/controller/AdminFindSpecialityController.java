package hospitalmanagement.controller;

import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Speciality;
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

public class AdminFindSpecialityController extends AdminController {

    ObservableList<ObservableList> listSpecialities = FXCollections.observableArrayList();

    @FXML
    private Button buttonMainMenu, buttonPower, buttonAddSpeciality;
    @FXML
    private TableView tableSpecialities;
    @FXML
    private TableColumn specialityColumn, priceColumn;
    @FXML
    private TextField nameTextField;
    @FXML
    Text textAdmin;



    @FXML
    public void selectSpecialities(MouseEvent mouseEvent) {

        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                String row = tableSpecialities.getSelectionModel().getSelectedItems().get(0).toString();
                String[] row1 = row.split(",");
                String specialityName = row1[0].replace("[", "").trim();

                try {
                    setScreen(buttonPower, "AdminEditSpecialityScene.fxml");
                    AdminEditSpecialityController adminEditSpecialityController = getFXML().getController();
                    adminEditSpecialityController.textAdmin.setText(LoginMenuController.getEmployee_name());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                AdminEditSpecialityController adminEditSpecialityController = getFXML().getController();
                adminEditSpecialityController.setInputs(specialityName);
            }
        }
    }

    public void clearInfo() {
        listSpecialities.clear();
        tableSpecialities.getItems().clear();
    }

    public void startTable() {
        if (listSpecialities.size() != 0) {
            tableSpecialities.setVisible(true);
        }

        try {
            initiateCols();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void showAllSpecialities() {

        if (nameTextField.getText().isBlank()) {

            clearInfo();

            for (Speciality speciality : Information.getSpecialities()) {
                loadSpecialityInfo(speciality);
            }
            tableSpecialities.getItems().addAll(listSpecialities);

            startTable();
        }
    }

    public void loadSpecialityInfo(Speciality speciality) {
        ObservableList<String> row = FXCollections.observableArrayList();
        row.add(speciality.getName());
        row.add(String.valueOf(speciality.getPrice()));
        listSpecialities.add(row);
    }

    @FXML
    private void loadData(KeyEvent key) {
        String name = nameTextField.getText();

        if (!name.isBlank()) {

            clearInfo();

            for (Speciality speciality : Information.getSpecialities()) {

                String hName = speciality.getName().toUpperCase();
                name = name.toUpperCase();

                if (hName.contains(name)) {
                    loadSpecialityInfo(speciality);
                }
            }

            tableSpecialities.getItems().addAll(listSpecialities);
            startTable();

        } else if (key.getCode().toString().equals("BACK_SPACE")) {
            showAllSpecialities();
        }

    }

    private void initiateCols() throws SQLException {

        specialityColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(0).toString());
            }
        });

        priceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(1).toString());
            }
        });

    }

    @FXML
    public void createNewSpeciality() {
        try {
            setScreen(buttonAddSpeciality, "AdminNewSpecialityScene.fxml");
            AdminNewSpecialityController adminNewSpecialityController = getFXML().getController();
            adminNewSpecialityController.textAdmin.setText(LoginMenuController.getEmployee_name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void setMainMenu() {setMainMenu(buttonMainMenu);}

    @FXML
    public void setLogout() {setLogout(buttonPower);}


}
