package hospitalmanagement.model.people;

import hospitalmanagement.utility.SexUtil;
import hospitalmanagement.model.medicalLists.Hospital;

import java.time.LocalDate;

public class Patient extends Person{

    private String patientCC;

    private boolean hasInsurance;

    private Insurance insurance;

    private Hospital favoriteHospital;

    private boolean hasDiseases;

    private String report;


    public Patient(String name, LocalDate birthDate, SexUtil sexUtil, String address, String phoneNumber, String email, String patientCC, Hospital favoriteHospital, boolean hasDiseases, boolean hasInsurance) {
        super(name, birthDate, sexUtil, address, phoneNumber, email);
        this.patientCC = patientCC;
        this.favoriteHospital = favoriteHospital;
        this.hasInsurance = hasInsurance;
        this.hasDiseases = hasDiseases;
    }

    public String getPatientCC() {
        return patientCC;
    }

    public boolean isHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
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

    public boolean isHasDiseases() {
        return hasDiseases;
    }

    public void setHasDiseases(boolean hasDiseases) {
        this.hasDiseases = hasDiseases;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

}
