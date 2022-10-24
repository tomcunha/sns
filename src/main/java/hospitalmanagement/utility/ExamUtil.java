package hospitalmanagement.utility;

public class ExamUtil {

    private final int id;
    private final String name;

    public ExamUtil(int id, String name) {
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
