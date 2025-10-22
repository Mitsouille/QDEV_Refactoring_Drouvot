package org.iut.refactoring;

import java.util.*;
import java.time.*;

public class GestionPersonnel {

    public ArrayList<Employe> employes = new ArrayList<>();
    public HashMap<UUID, Double> salairesEmployes = new HashMap<>();
    public ArrayList<String> logs = new ArrayList<>();

    public void ajouteSalarie(Employe emp) {

        employes.add(emp);

        double salaireFinal = emp.getSalaireDeBase();
        TypeEmploye type = emp.getType();
        if (type == TypeEmploye.DEVELOPPER) {
            salaireFinal = emp.getSalaireDeBase() * 1.2;
            if (emp.getExperience() > 5) {
                salaireFinal = salaireFinal * 1.15;
            }
        } else if (type == TypeEmploye.CHEF_PROJET) {
            salaireFinal = emp.getSalaireDeBase() * 1.5;
            if (emp.getExperience() > 3) {
                salaireFinal = salaireFinal * 1.1;
            }
        } else if (type == TypeEmploye.STAGIAIRE) {
            salaireFinal = emp.getSalaireDeBase() * 0.6;
        }

        salairesEmployes.put(emp.getId(), salaireFinal);

        logs.add(LocalDateTime.now() + " - Ajout de l'employé: " + emp.getNom());
    }

    public double calculSalaire(UUID employeId) {
        Employe emp = null;
        for (Employe e : employes) {
            if (e.getId().equals(employeId)) {
                emp = e;
                break;
            }
        }
        if (emp == null) {
            System.out.println("ERREUR: impossible de trouver l'employé");
            return 0;
        }

        TypeEmploye type = emp.getType();
        double salaireDeBase = emp.getSalaireDeBase();
        int experience = emp.getExperience();

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

    public void generationRapport(String typeRapport, String filtre) {
        System.out.println("=== RAPPORT: " + typeRapport + " ===");

        if (typeRapport.equals("SALAIRE")) {
            for (Employe emp: employes) {
                if (filtre == null || filtre.isEmpty() ||
                        emp.getEquipe().equals(filtre)) {
                    UUID id = emp.getId();
                    String nom = emp.getNom();
                    double salaire = calculSalaire(id);
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

                double baseSalary = emp.getSalaireDeBase();
                double nouveauSalaire = calculSalaire(employeId);
                salairesEmployes.put(employeId, nouveauSalaire);

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

    public double calculBonusAnnuel(UUID employeId) {
        Employe emp = null;
        for (Employe e : employes) {
            if (e.getId().equals(employeId)) {
                emp = e;
                break;
            }
        }
        if (emp == null) return 0;

        TypeEmploye type = emp.getType();
        int experience = emp.getExperience();
        double salaireDeBase = emp.getSalaireDeBase();

        double bonus = 0;
        if (type == TypeEmploye.DEVELOPPER) {
            bonus = salaireDeBase * 0.1;
            if (experience > 5) {
                bonus = bonus * 1.5;
            }
        } else if (type == TypeEmploye.CHEF_PROJET) {
            bonus = salaireDeBase * 0.2;
            if (experience > 3) {
                bonus = bonus * 1.3;
            }
        } else if (type == TypeEmploye.STAGIAIRE) {
            bonus = 0; // Pas de bonus
        }
        return bonus;
    }
}



