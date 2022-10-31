package hospitalmanagement.controller;

import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Appointment;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.SQLException;
import java.time.LocalDate;

public class StaffNewAppointment2Controller extends StaffController{

    String[] hours = {"9:00-9:30", "9:30-10:00", "10:00-10:30", "10:30-11:00", "11:00-11:30", "11:30-12:00", "12:00-12:30", "12:30-13:00", "13:00-13:30", "13:30-14:00", "14:00-14:30", "14:30-15:00", "15:00-15:30", "15:30-16:00", "16:00-16:30", "16:30-17:00", "17:00-17:30", "17:30-18:00"};
    ObservableList<ObservableList> listApp = FXCollections.observableArrayList();

    String medicalLicence = "151";

    LocalDate today = LocalDate.now();
    @FXML
    TableView timeSchedule;
    @FXML
    TableColumn time, monday, tuesday, wednesday, thursday, friday;

    public void populate(){
        loadDoctorSchedule();
        timeSchedule.getItems().addAll(listApp);
        timeSchedule.getSelectionModel().setCellSelectionEnabled(true);
        startTable();
    }

    public void loadDoctorSchedule() {
        for (int i = 0; i < hours.length; i++) {
            ObservableList<String> row = FXCollections.observableArrayList();
            row.add(hours[i]);
            today = LocalDate.now();
            for(int j = 0;j<5;j++){
                boolean flag = false;
                for(Appointment appointment: Information.getAppointments()){
                    if(appointment.getDoctor().getMedicalLicense().equals(medicalLicence) && appointment.getDate().isEqual(today) && appointment.getSlot().equals(hours[i])){
                        row.add(appointment.getPatient().getName());
                        flag = true;
                    }
                }
                today = today.plusDays(1);
                if(!flag){row.add("FREE SLOT");}
            }
            listApp.add(row);
        }
    }

    public void startTable() {
        try {
            initiateCols();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initiateCols() throws SQLException {

        time.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(0).toString());
            }
        });

        monday.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(1).toString());
            }
        });

        tuesday.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(2).toString());
            }
        });

        wednesday.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(3).toString());
            }
        });
        thursday.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(2).toString());
            }
        });
        friday.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(2).toString());
            }
        });
    }


}
