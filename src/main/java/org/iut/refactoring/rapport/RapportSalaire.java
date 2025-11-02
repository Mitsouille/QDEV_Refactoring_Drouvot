package org.iut.refactoring.rapport;

import org.iut.refactoring.Employe;

import java.util.List;

public class RapportSalaire implements RapportStrategy{
    @Override
    public void generer(List<Employe> employeList, String filtre) {
        for (Employe emp: employeList) {
            if (filtre == null || filtre.isEmpty() ||
                    emp.getEquipe().equals(filtre)) {
                String nom = emp.getNom();
                double salaire = emp.calculSalaire();
                System.out.println(nom + ": " + salaire + " €");
            }
        }
    }
}
