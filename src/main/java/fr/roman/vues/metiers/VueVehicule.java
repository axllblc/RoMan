package fr.roman.vues.metiers;

import fr.roman.controleurs.metiers.CtrlVehicule;
import javafx.stage.Stage;

/**
 * Classe de l'objet vehicule, elle nous permettra de visualiser les objets de ce type
 * et de rediriger vers une  suppression ou une modification.
 */
public class VueVehicule {

  /**
   * Contrôleur de la vue Vehicule.
   */
  private CtrlVehicule ctrl = null;

  /**
   * Constructeur de la classe elle permettra de mettre en place tout les éléments
   * graphique de l'object vehicule.
   *
   * @param stage stage de l'application
   */
  public VueVehicule(Stage stage){

  }

  public void setCtrl(CtrlVehicule ctrl) {
    this.ctrl = ctrl;
  }
}
