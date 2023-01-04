package fr.roman.controleurs.metiers;

import fr.roman.modeles.Producteur;
import fr.roman.vues.metiers.VueAdresse;
// import fr.roman.dao.DAOProducteur;

public class CtrlProducteur {

  /**
   * Instance de VueAdresse.
   */
  private final VueAdresse vueAdresse;

  public CtrlProducteur(VueAdresse vueAdresse) {
    this.vueAdresse = vueAdresse;
  }

  public void removeProducteur(Producteur producteur) {
    //DAOProducteur.delete(producteur.getIdProducteur);
  }
}
