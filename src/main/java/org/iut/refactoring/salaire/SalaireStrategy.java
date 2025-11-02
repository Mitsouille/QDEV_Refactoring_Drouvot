package org.iut.refactoring.salaire;

public interface SalaireStrategy {
    double calculerSalaire(double base, int exp);
    double calculerBonus(double base, int exp);
}
