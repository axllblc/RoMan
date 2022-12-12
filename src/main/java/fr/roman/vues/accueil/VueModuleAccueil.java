package fr.roman.vues.accueil;

import javafx.scene.Node;

/**
 * Définit une vue d'un module accessible depuis l'accueil, au moyen d'un onglet. Afin de
 * permettre l'intégration de cette vue dans un onglet, celle-ci doit implémenter une méthode
 * {@link VueModuleAccueil#getNode()} qui retourne un élément graphique JavaFX ({@link Node}).
 *
 * @see fr.roman.vues.accueil.VueAccueil
 *
 * @author Axel Leblanc
 */
public interface VueModuleAccueil {
  /**
   * Retourne l'élément graphique JavaFX ({@link Node}) correspondant à la vue. Il s'agit
   * généralement d'un conteneur qui regroupe d'autres éléments graphiques.
   *
   * @return Élément graphique JavaFX de la vue.
   */
  Node getNode();
}
