package fr.roman.vues.metiers;

import fr.roman.controleurs.metiers.CtrlTournee;
import javafx.stage.Stage;

/**
 * Classe de l'objet tournee, elle nous permettra de visualiser les objets de ce type
 * et de rediriger vers une  suppression ou une modification.
 */
public class VueTournee {

  /**
   * Contrôleur de la vue Tournee.
   */
  private CtrlTournee ctrl = null;

  /**
   * Constructeur de la classe elle permettra de mettre en place tout les éléments
   * graphique de l'object tournee.
   *
   * @param stage stage de l'application
   */
  public VueTournee(Stage stage){

  }

  public void setCtrl(CtrlTournee ctrl) {
    this.ctrl = ctrl;
  }
}
