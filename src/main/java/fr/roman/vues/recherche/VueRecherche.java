package fr.roman.vues.recherche;

import fr.roman.controleurs.recherche.CtrlRecherche;
import fr.roman.modeles.Modele;
import fr.roman.vues.VueIntegrable;
import fr.roman.vues.composants.BoutonAction;
import fr.roman.vues.tableaux.Tableau;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Vue permettant la recherche d'objets métiers dans la base. Elle se compose d'un formulaire de
 * recherche et d'un tableau présentant les résultats.
 *
 * @param <T> Type d'objets métier à afficher
 *
 * @see CtrlRecherche
 *
 * @author Axel Leblanc
 */
public abstract class VueRecherche<T extends Modele> implements VueIntegrable {
  /**
   * URL de la feuille de style (CSS) de la vue.
   */
  private static final String STYLESHEET_URI = "file:src/main/resources/css/VuesRecherche.css";

  /**
   * Conteneur de la vue.
   */
  private final BorderPane conteneur;
  /**
   * Conteneur du panneau des filtres de recherche. Il peut être réduit.
   */
  private final TitledPane conteneurPanneauFiltres;
  /**
   * Panneau des filtres de recherche.
   */
  protected final GridPane panneauFiltres;
  /**
   * Tableau présentant les résultats de recherche.
   */
  private Tableau<T> tableau;
  /**
   * Conteneur des boutons affichés en bas de la vue.
   */
  private final HBox conteneurBoutons;

  /**
   * Contrôleur de la vue de recherche.
   */
  protected CtrlRecherche<T> ctrl;

  /**
   * Créer une vue de recherche.
   *
   * @see VueRecherche
   */
  public VueRecherche() {
    // Instanciation des éléments graphiques
    conteneur = new BorderPane();
    panneauFiltres = new GridPane();

    conteneurPanneauFiltres = new TitledPane("Filtres de recherche", panneauFiltres);
    conteneurPanneauFiltres.setExpanded(false);

    conteneurBoutons = new HBox();

    // Placement des éléments dans le conteneur principal
    conteneur.setTop(conteneurPanneauFiltres);
    conteneur.setBottom(conteneurBoutons);
    // Il manque le tableau : il sera inséré lors de l'appel de setTableau(...)

    appliquerStyles();
  }

  /**
   * Définir les classes CSS des éléments graphiques et appliquer les styles CSS.
   */
  private void appliquerStyles() {
    conteneur.getStylesheets().add(STYLESHEET_URI);
    conteneur.applyCss();

    conteneur.getStyleClass().add("conteneur");

    panneauFiltres.getStyleClass().add("panneau-filtres");

    conteneurBoutons.getStyleClass().add("conteneur-boutons");
  }

  @Override
  public Node getNode() {
    return conteneur;
  }

  /**
   * Définir le contrôleur qui gère la vue.
   *
   * @param ctrl Contrôleur
   */
  public void setCtrl(CtrlRecherche<T> ctrl) {
    this.ctrl = ctrl;
  }

  /**
   * Définir le tableau qui affichera les résultats de recherche.
   *
   * @param tableau Un objet {@link Tableau}
   */
  public void setTableau(Tableau<T> tableau) {
    this.tableau = tableau;
    conteneur.setCenter(this.tableau.getTableau());
  }

  /**
   * Retourne le tableau affichant les résultats de recherche.
   *
   * @return Un objet {@link Tableau}
   */
  public Tableau<T> getTableau() {
    return tableau;
  }

  /**
   * Définir les boutons affichés en bas de la vue.
   *
   * @param boutons Liste de boutons ({@link BoutonAction})
   */
  public void setBoutons(List<BoutonAction> boutons) {
    conteneurBoutons.getChildren().setAll(
        boutons.stream().map(BoutonAction::asButton).toList()
    );
  }

  /**
   * Appliquer les filtres de recherche. Cette méthode est appelée lorsqu'un filtre de recherche
   * a été modifié.
   */
  protected abstract void appliquerFiltres();
}
