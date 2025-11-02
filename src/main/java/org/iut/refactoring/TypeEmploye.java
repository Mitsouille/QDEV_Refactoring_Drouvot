package org.iut.refactoring;

public enum TypeEmploye {
    DEVELOPER("DEVELOPPEUR"),
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
