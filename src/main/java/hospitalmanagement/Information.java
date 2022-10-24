package hospitalmanagement;

import hospitalmanagement.model.medicalLists.Disease;
import hospitalmanagement.model.medicalLists.Hospital;
import hospitalmanagement.model.people.Doctor;
import hospitalmanagement.model.people.Insurance;
import hospitalmanagement.model.people.Patient;
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

    public static List<Insurance> getInsurances(){
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

                doctors.add(new Doctor(name, birthdate, sexUtil, email, phoneNumber, address, "", "" , 2 ,medicalLicense, selectedSpeciality, selectedHospital));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return doctors;
    }

    public static List<Doctor> getDoctorsFiltered(String nameToSearch) {
        String query = "SELECT name, medicalLicense, speciality_id, hospital_id FROM Doctors JOIN Employees ON Doctors.employee_id = Employees.employee_id JOIN Persons ON Employees.person_id = Persons.person_id WHERE Persons.name LIKE '%"+nameToSearch+"%'";
        resultSet = Database.queryTable(query);

        List<Doctor> doctors = new ArrayList<>();
        try {
            while (resultSet.next()) {

                String name = resultSet.getString("name");
                String medicalLicense = resultSet.getString("medicalLicense");
                int hospital_id = resultSet.getInt("hospital_id");
                int speciality_id = resultSet.getInt("speciality_id");


                List<Hospital> hospitals = getHospitals();
                Hospital selectedHospital = null;
                for (Hospital hospital : hospitals) {
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

                doctors.add(new Doctor(name, null, null, "", "", "", "", "" , 2 ,medicalLicense, selectedSpeciality, selectedHospital));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return doctors;
    }

    public static List<Patient> getPatients() {
        ResultSet resultSet = Database.queryTable("SELECT * FROM Patients");
        List<Patient> patients = new ArrayList<>();
        try {
            while (resultSet.next()) {

                String name = resultSet.getString("name");
                LocalDate birthdate = resultSet.getDate("birthdate").toLocalDate();
                String sex = resultSet.getString("sex");
                String cellphone = resultSet.getString("cellphone");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                String patientCC = resultSet.getString("patientCC");
                int favouriteHospital_id = resultSet.getInt("favourite_hospital_id");
                int insurance_id=resultSet.getInt("insurance_id");

                Hospital selectedHospital = null;
                for (Hospital hospital : getHospitals()) {
                    if (favouriteHospital_id == hospital.getId()) {
                        selectedHospital = hospital;
                        break;
                    }
                }





            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return patients;
    }
}
