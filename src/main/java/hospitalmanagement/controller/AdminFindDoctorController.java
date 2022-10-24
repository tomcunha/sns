package hospitalmanagement.controller;

import hospitalmanagement.model.people.Doctor;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminFindDoctorController extends SceneController{

    ObservableList <Doctor> listDoctors = FXCollections.observableArrayList();

    private List<Doctor> doctors = new ArrayList<>();


    @FXML
    private TableView tableDoctors;

    @FXML
    private TableColumn <Doctor, String> doctorLicense;

    @FXML
    private TableColumn <Doctor, String> doctorName;

    @FXML
    private TableColumn <Doctor, String> doctorHospital;

    @FXML
    private TableColumn <Doctor, String> doctorSpeciality;

    @FXML
    public void setButtonSearch()  throws IOException {
        initiateCols();
        loadData();
        tableDoctors.setVisible(true);
    }


    private void loadData(){
        listDoctors.removeAll(listDoctors);


        tableDoctors.getItems().addAll(listDoctors);

    }

    private void initiateCols(){
        doctorLicense.setCellValueFactory(new PropertyValueFactory<>(""));
        doctorName.setCellValueFactory(new PropertyValueFactory<>(""));
        doctorHospital.setCellValueFactory(new PropertyValueFactory<>(""));
        doctorSpeciality.setCellValueFactory(new PropertyValueFactory<>(""));
    }
}
