package fr.roman.vues.metiers;

import fr.roman.controleurs.metiers.CtrlCommande;
import javafx.stage.Stage;

/**
 * Classe de l'object commande, elle nous permettra de visualiser les objects de ce type
 * et de rediriger vers une  suppression ou une modification.
 */
public class VueCommande {

  /**
   * Contrôleur de la vue Commande.
   */
  private CtrlCommande ctrl = null;

  /**
   * Constructeur de la classe elle permettra de mettre en place tout les éléments
   * graphique de l'object commande.
   *
   * @param stage stage de l'application
   */
  public VueCommande(Stage stage){

  }

  public void setCtrl(CtrlCommande ctrl) {
    this.ctrl = ctrl;
  }

}
