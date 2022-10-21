package hospitalmanagement.utility;

public enum SymptomsUtil {

    FEVER ("Fever/Headache/Vertigo/Nausea"),
    ABDOMEN_PAIN ("Abdomen Pain/Vomiting/Fever"),
    HEART_ATTACK ("Pain in left Shoulder/Chest with/wo Vertigo"),
    PALPITATION ("Palpitation"),
    INSOMNIA ("Insomnia"),
    BLACKOUT ("Unconsciousness/Blackout/Paralysis"),
    VOMITING ("Head-Trauma/Vomiting"),
    CONVULSION ("Convulsion"),
    FLATULENCE ("Flatulence/Gas in Abdomen"),
    HYPER_ACIDITY("Hyper-Acidity"),
    PREGNANT_WOMAN("Pregnant Woman"),
    STD("STD"),
    CHILDREN("Children/Below 12 Years"),
    LOW_BACK_PAIN("Low Back Pain"),
    PAIN_IN_JOINTS("Pain in Joints"),
    TRAUMA_IN_LIMBS("Trauma in Limbs with Swelling & Pain"),
    NOSE_BLEEDING("Nose Bleeding"),
    COMMON_COLD("Common Cold"),
    THROAT_PAIN("Throat Pain/Difficulty in deglutition"),
    EYE_PROBLEMS("Eye Problems"),
    SKIN_ITCHING("Skin Itching"),
    DISCOLORATION_SKIN("Discoloration of skin"),
    HAIR_FALL ("Hairfall/Dandruff in Scalp");
    private final String name;
    SymptomsUtil(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
