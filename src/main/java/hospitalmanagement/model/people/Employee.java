package hospitalmanagement.model.people;

import hospitalmanagement.utility.SexUtil;

import java.time.LocalDate;

public class Employee extends Person{

    private int employee_id;

    private  String username;

    private String password;

    private int type;

    public Employee(String name, LocalDate birthDate, SexUtil sexUtil, String email, String phoneNumber, String address, int person_id, String username, String password, int type, int employee_id) {
        super(name, birthDate, sexUtil, email, phoneNumber, address, person_id);
        this.username = username;
        this.password = password;
        this.type = type;
        this.employee_id = employee_id;
    }

    public int getEmployeeID() {
        return employee_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
