package fr.roman.controleurs.metiers;

import fr.roman.modeles.Commande;
import fr.roman.vues.metiers.VueCommande;
import fr.roman.dao.DAOCommande;

import java.sql.SQLException;

public class CtrlCommande {

  /**
   * Instance de VueCommande.
   */
  private final VueCommande vueCommande;

  /**
   * Instance DAOCommande.
   */
  private final DAOCommande daoCommande;

  public CtrlCommande(VueCommande vueCommande) throws Exception {
    this.vueCommande = vueCommande;
    daoCommande = new DAOCommande();
  }

  public void removeCommande(Commande commande) throws SQLException {
    daoCommande.delete(commande.getIdCommande());
  }
}
