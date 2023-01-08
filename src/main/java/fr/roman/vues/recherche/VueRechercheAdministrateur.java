package fr.roman.vues.recherche;

/*
 * ⚠️ Cette vue n'est pas encore implémentée. Le code présent dans celle-ci est provisoire.
 */

import fr.roman.modeles.Utilisateur;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Vue de recherche pour la gestion des administrateurs.
 *
 * <p><b>Cette vue n'est pas encore implémentée. Le code présent dans celle-ci est
 * provisoire.</b></p>
 */
public class VueRechercheAdministrateur extends VueRecherche<Utilisateur> {
  public VueRechercheAdministrateur() {
    super();
  }

  @Override
  public Node getNode() {
    return new Label("Vue de recherche des administrateurs (non implémentée)");
  }

  @Override
  protected void appliquerFiltres() {}
}
