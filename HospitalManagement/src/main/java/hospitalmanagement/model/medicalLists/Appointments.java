package hospitalmanagement.model.medicalLists;

import hospitalmanagement.utility.ExamUtil;
import hospitalmanagement.utility.SpecialitiesUtil;
import hospitalmanagement.model.people.Doctor;
import hospitalmanagement.model.people.Patient;

import java.time.LocalTime;

public class Appointments {

    private int id;

    private Hospital hospital;

    private Patient patient;

    private Doctor doctor;



    private double appointmentCost;

    private LocalTime localTime;

    private char type;

    private ExamUtil exam;

    private SpecialitiesUtil speciality;

    public Appointments(int id, Hospital hospital, Patient patient, Doctor doctor, double appointmentCost, LocalTime localTime, char type) {
        this.id = id;
        this.hospital = hospital;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentCost = appointmentCost;
        this.localTime = localTime;
        this.type = type;
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

    public double getAppointmentCost() {
        return appointmentCost;
    }

    public void setAppointmentCost(double appointmentCost) {
        this.appointmentCost = appointmentCost;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
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
