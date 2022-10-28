package hospitalmanagement.controller;

import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Exam;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.io.IOException;

import static hospitalmanagement.controller.AdminEditExamsController.setExamId;

public class AdminFindExamsController extends SceneController {

    @FXML
    TextField nameTextField;
    @FXML
    private Button buttonMainMenu;
    @FXML
    private Button buttonPower;
    @FXML
    private TableView<Exam> tableExams;
    @FXML
    private Button buttonAddExam;
    @FXML
    private TableColumn<Exam, String> examColumn;
    ObservableList<Exam> listExams = FXCollections.observableArrayList();

    public void setMainMenu() throws IOException {
        setScreen(buttonMainMenu, "AdminMenuScene.fxml");
    }

    public void setLogout() throws IOException {
        setScreen(buttonPower, "LoginScene.fxml");
    }

    public void setButtonSearch() {
        tableExams.getItems().clear();


        initColumn();
        loadData();


        tableExams.setVisible(true);

    }

    private void initColumn() {
        examColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Exam, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Exam, String> examStringCellDataFeatures) {
                return examStringCellDataFeatures.getValue().nameProperty();
            }
        });

    }


    public void loadData() {

        listExams.removeAll(listExams);
        listExams.addAll(Information.getExams());
        tableExams.getItems().setAll(listExams);



            ObservableList<Exam> filterList = FXCollections.observableArrayList();

            for (Exam examName : Information.getExams()) {
                if (examName.getName().toLowerCase().contains(nameTextField.getText().toLowerCase())) {
                    filterList.addAll(examName);
                }
                tableExams.getItems().setAll(filterList);
                if(listExams.size() != 0)
                    tableExams.setVisible(true);
                initColumn();
            }
        //}
    }


    public void setNewExam() throws IOException {
        setScreen(buttonAddExam, "AdminNewExamScene.fxml");

    }

    public void setMouseClicked() throws IOException {

        Integer examSelected = tableExams.getSelectionModel().getSelectedItems().get(0).getId();
        setExamId(examSelected);

        setScreen(buttonPower, "AdminEditExamScene.fxml");

        AdminEditExamsController adminEditExamsController = getFXML().getController();
        adminEditExamsController.setNameInput();

    }

}
