package org.iut.refactoring.rapport;

public class RapportFactory {
    public static RapportStrategy create(TypeRapport type){
        switch (type){
            case DIVISION: return new RapportDivision();
            case SALAIRE: return new RapportSalaire();
            case EXPERIENCE: return new RapportExperience();
            default: throw new IllegalArgumentException("Ce type de rapport n'est pas implémenter");
        }
    }
}
