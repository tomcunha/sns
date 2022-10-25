package hospitalmanagement.utility;

public enum SymptomsUtil {

    FEVER(1, "Fever/Headache/Vertigo/Nausea"),
    ABDOMEN_PAIN(2, "Abdomen Pain/Vomiting/Fever"),
    HEART_ATTACK(3, "Pain in left Shoulder/Chest with/wo Vertigo"),
    PALPITATION(4, "Palpitation"),
    INSOMNIA(5, "Insomnia"),
    BLACKOUT(6, "Unconsciousness/Blackout/Paralysis"),
    VOMITING(7, "Head-Trauma/Vomiting"),
    CONVULSION(8, "Convulsion"),
    FLATULENCE(9, "Flatulence/Gas in Abdomen"),
    HYPER_ACIDITY(10, "Hyper-Acidity"),
    PREGNANT_WOMAN(11, "Pregnant Woman"),
    STD(12, "STD"),
    CHILDREN(13, "Children/Below 12 Years"),
    LOW_BACK_PAIN(14, "Low Back Pain"),
    PAIN_IN_JOINTS(15, "Pain in Joints"),
    TRAUMA_IN_LIMBS(16, "Trauma in Limbs with Swelling & Pain"),
    NOSE_BLEEDING(17, "Nose Bleeding"),
    COMMON_COLD(18, "Common Cold"),
    THROAT_PAIN(19, "Throat Pain/Difficulty in deglutition"),
    EYE_PROBLEMS(20, "Eye Problems"),
    SKIN_ITCHING(21, "Skin Itching"),
    DISCOLORATION_SKIN(22, "Discoloration of skin"),
    HAIR_FALL(23, "Hairfall/Dandruff in Scalp");
    private final String name;
    private final int id;

    SymptomsUtil(int id, String name) {
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
