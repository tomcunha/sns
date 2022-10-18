module hospitalmanagement.hospitalmanagement {
    requires javafx.controls;
    requires javafx.fxml;


    exports hospitalmanagement;
    opens hospitalmanagement to javafx.fxml;
}