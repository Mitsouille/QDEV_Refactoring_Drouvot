package org.iut.refactoring.salaire;

public class StagiaireStrategy implements SalaireStrategy {

    @Override
    public double calculerSalaire(double base, int exp) {
        return base * 0.6;
    }

    @Override
    public double calculerBonus(double base, int exp) {
        return 0;
    }
}
