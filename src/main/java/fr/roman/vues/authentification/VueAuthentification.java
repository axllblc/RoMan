package fr.roman.vues.authentification;

import fr.roman.controleurs.authentification.CtrlAuthentification;
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
   * {@link Scene} principale.
   */
  private final Scene scene;

  /**
   * Label indiquant à l'utilisateur qu'il doit se connecter.
   */
  private final Label labelConnexion;

  /**
   * Label à afficher lorsque le/les champ(s) est/sont incorrect(s).
   */
  private final Label labelErreurConnexion;

  /**
   * Champ à renseigner avec un mot de passe.
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
   * Box contenant tous les éléments de la {@link Scene}.
   */
  private final VBox vbox;

  /**
   * Contrôleur de la vue Authentification.
   */
  private CtrlAuthentification ctrl = null;
  /**
   * boolean permettant de ne pas ajouter l'erreur de saisie plusieurs fois à la scène.
   */
  private boolean erreurIdAdd;

  /**
   * {@link Stage} authentification.
   */
  private final Stage stageAuthentification;


  /**
   * Constructeur de la classe. Elle permettra de mettre en place tous les éléments
   * graphiques de la fonction authentification.
   *
   * @param stage {@link Stage} de la fonction start
   */
  public VueAuthentification(Stage stage) {
    // Définition des éléments graphiques
    textFieldIdentifiant = new TextField();
    passwordField = new PasswordField();
    btnConnexion = new Button("Connexion");
    labelErreurConnexion = new Label("Champs incorrects");
    labelConnexion = new Label("Connexion");
    vbox = new VBox();
    erreurIdAdd = false;
    scene = new Scene(vbox);
    stageAuthentification = stage;

    structureAuthentification(stageAuthentification);
  }

  /**
   * Getter permettant d'obtenir la chaine de caractère entrée dans le champ identifiant.
   *
   * @return NomUtilisateur.
   */
  public String getNomUtilisateur() {
    return textFieldIdentifiant.getText();
  }

  /**
   * Getter permettant d'obtenir la chaine de caractère entrée dans le champ password.
   *
   * @return Password.
   */
  public String getPassword() {
    return passwordField.getText();
  }

  /**
   * Permet de mettre en place les éléments de la {@link Scene}.
   *
   * @param stage Stage de la partie Authentification.
   */
  private void structureAuthentification(Stage stage) {
    // Ajout de prompt text dans les TextFields (pour donner des indications à l'utilisateur)
    textFieldIdentifiant.setPromptText("Identifiant");
    passwordField.setPromptText("Mot de passe");

    // Gestion taille des éléments graphiques
    textFieldIdentifiant.setMaxWidth(200);
    passwordField.setMaxWidth(200);
    btnConnexion.setMaxWidth(200);

    // Gestion espacements + alignement dans la VBox
    vbox.setSpacing(10);
    vbox.setPadding(new Insets(20));
    vbox.setAlignment(Pos.CENTER);

    // Ajout des éléments dans la scène (Scene)
    vbox.getChildren().add(labelConnexion);
    vbox.getChildren().add(textFieldIdentifiant);
    vbox.getChildren().add(passwordField);
    vbox.getChildren().add(btnConnexion);

    // Définition du bouton de connexion comme bouton par défaut (il est ainsi actionné lorsque
    // l'utilisateur appuie sur la touche Entrée) et ajout de son Event handler.
    btnConnexion.setDefaultButton(true);
    btnConnexion.setOnAction(event -> ctrl.verify());

    // Gestion du Stage et affichage
    stage.setScene(scene);
    stage.sizeToScene();
    stage.setResizable(false);
    stage.setTitle("RoMan");
    stage.show();
  }

  /**
   * Affectation du contrôleur de la vue.
   *
   * @param ctrl Contrôleur à affecter
   */
  public void setCtrl(CtrlAuthentification ctrl) {
    this.ctrl = ctrl;
  }

  /**
   * méthode affichant un message d'erreur lorsque les champs sont erronés.
   */
  public void erreurSaisie() {
    if (!erreurIdAdd) {
      vbox.getChildren().add(labelErreurConnexion);
      erreurIdAdd = true;
    }

    // Le champ du mot de passe est vidé
    passwordField.setText("");
  }

  public void close() {
    stageAuthentification.close();
  }
}
