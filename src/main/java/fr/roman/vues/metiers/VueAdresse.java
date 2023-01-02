package fr.roman.vues.metiers;

import fr.roman.controleurs.metiers.CtrlAdresse;
import javafx.stage.Stage;

/**
 * Classe de l'objet adresse, elle nous permettra de visualiser les objets de ce type
 * et de rediriger vers une  suppression ou une modification.
 */
public class VueAdresse {

  /**
   * Contrôleur de la vue Adresse.
   */
  private CtrlAdresse ctrl = null;

  /**
   * Constructeur de la classe elle permettra de mettre en place tout les éléments
   * graphique de l'object adresse.
   *
   * @param stage stage de l'application
   */
  public VueAdresse(Stage stage){

  }

  public void setCtrl(CtrlAdresse ctrl) {
    this.ctrl = ctrl;
  }
}


