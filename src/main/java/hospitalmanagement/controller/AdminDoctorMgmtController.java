package hospitalmanagement.controller;

import hospitalmanagement.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdminDoctorMgmtController {
    @FXML
    TextField searchBar;
    @FXML
    Button buttonSearch;

    @FXML
    public void setSearchButton() {

        List<String> names = new ArrayList<>();
        List<String> medicalLicenses = new ArrayList<>();
        List<Integer> hospital_ids = new ArrayList<>();
        List<String> specialities = new ArrayList<>();


        ResultSet resultSet = Database.queryTable("SELECT * FROM Doctors JOIN Persons WHERE idDoctors = person_id");


        try {
            while (resultSet.next()) {

                medicalLicenses.add(resultSet.getString("medicalLicense"));
                names.add(resultSet.getString("name"));
                hospital_ids.add(resultSet.getInt("hospital_id"));
                specialities.add(resultSet.getString("speciality"));
            }

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }


        for (String name : names) {
            if (name.toLowerCase().contains(searchBar.getText().toLowerCase())){
                System.out.println(name);
            }

        }

    }
}
