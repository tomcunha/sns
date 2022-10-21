package hospitalmanagement.utility;

public enum SpecialitiesUtil {
    ALLERGY_AND_IMMUNOLOGY(1,"Allergy And Immunology"),
    ANESTHESIOLOGY(2,"Anesthesiology"),
    DERMATOLOGY(3,"Dermatology"),
    DIAGNOSTIC_RADIOLOGY(4,"Diagnostic Radiology"),
    EMERGENCY_MEDICINE(5,"Emergency Medicine"),
    FAMILY_MEDICINE(6,"Family Medicine"),
    INTERNAL_MEDICINE(7,"Internal Medicine"),
    MEDICAL_GENETICS(8,"Medical Genetics"),
    NEUROLOGY(9,"Neurology"),
    NUCLEAR_MEDICINE(10,"Nuclear Medicine"),
    OBSTETRICS_AND_GYNECOLOGY(11,"Obstetrics And Gynecology"),
    OPHTHALMOLOGY(12,"Ophthalmology"),
    PATHOLOGY(13,"Pathology"),
    PEDIATRICS(14,"Pediatrics"),
    PHYSICAL_MEDICINE_AND_REHABILITATION(15,"Physical Medicine And Rehabilitation"),
    PREVENTIVE_MEDICINE(16,"Preventive Medicine"),
    PSYCHIATRY(17,"Psychiatry"),
    RADIATION_ONCOLOGY(18,"Radiation Oncology"),
    SURGERY(19,"Surgery"),
    UROLOGY(20,"Urology");

    private final int id;
    private final String name;

    SpecialitiesUtil(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


}
