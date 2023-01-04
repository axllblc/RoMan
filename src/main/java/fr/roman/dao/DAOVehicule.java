package fr.roman.dao;

import fr.roman.modeles.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

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
   * @return Un objet Vehicule avec son identifiant.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public Vehicule insert(Vehicule v) throws Exception {
    // Le véhicule est nécessairement associé à un producteur et a une immatriculation
    if (v.getProducteur() == null || v.getImmatriculation() == null) {
      throw new Exception("Producteur et/ou immatriculation non renseigné");
    }
    // On vérifie que l'immatriculation n'est pas déjà existante dans la base
    LinkedHashMap<Vehicule.Champs, String> critereImmatriculation = new LinkedHashMap<>();
    critereImmatriculation.put(Vehicule.Champs.immatriculation, v.getImmatriculation());
    if (!find(critereImmatriculation).isEmpty()) {
      throw new Exception("Immatriculation déjà renseignée");
    }
    // La requête
    String sql = "INSERT INTO vehicules (immatriculation, poidsMax, libelle, idProducteur) "
            + "VALUES (?,?,?,?)";
    try (PreparedStatement req = this.getCo().prepareStatement(sql,
            PreparedStatement.RETURN_GENERATED_KEYS)) {
      // L'ajout des valeurs
      req.setString(1, v.getImmatriculation());
      req.setInt(2, v.getPoidsMax());
      req.setString(3, v.getLibelle());
      req.setObject(4, v.getProducteur() != null ? v.getProducteur().getIdProducteur() : null);
      // L'exécution de la requête
      req.execute();
      // Récupération de la clé primaire
      ResultSet rs = req.getGeneratedKeys();
      if (rs.next()) {
        // Si l'ajout a eu lieu, on retourne l'objet utilisateur avec son identifiant
        return new Vehicule(rs.getInt(1), v.getImmatriculation(),
                v.getPoidsMax(), v.getLibelle(), v.getProducteur());
      }
      // En cas d'échec de l'ajout
      throw new Exception("Erreur dans l'ajout du véhicule'");
    }
  }

  /**
   * Mise à jour d'un véhicule dans la base.
   *
   * @param v Un objet Vehicule.
   * @return True si le véhicule a été modifié, false sinon.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public boolean update(Vehicule v) throws Exception {
    // Le véhicule est nécessairement associé à un producteur et a une immatriculation
    if (v.getProducteur() == null || v.getImmatriculation() == null) {
      throw new Exception("Producteur et/ou immatriculation non renseigné");
    }
    // On vérifie que l'immatriculation (si modifiée) n'est pas déjà existante dans la base
    LinkedHashMap<Vehicule.Champs, String> critereImmatriculation = new LinkedHashMap<>();
    critereImmatriculation.put(Vehicule.Champs.immatriculation, v.getImmatriculation());
    ArrayList<Vehicule> resRech = find(critereImmatriculation);
    if (!resRech.isEmpty() && resRech.get(0).getIdVehicule() != v.getIdVehicule()) {
      throw new Exception("Immatriculation déjà renseignée");
    }
    String sql = "UPDATE vehicules SET immatriculation = ?, poidsMax = ?, "
            + "libelle = ?, idProducteur = ? WHERE idVehicule = ?";
    try (PreparedStatement req  = this.getCo().prepareStatement(sql)) {
      req.setString(1, v.getImmatriculation());
      req.setInt(2, v.getPoidsMax());
      req.setString(3, v.getLibelle());
      req.setObject(4, v.getProducteur() != null ? v.getProducteur().getIdProducteur() : null);
      req.setInt(5, v.getIdVehicule());
      // L'exécution de la requête
      return (req.executeUpdate() > 0);
    }
  }

  /**
   * Suppression d'un véhicule dans la base.
   *
   * @param id L'identifiant du véhicule à supprimer.
   * @return True si le véhicule a été supprimé, false sinon.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public boolean delete(int id) throws SQLException {
    String sql = "DELETE FROM vehicules WHERE idVehicule = ?";
    try (PreparedStatement req = this.getCo().prepareStatement(sql)) {
      req.setInt(1, id);
      // Si l'entrée a été supprimée, on retourne true
      return req.executeUpdate() == 1;
      // Sinon, on retourne false
    }
  }

  /**
   * Recherche de véhicules dans la base avec les attributs renseignés de l'objet métier.
   *
   * @param criteres Les critères de recherche de véhicules.
   * @return Une collection d'objets qui correspond aux critères mis en paramètre.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public ArrayList<Vehicule> find(LinkedHashMap<Vehicule.Champs, String> criteres) throws Exception {
    // On fait une requête avec les critères de recherche
    String sql = "SELECT * FROM vehicules WHERE 1=1 " + criteresPourWHERE(criteres);
    try (PreparedStatement req = this.getCo().prepareStatement(sql)) {
      int noCritere = 1;
      for (String critere : criteres.values()) {
        req.setString(noCritere, critere);
        noCritere++;
      }
      // On récupère le résultat
      ResultSet rs = req.executeQuery();
      // On les stockera dans un ArrayList de commandes
      ArrayList<Vehicule> vehicules = new ArrayList<>();
      // On aura besoin de créer un objet Producteur
      DAOProducteur daoP = new DAOProducteur();
      Producteur producteur;
      while (rs.next()) {
        // Tant qu'il y a des lignes dans le résultat
        producteur = daoP.findById(rs.getInt("idProducteur"));

        vehicules.add(new Vehicule(rs.getInt("idVehicule"),
                rs.getString("immatriculation"),
                rs.getInt("poidsMax"), rs.getString("libelle"), producteur));
      }
      return vehicules;
    }
  }

  /**
   * Recherche d'un véhicule à partir de sa clé primaire.
   *
   * @param id L'identifiant du véhicule.
   * @return L'objet Vehicule contenant les informations de la ligne.
   * Renvoie null si le véhicule n'a pas été trouvé.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public Vehicule findById(int id) throws Exception {
    // On réutilise la méthode find avec comme seul critère l'identifiant
    LinkedHashMap<Vehicule.Champs, String> criteres = new LinkedHashMap<>();
    criteres.put(Vehicule.Champs.idVehicule, String.valueOf(id));
    ArrayList<Vehicule> resultatRecherche = find(criteres);
    if (resultatRecherche.isEmpty()) {
      return null;
    }
    return resultatRecherche.get(0);
  }
}