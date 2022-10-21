package hospitalmanagement.model.people;

import hospitalmanagement.utility.SexUtil;
import hospitalmanagement.utility.SpecialitiesUtil;
import hospitalmanagement.model.medicalLists.Hospital;

import java.time.LocalDate;

public class Doctor extends Employee {

    private String medical_id;

    private SpecialitiesUtil specialty;

    private Hospital workingHospital;


    public Doctor(String name, LocalDate birthDate, SexUtil sexUtil, String email, String phoneNumber, String address, String username, String password, int type, String medical_id, SpecialitiesUtil specialty, Hospital workingHospital) {
        super(name, birthDate, sexUtil, email, phoneNumber, address, username, password, type);
        this.medical_id = medical_id;
        this.specialty = specialty;
        this.workingHospital = workingHospital;
    }

    public String getMedical_id() {
        return medical_id;
    }

    public void setMedical_id(String medical_id) {
        this.medical_id = medical_id;
    }

    public SpecialitiesUtil getSpecialty() {
        return specialty;
    }

    public void setSpecialty(SpecialitiesUtil specialty) {
        this.specialty = specialty;
    }

    public Hospital getWorkingHospital() {
        return workingHospital;
    }

    public void setWorkingHospital(Hospital workingHospital) {
        this.workingHospital = workingHospital;
    }
}
