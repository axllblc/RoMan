package fr.roman.vues.authentification;

import fr.roman.controleurs.authentification.CtrlAuthentification;
import fr.roman.modeles.Utilisateur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Classe VueAuthentification.
 */
public class VueAuthentification {

  /**
   * Scene pricipale.
   */
  private final Scene scene;

  /**
   * Label indiquant à l'utilisateur qu'il doit se connecter.
   */
  private final Label labelConnexion;

  /**
   * Label à afficher lorsque le/les champ(s) est/sont incorrrect(s).
   */
  private final Label labelErreurConnexion;

  /**
   * Champ à renseiger avec un mot de passe.
   */
  private final PasswordField passwordField;

  /**
   * Champ à renseigner avec un Identifiant.
   */
  private final TextField textFieldIdentifiant;

  /**
   * Bouton permettant de valider ou non les champs renseignés.
   */
  private final Button btnConnexion;

  /**
   * Box contenant tout les élément de la scéne.
   */
  private final VBox vbox;

  /**
   * Contrôleur de la vue Authentification.
   */
  private CtrlAuthentification ctrl = null;


  /**
   * Constructeur de la classe.
   *
   * @param stage stage de la fonction start
   */
  public VueAuthentification(Stage stage) {
    // définition des éléments de la scéne
    textFieldIdentifiant = new TextField();
    passwordField = new PasswordField();
    btnConnexion = new Button("Connexion");
    labelErreurConnexion = new Label("Champs incorrects");
    labelConnexion = new Label("Connexion");
    vbox = new VBox();
    scene = new Scene(vbox);

    stuctureAuthentification(stage);
  }

  private void stuctureAuthentification(Stage stage) {

    // Ajout chaine de caratère grisée dans les TextFields
    textFieldIdentifiant.setPromptText("Identifiant");
    passwordField.setPromptText("Mot de passe");

    // Gestion taille des éléments de la scène
    textFieldIdentifiant.setMaxWidth(200);
    passwordField.setMaxWidth(200);
    btnConnexion.setMaxWidth(200);

    // Gestion espacements + alignement dans la box
    vbox.setSpacing(10);
    Insets padding = new Insets(20);
    vbox.setPadding(padding);
    vbox.setAlignment(Pos.CENTER);

    // Ajout des éléments dans la scène
    vbox.getChildren().add(labelConnexion);
    vbox.getChildren().add(textFieldIdentifiant);
    vbox.getChildren().add(passwordField);
    vbox.getChildren().add(btnConnexion);

    // Gestion scène
    stage.setScene(scene);
    stage.sizeToScene();
    stage.setResizable(false);
    stage.setTitle("RoMan");


    // Affichage scène
    stage.show();

    VueAuthentification vueAuthentification = this;

    btnConnexion.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        // Appel au controlleur
        ctrl = new CtrlAuthentification(vueAuthentification,
            textFieldIdentifiant.getText(), passwordField.getText());

      }
    });

  }

  public void erreurSaisie() {
    vbox.getChildren().add(labelErreurConnexion);
  }


}
