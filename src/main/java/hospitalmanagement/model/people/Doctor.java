package hospitalmanagement.model.people;

import hospitalmanagement.model.medicalLists.Speciality;
import hospitalmanagement.utility.SexUtil;
import hospitalmanagement.utility.SpecialitiesUtil;
import hospitalmanagement.model.medicalLists.Hospital;

import java.time.LocalDate;

public class Doctor extends Employee {

    private String medicalLicense;

    private Speciality specialty;

    private Hospital workingHospital;


    public Doctor(String name, LocalDate birthDate, SexUtil sexUtil, String email, String phoneNumber, String address, int person_id, String username, String password, int type, int employee_id, String medicalLicense, Speciality specialty, Hospital workingHospital) {
        super(name, birthDate, sexUtil, email, phoneNumber, address, person_id, username, password, type, employee_id);
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

    public Speciality getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Speciality specialty) {
        this.specialty = specialty;
    }

    public Hospital getWorkingHospital() {
        return workingHospital;
    }

    public void setWorkingHospital(Hospital workingHospital) {
        this.workingHospital = workingHospital;
    }
}
