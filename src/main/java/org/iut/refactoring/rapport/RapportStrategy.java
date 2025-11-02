package org.iut.refactoring.rapport;

import org.iut.refactoring.Employe;

import java.util.List;

public interface RapportStrategy {
    void generer(List<Employe> employeList, String filtre);
}
