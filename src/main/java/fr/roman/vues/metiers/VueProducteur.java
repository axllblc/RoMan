package fr.roman.vues.metiers;

import fr.roman.controleurs.metiers.CtrlProducteur;
import javafx.stage.Stage;

/**
 * Classe de l'objet producteur, elle nous permettra de visualiser les objets de ce type
 * et de rediriger vers une  suppression ou une modification.
 */
public class VueProducteur {

  /**
   * Contrôleur de la vue Producteur.
   */
  private CtrlProducteur ctrl = null;

  /**
   * Constructeur de la classe elle permettra de mettre en place tout les éléments
   * graphique de l'object producteur.
   *
   * @param stage stage de l'application
   */
  public VueProducteur(Stage stage){

  }

  public void setCtrl(CtrlProducteur ctrl) {
    this.ctrl = ctrl;
  }
}
