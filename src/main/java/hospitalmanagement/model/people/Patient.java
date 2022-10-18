package hospitalmanagement.model.people;

import hospitalmanagement.utility.SexUtil;
import hospitalmanagement.model.medicalLists.Hospital;

import java.time.LocalDate;

public class Patient extends Person{

    private long cc_id;

    private boolean hasInsurance;

    private Insurance insurance;

    private Hospital favoriteHospital;

    private boolean hasDiseases;

    private String report;


    public Patient(String name, LocalDate birthDate, SexUtil sexUtil, String email, String phoneNumber, String address, long cc_id, Hospital favoriteHospital, boolean hasDiseases, boolean hasInsurance) {
        super(name, birthDate, sexUtil, email, phoneNumber, address);
        this.cc_id = cc_id;
        this.favoriteHospital = favoriteHospital;
        this.hasInsurance = hasInsurance;
        this.hasDiseases = hasDiseases;
    }

    public long getCc_id() {
        return cc_id;
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
