package fr.roman.dao;

import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Classe abstraite DAO qui définie les principales méthodes des classes filles.
 * Possède un attribut Connection pour la liaison avec la base de donnée.
 * Est paramétré d'un modèle (M) de l'application et de l'énumération de ses attributs/champs (C).
 */
public abstract class DAO<M, C extends Enum<C>> {

  private Connection co;

  /**
   * Constructeur.
   *
   * @param co L'objet Connection obtenu à partir de la classe SingletonConnection.
   *           Sert à effectuer la connection avec la base de données.
   */
  public DAO(Connection co) {
    this.co = co;
  }

  public Connection getCo() {
    return co;
  }

  /**
   * Entrée d'une ligne dans la table.
   *
   * @param o Un objet métier.
   * @return L'objet métier avec son identifiant ou null si l'insertion n'a pas eu lieu.
   */
  public abstract M insert(M o);

  /**
   * Mise à jour d'une ligne dans une table de la base.
   *
   * @param o Un objet métier.
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
   * @param criteres Un objet HashMap<C, String> où la clé est le nom du critère
   *                 (énumération des champs dans l'objet métier) et la valeur est celle du critère.
   * @return Une collection d'objets qui correspond aux critères mis en paramètre.
   */
  public abstract Collection<M> find(HashMap<C, String> criteres);

  /**
   * Recherche d'une ligne dans une table de la base à partir de sa clé primaire
   *
   * @param id L'identifiant de l'objet métier.
   * @return L'objet métier contenant les informations de la ligne.
   *         Renvoie null si la ligne n'a pas été trouvée.
   */
  public abstract M findById(int id);

  /**
   * Recherche de l'ensemble des données de la table.
   *
   * @return Une collection d'objets de l'ensemble des données de la table.
   */
  public Collection<M> findAll(){
    // On réutilise la méthode find avec aucun critère comme paramètre
    return find(new HashMap<C, String>());
  }

  String criteresPourWHERE(HashMap<C, String> criteres){

    return criteres.entrySet()
            .stream()
            .filter(c -> !c.getValue().isBlank())
            .map(c -> " AND " + c.getKey() + " LIKE '" + c.getValue() + "'")
            .collect(Collectors.joining());
  }
}