package fr.roman.dao;

import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Classe abstraite {@link DAO} qui définie les principales méthodes des classes filles.
 * Possède un attribut {@link Connection} pour la liaison avec la base de donnée.
 * Est paramétré d'un ({@link M modèle M}) de l'application et de l'énumération de ses attributs/champs ({@link C C}).
 */
public abstract class DAO<M, C extends Enum<C>> {

  /**
   * Objet {@link Connection} servant à établir la connection avec la base de donnée.
   */
  private Connection co;

  /**
   * Constructeur.
   *
   * @param co L'objet {@link Connection} obtenu à partir de la classe {@link SingletonConnection}.
   *           Sert à effectuer la connection avec la base de données.
   */
  public DAO(Connection co) {
    this.co = co;
  }

  /**
   * Permet de retrouver l'objet {@link Connection} servant à JDBC
   * @return
   */
  public Connection getCo() {
    return co;
  }

  /**
   * Entrée d'une ligne dans la table.
   *
   * @param o Un {@link M objet métier}.
   * @return {@link M L'objet métier} avec son identifiant ou null si l'insertion n'a pas eu lieu.
   */
  public abstract M insert(M o);

  /**
   * Mise à jour d'une ligne dans une table de la base.
   *
   * @param o Un {@link M objet métier.
   * @return True si la ligne a été modifiée, false sinon.
   */
  public abstract boolean update(M o);

  /**
   * Suppression de données dans la base
   *
   * @param id L'identifiant dans la base de donnée de la ligne à supprimer.
   * @return True si la ligne a été supprimée, false sinon.
   */
  public abstract boolean delete(int id);

  /**
   * Recherche de données dans une table de la base avec les attributs renseignés de l'objet métier.
   *
   * @param criteres Un objet HashMap<C, String> où ({@link C C} est la clé est le nom du critère
   *                 (énumération des champs dans l'objet métier) et la {@link String valeur} est celle du critère.
   * @return Une {@link Collection collection}d'{@link M objets métiers} qui correspond aux {@link C critères} mis en paramètre.
   */
  public abstract Collection<M> find(HashMap<C, String> criteres);

  /**
   * Recherche d'une ligne dans une table de la base à partir de sa clé primaire
   *
   * @param id L'identifiant de l'{@link M objet métier}.
   * @return L'{@link M objet métier} contenant les informations de la ligne.
   *         Renvoie null si la ligne n'a pas été trouvée.
   */
  public abstract M findById(int id);

  /**
   * Recherche de l'ensemble des données de la table.
   *
   * @return Une {@link Collection collection} d'objets de l'ensemble des données de la table.
   */
  public Collection<M> findAll(){
    // On réutilise la méthode find avec aucun critère comme paramètre
    return find(new HashMap<C, String>());
  }

  /**
   * Méthode permettant construire les critères de recherches (après la clause WHERE dans la requête SQL)
   *  pour la méthode {@link #find}.
   *
   * @param criteres Un objet {@link HashMap}<{@link C}, {@link String}> où {@link C C}, la clé, est le nom du critère
   *                 ({@link Enum<> énumération} des champs dans l'objet métier) et la {@link String valeur} est celle du critère.
   * @return Une {@link String chaine de caractères} correspondant à ce qu'il faut mettre après la clause WHERE.
   */
  String criteresPourWHERE(HashMap<C, String> criteres){
    // Réalisé à l'aide d'expressions régulières et de l'API Stream.
    return criteres.entrySet()
            .stream()
            .filter(c -> !c.getValue().isBlank()) // On retire les critères sans valeurs
            .map(c -> " AND " + c.getKey() + " LIKE '" + c.getValue() + "'")
            .collect(Collectors.joining()); // On concatène les conditions
  }
}