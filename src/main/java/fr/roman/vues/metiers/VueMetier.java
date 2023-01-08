package fr.roman.vues.metiers;

import fr.roman.controleurs.accueil.CtrlAccueil;
import fr.roman.modeles.Utilisateur;
import fr.roman.vues.accueil.VueAccueil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Classe abstraite des vues metiers, elle aura pour but de généraliser certaines
 * méthodes de ces dernières.
 */
public abstract class VueMetier {

  /**
   * Utilisateur actuel.
   */
  Utilisateur utilisateur;

  /**
   * Boutton redirigant l'utilisateur vers la vue de modification.
   */
  private final Button btnModifier;

  /**
   * Boutton permettant de supprimer l'objet affiché à l'écran.
   */
  private final Button btnSupprimer;
  /**
   * Boutton de valitation de suppression de l'objet
   */
  final Button btnOui;

  /**
   * Boutton d'annulation de la suppression de l'objet
   */
  private final Button btnNon;

  /**
   * Label demandant à l'utilisateur s'il est sûr de vouloir supprimer l'objet
   */
  private final Label validation;

  /**
   * Hbox contenant les boutons de la scène.
   */
  private final HBox hbox;

  /**
   * Vbox contenant les labels de la scène.
   */
  private final VBox vbox;

  /**
   * Hbox contenant les boutons de validation de suppression.
   */
  private final HBox validationBox;

  /**
   * Scène métier.
   */
  private final Scene scene;

  /**
   * VBox permettant de stocker les 2 box filles.
   */
  private final VBox rootVbox;

  /**
   * Booleen permettant de gérer l'ajout des éléments graphiques de validation.
   */
  private boolean isValidationAdd;

  /**
   * Booleen permettant de réactiver les champs de validation.
   */
  private boolean validationIsDisable;

  /**
   * Stage de la vue.
   */
  private final Stage stage;

  /**
   * Panneau d'introduction.
   */
  private final VBox introductionBox;

  /**
   * Scene validation suppression + redirection vers accueil.
   */
  private final Scene sceneSuppEffectuee;

  /**
   * Vbox annonçant à l'utilisateur que l'élément a bien été supprimé.
   */
  private final VBox vboxRetourAccueil;

  /**
   * Bouton redirection vers accueil.
   */
  private final Button btnRetourAccueil;

  /**
   * Label Supression effectuée.
   */
  private final Label labelSuppressionEffectuee;

  /**
   * Stage redirection accueil.
   */
  private final Stage stageRedirection;


  /**
   * Constructeur de la classe abstraite des objets métiers, on y ajoutera
   * les éléments communs à tout les objets.
   */
  public VueMetier(Utilisateur utilisateur) {

    this.utilisateur = utilisateur;
    btnModifier = new Button("Modifier");
    btnSupprimer = new Button("Supprimer");
    btnOui = new Button("Oui");
    btnNon = new Button("Non");
    btnRetourAccueil = new Button("Accueil");
    validation = new Label("Voulez vous vraiment supprimer cet élément ? ");
    labelSuppressionEffectuee = new Label("L'élément à bien été supprimé");
    hbox = new HBox();
    vbox = new VBox();
    validationBox = new HBox();
    rootVbox = new VBox();
    introductionBox = new VBox();
    vboxRetourAccueil = new VBox();
    isValidationAdd = false;
    validationIsDisable = true;
    scene = new Scene(rootVbox);
    sceneSuppEffectuee = new Scene(vboxRetourAccueil);
    rootVbox.getChildren().addAll(introductionBox, vbox, hbox);
    stage = new Stage();
    stageRedirection = new Stage();


  }

  /**
   * Permet de structurer la scène.
   *
   * @param labels tableau de label qui contiendra tout les lables à ajouter à la scène.
   */
  public void structure(ArrayList<Label> labels, Label introduction) {

    Insets padding = new Insets(20);

    introductionBox.getChildren().add(introduction);
    // (int numLabel = 0; numLabel  < labels.length; numLabel++)
    for (Label label : labels) {
      vbox.getChildren().add(label);
      label.setFont(new Font("Arial", 10));
    }
    hbox.getChildren().add(btnModifier);
    hbox.getChildren().add(btnSupprimer);

    // Gestion espacements + alignement des éléments
    introduction.setFont(new Font("Arial", 15));
    introductionBox.setAlignment(Pos.TOP_CENTER);
    introductionBox.setPadding(padding);

    vbox.setSpacing(20);
    vbox.setPadding(padding);
    vbox.setAlignment(Pos.CENTER_LEFT);

    hbox.setSpacing(40);
    hbox.setPadding(padding);
    hbox.setAlignment(Pos.CENTER);

    rootVbox.setAlignment(Pos.CENTER);

    validation.setPadding(padding);
    validation.setAlignment(Pos.CENTER);

    validationBox.setSpacing(5);
    validationBox.setPadding(padding);
    validationBox.setAlignment(Pos.CENTER);

    btnSupprimer.setMinWidth(100);
    btnSupprimer.setMinHeight(30);

    btnModifier.setMinWidth(100);
    btnModifier.setMinHeight(30);

    // Gestion scène
    stage.setScene(scene);
    stage.setTitle("RoMan");
    stage.sizeToScene();


    // Affichage scène
    stage.show();

    btnSupprimer.setOnAction((event) -> {
      if (!isValidationAdd) {
        validationBox.getChildren().add(btnOui);
        validationBox.getChildren().add(btnNon);
        rootVbox.getChildren().addAll(validation, validationBox);
        stage.sizeToScene();
        validation.setDisable(true);
        isValidationAdd = true;
      }


      if (validationIsDisable) {
        validationBox.setDisable(false);
        validation.setDisable(false);
      }
    });



    btnRetourAccueil.setOnAction((event) -> {
      // Instanciation et affichage de l'accueil
      VueAccueil vueAccueil = new VueAccueil();
      new CtrlAccueil(this.utilisateur, vueAccueil);
      stage.close();
      vueAccueil.show();
    });

    btnNon.setOnAction((event) -> {
      if (isValidationAdd) {
        validationBox.setDisable(true);
        validation.setDisable(true);
        validationIsDisable = true;
      }
    });

    btnModifier.setOnAction((event) -> {

    });


  }

  public void redirectionAccueil() {
    stage.close();

    Insets padding = new Insets(20);

    vboxRetourAccueil.setPadding(padding);
    btnRetourAccueil.setPadding(padding);
    labelSuppressionEffectuee.setPadding(padding);

    vboxRetourAccueil.setAlignment(Pos.CENTER);
    btnRetourAccueil.setAlignment(Pos.CENTER);
    labelSuppressionEffectuee.setAlignment(Pos.CENTER);

    labelSuppressionEffectuee.setFont(new Font("Arial", 20));

    vboxRetourAccueil.getChildren().addAll(labelSuppressionEffectuee, btnRetourAccueil);

    stageRedirection.setScene(sceneSuppEffectuee);
    stageRedirection.sizeToScene();
    stage.setTitle("RoMan");

    stageRedirection.show();
  }
}
