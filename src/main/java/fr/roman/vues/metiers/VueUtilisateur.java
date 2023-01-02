package fr.roman.vues.metiers;

import fr.roman.controleurs.metiers.CtrlUtilisateur;
import javafx.stage.Stage;

/**
 * Classe de l'objet utilisateur, elle nous permettra de visualiser les objets de ce type
 * et de rediriger vers une  suppression ou une modification.
 */
public class VueUtilisateur {

  /**
   * Contrôleur de la vue Utilisateur.
   */
  private CtrlUtilisateur ctrl = null;

  /**
   * Constructeur de la classe elle permettra de mettre en place tout les éléments
   * graphique de l'object utilisateur.
   *
   * @param stage stage de l'application
   */
  public VueUtilisateur(Stage stage){

  }

  public void setCtrl(CtrlUtilisateur ctrl) {
    this.ctrl = ctrl;
  }
}
