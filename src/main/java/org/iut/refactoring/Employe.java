package org.iut.refactoring;

import java.util.UUID;

public class Employe {

    private UUID id;
    private TypeEmploye type;
    private String nom;
    private double salaireDeBase;
    private int experience;
    private String equipe;
    private double salaire;

    public Employe(TypeEmploye type, String nom, double salaireDeBase, int experience, String equipe) {
        id = UUID.randomUUID();
        this.equipe = equipe;
        this.experience = experience;
        this.salaireDeBase = salaireDeBase;
        this.nom = nom;
        this.type = type;
        this.salaire = this.calculSalaire();
    }


    public double calculSalaire() {
        TypeEmploye type = this.getType();
        double salaireDeBase = this.getSalaireDeBase();
        int experience = this.getExperience();

        double salaireFinal = salaireDeBase;
        if (TypeEmploye.DEVELOPPER == type) {
            salaireFinal = salaireDeBase * 1.2;
            if (experience > 5) {
                salaireFinal = salaireFinal * 1.15;
            }
            if (experience > 10) {
                salaireFinal = salaireFinal * 1.05; // bonus
            }
        } else if (type == TypeEmploye.CHEF_PROJET) {
            salaireFinal = salaireDeBase * 1.5;
            if (experience > 3) {
                salaireFinal = salaireFinal * 1.1;
            }
            salaireFinal = salaireFinal + 5000; // bonus
        } else if (type == TypeEmploye.STAGIAIRE) {
            salaireFinal = salaireDeBase * 0.6;
            // Pas de bonus pour les stagiaires
        } else {
            salaireFinal = salaireDeBase;
        }
        return salaireFinal;
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

    public double getSalaire() {
        return salaire;
    }
}
