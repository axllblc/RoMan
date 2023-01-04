package fr.roman.vues.accueil;

import fr.roman.controleurs.accueil.CtrlTabBordProducteur;
import fr.roman.modeles.ModuleApplication;
import fr.roman.vues.composants.FabriqueIcone;
import fr.roman.vues.composants.Icone;
import fr.roman.vues.tableaux.TableauCommandes;
import fr.roman.vues.tableaux.TableauTournees;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Tableau de bord du producteur, présentant les commandes à livrer et les tournées à effectuer.
 * Cette vue est intégrée à la vue <i>Accueil</i>.
 *
 * @see VueAccueil
 *
 * @author Axel Leblanc
 */
public class TableauDeBordProducteur implements VueOngletAccueil {
  /**
   * URL de la feuille de styles (CSS) du tableau de bord.
   */
  private static final String STYLESHEET_URI = "file:src/main/resources/css/TableauDeBord.css";
  private static final String AUCUNE_COMMANDE = """
      Aucune commande à venir. Pour rechercher des commandes passées, cliquez sur le bouton "Gérer les commandes".""";
  private static final String AUCUNE_TOURNEE = """
      Aucune tournée planifiée. Pour rechercher d'autres tournées, cliquez sur le bouton "Gérer les tournées".""";

  /**
   * Vue qui accueille le tableau de bord ({@link VueAccueil}).
   */
  private final VueAccueil parent;
  /**
   * Contrôleur de la vue <i>Tableau de bord</i>.
   */
  private CtrlTabBordProducteur ctrl;

  /**
   * Conteneur principal de la vue <i>Tableau de bord</i>.
   */
  private final VBox node;
  /**
   * Label de titre de la section <i>Commandes</i> du tableau de bord.
   */
  private final Label labelCommandes;
  /**
   * Label de titre de la section <i>Tournées</i> du tableau de bord.
   */
  private final Label labelTournees;
  private final TableauCommandes tableauCommandes;
  private final TableauTournees tableauTournees;
  /**
   * Conteneur pour les boutons d'ajout et de gestion des commandes, situé en bas de la section
   * <i>Commandes</i>.
   */
  private final HBox conteneurBtnCommandes;
  /**
   * Conteneur pour les boutons d'ajout et de gestion des tournées, situé en bas de la section
   * <i>Tournées</i>.
   */
  private final HBox conteneurBtnTournees;
  /**
   * Bouton pour la création d'une nouvelle commande.
   */
  private final Button btnNouvelleCommande;
  /**
   * Bouton pour la création d'une nouvelle tournée.
   */
  private final Button btnNouvelleTournee;
  /**
   * Bouton permettant de basculer vers l'onglet <i>Commandes</i> de l'accueil.
   */
  private final Button btnGestionCommandes;
  /**
   * Bouton permettant de basculer vers l'onglet <i>Tournées</i> de l'accueil.
   */
  private final Button btnGestionTournees;

  /**
   * Constructeur du tableau de bord du producteur.
   *
   * @param parent Vue qui accueille le tableau de bord ({@link VueAccueil})
   */
  public TableauDeBordProducteur(VueAccueil parent) {
    this.parent = parent;

    // Instanciation des éléments graphiques
    node = new VBox();

    labelCommandes = new Label("Commandes à venir");
    labelTournees = new Label("Tournées planifiées");

    tableauCommandes = new TableauCommandes();
    tableauTournees = new TableauTournees();

    conteneurBtnCommandes = new HBox();
    conteneurBtnTournees = new HBox();

    btnNouvelleCommande = new Button("Nouvelle commande", FabriqueIcone.get(Icone.NOUVEAU));
    btnNouvelleTournee = new Button("Nouvelle tournée", FabriqueIcone.get(Icone.NOUVEAU));

    btnGestionCommandes = new Button("Gérer les commandes");
    btnGestionTournees = new Button("Gérer les tournées");

    structureVue();
    appliquerStyles();
    definirGestionnairesEvenements();
  }

  /**
   * Établir la structure de la vue <i>Tableau de bord</i> en plaçant les éléments graphiques
   * dans {@link #node}.
   */
  private void structureVue() {
    // Placement des éléments
    conteneurBtnCommandes.getChildren().addAll(btnNouvelleCommande, btnGestionCommandes);
    conteneurBtnTournees.getChildren().addAll(btnNouvelleTournee, btnGestionTournees);

    node.getChildren().addAll(
        labelCommandes, tableauCommandes.getTableau(), conteneurBtnCommandes,
        new Separator(),
        labelTournees, tableauTournees.getTableau(), conteneurBtnTournees
    );

    // Texte affiché s'il n'y a aucune donnée dans le tableau
    tableauCommandes.getTableau().setPlaceholder(new Label(AUCUNE_COMMANDE));
    tableauTournees.getTableau().setPlaceholder(new Label(AUCUNE_TOURNEE));
  }

  /**
   * Définir les classes CSS des éléments graphiques et appliquer les styles CSS.
   */
  private void appliquerStyles() {
    node.getStylesheets().add(STYLESHEET_URI);
    node.applyCss();

    node.getStyleClass().add("node");

    labelCommandes.getStyleClass().add("titre-section");
    labelTournees.getStyleClass().add("titre-section");

    conteneurBtnCommandes.getStyleClass().add("conteneur-btn");
    conteneurBtnTournees.getStyleClass().add("conteneur-btn");
  }

  /**
   * Définir les gestionnaires d'événements des éléments graphiques de la vue.
   */
  private void definirGestionnairesEvenements() {
    // Boutons permettant de basculer vers un autre onglet
    btnGestionCommandes.setOnAction(event -> parent.afficherOnglet(ModuleApplication.COMMANDES));
    btnGestionTournees.setOnAction(event -> parent.afficherOnglet(ModuleApplication.TOURNEES));

    btnNouvelleCommande.setOnAction(event -> ctrl.nouvelleCommande());
    btnNouvelleTournee.setOnAction(event -> ctrl.nouvelleTournee());
  }

  @Override
  public Node getNode() {
    return node;
  }

  /**
   * Affectation du contrôleur de la vue <i>Tableau de bord</i>.
   *
   * @param ctrl Contrôleur à affecter
   */
  public void setCtrl(CtrlTabBordProducteur ctrl) {
    this.ctrl = ctrl;
  }

  public TableauCommandes getTableauCommandes() {
    return tableauCommandes;
  }

  public TableauTournees getTableauTournees() {
    return tableauTournees;
  }
}
