package fr.roman.vues;

/**
 * Interface implémentée par permettant à une vue de demander à son contrôleur de mettre à jour les
 * données qu'elle contient.
 *
 * @author Axel Leblanc
 */
public interface Actualisable {
  /**
   * Demander une mise à jour des données.
   *
   * @see Actualisable
   */
  void actualiser();
}
