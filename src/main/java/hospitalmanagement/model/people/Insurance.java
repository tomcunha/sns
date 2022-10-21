package hospitalmanagement.model.people;

public class Insurance {
    private int id;

    private String name;

    private double discountExam;
    private double discountAppointment;

    public Insurance(String name, double discountExam, double discountAppointment) {
        this.name = name;
        this.discountExam = discountExam;
        this.discountAppointment = discountAppointment;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscountExam() {
        return discountExam;
    }

    public void setDiscountExam(double discountExam) {
        this.discountExam = discountExam;
    }

    public double getDiscountAppointment() {
        return discountAppointment;
    }

    public void setDiscountAppointment(double discountAppointment) {
        this.discountAppointment = discountAppointment;
    }
}