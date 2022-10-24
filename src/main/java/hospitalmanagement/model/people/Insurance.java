package hospitalmanagement.model.people;

public class Insurance {
    private int id;
    private String name;

    private float discountExam;
    private float discountAppointment;

    public Insurance(int id, String name, float discountExam, float discountAppointment) {
        this.id = id;
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

    public float getDiscountExam() {
        return discountExam;
    }

    public void setDiscountExam(float discountExam) {
        this.discountExam = discountExam;
    }

    public float getDiscountAppointment() {
        return discountAppointment;
    }

    public void setDiscountAppointment(float discountAppointment) {
        this.discountAppointment = discountAppointment;
    }
}