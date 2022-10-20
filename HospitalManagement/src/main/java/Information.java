import hospitalmanagement.Database;
import hospitalmanagement.model.people.Doctor;
import hospitalmanagement.model.people.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Information {

    public static List<Doctor> getDoctors(){
        Database.connect();
        ResultSet resultSet = Database.queryTable("SELECT * FROM Medics");
        List<Doctor> doctors = new ArrayList<>();
        try{
            while (resultSet.next()){

                String medicalLicense=resultSet.getString("medicalLicense");
                String name = resultSet.getString("name");
                LocalDate birthdate = resultSet.getDate("birthdate").toLocalDate();
                String sex = resultSet.getString("sex");
                String cellphone = resultSet.getString("cellphone");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                int hospital_id =resultSet.getInt("hospital_id");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return doctors;
    }

    public static List<Patient> getPatients(){
        Database.connect();
        ResultSet resultSet = Database.queryTable("SELECT * FROM Patients");
        List<Patient> patients = new ArrayList<>();
        try{
            while (resultSet.next()){

                String patientCC = resultSet.getString("patientCC");
                String name = resultSet.getString("name");
                LocalDate birthdate =resultSet.getDate("birthdate").toLocalDate();
                String sex = resultSet.getString("sex");
                String cellphone =resultSet.getString("cellphone");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                int favouriteHospital_id =resultSet.getInt("favourite_hospital_id");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return patients;
    }


}
