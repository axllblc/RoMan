package fr.roman.controleurs.recherche;

import fr.roman.modeles.Modele;
import fr.roman.vues.recherche.VueRecherche;

/**
 * Contrôleur des vues de recherche.
 *
 * @param <T> Le type d'objets métier affichés dans la vue de recherche
 *
 * @see VueRecherche
 *
 * @author Axel Leblanc
 */
public abstract class CtrlRecherche<T extends Modele> {
  protected final VueRecherche<T> vue;

  public CtrlRecherche(VueRecherche<T> vue) {
    this.vue = vue;
    vue.setCtrl(this);
  }
}
