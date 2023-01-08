package fr.roman.controleurs.metiers;

import fr.roman.dao.DAOCommande;
import fr.roman.modeles.Producteur;
import fr.roman.vues.metiers.VueAdresse;
import fr.roman.dao.DAOProducteur;

public class CtrlProducteur {

  /**
   * Instance de VueAdresse.
   */
  private final VueAdresse vueAdresse;

  /**
   * Insatance DAOProducteur.
   */
  private final DAOProducteur daoProducteur;

  public CtrlProducteur(VueAdresse vueAdresse) throws Exception {
    this.vueAdresse = vueAdresse;
    daoProducteur = new DAOProducteur();
  }

  public void removeProducteur(Producteur producteur) throws Exception {
   daoProducteur.delete(producteur.getIdProducteur());
  }
}
