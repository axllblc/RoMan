package fr.roman.vues.accueil;

import fr.roman.controleurs.accueil.CtrlAccueil;
import fr.roman.modeles.ModuleApplication;
import fr.roman.modeles.Utilisateur;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Vue de base pour l'accueil. Celle-ci accueille un ensemble d'onglets, défini selon le rôle de
 * l'utilisateur.
 *
 * @see ModuleApplication
 * @see fr.roman.controleurs.accueil.CtrlAccueil
 *
 * @author Axel Leblanc
 */
public class VueAccueil {
  /**
   * Icône représentant un compte utilisateur.
   */
  private static final Image IC_COMPTE =
      new Image("file:src/main/resources/icons/account_circle_24.png");

  /**
   * Contrôleur de la vue <i>Accueil</i>.
   */
  private CtrlAccueil ctrl = null;

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
   * Label affiché en haut de la vue pour accueillir l'utilisateur.
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

  /**
   * Modules disponibles pour l'utilisateur, associés à l'onglet ({@link Tab}) qui leur correspond.
   */
  private final Map<ModuleApplication, Tab> ongletsAccueil;

  /**
   * Construire la vue <i>Accueil</i>.
   */
  public VueAccueil() {
    ongletsAccueil = new HashMap<>();

    // Définition des éléments de la vue
    conteneur = new BorderPane();
    entete = new BorderPane();
    titre = new Label("Bonjour !");
    btnCompte = new Button("Mon compte", new ImageView(IC_COMPTE));
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

    // Marges, espacement, alignement
    conteneur.setPadding(new Insets(15));
    entete.setPadding(new Insets(0, 0, 10, 0));

    // Mise en forme et divers
    titre.setFont(Font.font("", FontWeight.BOLD, 24));
    btnCompte.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    btnCompte.setBackground(Background.EMPTY);
    btnCompte.setTooltip(new Tooltip("Mon compte"));

    // Empêcher la fermeture des onglets
    tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
  }

  /**
   * Affectation du contrôleur de la vue.
   *
   * @param ctrl Contrôleur à affecter
   */
  public void setCtrl(CtrlAccueil ctrl) {
    this.ctrl = ctrl;
  }

  /**
   * Définir l'utilisateur dont les informations doivent être affichées dans la vue. L'objet
   * métier {@link Utilisateur} n'est utilisé qu'à des fins d'affichage (interrogation uniquement).
   *
   * @param utilisateur Utilisateur dont les informations doivent être affichées
   */
  public void afficherInfosUtilisateur(Utilisateur utilisateur) {
    // Titre affiché en haut de la vue
    titre.setText("Bonjour %s %s !".formatted(utilisateur.getPrenom(), utilisateur.getNom()));
  }

  /**
   * Définir les modules qui seront affichés (sous la forme d'onglets), associés à la vue
   * qui leur correspond.
   *
   * @param mapModuleVue Modules associés à la vue qui leur correspond.
   */
  public void afficherModules(Map<ModuleApplication, VueOngletAccueil> mapModuleVue) {
    for (ModuleApplication module : mapModuleVue.keySet()) {
      Tab onglet = new Tab(module.getTitre(), mapModuleVue.get(module).getNode());
      onglet.setTooltip(new Tooltip(module.getDescription()));

      ongletsAccueil.put(module, onglet);

      tabPane.getTabs().add(onglet);
    }
  }

  /**
   * Basculer vers l'onglet de la vue <i>Accueil</i> correspondant au module passé en paramètre.
   *
   * <p><b>Exemple :</b> {@code afficherOnglet(ModuleAccueil.COMMANDES)} permet de sélectionner
   * l'onglet du module de gestion des commandes.</p>
   *
   * <p>Si le module n'est pas disponible pour l'utilisateur actuellement connecté, la méthode ne
   * fait rien.</p>
   *
   * @param module Module correspondant à l'onglet à afficher
   */
  public void afficherOnglet(ModuleApplication module) {
    if (ongletsAccueil.containsKey(module)) {
      tabPane.getSelectionModel().select(ongletsAccueil.get(module));
    }
  }

  /**
   * Créer un {@link Stage} contenant la vue <i>Accueil</i> et l'afficher.
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
   * Fermer le {@link Stage} contenant la vue <i>Accueil</i>.
   */
  public void close() {
    stage.close();
  }
}
