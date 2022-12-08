package fr.roman.dao;

import fr.roman.modeles.Modele;

import java.sql.Connection;
import java.util.Collection;

/**
 * Classe abstraite DAO qui définie les principales méthodes des classes filles.
 * Possède un attribut Connection pour la liaison avec la base de donnée.
 * Est paramétré d'un modèle de l'application.
 */
public abstract class DAO<M extends Modele> {

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
   * @return L'identifiant de la ligne ajoutée. -1 en cas d'échec.
   */
  public abstract int insert(M o);

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
   * @param o Un objet métier.
   * @return Une collection d'objets qui correspond aux critères mis en paramètre.
   */
  public abstract Collection<M> find(M o);

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
  public abstract Collection<M> findAll();
}