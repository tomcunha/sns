package hospitalmanagement.model.people;

import hospitalmanagement.Utility.SpecialitiesUtil;
import hospitalmanagement.model.medicalLists.Hospital;

public class Doctor {

    private long medical_id;

    private SpecialitiesUtil specialty;

    private Hospital workingHospital;

    public Doctor(long medical_id, SpecialitiesUtil specialty, Hospital workingHospital) {
        this.medical_id = medical_id;
        this.specialty = specialty;
        this.workingHospital = workingHospital;
    }

    public long getMedical_id() {
        return medical_id;
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
