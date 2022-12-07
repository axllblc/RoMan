package fr.roman.modeles;

/**
* Enumeration des rôles possibles des utilisateurs de l'application.
*/
public enum Role {
  /**
   * Un administrateur a comme privilège l'accès à l'ensemble des données de la base.
   * Il peut effectuer n'importe quelles modifications,
   * hormis celles qui concernent les autres administrateurs.
   */
  ADMINISTRATEUR,
  /**
   * Un producteur est l'utilisateur principal de l'application.
   * Il a accès à ses commandes, ses tournées, ses véhicules, ses adresses
   * et l'ensemble des clients de la base.
   */
  PRODUCTEUR,
  /**
   * Le root a les mêmes privilèges que l'administrateur, à l'exception qu'il est unique
   * et qu'il peut ajouter ou modifier des administrateurs.
   */
  ROOT
}