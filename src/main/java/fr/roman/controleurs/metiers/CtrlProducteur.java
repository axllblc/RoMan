package fr.roman.controleurs.metiers;

import fr.roman.controleurs.edition.CtrlEditionAdresse;
import fr.roman.controleurs.edition.CtrlEditionProducteur;
import fr.roman.controleurs.edition.TypeEdition;
import fr.roman.dao.DAOCommande;
import fr.roman.modeles.Adresse;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.vues.edition.VueEdition;
import fr.roman.vues.metiers.VueAdresse;
import fr.roman.dao.DAOProducteur;
import javafx.stage.Stage;

public class CtrlProducteur {

  /**
   * Instance de VueAdresse.
   */
  private final VueAdresse vueAdresse;

  /**
   * Insatance DAOProducteur.
   */
  private final DAOProducteur daoProducteur;

  /**
   * Stage de modification.
   */
  private final Stage primaryStage;

  public CtrlProducteur(VueAdresse vueAdresse) throws Exception {
    this.vueAdresse = vueAdresse;
    daoProducteur = new DAOProducteur();
    primaryStage = new Stage();
  }

  public void removeProducteur(Producteur producteur) throws Exception {
   daoProducteur.delete(producteur.getIdProducteur());
  }

  public void modification(Producteur producteur, Role role) throws Exception {
    VueEdition vue = new VueEdition(TypeEdition.MODIFICATION);
    CtrlEditionProducteur ctrl = new CtrlEditionProducteur(producteur.getUtilisateur(), producteur, vue, TypeEdition.MODIFICATION, role);
    primaryStage.setScene(vue.getScene());
    primaryStage.show();
  }
}
