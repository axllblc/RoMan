package fr.roman.vues.accueil;

import fr.roman.modeles.Utilisateur;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Vue de base pour l'accueil. Celle-ci accueille un ensemble d'onglets, dépendamment du rôle de
 * l'utilisateur.
 *
 * @author Axel Leblanc
 */
public class VueAccueil {
  /**
   * Icône représentant un compte utilisateur.
   */
  private static final Image icCompte =
      new Image("file:src/main/resources/icons/account_circle_24.png");

  /**
   * Utilisateur de l'application. Permet d'adapter le contenu de l'accueil à ses informations.
   */
  private final Utilisateur utilisateur;

  private final Scene scene;
  private Stage stage;

  /**
   * Conteneur principal de la vue.
   */
  private final BorderPane conteneur;
  /**
   * Conteneur de l'en-tête de la vue.
   */
  private final BorderPane entete;
  /**
   * Label accueillant l'utilisateur avec une formule de politesse et son nom.
   */
  private final Label titre;
  /**
   * Bouton pour accéder à la gestion du compte de l'utilisateur.
   */
  private final Button btnCompte;
  /**
   * Panneau principal de la vue <i>Accueil</i>, organisé sous la forme d'onglets.
   */
  private final TabPane tabPane;

  public VueAccueil(Utilisateur utilisateur) {
    this.utilisateur = utilisateur;

    String texteTitre =
        "Bonjour %s %s !".formatted(utilisateur.getPrenom(), utilisateur.getNom());

    // Définition des éléments de la vue
    conteneur = new BorderPane();
    entete = new BorderPane();
    titre = new Label(texteTitre);
    btnCompte = new Button("Mon compte", new ImageView(icCompte));
    tabPane = new TabPane();

    scene = new Scene(conteneur);

    structureAccueil();
  }

  /**
   * Établir la structure de base de la vue <i>Accueil</i> : créer une {@code Scene} et lui
   * ajouter des éléments (titre, bouton de gestion du compte, panneau d'onglets).
   */
  private void structureAccueil() {
    // Placement des éléments
    entete.setLeft(titre);
    entete.setRight(btnCompte);
    conteneur.setTop(entete);
    conteneur.setCenter(tabPane);

    // Ajout des onglets
    listeOnglets();

    // Marges, espacement, alignement
    conteneur.setPadding(new Insets(15));
    entete.setPadding(new Insets(0, 0, 10, 0));

    // Mise en forme et divers
    titre.setFont(Font.font("", FontWeight.BOLD, 24));
    btnCompte.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    btnCompte.setBackground(Background.EMPTY);
    btnCompte.setTooltip(new Tooltip("Mon compte"));
  }

  /**
   * Établir la liste des onglets affichés dans la vue <i>Accueil</i>, en fonction du rôle de
   * l'utilisateur.
   */
  private void listeOnglets() {
    switch (utilisateur.getRole()) {
      case ROOT:
        tabPane.getTabs().addAll(
            new Tab("Gestion des administrateurs")
        );
        // L'utilisateur ROOT hérite des privilèges administrateur standard
      case ADMINISTRATEUR:
        tabPane.getTabs().addAll(
            new Tab("Producteurs"),
            new Tab("Clients")
        );
        break;
      case PRODUCTEUR:
        tabPane.getTabs().addAll(
            new Tab("Tableau de bord"),
            new Tab("Commandes"),
            new Tab("Tournées")
        );
        break;
    }

    // Empêcher la fermeture des onglets
    tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
  }

  /**
   * Créer un {@code Stage} contenant la vue <i>Accueil</i> et l'afficher.
   */
  public void show() {
    stage = new Stage();
    stage.setScene(scene);
    stage.setTitle("Accueil – RoMan");

    stage.setMinWidth(1024);
    stage.setMinHeight(660);

    stage.show();
  }

  /**
   * Fermer le {@code Stage} contenant la vue <i>Accueil</i>
   */
  public void close() {
    stage.close();
  }
}
