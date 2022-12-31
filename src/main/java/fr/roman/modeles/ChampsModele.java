package fr.roman.modeles;

/**
 * Interface pour l'implémentation des énumérations des champs dans les classes {@link Modele métiers}
 */
public interface ChampsModele {
    /**
     * Vrai si un {@link Role producteur} peut modifier ce champ, faux sinon
     */
    boolean modifProd = true;
    /**
     * Vrai si un {@link Role administrateur} peut modifier ce champ, faux sinon
     */
    boolean modifAdmin = true;
    /**
     * Vrai si le champ est l'identifiant unique dans la base de données, faux sinon
     */
    boolean id = false;
    /**
     * Vrai si le champ est un identifiant d'une clé étrangère, faux sinon
     */
    boolean idExt = false;
    /**
     * Vrai si le champ peut être null, faux sinon
     */
    boolean nullable = true;
}
