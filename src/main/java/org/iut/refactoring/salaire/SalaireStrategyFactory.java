package org.iut.refactoring.salaire;

import org.iut.refactoring.TypeEmploye;

public class SalaireStrategyFactory {
    public static SalaireStrategy create(TypeEmploye type){
        switch (type){
            case DEVELOPER : return new DeveloperStrategy();
            case CHEF_PROJET: return new ChefProjetStrategy();
            case STAGIAIRE: return new StagiaireStrategy();
            default: return new StagiaireStrategy();
        }
    }
}
