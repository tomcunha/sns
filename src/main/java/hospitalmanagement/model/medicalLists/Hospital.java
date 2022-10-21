package hospitalmanagement.model.medicalLists;

import hospitalmanagement.model.Contact;

public class Hospital {
    private String name;

    private Contact contact;

    private int id;

    public Hospital(int id, String name, String address, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.contact = new Contact(address, phoneNumber, email);
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
