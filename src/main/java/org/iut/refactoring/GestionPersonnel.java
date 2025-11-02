package org.iut.refactoring;

import org.iut.refactoring.rapport.RapportFactory;
import org.iut.refactoring.rapport.RapportStrategy;
import org.iut.refactoring.rapport.TypeRapport;

import java.util.*;
import java.time.*;

public class GestionPersonnel {

    private ArrayList<Employe> employes = new ArrayList<>();
    private LogManager logManager = new LogManager();

    public void ajouteSalarie(Employe emp) {
        employes.add(emp);
        logManager.ajouterLog(LocalDateTime.now() + " - Ajout de l'employé: " + emp.getNom());
    }

    public void generationRapport(TypeRapport typeRapport, String filtre) {
        System.out.println("=== RAPPORT: " + typeRapport + " ===");
        RapportStrategy strategy = RapportFactory.create(typeRapport);
        strategy.generer(employes, filtre);
        logManager.ajouterLog(LocalDateTime.now() + " - Rapport généré: " + typeRapport);
    }

    public void avancementEmploye(UUID employeId, TypeEmploye newType) {

        for (Employe emp : employes) {
            if (emp.getId().equals(employeId)) {
                emp.setType(newType);
                emp.calculSalaire();

                logManager.ajouterLog(LocalDateTime.now() + " - Employé promu: " + emp.getNom());
                System.out.println("Employé promu avec succès!");
                return;
            }
        }
        System.out.println("ERREUR: impossible de trouver l'employé");
    }

    public ArrayList<Employe> getEmployesParDivision(String division) {
        ArrayList<Employe> resultat = new ArrayList<>();
        for (Employe emp : employes) {
            if (emp.getEquipe().equals(division)) {
                resultat.add(emp);
            }
        }
        return resultat;
    }

    public void printLogs() {
        logManager.afficherLogs();
    }

    public ArrayList<Employe> getEmployes() {
        return employes;
    }

    public LogManager getlogManagers() {
        return logManager;
    }
}



