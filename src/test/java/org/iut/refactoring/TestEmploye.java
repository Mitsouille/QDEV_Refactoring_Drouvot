package org.iut.refactoring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEmploye {

    private GestionPersonnel gestionPersonnel;

    @BeforeEach
    public void init() {
        gestionPersonnel = new GestionPersonnel();
    }

    public void createEmploye(){
        gestionPersonnel.ajouteSalarie(new Employe(TypeEmploye.DEVELOPER,"Alice", 50000, 6, "IT"));
        gestionPersonnel.ajouteSalarie(new Employe(TypeEmploye.CHEF_PROJET, "Bob", 60000, 4, "RH"));
        gestionPersonnel.ajouteSalarie(new Employe(TypeEmploye.STAGIAIRE, "Charlie", 20000, 0, "IT"));
        gestionPersonnel.ajouteSalarie(new Employe(TypeEmploye.DEVELOPER, "Dan", 55000, 12, "IT"));
        gestionPersonnel.ajouteSalarie(new Employe(TypeEmploye.DEVELOPER, "John", 35000, 3, "JSP"));
        gestionPersonnel.ajouteSalarie(new Employe(TypeEmploye.CHEF_PROJET, "Léa", 45000, 2, "RH"));
    }

    @Test
    void test_createEmploye(){
        Employe emp = new Employe(TypeEmploye.DEVELOPER,"Toto",1500,1,"IT");
        assertAll(
                () -> assertEquals(TypeEmploye.DEVELOPER, emp.getType()),
                () -> assertEquals("Toto", emp.getNom()),
                () -> assertEquals(1500, emp.getSalaireDeBase()),
                () -> assertEquals(1, emp.getExperience()),
                () -> assertEquals("IT", emp.getEquipe()),
                () -> assertEquals(1800, emp.getSalaire())
        );
    }

    @Test
    void test_calculSalaire(){
        createEmploye();
        assertAll(
                () -> assertEquals(69000, gestionPersonnel.employes.get(0).calculSalaire()),
                () -> assertEquals(104000, gestionPersonnel.employes.get(1).calculSalaire(),000.1),
                () -> assertEquals(12000, gestionPersonnel.employes.get(2).calculSalaire()),
                () -> assertEquals(79695, gestionPersonnel.employes.get(3).calculSalaire()),
                () -> assertEquals(42000, gestionPersonnel.employes.get(4).calculSalaire()),
                () -> assertEquals(72500, gestionPersonnel.employes.get(5).calculSalaire())
        );
    }

    @Test
    void test_calculBonusAnnuel(){
        createEmploye();
        assertAll(
                () -> assertEquals(7500, gestionPersonnel.employes.get(0).calculBonusAnnuel()),
                () -> assertEquals(15600, gestionPersonnel.employes.get(1).calculBonusAnnuel(),000.1),
                () -> assertEquals(0, gestionPersonnel.employes.get(2).calculBonusAnnuel()),
                () -> assertEquals(8250, gestionPersonnel.employes.get(3).calculBonusAnnuel()),
                () -> assertEquals(3500, gestionPersonnel.employes.get(4).calculBonusAnnuel()),
                () -> assertEquals(9000, gestionPersonnel.employes.get(5).calculBonusAnnuel())
        );
    }
}
