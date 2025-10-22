package org.iut.refactoring;

public enum TypeEmploye {
    DEVELOPPER("DEVELOPPEUR"),
    STAGIAIRE("STAGIAIRE"),
    CHEF_PROJET("CHEF DE PROJET");

    private final String type;

    TypeEmploye(String type) {
        this.type = type;
    }

    public String getDescription() {
        return type;
    }
}
