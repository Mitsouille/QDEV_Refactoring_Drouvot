package org.iut.refactoring.rapport;

import org.iut.refactoring.Employe;

import java.util.List;

public class RapportExperience implements RapportStrategy{
    @Override
    public void generer(List<Employe> employeList, String filtre) {
        for (Employe emp : employeList) {
            if (filtre == null || filtre.isEmpty() ||
                    emp.getEquipe().equals(filtre)) {
                String nom = emp.getNom();
                int exp = emp.getExperience();
                System.out.println(nom + ": " + exp + " années");
            }
        }
    }
}
