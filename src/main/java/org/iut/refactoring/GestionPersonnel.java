package org.iut.refactoring;

import java.util.*;
import java.time.*;

public class GestionPersonnel {

    public ArrayList<Employe> employes = new ArrayList<>();
    public ArrayList<String> logs = new ArrayList<>();

    public void ajouteSalarie(Employe emp) {
        employes.add(emp);
        logs.add(LocalDateTime.now() + " - Ajout de l'employé: " + emp.getNom());
    }

    public void generationRapport(String typeRapport, String filtre) {
        System.out.println("=== RAPPORT: " + typeRapport + " ===");

        if (typeRapport.equals("SALAIRE")) {
            for (Employe emp: employes) {
                if (filtre == null || filtre.isEmpty() ||
                        emp.getEquipe().equals(filtre)) {
                    String nom = emp.getNom();
                    double salaire = emp.calculSalaire();
                    System.out.println(nom + ": " + salaire + " €");
                }
            }
        } else if (typeRapport.equals("EXPERIENCE")) {
            for (Employe emp : employes) {
                if (filtre == null || filtre.isEmpty() ||
                        emp.getEquipe().equals(filtre)) {
                    String nom = emp.getNom();
                    int exp = emp.getExperience();
                    System.out.println(nom + ": " + exp + " années");
                }
            }
        } else if (typeRapport.equals("DIVISION")) {
            HashMap<String, Integer> compteurDivisions = new HashMap<>();
            for (Employe emp : employes) {
                String div = emp.getEquipe();
                compteurDivisions.put(div, compteurDivisions.getOrDefault(div, 0) + 1);
            }
            for (Map.Entry<String, Integer> entry : compteurDivisions.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " employés");
            }
        }
        logs.add(LocalDateTime.now() + " - Rapport généré: " + typeRapport);
    }

    public void avancementEmploye(UUID employeId, TypeEmploye newType) {

        for (Employe emp : employes) {
            if (emp.getId().equals(employeId)) {
                emp.setType(newType);
                emp.calculSalaire();

                logs.add(LocalDateTime.now() + " - Employé promu: " + emp.getNom());
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
        System.out.println("=== LOGS ===");
        for (String log : logs) {
            System.out.println(log);
        }
    }
}



