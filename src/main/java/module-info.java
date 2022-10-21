module hospitalmanagement.hospitalmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports hospitalmanagement;
    opens hospitalmanagement to javafx.fxml;
    exports hospitalmanagement.controller;
    opens hospitalmanagement.controller to javafx.fxml;
}