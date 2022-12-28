package fr.roman.dao;

import fr.roman.modeles.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
* DAO pour la classe Vehicule.
*/
public class DAOVehicule extends DAO<Vehicule, Vehicule.Champs> {

  /**
   * Constructeur.
   */
  public DAOVehicule() throws Exception {
    super();
  }

  /**
   * Ajout d'un véhicule dans la base.
   *
   * @param v Un objet Vehicule.
   * @return L'identifiant du véhicule ajouté. -1 en cas d'échec.
   */
  @Override
  public Vehicule insert(Vehicule v) {
    try {
      // Le véhicule est nécessairement associée à un prodcuteur et a une immatriculation
      if(v.getProducteur() == null || v.getImmatriculation() == null){
        return null;
      }
      // La requête
      PreparedStatement req = this.getCo().prepareStatement("INSERT INTO vehicules " +
                      "(immatriculation, poidsMax, libelle, idProducteur) " +
                      "VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
      // L'ajout des valeurs
      req.setString(1, v.getImmatriculation());
      req.setInt(2, v.getPoidsMax());
      req.setString(3, v.getLibelle());
      req.setInt(4, v.getProducteur().getIdProducteur());
      // L'exécution de la requête
      System.out.println(req);
      req.execute();
      // Récupération de la clé primaire
      ResultSet rs = req.getGeneratedKeys();
      if(rs.next()){
        // Si l'ajout a eu lieu, on retourne l'objet utilisateur avec son identifiant
        return new Vehicule(rs.getInt(1), v.getImmatriculation(), v.getPoidsMax(),
                v.getLibelle(), v.getProducteur());
      }
      // En cas d'échec de l'ajout, on ne renvoie rien
      return null;
    } catch (Exception e) { // En cas d'échec de la requête on ne renvoie rien
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Mise à jour d'un véhicule dans la base.
   *
   * @param v Un objet Vehicule.
   * @return True si le véhicule a été modifié, false sinon.
   */
  @Override
  public boolean update(Vehicule v) {
    try {
      PreparedStatement req  = this.getCo().prepareStatement("UPDATE vehicules " +
              "SET immatriculation = ?, poidsMax = ?, libelle = ?, idProducteur = ? " +
              "WHERE idVehicule = ?");
      req.setString(1, v.getImmatriculation());
      req.setInt(2, v.getPoidsMax());
      req.setString(3, v.getLibelle());
      req.setInt(4, v.getProducteur().getIdProducteur());
      req.setInt(5, v.getIdVehicule());
      // L'exécution de la requête
      req.execute();
      return true;
    } catch (SQLException e) { // En cas d'échec de la requête
      return false;
    }
  }

  /**
   * Suppression d'un véhicule dans la base
   *
   * @param id L'identifiant du véhicule à supprimer.
   * @return True si le véhicule a été supprimé, false sinon.
   */
  @Override
  public boolean delete(int id) {
    try {
      PreparedStatement req = this.getCo().prepareStatement("DELETE FROM vehicules WHERE idVehicule = ?");
      req.setInt(1, id);
      System.out.println(req);
      // Si l'entrée a été supprimée, on retourne true
      return req.executeUpdate() == 1;
      // Sinon, on retourne false
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Recherche de véhicules dans la base avec les attributs renseignés de l'objet métier.
   *
   * @param criteres Les critères de recherche de véhicules.
   * @return Une collection d'objets qui correspond aux critères mis en paramètre.
   */
  @Override
  public ArrayList<Vehicule> find(HashMap<Vehicule.Champs, String> criteres) {
    PreparedStatement req;
    try {
      // On fait une requête avec les critères de recherche
      req = this.getCo().prepareStatement("SELECT * FROM vehicules WHERE 1=1 " +
              criteresPourWHERE(criteres));
      // On récupère le résultat
      ResultSet rs = req.executeQuery();
      // On les stockera dans un ArrayList de commandes
      ArrayList<Vehicule> vehicules = new ArrayList<Vehicule>();
      // On aura besoin de créer un objet Producteur
      DAOProducteur daoP = new DAOProducteur();
      Producteur producteur;
      while (rs.next()) {
        // Tant qu'il y a des lignes dans le résultat
        producteur = daoP.findById(Integer.parseInt(rs.getString("idProducteur")));

        vehicules.add(new Vehicule(rs.getInt("idVehicule"),
                rs.getString("immatriculation"),
                rs.getInt("poidsMax"), rs.getString("libelle"), producteur));
      }
      return vehicules;
    } catch (Exception e) {
      // On renvoie un ArrayList vide si la requête n'a pas pu être effectuée correctement.
      return new ArrayList<Vehicule>();
    }
  }

  /**
   * Recherche d'un véhicule à partir de sa clé primaire
   *
   * @param id L'identifiant du véhicule.
   * @return L'objet Vehicule contenant les informations de la ligne.
   * Renvoie null si le véhicule n'a pas été trouvé.
   */
  @Override
  public Vehicule findById(int id) {
    // On réutilise la méthode find avec comme seul critère l'identifiant
    HashMap<Vehicule.Champs, String> criteres = new HashMap<Vehicule.Champs, String>();
    criteres.put(Vehicule.Champs.idVehicule, String.valueOf(id));
    ArrayList<Vehicule> resultatRecherche = find(criteres);
    if(resultatRecherche.isEmpty()){
      return null;
    }
    return resultatRecherche.get(0);
  }
}