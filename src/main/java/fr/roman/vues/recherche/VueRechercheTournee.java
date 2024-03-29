package fr.roman.vues.recherche;

import fr.roman.controleurs.recherche.CtrlRechercheTournee;
import fr.roman.modeles.Tournee;
import fr.roman.vues.Actualisable;
import fr.roman.vues.tableaux.TableauTournees;

/**
 * Vue de recherche pour les tournées.
 *
 * @see VueRecherche
 * @see CtrlRechercheTournee
 * @see Tournee
 *
 * @author Axel Leblanc
 */
public class VueRechercheTournee extends VueRecherche<Tournee> implements Actualisable {
  /**
   * Créer une vue de recherche pour les tournées.
   */
  public VueRechercheTournee() {
    super();

    setTableau(new TableauTournees());
  }

  @Override
  public void actualiser() {
    ctrl.actualiserVue();
  }

  @Override
  protected void appliquerFiltres() {
    // Aucun filtre n'a été défini dans cette vue pour l'instant...
  }
}
