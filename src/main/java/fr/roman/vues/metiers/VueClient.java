package fr.roman.vues.metiers;

import fr.roman.controleurs.metiers.CtrlClient;
import javafx.stage.Stage;

/**
 * Classe de l'objet client, elle nous permettra de visualiser les objets de ce type
 * et de rediriger vers une  suppression ou une modification.
 */
public class VueClient {

  /**
   * Contrôleur de la vue Client.
   */
  private CtrlClient ctrl = null;

  /**
   * Constructeur de la classe elle permettra de mettre en place tout les éléments
   * graphique de l'object client.
   *
   * @param stage stage de l'application
   */
  public VueClient(Stage stage){

  }

  public void setCtrl(CtrlClient ctrl) {
    this.ctrl = ctrl;
  }
}
