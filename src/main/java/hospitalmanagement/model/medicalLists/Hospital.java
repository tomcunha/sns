package hospitalmanagement.model.medicalLists;

import hospitalmanagement.model.Contact;

public class Hospital {
    private String name;

    private Contact contact;

    private int id;

    public Hospital(String name, Contact contact, int id) {
        this.name = name;
        this.contact = contact;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
