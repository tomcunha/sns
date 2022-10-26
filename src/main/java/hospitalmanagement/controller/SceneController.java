package hospitalmanagement.controller;

import hospitalmanagement.Information;
import hospitalmanagement.StartApplication;
import hospitalmanagement.model.medicalLists.Hospital;
import hospitalmanagement.model.medicalLists.Speciality;
import hospitalmanagement.utility.SexUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public void setScreen(Button button, String fxml) throws IOException {
        fxmlLoader = new FXMLLoader(StartApplication.class.getResource(fxml));
        stage = ((Stage) button.getScene().getWindow());
        scene = new Scene(fxmlLoader.load(), 1280,720);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void initializeChoiceBoxSex(ChoiceBox sexDropdown){
        ObservableList<String> sexName = FXCollections.observableArrayList();
        for (SexUtil sex : SexUtil.values()){
            sexName.add(sex.toString());
        }
        sexDropdown.setItems(sexName);
    }

    public void initializeComboBoxHospital(ComboBox hospitalDropdown){
        ObservableList<String> hospitalName = FXCollections.observableArrayList();
        for (Hospital hospital : Information.getHospitals()) {
            hospitalName.add(hospital.getName());
        }
        hospitalDropdown.setItems(hospitalName);
    }

    public void initializeComboBoxSpeciality(ComboBox specialityDropdown){
        ObservableList<String> specialityName = FXCollections.observableArrayList();
        for (Speciality speciality : Information.getSpecialities()) {
            specialityName.add(speciality.getName());
        }
        specialityDropdown.setItems(specialityName);
    }



    public FXMLLoader getFXML(){
        return fxmlLoader;
    }
}
