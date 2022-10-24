package hospitalmanagement;

import hospitalmanagement.model.medicalLists.Appointment;
import hospitalmanagement.model.medicalLists.Disease;
import hospitalmanagement.model.medicalLists.Hospital;
import hospitalmanagement.model.people.Doctor;
import hospitalmanagement.model.people.Insurance;
import hospitalmanagement.model.people.Patient;
import hospitalmanagement.utility.ExamUtil;
import hospitalmanagement.utility.MedicineUtil;
import hospitalmanagement.utility.SexUtil;
import hospitalmanagement.utility.SpecialitiesUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Information {

    static ResultSet resultSet;

    public static List<Hospital> getHospitals() {
        resultSet = Database.queryTable("SELECT* FROM Hospitals");
        List<Hospital> hospitals = new ArrayList<>();
        try {
            while (resultSet.next()) {

                int hospital_id = resultSet.getInt("hospital_id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String email = resultSet.getString("email");

                hospitals.add(new Hospital(hospital_id, name, address, phoneNumber, email));


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hospitals;
    }

    public static List<Insurance> getInsurances() {
        resultSet = Database.queryTable("SELECT* FROM Insurances");
        List<Insurance> insurances = new ArrayList<>();
        try {
            while (resultSet.next()) {

                int insurance_id = resultSet.getInt("insurance_id");
                String name = resultSet.getString("name");
                float discountExam = resultSet.getFloat("dExam");
                float discountAppointment = resultSet.getFloat("dAppointment");

                insurances.add(new Insurance(insurance_id, name, discountExam, discountAppointment));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return insurances;
    }

    public static List<Disease> getDiseases() {

        resultSet = Database.queryTable("SELECT * FROM Diseases");
        List<Disease> diseases = new ArrayList<>();
        try {
            while ((resultSet.next())) {

                int disease_id = resultSet.getInt("disease_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");

                diseases.add(new Disease(disease_id, name, description));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return diseases;
    }

    public static List<ExamUtil> getExams() {
        resultSet = Database.queryTable("SELECT * FROM Exams");
        List<ExamUtil> exams = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int exam_id = resultSet.getInt("exam_id");
                String name = resultSet.getString("name");

                exams.add(new ExamUtil(exam_id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exams;

    }

    public static List<MedicineUtil> getMedicines() {
        resultSet = Database.queryTable("SELECT * FROM Medicines");
        List<MedicineUtil> medicines = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int medicine_id = resultSet.getInt("medicine_id");
                String name = resultSet.getString("name");

                medicines.add(new MedicineUtil(medicine_id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medicines;
    }

    public static List<Doctor> getDoctors() {
        String query = "SELECT name, birthDate,sex, address, phoneNumber, email, medicalLicense, speciality_id, hospital_id FROM Doctors JOIN Employees ON Doctors.employee_id = Employees.employee_id JOIN Persons ON Employees.person_id = Persons.person_id";
        resultSet = Database.queryTable(query);

        List<Doctor> doctors = new ArrayList<>();
        try {
            while (resultSet.next()) {

                String name = resultSet.getString("name");
                LocalDate birthdate = resultSet.getDate("birthdate").toLocalDate();
                String sex = resultSet.getString("sex");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String email = resultSet.getString("email");
                String medicalLicense = resultSet.getString("medicalLicense");
                int hospital_id = resultSet.getInt("hospital_id");
                int speciality_id = resultSet.getInt("speciality_id");

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

                SpecialitiesUtil selectedSpeciality = null;
                for (SpecialitiesUtil speciality : SpecialitiesUtil.values()) {
                    if (speciality_id == speciality.getId()) {
                        selectedSpeciality = speciality;
                        break;
                    }
                }

                doctors.add(new Doctor(name, birthdate, sexUtil, address, phoneNumber, email, medicalLicense, selectedSpeciality, selectedHospital));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return doctors;
    }

    public static List<Patient> getPatients() {
        ResultSet resultSet = Database.queryTable("SELECT name, birthDate,sex, phoneNumber, address, email, patientCC, hospital_id, insurance_id FROM Patients JOIN Persons ON Patients.person_id=Persons.person_id");
        List<Patient> patients = new ArrayList<>();
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

                patients.add(new Patient(name, birthdate, sexUtil, address, phoneNumber, email, patientCC, selectedHospital, selectedInsurance, diseases));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return patients;
    }

    public static List<Appointment> getAppointments() {

        List<Appointment> appointments = new ArrayList<>();


        return appointments;
    }
}
