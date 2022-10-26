package hospitalmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        Database.connect();
        Information.updateAll();

        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("StaffFindPatientScene.fxml"));
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