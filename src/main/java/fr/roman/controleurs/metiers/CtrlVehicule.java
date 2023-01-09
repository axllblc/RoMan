package fr.roman.controleurs.metiers;

import fr.roman.controleurs.edition.CtrlEditionAdresse;
import fr.roman.controleurs.edition.CtrlEditionVehicule;
import fr.roman.controleurs.edition.TypeEdition;
import fr.roman.modeles.Adresse;
import fr.roman.modeles.Role;
import fr.roman.modeles.Utilisateur;
import fr.roman.modeles.Vehicule;
import fr.roman.vues.edition.VueEdition;
import fr.roman.vues.metiers.VueAdresse;
import fr.roman.dao.DAOVehicule;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CtrlVehicule {

  /**
   * Instance de VueVehicule.
   */
  private final VueAdresse vueVehicule;

  /**
   * Instance DAOVehicule
   */
  private final DAOVehicule daoVehicule;

  /**
   * Stage de modification.
   */
  private final Stage primaryStage;

  public CtrlVehicule(VueAdresse vueVehicule) throws Exception {
    this.vueVehicule = vueVehicule;
    daoVehicule = new DAOVehicule();
    primaryStage = new Stage();
  }

  public void removeVehicule(Vehicule vehicule) throws SQLException {
    daoVehicule.delete(vehicule.getIdVehicule());
  }

  public void modification(Vehicule vehicule, Role role) throws Exception {
    VueEdition vue = new VueEdition(TypeEdition.MODIFICATION);
    CtrlEditionVehicule ctrl = new CtrlEditionVehicule(vehicule.getProducteur().getUtilisateur(), vehicule, vue, TypeEdition.MODIFICATION, role);
    primaryStage.setScene(vue.getScene());
    primaryStage.show();
  }
}
