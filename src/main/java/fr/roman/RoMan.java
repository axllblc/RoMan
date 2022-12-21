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
    Application.launch(args);

  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    VueAuthentification launcher = new VueAuthentification(primaryStage);
    CtrlAuthentification ctrlAuthentification = new CtrlAuthentification(launcher);
    launcher.setCtrl(ctrlAuthentification);
  }

}