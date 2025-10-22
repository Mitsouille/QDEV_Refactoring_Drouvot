package org.iut.refactoring;

import java.util.UUID;

public class Employe {

    private UUID id;
    private TypeEmploye type;
    private String nom;
    private double salaireDeBase;
    private int experience;
    private String equipe;

    public Employe(TypeEmploye type, String nom, double salaireDeBase, int experience, String equipe) {
        id = UUID.randomUUID();
        this.equipe = equipe;
        this.experience = experience;
        this.salaireDeBase = salaireDeBase;
        this.nom = nom;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public TypeEmploye getType() {
        return type;
    }

    public void setType(TypeEmploye type){
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public double getSalaireDeBase() {
        return salaireDeBase;
    }

    public int getExperience() {
        return experience;
    }

    public String getEquipe() {
        return equipe;
    }
}
