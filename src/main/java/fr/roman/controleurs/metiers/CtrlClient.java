package fr.roman.controleurs.metiers;


import fr.roman.modeles.Client;
import fr.roman.vues.metiers.VueClient;
// import fr.roman.dao.DAOClient;

public class CtrlClient {

  /**
   * Instance de VueAdresse.
   */
  private final VueClient vueClient;

  public CtrlClient(VueClient vueClient) {

    this.vueClient = vueClient;

  }

  public void removeClient(Client client) {
    //DAOClient.delete(client.getIdClient);
    System.out.println("ça fonctionne");
  }
}
