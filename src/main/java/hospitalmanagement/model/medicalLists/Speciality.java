package hospitalmanagement.model.medicalLists;

import javafx.beans.property.SimpleStringProperty;

public class Speciality {

    private int id;
    private final SimpleStringProperty name;



    public Speciality(int id, String name) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    public SimpleStringProperty nameProperty() {
        return name;
    }

}
