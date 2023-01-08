package fr.roman.controleurs.metiers;

import fr.roman.modeles.Adresse;
import fr.roman.vues.metiers.VueAdresse;
import fr.roman.dao.DAOAdresse;

import java.sql.SQLException;

public class CtrlAdresse {

  /**
   * Instance de VueAdresse.
   */
  private final VueAdresse vueAdresse;

  /**
   * Instance de DAOAdresse.
   */
  private final DAOAdresse daoAdresse;

  public CtrlAdresse(VueAdresse vueAdresse) throws Exception {

    this.vueAdresse = vueAdresse;
    daoAdresse = new DAOAdresse();

  }

  public void removeAdresse(Adresse adresse) throws SQLException {
    daoAdresse.delete(adresse.getIdAdresse());
  }
}
