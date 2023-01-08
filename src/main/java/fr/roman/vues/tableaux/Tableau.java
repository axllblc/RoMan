package fr.roman.vues.tableaux;

import fr.roman.modeles.Modele;
import fr.roman.vues.composants.BoutonAction;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

/**
 * Classe abstraite et générique englobant un tableau JavaFX ({@link TableView}) ainsi qu'une
 * collection d'objets métier (du même type) à présenter dans ce tableau.
 *
 * <p>Ce tableau peut être récupéré pour être intégré dans une vue à l'aide de la méthode
 * {@link #getTableau()}.</p>
 * <p>La collection d'objets métier ne peut pas contenir de doublons, c'est-à-dire des objets
 * métiers ayant le même identifiant (<i>cf.</i> {@link Modele#getId()}).</p>
 * <p>La collection peut être filtrée à l'aide de la méthode ({@link #setFiltre(Predicate)}).</p>
 *
 * @param <T> Type des objets métier affichés dans le tableau.
 *
 * @see TableView
 * @see Modele
 *
 * @author Axel Leblanc
 */
public abstract class Tableau<T extends Modele> {
  /**
   * Le tableau JavaFX ({@link TableView}).
   */
  private final TableView<T> tableau;
  /**
   * Collection des objets métier affichés dans le tableau. (1)
   */
  protected final ObservableList<T> contenu;
  /**
   * Contenu du tableau, avec un filtrage. (2)
   *
   * @see #contenu
   */
  private final FilteredList<T> contenuAvecFiltre;
  /**
   * Contenu du tableau, avec un tri (après avoir appliqué un filtrage). (3)
   *
   * @see #contenu
   * @see #contenuAvecFiltre
   */
  private final SortedList<T> contenuAvecTri;
  /**
   * Menu contextuel du tableau.
   */
  protected ContextMenu menu;

  /**
   * Construit un tableau pour l'affichage d'objets métier. Ce constructeur initialise un objet
   * {@link TableView}, qui peut être récupéré à l'aide de la méthode {@link #getTableau()},
   * ainsi que la collection des objets métier à afficher.
   *
   * @see Tableau
   * @see TableView
   */
  public Tableau() {
    tableau = new TableView<>();

    // Définition de la collection représentant le contenu du tableau. (1)
    contenu = FXCollections.observableArrayList();
    // Définition de la liste filtrée (FilteredList). (2)
    // Initialement, tous les objets de la collection sont affichés.
    contenuAvecFiltre = new FilteredList<>(contenu);
    // Définition de la liste triée (SortedList). (3)
    // Le comparateur de cette liste est lié à celui du tableau.
    contenuAvecTri = new SortedList<>(contenuAvecFiltre);
    contenuAvecTri.comparatorProperty().bind(tableau.comparatorProperty());
    // Enfin, la collection est intégrée au tableau.
    tableau.setItems(contenuAvecTri);

    // Définition du menu contextuel, appliqué sur les lignes du tableau
    menu = new ContextMenu();
    tableau.setRowFactory(tableView -> {
          TableRow<T> row = new TableRow<>();
          row.setContextMenu(menu);
          return row;
        }
    );
  }

  /**
   * Retourne le tableau JavaFX ({@link TableView}).
   *
   * @return Le tableau JavaFX ({@link TableView})
   *
   * @see TableView
   */
  public TableView<T> getTableau() {
    return tableau;
  }

  /**
   * Activer ou désactiver la sélection multiple dans le tableau.
   *
   * @param b {@code true} pour autoriser la sélection multiple, {@code false} pour ne permettre
   *                      la sélection que d'un seul élément à la fois.
   */
  public void autoriserSelectionMultiple(boolean b) {
    tableau.getSelectionModel().setSelectionMode(b ? SelectionMode.MULTIPLE : SelectionMode.SINGLE);
  }

  /**
   * Retourne l'objet sélectionné dans le tableau. Si plusieurs sont sélectionnés, le dernier
   * ajouté à la sélection est retourné.
   *
   * @return L'objet sélectionné dans le tableau
   */
  public T getSelectionSimple() {
    return tableau.getSelectionModel().getSelectedItem();
  }

  /**
   * Retourne les objets sélectionnés dans le tableau.
   *
   * @return Liste des objets sélectionnés dans le tableau
   */
  public List<T> getSelectionMultiple() {
    return tableau.getSelectionModel().getSelectedItems().stream().toList();
  }

  // Opérations sur la collection d'objets affichés

  /**
   * Vide le tableau puis ajoute au tableau la collection d'objets passée en paramètre.
   *
   * @param objets Nouveau contenu du tableau
   */
  public void setContenu(List<T> objets) {
    vider();
    definir(objets);
  }

  /**
   * Ajoute au tableau l'objet passé en paramètre. Si cet objet <i>A</i> possède le même
   * identifiant ({@link Modele#getId()}) qu'un objet <i>B</i> contenu dans le tableau, <i>B</i>
   * est remplacé par <i>A</i>.
   *
   * @param objetA Objet à ajouter au tableau.
   */
  public void definir(T objetA) {
    // Rechercher un objetB ayant le même identifiant que objetA
    Optional<T> objetB =
        contenu.stream().filter(objet -> objet.getId() == objetA.getId()).findFirst();

    if (objetB.isPresent()) {
      // Si cet objetB existe, remplacer objetB par objetA dans contenu
      contenu.set(contenu.indexOf(objetB.get()), objetA);
    } else {
      // Sinon, ajouter objetA à la fin de contenu
      contenu.add(objetA);
    }
    tableau.sort();
  }

  /**
   * Ajoute au tableau la collection d'objets passée en paramètre. Si un objet <i>A</i> possède le
   * même identifiant ({@link Modele#getId()}) qu'un objet <i>B</i> contenu dans le tableau,
   * <i>B</i> est remplacé par <i>A</i>.
   *
   * @param objets Objets à ajouter au tableau.
   */
  public void definir(List<T> objets) {
    for (T objetA : objets) {
      definir(objetA);
    }
  }

  /**
   * Retirer du tableau l'objet passé en paramètre.
   *
   * @param objet Objet à retirer du tableau
   */
  public void supprimer(T objet) {
    // Récupération de l'identifiant de l'objet à retirer
    int id = objet.getId();

    // Retrait de l'objet ayant l'identifiant récupéré précédemment
    contenu.removeIf(objetB -> objetB.getId() == id);

    tableau.sort();
  }

  /**
   * Retirer du tableau la collection d'objets passée en paramètre.
   *
   * @param objets Objets à retirer du tableau
   */
  public void supprimer(List<T> objets) {
    // Récupération des identifiants des objets à retirer
    List<Integer> ids = objets.stream().map(Modele::getId).toList();

    // Retrait des objets ayant les identifiants récupérés précédemment
    contenu.removeIf(objet -> ids.contains(objet.getId()));
  }

  /**
   * Vider le tableau.
   */
  public void vider() {
    contenu.clear();
  }

  /**
   * Définir le prédicat que les éléments de la collection devront satisfaire pour être affichés.
   *
   * @param predicat Le prédicat utilisé pour le filtrage.
   */
  public void setFiltre(Predicate<T> predicat) {
    contenuAvecFiltre.setPredicate(predicat);
  }

  /**
   * Définir les éléments ({@link BoutonAction}) du menu contextuel.
   *
   * @param items Éléments du menu contextuel
   */
  public void setMenu(List<BoutonAction> items) {
    menu.getItems().setAll(
        items.stream().map(BoutonAction::asMenuItem).toList()
    );
  }
}
