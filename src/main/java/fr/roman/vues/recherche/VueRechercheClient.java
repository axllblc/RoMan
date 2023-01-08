package fr.roman.vues.recherche;

/*
 * ⚠️ Cette vue n'est pas encore implémentée. Le code présent dans celle-ci est provisoire.
 */

import fr.roman.modeles.Client;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Vue de recherche pour les clients.
 *
 * <p><b>Cette vue n'est pas encore implémentée. Le code présent dans celle-ci est
 * provisoire.</b></p>
 */
public class VueRechercheClient extends VueRecherche<Client> {
  public VueRechercheClient() {
    super();
  }

  @Override
  public Node getNode() {
    return new Label("Vue de recherche des clients (non implémentée)");
  }

  @Override
  protected void appliquerFiltres() {}
}
