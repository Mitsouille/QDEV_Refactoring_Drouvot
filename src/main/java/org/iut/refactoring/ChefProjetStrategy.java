package org.iut.refactoring;

public class ChefProjetStrategy implements SalaireStrategy {
    @Override
    public double calculerSalaire(double base, int exp) {
        double salaire = base * 1.5;
        if (exp > 3) {
            salaire = salaire * 1.1;
        }
        salaire = salaire + 5000; // bonus
        return salaire;
    }

    @Override
    public double calculerBonus(double base, int exp) {
        double bonus = base * 0.2;
        if (exp > 3) {
            bonus = bonus * 1.3;
        }
        return bonus;
    }
}
