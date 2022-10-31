package hospitalmanagement.controller;

import hospitalmanagement.Information;
import hospitalmanagement.model.medicalLists.Hospital;
import hospitalmanagement.model.medicalLists.Speciality;
import hospitalmanagement.model.people.Doctor;
import hospitalmanagement.model.people.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class StaffNewExamController extends StaffController {
    private Hospital selectedHospital = null;
    private Speciality selectedExam = null;

    @FXML
    Button buttonMainMenu, buttonPower;
    @FXML
    Text textStaff;
    @FXML
    ComboBox hospitalDropdown, examDropdown, doctorDropdown;


    public void initializeComboBox() {
        initializeComboBoxHospital(hospitalDropdown);
        String cc = StaffFindPatientController.getPatientCC();
        for (Patient patient : Information.getPatients()) {
            if (patient.getPatientCC().equals(cc)) {
                selectedHospital = patient.getFavoriteHospital();
                hospitalDropdown.setValue(selectedHospital.getName());
                break;
            }
        }
        initializeComboBoxExam(examDropdown);
    }

    public void refreshDoctors() {
        ObservableList<String> doctorsHospital = FXCollections.observableArrayList();
        for (Doctor doctor : Information.getDoctors()) {
            if (doctor.getWorkingHospital().equals(selectedHospital) && !doctorsHospital.contains(doctor.getName())) {
                try {
                    doctorsHospital.add(doctor.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        doctorDropdown.setItems(doctorsHospital);
    }

    public void hospitalSelection() {
        selectedHospital = getHospital();
        doctorDropdown.setValue(null);
        refreshDoctors();
    }

    public void examSelection() {
        doctorDropdown.setDisable(false);
    }

    private Hospital getHospital() {
        for (Hospital hospital : Information.getHospitals()) {
            if (hospital.getName().equals(hospitalDropdown.getValue())) {
                return hospital;
            }
        }
        return null;
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
