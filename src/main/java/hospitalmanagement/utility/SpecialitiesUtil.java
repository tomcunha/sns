package hospitalmanagement.utility;

public enum SpecialitiesUtil {
    ALLERGY_AND_IMMUNOLOGY ("Allergy And Immunology"),
    ANESTHESIOLOGY("Anesthesiology"),
    DERMATOLOGY("Dermatology"),
    DIAGNOSTIC_RADIOLOGY("Diagnostic Radiology"),
    EMERGENCY_MEDICINE("Emergency Medicine"),
    FAMILY_MEDICINE("Family Medicine"),
    INTERNAL_MEDICINE("Internal Medicine"),
    MEDICAL_GENETICS("Medical Genetics"),
    NEUROLOGY("Neurology"),
    NUCLEAR_MEDICINE("Nuclear Medicine"),
    OBSTETRICS_AND_GYNECOLOGY("Obstetrics And Gynecology"),
    OPHTHALMOLOGY("Ophthalmology"),
    PATHOLOGY("Pathology"),
    PEDIATRICS("Pediatrics"),
    PHYSICAL_MEDICINE_AND_REHABILITATION("Physical Medicine And Rehabilitation"),
    PREVENTIVE_MEDICINE("Preventive Medicine"),
    PSYCHIATRY("Psychiatry"),
    RADIATION_ONCOLOGY("Radiation Oncology"),
    SURGERY("Surgery"),
    UROLOGY("Urology");


    private final String name;
    SpecialitiesUtil(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


}
