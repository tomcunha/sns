package hospitalmanagement.Utility;

public enum SpecialitiesUtil {
    ALLERGYANDIMMUNOLOGY ("Allergy And Immunology"),
    ANESTHESIOLOGY("Anesthesiology"),
    DERMATOLOGY("Dermatology"),
    DIAGNOSTICRADIOLOGY("Diagnostic Radiology"),
    EMERGENCYMEDICINE("Emergency Medicine"),
    FAMILYMEDICINE("Family Medicine"),
    INTERNALMEDICINE("Internal Medicine"),
    MEDICALGENETICS("Medical Genetics"),
    NEUROLOGY("Neurology"),
    NUCLEARMEDICINE("Nuclear Medicine"),
    OBSTETRICSANDGYNECOLOGY("Obstetrics And Gynecology"),
    OPHTHALMOLOGY("Ophthalmology"),
    PATHOLOGY("Pathology"),
    PEDIATRICS("Pediatrics"),
    PHYSICALMEDICINEANDREHABILITATION("Physical Medicine And Rehabilitation"),
    PREVENTIVEMEDICINE("Preventive Medicine"),
    PSYCHIATRY("Psychiatry"),
    RADIATIONONCOLOGY("Radiation Oncology"),
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
