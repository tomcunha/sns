package hospitalmanagement.model.people;

import hospitalmanagement.utility.SexUtil;
import hospitalmanagement.utility.SpecialitiesUtil;
import hospitalmanagement.model.medicalLists.Hospital;

import java.time.LocalDate;

public class Doctor extends Employee {

    private String medicalLicense;

    private SpecialitiesUtil specialty;

    private Hospital workingHospital;


    public Doctor(String name, LocalDate birthDate, SexUtil sexUtil, String email, String phoneNumber, String address, int personId, String username, String password, int type, int employeeId, String medicalLicense, SpecialitiesUtil specialty, Hospital workingHospital) {
        super(name, birthDate, sexUtil, email, phoneNumber, address, personId, username, password, type, employeeId);
        this.medicalLicense = medicalLicense;
        this.specialty = specialty;
        this.workingHospital = workingHospital;
    }

    public String getMedicalLicense() {
        return medicalLicense;
    }

    public void setMedicalLicense(String medicalLicense) {
        this.medicalLicense = medicalLicense;
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
