package fr.roman.controleurs.metiers;

import fr.roman.dao.DAO;
import fr.roman.modeles.Tournee;
import fr.roman.vues.metiers.VueTournee;
import fr.roman.dao.DAOTournee;

import java.sql.SQLException;

public class CtrlTournee {

  /**
   * Instance de VueTournee.
   */
  private final VueTournee vueTournee;

  /**
   * Instance DAOTournee
   */
  private final DAOTournee daoTournee;

  public CtrlTournee(VueTournee vueTournee) throws Exception {
    this.vueTournee = vueTournee;
    daoTournee = new DAOTournee();
  }

  public void removeTournee(Tournee tournee) throws SQLException {
    daoTournee.delete(tournee.getIdTournee());
  }
}
