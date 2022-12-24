package fr.roman;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Classe principale de l'application, utilisée comme point d'entrée pour l'exécution.
 */
public class RoMan extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    // TODO à implémenter
  }

  /**
   * Méthode statique servant à afficher une erreur sur l'interface graphique de l'application.
   * Il s'agit d'une nouvelle fenêtre préconfigurée par la classe {@link Alert} de JavaFX.
   * <p>Aucune action ne peut être réalisée par l'utilisateur tant que la fenêtre n'est pas fermée.</p>
   * <p>Est affiché l'erreur en question et la trace d'appels où a eu lieu l'erreur.</p>
   *
   * @param e L'exception levée
   * @param messagePersonalise (optionnel) Une (ou des) chaines de caractères pour expliquer
   *                           l'erreur. La trace d'appels est toujours affichée.
   */
  public static void afficherErreur(Exception e, String... messagePersonalise) {
    // On configure l'affichage des sections de la fenêtre d'alerte
    Alert alerte = new Alert(Alert.AlertType.ERROR);
    alerte.setTitle("Erreur RoMan");
    alerte.setHeaderText("Une erreur a été rencontrée");

    // On récupère le message d'erreur à afficher
    StringBuilder contenuErreur = new StringBuilder();
    contenuErreur.append("""
            L'application Roman a rencontrée un problème.
            Explication de l'erreur :

            """);
    if(messagePersonalise != null){
      // Un message personnalisé a été passé en appel
      for (String l : messagePersonalise) {
        contenuErreur.append(l).append("\n");
      }
    }
    else{
      // On affiche le message de l'erreur par défaut sinon
      contenuErreur.append(e.getMessage());
    }

    // On affiche l'erreur
    alerte.setContentText(contenuErreur.toString());

    // On récupère le stacktrace de l'erreur
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    String exceptionText = sw.toString();

    // On ajoute le stacktrace à un TextArea
    TextArea zoneTrace = new TextArea(exceptionText);
    zoneTrace.setEditable(false); // Le tracé n'est pas modifiable
    zoneTrace.setWrapText(true); // Le tracé est masqué au départ

    GridPane fenErreur = new GridPane();
    fenErreur.add(new Label("Voici la trace d'appels :"), 0, 0);
    fenErreur.add(zoneTrace, 0, 1);

    alerte.getDialogPane().setExpandableContent(fenErreur);

    alerte.showAndWait();
  }
}