package hospitalmanagement.utility;
public enum SexUtil {
    FEMALE("Female"),
    MALE("Male"),
    OTHER("Other");
    private final String name;

    SexUtil(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}