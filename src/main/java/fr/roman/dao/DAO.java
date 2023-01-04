package fr.roman.dao;

import fr.roman.modeles.Modele;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

/**
 * Classe abstraite {@link DAO} qui définie les principales méthodes des classes filles.
 * Possède un attribut {@link Connection} pour la liaison avec la base de donnée.
 *
 * @param <M> Est paramétré d'un {@link fr.roman.modeles.Modele modèle} de l'application
 * @param <C> Est paramétré de l'énumération de ses attributs/champs.
 */
public abstract class DAO<M extends Modele, C extends Enum<C>> {
  /**
   * Objet {@link Connection} servant à établir la connection avec la base de donnée.
   */
  private final Connection co;

  /**
   * Constructeur.
   */
  public DAO() throws Exception {
    this.co = SingletonConnection.getInstance();
  }

  /**
   * Permet de retrouver l'objet {@link Connection} servant à JDBC.
   *
   * @return L'objet {@link Connection}
   */
  public Connection getCo() {
    return co;
  }

  /**
   * Entrée d'une ligne dans la table.
   *
   * @param o Un {@link fr.roman.modeles objet métier}.
   * @return {@link M L'objet métier} avec son identifiant ou null si l'insertion n'a pas eu lieu.
   */
  public abstract M insert(M o) throws Exception;

  /**
   * Mise à jour d'une ligne dans une table de la base.
   *
   * @param o Un {@link fr.roman.modeles objet métier.}
   * @return True si la ligne a été modifiée, false sinon.
   */
  public abstract boolean update(M o) throws Exception;

  /**
   * Suppression de données dans la base.
   *
   * @param id L'identifiant dans la base de donnée de la ligne à supprimer.
   * @return True si la ligne a été supprimée, false sinon.
   */
  public abstract boolean delete(int id) throws Exception;

  /**
   * Recherche de données dans une table de la base avec les attributs renseignés de l'objet métier.
   *
   * @param criteres Un objet HashMap<C, String> où ({@link C C} est la clé est le nom du critère
   *                 (énumération des champs dans l'objet métier)
   *                 et la {@link String valeur} est celle du critère.
   * @return Un {@link ArrayList}d' {@link fr.roman.modeles objets métiers}
   *         qui correspond aux {@link C critères} mis en paramètre.
   */
  public abstract ArrayList<M> find(LinkedHashMap<C, String> criteres) throws Exception;

  /**
   * Recherche d'une ligne dans une table de la base à partir de sa clé primaire.
   *
   * @param id L'identifiant de l'{@link fr.roman.modeles objet métier}.
   * @return L'{@link M objet métier} contenant les informations de la ligne.
   *         Renvoie null si la ligne n'a pas été trouvée.
   */
  public abstract M findById(int id) throws Exception;

  /**
   * Recherche de l'ensemble des données de la table.
   *
   * @return Un {@link ArrayList} d'objets de l'ensemble des données de la table.
   */
  public ArrayList<M> findAll() throws Exception {
    // On réutilise la méthode find avec aucun critère comme paramètre
    return find(new LinkedHashMap<>());
  }

  /**
   * Méthode permettant construire les critères de recherches
   * (après la clause WHERE dans la requête SQL) pour la méthode {@link #find}.
   *
   * @param criteres Un objet {@link LinkedHashMap} où {@link C C},
   *                 la clé, est le nom du critère ({@link Enum énumération} des champs dans
   *                 l'objet métier) et la {@link String valeur} est celle du critère.
   * @return         Une {@link String chaine de caractères} correspondant
   *                 à ce qu'il faut mettre après la clause WHERE.
   */
  String criteresPourWHERE(final LinkedHashMap<C, String> criteres) {
    // Réalisé à l'aide d'expressions régulières et de l'API Stream.
    return criteres.entrySet()
            .stream()
            .filter(c -> !c.getValue().isBlank()) // On retire les critères sans valeurs
            .map(c -> " AND " + c.getKey() + " LIKE ?")
            .collect(Collectors.joining()); // On concatène les conditions
  }
}
