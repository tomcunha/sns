package hospitalmanagement.model.people;

import hospitalmanagement.model.medicalLists.Disease;
import hospitalmanagement.utility.SexUtil;
import hospitalmanagement.model.medicalLists.Hospital;

import java.time.LocalDate;
import java.util.List;

public class Patient extends Person {

    private String patientCC;

    private Insurance insurance;

    private Hospital favoriteHospital;

    private List<Disease> diseases;


    public Patient(String name, LocalDate birthDate, SexUtil sexUtil, String address, String phoneNumber, String email, int personId, String patientCC, Insurance insurance, Hospital favoriteHospital, List<Disease> diseases) {
        super(name, birthDate, sexUtil, address, phoneNumber, email, personId);
        this.patientCC = patientCC;
        this.insurance = insurance;
        this.favoriteHospital = favoriteHospital;
        this.diseases = diseases;
    }

    public String getPatientCC() {
        return patientCC;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public Hospital getFavoriteHospital() {
        return favoriteHospital;
    }

    public void setFavoriteHospital(Hospital favoriteHospital) {
        this.favoriteHospital = favoriteHospital;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }
}
