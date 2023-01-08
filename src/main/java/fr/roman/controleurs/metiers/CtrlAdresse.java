package fr.roman.controleurs.metiers;

import fr.roman.controleurs.edition.CtrlEditionAdresse;
import fr.roman.controleurs.edition.TypeEdition;
import fr.roman.dao.DAOAdresse;
import fr.roman.modeles.Adresse;
import fr.roman.modeles.Role;
import fr.roman.vues.edition.VueEdition;
import fr.roman.vues.metiers.VueAdresse;
import javafx.stage.Stage;

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

  /**
   * Stage de modification.
   */
  private final Stage primaryStage;

  public CtrlAdresse(VueAdresse vueAdresse) throws Exception {

    this.vueAdresse = vueAdresse;
    daoAdresse = new DAOAdresse();
    primaryStage = new Stage();

  }

  public void removeAdresse(Adresse adresse) throws SQLException {
    daoAdresse.delete(adresse.getIdAdresse());
  }

  public void modification(Adresse adresse) throws Exception {
    VueEdition vue = new VueEdition(TypeEdition.MODIFICATION);
    CtrlEditionAdresse ctrl = new CtrlEditionAdresse(adresse, vue, TypeEdition.MODIFICATION, Role.PRODUCTEUR);
    primaryStage.setScene(vue.getScene());
    primaryStage.show();
  }
}
