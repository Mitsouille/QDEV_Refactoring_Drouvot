package org.iut.refactoring;

import java.util.UUID;

class   GestionApp {
    public static void main(String[] args) {
        GestionPersonnel app = new GestionPersonnel();

        app.ajouteSalarie(new Employe(TypeEmploye.DEVELOPPER, "Alice", 50000, 6, "IT"));
        app.ajouteSalarie(new Employe(TypeEmploye.CHEF_PROJET, "Bob", 60000, 4, "RH"));
        app.ajouteSalarie(new Employe(TypeEmploye.STAGIAIRE, "Charlie", 20000, 0, "IT"));
        app.ajouteSalarie(new Employe(TypeEmploye.DEVELOPPER, "Dan", 55000, 12, "IT"));

        UUID aliceId = app.employes.get(0).getId();

        System.out.println("Salaire de Alice: " + app.calculSalaire(aliceId) + " €");
        System.out.println("Bonus de Alice: " + app.calculBonusAnnuel(aliceId) + " €");

        app.generationRapport("SALAIRE", "IT");
        app.generationRapport("EQUIPE", null);

        app.avancementEmploye(aliceId, TypeEmploye.CHEF_PROJET);

        app.printLogs();
    }
}
