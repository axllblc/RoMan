package fr.roman.vues.metiers;

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
   * Constructeur de la classe abstraite des objets métiers, on y ajoutera
   * les éléments communs à tout les objets.
   */
  public VueMetier() {

    btnModifier = new Button("Modifier");
    btnSupprimer = new Button("Supprimer");
    btnOui = new Button("Oui");
    btnNon = new Button("Non");
    validation = new Label("Voulez vous vraiment supprimer cet élément ? ");
    hbox = new HBox();
    vbox = new VBox();
    validationBox = new HBox();
    rootVbox = new VBox();
    introductionBox = new VBox();
    isValidationAdd = false;
    validationIsDisable = true;
    scene = new Scene(rootVbox);
    rootVbox.getChildren().addAll(introductionBox, vbox, hbox);
    stage = new Stage();

  }

  /**
   * Permet de structurer la scène.
   *
   * @param labels tableau de label qui contiendra tout les lables à ajouter à la scène.
   */
  public void structure(ArrayList<Label> labels, Label introduction) {

    introductionBox.getChildren().add(introduction);
    // (int numLabel = 0; numLabel  < labels.length; numLabel++)
    for (Label label : labels) {
      vbox.getChildren().add(label);
      label.setFont(new Font("Arial", 30));
    }
    hbox.getChildren().add(btnModifier);
    hbox.getChildren().add(btnSupprimer);

    // Gestion espacements + alignement des éléments
    introduction.setFont(new Font("Arial", 40));
    introductionBox.setAlignment(Pos.TOP_CENTER);

    vbox.setSpacing(20);
    Insets padding = new Insets(20);
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
    stage.setMinWidth(1024);
    stage.setMinHeight(660);


    // Affichage scène
    stage.show();

    btnSupprimer.setOnAction((event) -> {
      if (!isValidationAdd) {
        validationBox.getChildren().add(btnOui);
        validationBox.getChildren().add(btnNon);
        rootVbox.getChildren().addAll(validation, validationBox);
        validation.setDisable(true);
        isValidationAdd = true;
      }

      if (validationIsDisable) {
        validationBox.setDisable(false);
        validation.setDisable(false);
      }
    });

    btnNon.setOnAction((event) -> {
      if (isValidationAdd) {
        validationBox.setDisable(true);
        validation.setDisable(true);
        validationIsDisable = true;
      }
    });




  }
}
