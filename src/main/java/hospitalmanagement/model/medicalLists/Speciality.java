package hospitalmanagement.model.medicalLists;

import javafx.beans.property.SimpleStringProperty;

public class Speciality {

    private int id;
    private final SimpleStringProperty name;
    private int price;




    public Speciality(int id, String name, int price) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.price = price;
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

    @Override
    public String toString() {
        return String.valueOf(price);
    }
}
