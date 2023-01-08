package fr.roman;

import fr.roman.controleurs.authentification.CtrlAuthentification;
import fr.roman.vues.authentification.VueAuthentification;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Classe principale de l'application, utilisée comme point d'entrée pour l'exécution.
 */
public class RoMan extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      // Instanciation de la vue d'authentification et de son contrôleur
      VueAuthentification vueAuthentification = new VueAuthentification(primaryStage);
      CtrlAuthentification ctrlAuthentification = new CtrlAuthentification(vueAuthentification);
      vueAuthentification.setCtrl(ctrlAuthentification);
    } catch (Exception e) {
      // En cas d'exception, une fenêtre d'erreur est affichée
      RoManErreur.afficher(e);
      e.printStackTrace();
    }
  }

}