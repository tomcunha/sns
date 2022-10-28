package hospitalmanagement;

import hospitalmanagement.model.medicalLists.*;
import hospitalmanagement.model.people.*;
import hospitalmanagement.utility.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Information {
    private static List<Hospital> hospitals = new ArrayList<>();
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Disease> diseases = new ArrayList<>();
    private static List<Insurance> insurances = new ArrayList<>();
    private static List<Patient> patients = new ArrayList<>();
    private static List<Exam> exams = new ArrayList<>();
    private static List<MedicineUtil> medicines = new ArrayList<>();
    private static List<Speciality> specialities = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();

    public static void updateAll() {
        updateHospitals();
        updateSpecialities();
        updateInsurances();
        updateDiseases();
        updateExams();
        updateMedicines();
        updateDoctors();
        updatePatients();
        updateAppointments();
    }

    public static void updateHospitals() {
        ResultSet resultSet = Database.queryTable("SELECT* FROM Hospitals");
        List<Hospital> ho = new ArrayList<>();
        try {
            while (resultSet.next()) {

                int hospital_id = resultSet.getInt("hospital_id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String email = resultSet.getString("email");

                ho.add(new Hospital(hospital_id, name, address, phoneNumber, email));


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        hospitals.clear();
        hospitals.addAll(ho);
    }

    public static void updateSpecialities() {
        ResultSet resultSet = Database.queryTable("SELECT* FROM Specialities");
        List<Speciality> spe = new ArrayList<>();
        try {
            while (resultSet.next()) {

                int speciality_id = resultSet.getInt("speciality_id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");

                spe.add(new Speciality(speciality_id, name, price));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        specialities.clear();
        specialities.addAll(spe);

    }

    public static void updateInsurances() {
        ResultSet resultSet = Database.queryTable("SELECT* FROM Insurances");
        List<Insurance> insu = new ArrayList<>();
        try {
            while (resultSet.next()) {

                int insurance_id = resultSet.getInt("insurance_id");
                String name = resultSet.getString("name");
                float discountExam = resultSet.getFloat("dExam");
                float discountAppointment = resultSet.getFloat("dAppointment");

                insu.add(new Insurance(insurance_id, name, discountExam, discountAppointment));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        insurances.clear();
        insurances.addAll(insu);
    }

    public static void updateDiseases() {

        ResultSet resultSet = Database.queryTable("SELECT * FROM Diseases");
        List<Disease> dis = new ArrayList<>();
        try {
            while ((resultSet.next())) {

                int disease_id = resultSet.getInt("disease_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");

                dis.add(new Disease(disease_id, name, description));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        diseases.clear();
        diseases.addAll(dis);
    }

    public static void updateExams() {
        ResultSet resultSet = Database.queryTable("SELECT * FROM Exams");
        List<Exam> ex = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int exam_id = resultSet.getInt("exam_id");
                String name = resultSet.getString("name");

                ex.add(new Exam(exam_id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        exams.clear();
        exams.addAll(ex);

    }

    public static void updateMedicines() {
        ResultSet resultSet = Database.queryTable("SELECT * FROM Medicines");
        List<MedicineUtil> med = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int medicine_id = resultSet.getInt("medicine_id");
                String name = resultSet.getString("name");

                med.add(new MedicineUtil(medicine_id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        medicines.clear();
        medicines.addAll(med);
    }

    public static void updateDoctors() {
        String query = "SELECT name, birthDate, sex, email, phoneNumber, address, medicalLicense, speciality_id, hospital_id, Employees.employee_id, user, password, Persons.person_id FROM Doctors JOIN Employees ON Doctors.employee_id = Employees.employee_id JOIN Persons ON Employees.person_id = Persons.person_id";
        ResultSet rs = Database.queryTable(query);

        List<Doctor> doct = new ArrayList<>();
        try {
            while (rs.next()) {
                String name = rs.getString("name");
                LocalDate birthdate = rs.getDate("birthdate").toLocalDate();
                String sex = rs.getString("sex");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                String address = rs.getString("address");
                String medicalLicense = rs.getString("medicalLicense");
                int hospital_id = rs.getInt("hospital_id");
                int speciality_id = rs.getInt("speciality_id");
                int person_id = rs.getInt("person_id");
                int employee_id = rs.getInt("employee_id");
                String username = rs.getString("user");
                String password = rs.getString("password");

                SexUtil sexUtil;
                switch (sex) {
                    case "M" -> sexUtil = SexUtil.MALE;
                    case "F" -> sexUtil = SexUtil.FEMALE;
                    default -> sexUtil = SexUtil.OTHER;
                }

                Hospital selectedHospital = null;
                for (Hospital hospital : getHospitals()) {
                    if (hospital_id == hospital.getId()) {
                        selectedHospital = hospital;
                        break;
                    }
                }

                Speciality selectedSpeciality = null;
                for (Speciality speciality : getSpecialities()) {
                    if (speciality_id == speciality.getId()) {
                        selectedSpeciality = speciality;
                        break;
                    }
                }

                doct.add(new Doctor(name, birthdate, sexUtil, email, phoneNumber, address, person_id, username, password, 2, employee_id, medicalLicense, selectedSpeciality, selectedHospital));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        doctors.clear();
        doctors.addAll(doct);
    }

    public static void updatePatients() {
        ResultSet resultSet = Database.queryTable("SELECT name, birthDate,sex, phoneNumber, address, email, patientCC, hospital_id, insurance_id, Persons.person_id FROM Patients JOIN Persons ON Patients.person_id=Persons.person_id");
        List<Patient> pat = new ArrayList<>();
        try {
            while (resultSet.next()) {

                String name = resultSet.getString("name");
                LocalDate birthdate = resultSet.getDate("birthdate").toLocalDate();
                String sex = resultSet.getString("sex");
                String phoneNumber = resultSet.getString("phoneNumber");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                String patientCC = resultSet.getString("patientCC");
                int favouriteHospital_id = resultSet.getInt("hospital_id");
                int insurance_id = resultSet.getInt("insurance_id");
                int personId = resultSet.getInt("person_id");

                SexUtil sexUtil;
                switch (sex) {
                    case "M" -> sexUtil = SexUtil.MALE;
                    case "F" -> sexUtil = SexUtil.FEMALE;
                    default -> sexUtil = SexUtil.OTHER;
                }

                Hospital selectedHospital = null;
                for (Hospital hospital : getHospitals()) {
                    if (favouriteHospital_id == hospital.getId()) {
                        selectedHospital = hospital;
                        break;
                    }
                }

                Insurance selectedInsurance = null;
                for (Insurance insurance : getInsurances()) {
                    if (insurance_id == insurance.getId()) {
                        selectedInsurance = insurance;
                        break;
                    }
                }

                List<Disease> diseases = new ArrayList<>();
                ResultSet rs = Database.queryTable("SELECT Patients.patientCC, disease_id FROM Patients JOIN Diagnostics ON Patients.patientCC = Diagnostics.patientCC");
                while (rs.next()) {
                    if (rs.getString("patientCC").equals(patientCC)) {
                        for (Disease disease : getDiseases()) {
                            if (rs.getInt("disease_id") == disease.getId()) {
                                diseases.add(disease);
                            }
                        }
                    }
                }


                pat.add(new Patient(name, birthdate, sexUtil, address, phoneNumber, email, personId, patientCC, selectedInsurance, selectedHospital, diseases));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        patients.clear();
        patients.addAll(pat);
    }

    public static void updateAppointments() {

        ResultSet resultSet = Database.queryTable("SELECT * FROM Appointments");
        List<Appointment> appoints = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int appointment_id = resultSet.getInt("appointment_id");
                float cost = resultSet.getFloat("cost");
                Date date = resultSet.getDate("date");
                Time time = resultSet.getTime("time");
                date.setTime(date.getTime() + time.getTime()+3600000);
                int hospital_id = resultSet.getInt("hospital_id");
                int exam_id = resultSet.getInt("exam_id");
                String patientCC = resultSet.getString("patientCC");
                String medicalLicense = resultSet.getString("medicalLicense");

                Hospital selectedHospital = null;
                for (Hospital hospital : getHospitals()) {
                    if (hospital_id == hospital.getId()) {
                        selectedHospital = hospital;
                        break;
                    }
                }

                Patient selectedPatient = null;
                for (Patient patient : getPatients()) {
                    if (patientCC.equals(patient.getPatientCC())) {
                        selectedPatient = patient;
                        break;
                    }
                }

                Doctor selectedDoctor = null;
                for (Doctor doctor : getDoctors()) {
                    if (medicalLicense.equals(doctor.getMedicalLicense())) {
                        selectedDoctor = doctor;
                        break;
                    }
                }

                Exam selectedExam = null;
                for (Exam exam : getExams()) {
                    if (exam_id == exam.getId()) {
                        selectedExam = exam;
                    }
                }

                List<Disease> diseases = new ArrayList<>();
                ResultSet rs = Database.queryTable("SELECT * FROM App_Diseases");
                while (rs.next()) {
                    int app_id = rs.getInt("appointment_id");
                    int disease_id = rs.getInt("disease_id");

                    if (app_id == appointment_id) {
                        for (Disease disease : getDiseases()) {
                            if (disease_id == disease.getId()) {
                                diseases.add(disease);
                            }
                        }
                    }
                }

                List<SymptomsUtil> symptoms = new ArrayList<>();
                rs = Database.queryTable("SELECT * FROM App_Symptoms");
                while (rs.next()) {
                    int app_id = rs.getInt("appointment_id");
                    int symptoms_id = rs.getInt("symptom_id");

                    if (app_id == appointment_id) {
                        for (SymptomsUtil symptom : SymptomsUtil.values()) {
                            if (symptoms_id == symptom.getId()) {
                                symptoms.add(symptom);
                            }
                        }
                    }
                }

                List<MedicineUtil> medicines = new ArrayList<>();
                rs = Database.queryTable("SELECT * FROM App_Medicines");
                while (rs.next()) {
                    int app_id = rs.getInt("appointment_id");
                    int medicine_id = rs.getInt("medicine_id");

                    if (app_id == appointment_id) {
                        for (MedicineUtil medicine : getMedicines()) {
                            if (medicine_id == medicine.getId()) {
                                medicines.add(medicine);
                            }
                        }
                    }
                }

                appoints.add(new Appointment(appointment_id, selectedHospital, selectedPatient, selectedDoctor, cost, date, selectedExam, diseases, symptoms, medicines));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        appointments.clear();
        appointments.addAll(appoints);
    }

    public static List<Hospital> getHospitals() {
        return hospitals;
    }

    public static List<Doctor> getDoctors() {
        return doctors;
    }

    public static List<Disease> getDiseases() {
        return diseases;
    }

    public static List<Insurance> getInsurances() {
        return insurances;
    }

    public static List<Patient> getPatients() {
        return patients;
    }

    public static List<Exam> getExams() {
        return exams;
    }

    public static List<MedicineUtil> getMedicines() {
        return medicines;
    }

    public static List<Speciality> getSpecialities() {
        return specialities;
    }

    public static List<Appointment> getAppointments() {return appointments;};
}
