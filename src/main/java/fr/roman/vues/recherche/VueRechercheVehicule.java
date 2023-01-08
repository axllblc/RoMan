package fr.roman.vues.recherche;

/*
 * ⚠️ Cette vue n'est pas encore implémentée. Le code présent dans celle-ci est provisoire.
 */

import fr.roman.modeles.Vehicule;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Vue de recherche pour les véhicules.
 *
 * <p><b>Cette vue n'est pas encore implémentée. Le code présent dans celle-ci est
 * provisoire.</b></p>
 */
public class VueRechercheVehicule extends VueRecherche<Vehicule> {
  public VueRechercheVehicule() {
    super();
  }

  @Override
  public Node getNode() {
    return new Label("Vue de recherche des véhicules (non implémentée)");
  }

  @Override
  protected void appliquerFiltres() {}
}
