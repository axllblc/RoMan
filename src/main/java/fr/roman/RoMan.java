package fr.roman;


import javafx.application.Application;
import javafx.stage.Stage;
import fr.roman.vues.metiers.*;

/**
 * Classe principale de l'application, utilisée comme point d'entrée pour l'exécution.
 */
public class RoMan extends Application {
  public static void main(String[] args) {
    Application.launch(args);

  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    VueCommande vueCommande = new VueCommande(primaryStage);
    /*CtrlCommande ctrlCommande = new CtrlCommande(launcher);
    launcher.setCtrl(ctrlCommande);*/
  }
}