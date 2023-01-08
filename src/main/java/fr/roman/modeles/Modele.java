package fr.roman.modeles;

/**
 * Super-classe des modèles de l'application.
 */
public abstract class Modele {
  /**
   * Retourne l'identifiant de l'objet métier, tel qu'il est enregistré dans la base de données.
   * Par convention, pour un objet qui ne s'est pas vu attribuer un identifiant (car il n'a pas
   * encore été enregistré dans la base de données), la méthode retourne 0.
   *
   * @return Identifiant de l'objet, ou 0 si l'objet n'en n'a pas
   */
  public abstract int getId();
}
