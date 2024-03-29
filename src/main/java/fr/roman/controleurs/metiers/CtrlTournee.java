package fr.roman.controleurs.metiers;

import fr.roman.controleurs.edition.CtrlEditionTournee;
import fr.roman.controleurs.edition.TypeEdition;
import fr.roman.dao.DAOTournee;
import fr.roman.modeles.Role;
import fr.roman.modeles.Tournee;
import fr.roman.vues.edition.VueEdition;
import fr.roman.vues.metiers.VueTournee;
import java.sql.SQLException;
import javafx.stage.Stage;

public class CtrlTournee {

  /**
   * Instance de VueTournee.
   */
  private final VueTournee vueTournee;

  /**
   * Instance DAOTournee.
   */
  private final DAOTournee daoTournee;

  /**
   * Stage de modification.
   */
  private final Stage primaryStage;

  public CtrlTournee(VueTournee vueTournee) throws Exception {
    this.vueTournee = vueTournee;
    daoTournee = new DAOTournee();
    primaryStage = new Stage();
  }

  public void removeTournee(Tournee tournee) throws SQLException {
    daoTournee.delete(tournee.getIdTournee());
  }

  public void modification(Tournee tournee, Role role) throws Exception {
    VueEdition vue = new VueEdition(TypeEdition.MODIFICATION);
    new CtrlEditionTournee(tournee.getProducteur().getUtilisateur(), tournee, vue,
        TypeEdition.MODIFICATION, role);
    primaryStage.setScene(vue.getScene());
    primaryStage.show();
  }
}
