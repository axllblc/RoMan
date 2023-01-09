package fr.roman.controleurs.metiers;


import fr.roman.controleurs.edition.CtrlEditionClient;
import fr.roman.controleurs.edition.TypeEdition;
import fr.roman.dao.DAOClient;
import fr.roman.modeles.Client;
import fr.roman.modeles.Role;
import fr.roman.modeles.Utilisateur;
import fr.roman.vues.edition.VueEdition;
import fr.roman.vues.metiers.VueClient;
import javafx.stage.Stage;

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

  /**
   * Stage de modification.
   */
  private final Stage primaryStage;


  public CtrlClient(VueClient vueClient) throws Exception {

    this.vueClient = vueClient;
    daoClient = new DAOClient();
    primaryStage = new Stage();

  }

  public void removeClient(Client client) throws SQLException {
    daoClient.delete(client.getIdClient());
  }

  public void modification(Utilisateur utilisateur, Client client, Role role) throws Exception {
    VueEdition vue = new VueEdition(TypeEdition.MODIFICATION);
    CtrlEditionClient ctrl = new CtrlEditionClient(utilisateur, client, vue, TypeEdition.MODIFICATION, role);
    primaryStage.setScene(vue.getScene());
    primaryStage.show();
  }

}
