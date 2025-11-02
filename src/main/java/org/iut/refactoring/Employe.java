package org.iut.refactoring;

import org.iut.refactoring.salaire.SalaireStrategy;
import org.iut.refactoring.salaire.SalaireStrategyFactory;

import java.util.UUID;

public class Employe {

    private UUID id;
    private TypeEmploye type;
    private String nom;
    private double salaireDeBase;
    private int experience;
    private String equipe;
    private double salaire;
    private SalaireStrategy salaireStrategy;

    public Employe(TypeEmploye type, String nom, double salaireDeBase, int experience, String equipe) {
        id = UUID.randomUUID();
        this.equipe = equipe;
        this.experience = experience;
        this.salaireDeBase = salaireDeBase;
        this.nom = nom;
        this.type = type;
        this.salaireStrategy = SalaireStrategyFactory.create(type);
        this.salaire = this.calculSalaire();
    }


    public double calculSalaire() {
        return salaireStrategy.calculerSalaire(this.salaireDeBase, this.experience);
    }


    public double calculBonusAnnuel() {
        return salaireStrategy.calculerBonus(this.salaireDeBase, this.experience);
    }

    public UUID getId() {
        return id;
    }

    public TypeEmploye getType() {
        return type;
    }

    public void setType(TypeEmploye type) {
        this.type = type;
        this.salaireStrategy = SalaireStrategyFactory.create(type);
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
