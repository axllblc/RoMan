package fr.roman.controleurs.metiers;

import fr.roman.modeles.Adresse;
import fr.roman.vues.metiers.VueAdresse;
// import fr.roman.dao.DAOAdresse;

public class CtrlAdresse {

  /**
   * Instance de VueAdresse.
   */
  private final VueAdresse vueAdresse;

  public CtrlAdresse(VueAdresse vueAdresse) {

    this.vueAdresse = vueAdresse;

  }

  public void removeAdresse(Adresse adresse) {
    //DAOAdresse.delete(adresse.getIdAdresse);
  }
}
