package fr.roman.controleurs.metiers;

import fr.roman.controleurs.edition.CtrlEditionCommande;
import fr.roman.controleurs.edition.TypeEdition;
import fr.roman.dao.DAOCommande;
import fr.roman.modeles.Commande;
import fr.roman.modeles.Role;
import fr.roman.vues.edition.VueEdition;
import fr.roman.vues.metiers.VueCommande;
import javafx.stage.Stage;

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

  /**
   * Stage de modification.
   */
  private final Stage primaryStage;

  public CtrlCommande(VueCommande vueCommande) throws Exception {
    this.vueCommande = vueCommande;
    daoCommande = new DAOCommande();
    primaryStage = new Stage();
  }

  public void removeCommande(Commande commande) throws SQLException {
    daoCommande.delete(commande.getIdCommande());
  }

  public void modification(Commande commande) throws Exception {
    VueEdition vue = new VueEdition(TypeEdition.MODIFICATION);
    CtrlEditionCommande ctrl = new CtrlEditionCommande(commande, vue, TypeEdition.MODIFICATION, Role.PRODUCTEUR);
    primaryStage.setScene(vue.getScene());
    primaryStage.show();
  }
}
