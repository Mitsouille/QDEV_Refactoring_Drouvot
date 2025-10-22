package org.iut.refactoring;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGestionPersonnel {
    private GestionPersonnel gestionPersonnel;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void init(){
        gestionPersonnel = new GestionPersonnel();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    public void createEmploye(){
        gestionPersonnel.ajouteSalarie("DEVELOPPEUR", "Alice", 50000, 6, "IT");
        gestionPersonnel.ajouteSalarie("CHEF DE PROJET", "Bob", 60000, 4, "RH");
        gestionPersonnel.ajouteSalarie("STAGIAIRE", "Charlie", 20000, 0, "IT");
        gestionPersonnel.ajouteSalarie("DEVELOPPEUR", "Dan", 55000, 12, "IT");
        gestionPersonnel.ajouteSalarie("DEVELOPPEUR", "John", 35000, 3, "IT");
        gestionPersonnel.ajouteSalarie("CHEF DE PROJET", "Léa", 45000, 2, "RH");
        gestionPersonnel.ajouteSalarie("INCONNU", "Martin", 999999, 0, "JSP");
    }

    @Test
    void test_ajouteEmploye(){
        gestionPersonnel.ajouteSalarie("DEVELOPPEUR", "Alice", 50000, 6, "IT");
        assertAll(
                () -> assertEquals("DEVELOPPEUR", gestionPersonnel.employes.get(0)[1]),
                () -> assertEquals("Alice", gestionPersonnel.employes.get(0)[2].toString()),
                () -> assertEquals(50000, (double) gestionPersonnel.employes.get(0)[3]),
                () -> assertEquals(6, (int) gestionPersonnel.employes.get(0)[4]),
                () -> assertEquals("IT", gestionPersonnel.employes.get(0)[5].toString()),
                () -> assertEquals(69000, gestionPersonnel.salairesEmployes.get(gestionPersonnel.employes.get(0)[0]))
        );
    }

    @Test
    void test_calculSalaire(){
        createEmploye();
        assertAll(
                () -> assertEquals(69000, gestionPersonnel.calculSalaire(gestionPersonnel.employes.get(0)[0].toString())),
                () -> assertEquals(104000, gestionPersonnel.calculSalaire(gestionPersonnel.employes.get(1)[0].toString()),000.1),
                () -> assertEquals(12000, gestionPersonnel.calculSalaire(gestionPersonnel.employes.get(2)[0].toString())),
                () -> assertEquals(79695, gestionPersonnel.calculSalaire(gestionPersonnel.employes.get(3)[0].toString())),
                () -> assertEquals(42000, gestionPersonnel.calculSalaire(gestionPersonnel.employes.get(4)[0].toString())),
                () -> assertEquals(72500, gestionPersonnel.calculSalaire(gestionPersonnel.employes.get(5)[0].toString())),
                () -> assertEquals(999999, gestionPersonnel.calculSalaire(gestionPersonnel.employes.get(6)[0].toString())),
                //Cas de l'erreur
                () -> assertEquals(0, gestionPersonnel.calculSalaire("NonExistingUUID"))
        );
    }

    @Test
    void test_calculBonusAnnuel(){
        createEmploye();
        assertAll(
                () -> assertEquals(7500, gestionPersonnel.calculBonusAnnuel(gestionPersonnel.employes.get(0)[0].toString())),
                () -> assertEquals(15600, gestionPersonnel.calculBonusAnnuel(gestionPersonnel.employes.get(1)[0].toString()),000.1),
                () -> assertEquals(0, gestionPersonnel.calculBonusAnnuel(gestionPersonnel.employes.get(2)[0].toString())),
                () -> assertEquals(8250, gestionPersonnel.calculBonusAnnuel(gestionPersonnel.employes.get(3)[0].toString())),
                () -> assertEquals(3500, gestionPersonnel.calculBonusAnnuel(gestionPersonnel.employes.get(4)[0].toString())),
                () -> assertEquals(9000, gestionPersonnel.calculBonusAnnuel(gestionPersonnel.employes.get(5)[0].toString())),
                () -> assertEquals(0, gestionPersonnel.calculBonusAnnuel(gestionPersonnel.employes.get(6)[0].toString())),
                //Cas de l'erreur
                () -> assertEquals(0, gestionPersonnel.calculBonusAnnuel("NonExistingUUID"))
        );
    }

    @Test
    void test_avancementEmploye(){
        createEmploye();
        gestionPersonnel.avancementEmploye(gestionPersonnel.employes.get(2)[0].toString(),"CEO");
        assertEquals("CEO", gestionPersonnel.employes.get(2)[1]);
    }

    @Test
    void test_avancementEmployeNonExistant(){
        createEmploye();
        gestionPersonnel.avancementEmploye("Moi","CEO");
        assertEquals("ERREUR: impossible de trouver l'employé\r\n", outContent.toString());
    }

    @Test
    void test_generationRapportSalaire(){
        createEmploye();
        gestionPersonnel.generationRapport("SALAIRE", "IT");
        assertEquals("=== RAPPORT: SALAIRE ===\r\n" +
                        "Alice: 69000.0 €\r\n" +
                        "Charlie: 12000.0 €\r\n" +
                        "Dan: 79695.0 €\r\n" +
                        "John: 42000.0 €\r\n"
                , outContent.toString());
    }

    @Test
    void test_generationRapportEquipe(){
        createEmploye();
        gestionPersonnel.generationRapport("EQUIPE", null);
        assertEquals("=== RAPPORT: EQUIPE ===\r\n", outContent.toString());
    }

    @Test
    void test_generationRapportExperience(){
        createEmploye();
        gestionPersonnel.generationRapport("EXPERIENCE", "IT");
        assertEquals("=== RAPPORT: EXPERIENCE ===\r\n" +
                        "Alice: 6 années\r\n" +
                        "Charlie: 0 années\r\n" +
                        "Dan: 12 années\r\n" +
                        "John: 3 années\r\n"
                , outContent.toString());
    }

    @Test
    void test_generationRapportDivision(){
        createEmploye();
        gestionPersonnel.generationRapport("DIVISION", "");
        assertEquals("=== RAPPORT: DIVISION ===\r\n" +
                "RH: 2 employés\r\n" +
                "JSP: 1 employés\r\n" +
                "IT: 4 employés\r\n", outContent.toString());
    }

    @Test
    void test_getEmployesParDivision(){
        createEmploye();
        ArrayList<Object[]> emp = gestionPersonnel.getEmployesParDivision("IT");
        assertAll(
                () -> assertEquals(gestionPersonnel.employes.get(0), emp.get(0)),
                () -> assertEquals(gestionPersonnel.employes.get(2), emp.get(1)),
                () -> assertEquals(gestionPersonnel.employes.get(3), emp.get(2))
        );
    }

    @Test
    void test_printLogs(){
        createEmploye();
        gestionPersonnel.printLogs();
        String expected = "=== LOGS ===\r\n";
        expected += String.join("\r\n",gestionPersonnel.logs);
        expected += "\r\n";
        assertEquals(expected,outContent.toString());
    }
}
