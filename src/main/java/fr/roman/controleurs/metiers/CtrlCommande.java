package fr.roman.controleurs.metiers;

import fr.roman.modeles.Commande;
import fr.roman.vues.metiers.VueCommande;
// import fr.roman.dao.DAOCommande;

public class CtrlCommande {

  /**
   * Instance de VueAdresse.
   */
  private final VueCommande vueCommande;

  public CtrlCommande(VueCommande vueCommande) {
    this.vueCommande = vueCommande;
  }

  public void removeCommande(Commande commande) {
    //DAOCommande.delete(commande.getIdCommande);
  }
}
