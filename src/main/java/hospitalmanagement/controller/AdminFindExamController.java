package hospitalmanagement.controller;

import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Exam;
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

public class AdminFindExamController extends AdminController {

    @FXML
    private Button buttonMainMenu, buttonPower, buttonAddExam;

    ObservableList<ObservableList> listExams = FXCollections.observableArrayList();

    @FXML
    private TableView tableExams;

    @FXML
    private TableColumn examColumn, priceColumn;

    @FXML
    private TextField nameTextField;

    @FXML
    Text textAdmin;


    @FXML
    public void selectExams(MouseEvent mouseEvent) {

        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                String row = tableExams.getSelectionModel().getSelectedItems().get(0).toString();
                String[] row1 = row.split(",");
                String examName = row1[0].replace("[", "").trim();

                try {
                    setScreen(buttonPower, "AdminEditExamScene.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                AdminEditExamController adminEditExamControllerExamsController = getFXML().getController();
                adminEditExamControllerExamsController.setInputs(examName);
            }
        }
    }

    public void clearInfo() {
        listExams.clear();
        tableExams.getItems().clear();
    }

    public void startTable() {
        if (listExams.size() != 0) {
            tableExams.setVisible(true);
        }

        try {
            initiateCols();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void showAllExams() {

        if (nameTextField.getText().isBlank()) {

            clearInfo();

            for (Exam exam : Information.getExams()) {
                loadExamInfo(exam);
            }
            tableExams.getItems().addAll(listExams);

            startTable();
        }
    }

    public void loadExamInfo(Exam exam) {
        ObservableList<String> row = FXCollections.observableArrayList();
        row.add(exam.getName());
        row.add(String.valueOf(exam.getPrice()));
        listExams.add(row);
    }

    @FXML
    private void loadData(KeyEvent key) {
        String name = nameTextField.getText();

        if (!name.isBlank()) {

            clearInfo();

            for (Exam exam : Information.getExams()) {

                String hName = exam.getName().toUpperCase();
                name = name.toUpperCase();

                if (hName.contains(name)) {
                    loadExamInfo(exam);
                }
            }

            tableExams.getItems().addAll(listExams);
            startTable();

        } else if (key.getCode().toString().equals("BACK_SPACE")) {
            showAllExams();
        }

    }

    private void initiateCols() throws SQLException {

        examColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
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
    public void createNewExam() {
        try {
            setScreen(buttonAddExam, "AdminNewExamScene.fxml");
            AdminNewExamController adminMenuController = getFXML().getController();
            adminMenuController.textAdmin.setText(LoginMenuController.getEmployee_name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void setMainMenu() {
        setMainMenu(buttonMainMenu);
    }

    @FXML
    public void setLogout() {
        setLogout(buttonPower);
    }

}
