package org.iut.refactoring;

public class SalaireStrategyFactory {
    public static SalaireStrategy create(TypeEmploye type){
        switch (type){
            case DEVELOPER : return new DeveloperStrategy();
            case CHEF_PROJET: return new ChefProjetStrategy();
            default: return new StagiaireStrategy();
        }
    }
}
