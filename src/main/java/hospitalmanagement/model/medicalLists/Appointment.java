package hospitalmanagement.model.medicalLists;

import hospitalmanagement.model.people.Doctor;
import hospitalmanagement.model.people.Patient;
import hospitalmanagement.utility.MedicineUtil;
import hospitalmanagement.utility.SymptomsUtil;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Appointment {

    private final int id;

    private Hospital hospital;

    private Patient patient;

    private Doctor doctor;

    private float cost;

    private LocalDate date;

    private String slot;

    private Exam exam;

    private List<Disease> diseases;
    private List<SymptomsUtil> symptoms;
    private List<MedicineUtil> medicines;

    public Appointment(int id, Hospital hospital, Patient patient, Doctor doctor, float cost, LocalDate date, String slot, Exam exam, List<Disease> diseases, List<SymptomsUtil> symptoms, List<MedicineUtil> medicines) {
        this.id = id;
        this.hospital = hospital;
        this.patient = patient;
        this.doctor = doctor;
        this.cost = cost;
        this.date = date;
        this.slot = slot;
        this.exam = exam;
        this.diseases = diseases;
        this.symptoms = symptoms;
        this.medicines = medicines;
    }

    public int getId() {
        return id;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    public List<SymptomsUtil> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<SymptomsUtil> symptoms) {
        this.symptoms = symptoms;
    }

    public List<MedicineUtil> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<MedicineUtil> medicines) {
        this.medicines = medicines;
    }

    public String getSlot() {return slot;}

    public void setSlot(String slot) {this.slot = slot;}
}