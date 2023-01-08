package fr.roman.controleurs.metiers;


import fr.roman.modeles.Client;
import fr.roman.vues.metiers.VueClient;
import fr.roman.dao.DAOClient;

import java.sql.SQLException;

public class CtrlClient {

  /**
   * Instance de VueAdresse.
   */
  private final VueClient vueClient;

  /**
   * Instance de DAOClient.
   */
  private final DAOClient daoClient;


  public CtrlClient(VueClient vueClient) throws Exception {

    this.vueClient = vueClient;
    daoClient = new DAOClient();

  }

  public void removeClient(Client client) throws SQLException {
    daoClient.delete(client.getIdClient());
  }
}
