package hospitalmanagement.model.people;

import hospitalmanagement.utility.SexUtil;
import hospitalmanagement.model.Contact;

import java.time.LocalDate;
import java.time.Period;

public class Person {


    private int person_id;

    private String name;

    private LocalDate birthDate;

    private int age;

    private SexUtil sexUtil;

    private Contact contact;

    public Person(String name, LocalDate birthDate, SexUtil sexUtil, String address, String phoneNumber, String email, int person_id) {
        this.name = name;
        this.birthDate=birthDate;
        this.sexUtil = sexUtil;
        this.person_id = person_id;
        this.contact = new Contact(address, phoneNumber, email);
    }

    public int getPersonID() {
        return person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge() {
        this.age = Period.between(this.getBirthDate(), LocalDate.now()).getYears();
    }

    public SexUtil getSex() {
        return sexUtil;
    }

    public void setSex(SexUtil sexUtil) {
        this.sexUtil = sexUtil;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
