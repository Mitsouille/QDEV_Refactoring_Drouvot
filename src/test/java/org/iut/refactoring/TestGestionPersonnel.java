package org.iut.refactoring;

import org.assertj.core.util.diff.Delta;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        gestionPersonnel.ajouteSalarie(new Employe(TypeEmploye.DEVELOPER,"Alice", 50000, 6, "IT"));
        gestionPersonnel.ajouteSalarie(new Employe(TypeEmploye.CHEF_PROJET, "Bob", 60000, 4, "RH"));
        gestionPersonnel.ajouteSalarie(new Employe(TypeEmploye.STAGIAIRE, "Charlie", 20000, 0, "IT"));
        gestionPersonnel.ajouteSalarie(new Employe(TypeEmploye.DEVELOPER, "Dan", 55000, 12, "IT"));
        gestionPersonnel.ajouteSalarie(new Employe(TypeEmploye.DEVELOPER, "John", 35000, 3, "JSP"));
        gestionPersonnel.ajouteSalarie(new Employe(TypeEmploye.CHEF_PROJET, "Léa", 45000, 2, "RH"));
    }

    @Test
    void test_ajouteEmploye(){
        gestionPersonnel.ajouteSalarie(new Employe(TypeEmploye.DEVELOPER, "Alice", 50000, 6, "IT"));
        assertAll(
                () -> assertEquals(TypeEmploye.DEVELOPER, gestionPersonnel.employes.get(0).getType()),
                () -> assertEquals("Alice", gestionPersonnel.employes.get(0).getNom()),
                () -> assertEquals(50000, gestionPersonnel.employes.get(0).getSalaireDeBase()),
                () -> assertEquals(6, gestionPersonnel.employes.get(0).getExperience()),
                () -> assertEquals("IT", gestionPersonnel.employes.get(0).getEquipe()),
                () -> assertEquals(69000, gestionPersonnel.employes.get(0).getSalaire())
        );
    }

    @Test
    void test_avancementEmploye(){
        createEmploye();
        gestionPersonnel.avancementEmploye(gestionPersonnel.employes.get(2).getId(),TypeEmploye.DEVELOPER);
        assertEquals(TypeEmploye.DEVELOPER, gestionPersonnel.employes.get(2).getType());
    }

    @Test
    void test_avancementEmployeNonExistant(){
        createEmploye();
        assertThrows(IllegalArgumentException.class, () -> {
            gestionPersonnel.avancementEmploye(UUID.randomUUID(),TypeEmploye.valueOf("CEO"));
        });
    }

    @Test
    void test_generationRapportSalaire(){
        createEmploye();
        gestionPersonnel.generationRapport("SALAIRE", "IT");
        assertEquals("=== RAPPORT: SALAIRE ===\r\n" +
                        "Alice: 69000.0 €\r\n" +
                        "Charlie: 12000.0 €\r\n" +
                        "Dan: 79695.0 €\r\n"
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
                        "Dan: 12 années\r\n"
                , outContent.toString());
    }

    @Test
    void test_generationRapportDivision(){
        createEmploye();
        gestionPersonnel.generationRapport("DIVISION", "");
        assertEquals("=== RAPPORT: DIVISION ===\r\n" +
                "RH: 2 employés\r\n" +
                "JSP: 1 employés\r\n" +
                "IT: 3 employés\r\n", outContent.toString());
    }

    @Test
    void test_getEmployesParDivision(){
        createEmploye();
        ArrayList<Employe> emp = gestionPersonnel.getEmployesParDivision("IT");
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
