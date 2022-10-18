module hospitalmanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens hospitalmanagement to javafx.fxml;
    exports hospitalmanagement;

}