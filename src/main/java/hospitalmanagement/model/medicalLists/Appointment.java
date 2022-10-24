package hospitalmanagement.model.medicalLists;

import hospitalmanagement.utility.ExamUtil;
import hospitalmanagement.utility.SpecialitiesUtil;
import hospitalmanagement.model.people.Doctor;
import hospitalmanagement.model.people.Patient;

import java.time.LocalTime;

public class Appointment {

    private int id;

    private Hospital hospital;

    private Patient patient;

    private Doctor doctor;

    private float cost;

    private LocalTime localTime;

    private ExamUtil exam;

    private SpecialitiesUtil speciality;

    public Appointment(int id, Hospital hospital, Patient patient, Doctor doctor, float appointmentCost, LocalTime localTime, ExamUtil exam) {
        this.id = id;
        this.hospital = hospital;
        this.patient = patient;
        this.doctor = doctor;
        this.cost = appointmentCost;
        this.localTime = localTime;
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

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public ExamUtil getExam() {
        return exam;
    }

    public void setExam(ExamUtil exam) {
        this.exam = exam;
    }

    public SpecialitiesUtil getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialitiesUtil speciality) {
        this.speciality = speciality;
    }
}
