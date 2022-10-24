package hospitalmanagement.utility;

public class MedicineUtil {

    private final int id;
    private final String name;

    public MedicineUtil(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }
}
