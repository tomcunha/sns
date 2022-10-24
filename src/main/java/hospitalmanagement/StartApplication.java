package hospitalmanagement;

import hospitalmanagement.model.medicalLists.Hospital;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Database.connect();
        List<Hospital> hospitals= Information.getHospitals();

        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("AdminEditDoctorProfileScene.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("HealthCare System");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}