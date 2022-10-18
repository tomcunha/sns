package hospitalmanagement.model.people;

public class Insurance {

    private String name;

    private double discount;

    private long nr_policy;

    public Insurance(String name, double discount, long nr_policy) {
        this.name = name;
        this.discount = discount;
        this.nr_policy = nr_policy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public long getNr_policy() {
        return nr_policy;
    }

    public void setNr_policy(long nr_policy) {
        this.nr_policy = nr_policy;
    }
}
