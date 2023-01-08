package fr.roman.vues.recherche;

/*
 * ⚠️ Cette vue n'est pas encore implémentée. Le code présent dans celle-ci est provisoire.
 */

import fr.roman.modeles.Producteur;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Vue de recherche pour les clients.
 *
 * <p><b>Cette vue n'est pas encore implémentée. Le code présent dans celle-ci est
 * provisoire.</b></p>
 */
public class VueRechercheProducteur extends VueRecherche<Producteur> {
  public VueRechercheProducteur() {
    super();
  }

  @Override
  public Node getNode() {
    return new Label("Vue de recherche des producteurs (non implémentée)");
  }

  @Override
  protected void appliquerFiltres() {}
}
