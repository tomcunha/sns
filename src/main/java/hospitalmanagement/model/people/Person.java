package hospitalmanagement.model.people;

import hospitalmanagement.utility.SexUtil;
import hospitalmanagement.model.Contact;

import java.time.LocalDate;
import java.time.Period;

public class Person {


    private int personId;

    private String name;

    private LocalDate birthDate;

    private int age;

    private SexUtil sexUtil;

    private Contact contact;

    public Person(String name, LocalDate birthDate, SexUtil sexUtil, String address, String phoneNumber, String email, int personId) {
        this.name = name;
        this.setBirthDate(birthDate);
        this.sexUtil = sexUtil;
        this.contact = new Contact(address, phoneNumber, email);
        this.personId = personId;
    }

    public int getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        setAge();
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
