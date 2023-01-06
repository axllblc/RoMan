package fr.roman.vues;

import javafx.scene.Node;
import javafx.scene.Scene;

/**
 * Définit une vue qui peut être intégrée dans une {@link Scene} JavaFX au moyen de la méthode
 * {@link VueIntegrable#getNode()} qui retourne un élément graphique JavaFX ({@link Node}).
 *
 * <p>Cette interface est principalement utilisée pour permettre l'intégration des vues
 * affichées dans les onglets de l'accueil.</p>
 *
 * @see fr.roman.vues.accueil.VueAccueil
 *
 * @author Axel Leblanc
 */
public interface VueIntegrable {
  /**
   * Retourne l'élément graphique JavaFX ({@link Node}) correspondant à la vue. Il s'agit
   * généralement d'un conteneur qui regroupe d'autres éléments graphiques.
   *
   * @return Élément graphique JavaFX de la vue.
   */
  Node getNode();
}
