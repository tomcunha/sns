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

public class StaffNewAppointmentController extends StaffController {

    private Hospital selectedHospital = null;
    private Speciality selectedSpeciality = null;

    @FXML
    Button buttonMainMenu, buttonPower;
    @FXML
    Text textStaff;
    @FXML
    ComboBox hospitalDropdown, specialityDropdown, doctorDropdown;


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
        refreshSpecialities();
    }

    public void refreshSpecialities() {
        ObservableList<String> specialitiesHospital = FXCollections.observableArrayList();
        for (Doctor doctor : Information.getDoctors()) {
            String doctor_spe = doctor.getSpecialty().getName();
            if (doctor.getWorkingHospital().equals(selectedHospital) && !specialitiesHospital.contains(doctor_spe)) {
                try {
                    specialitiesHospital.add(doctor_spe);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        specialityDropdown.setItems(specialitiesHospital);
    }

    public void refreshDoctors() {
        ObservableList<String> doctorsSpecialityHospital = FXCollections.observableArrayList();
        for (Doctor doctor : Information.getDoctors()) {
            if (doctor.getWorkingHospital().equals(selectedHospital) && doctor.getSpecialty().equals(selectedSpeciality) && !doctorsSpecialityHospital.contains(doctor.getName())) {
                try {
                    doctorsSpecialityHospital.add(doctor.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        doctorDropdown.setItems(doctorsSpecialityHospital);
    }

    public void hospitalSelection() {
        selectedHospital = getHospital();
        specialityDropdown.setValue(null);
        refreshSpecialities();
        doctorDropdown.setDisable(true);
    }

    public void specialitySelection() {
        selectedSpeciality = getSpeciality();
        doctorDropdown.setValue(null);
        refreshDoctors();
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

    private Speciality getSpeciality() {
        for (Speciality speciality : Information.getSpecialities()) {
            if (speciality.getName().equals(specialityDropdown.getValue())) {
                return speciality;
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
