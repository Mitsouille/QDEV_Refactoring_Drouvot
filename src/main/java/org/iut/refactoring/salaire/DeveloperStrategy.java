package org.iut.refactoring.salaire;

public class DeveloperStrategy implements SalaireStrategy {
    @Override
    public double calculerSalaire(double base, int exp) {
        double salaire = base * 1.2;
        if (exp > 5) {
            salaire = salaire * 1.15;
        }
        if (exp > 10) {
            salaire = salaire * 1.05;
        }
        return salaire;
    }

    @Override
    public double calculerBonus(double base, int exp) {
        double bonus = base * 0.1;
        if (exp > 5) {
            bonus = bonus * 1.5;
        }
        return bonus;
    }
}
