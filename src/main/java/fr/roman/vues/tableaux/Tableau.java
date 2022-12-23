package fr.roman.vues.tableaux;

import fr.roman.modeles.Modele;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Classe abstraite et générique englobant un tableau JavaFX ({@link TableView}) ainsi qu'une
 * collection d'objets métier (du même type) à présenter dans ce tableau.
 *
 * <p>Ce tableau peut être récupéré pour être intégré dans une vue à l'aide de la méthode
 * {@link #getTableau()}.</p>
 * <p>La collection d'objets métier ne peut pas contenir de doublons, c'est-à-dire des objets
 * métiers ayant le même identifiant (<i>cf.</i> {@link Modele#getId()}).</p>
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
   * Collection des objets métier affichés dans le tableau.
   */
  protected final ObservableList<T> contenu;

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
    contenu = FXCollections.observableArrayList();
    tableau.setItems(contenu);
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

  // Opérations sur la collection d'objets affichés

  /**
   * Vide le tableau puis ajoute au tableau la collection d'objets passée en paramètre.
   *
   * @param objets Nouveau contenu du tableau
   */
  public void setContenu(List<T> objets) {
    contenu.setAll(objets);
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
}
