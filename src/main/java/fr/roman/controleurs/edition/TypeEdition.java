package fr.roman.controleurs.edition;

/**
 * Enumération des types d'éditions possibles pour les contrôleurs/vues des métiers
 */
public enum TypeEdition {
    CREATION("Formulaire de création"),
    MODIFICATION("Formulaire de modification");

    public final String libelle;

    TypeEdition(String libelle) {
        this.libelle = libelle;
    }

}
