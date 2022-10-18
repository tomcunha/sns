package hospitalmanagement.model.medicalLists;

import hospitalmanagement.utility.SpecialitiesUtil;

public class Exam {

    private int id;
    private String nome;
    private SpecialitiesUtil speciality;

    public Exam(int id, String nome, SpecialitiesUtil speciality) {
        this.id = id;
        this.nome = nome;
        this.speciality = speciality;
    }

    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public SpecialitiesUtil getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialitiesUtil speciality) {
        this.speciality = speciality;
    }
}
