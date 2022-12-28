package fr.roman.dao;

import fr.roman.modeles.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
* DAO pour la classe Tournee.
*/
public class DAOTournee extends DAO<Tournee, Tournee.Champs> {

  /**
   * Constructeur.
   */
  public DAOTournee() throws Exception {
    super();
  }

  /**
   * Ajout d'une tournée dans la table.
   *
   * @param t Un objet Tournee.
   * @return L'identifiant de la tournée ajoutée. -1 en cas d'échec.
   */
  @Override
  public Tournee insert(Tournee t) {
    try {
      // La tournée est nécessairement associée à un véhicule et un producteur
      if(t.getVehicule() == null || t.getProducteur() == null){
        return null;
      }
      // La requête
      PreparedStatement req = this.getCo().prepareStatement("INSERT INTO tournees " +
                      "(horaireDebut, horaireFin, estimationDuree, note, valide, idVehicule," +
                      "idProducteur) VALUES (?,?,?,?,?,?,?)",
              PreparedStatement.RETURN_GENERATED_KEYS);
      // L'ajout des valeurs
      req.setString(1, t.getHoraireDebut());
      req.setString(2, t.getHoraireFin());
      req.setString(3, t.getEstimationDuree());
      req.setString(4, t.getNote());
      req.setBoolean(5, t.isValide());
      req.setInt(6, t.getVehicule().getIdVehicule());
      req.setInt(7, t.getProducteur().getIdProducteur());
      // L'exécution de la requête
      req.execute();
      // Récupération de la clé primaire
      ResultSet rs = req.getGeneratedKeys();
      if(rs.next()){
        // Si l'ajout a eu lieu, on retourne l'objet utilisateur avec son identifiant
        return new Tournee(rs.getInt(1), t.getHoraireDebut(), t.getHoraireFin(),
                t.getEstimationDuree(), t.getNote(), t.isValide(), t.getProducteur(),
                t.getVehicule());
      }
      // En cas d'échec de l'ajout, on ne renvoie rien
      return null;
    } catch (Exception e) { // En cas d'échec de la requête on ne renvoie rien
      return null;
    }
  }

  /**
   * Mise à jour d'une tournée dans la base.
   *
   * @param t Un objet métier.
   * @return True si la tournée a été modifiée, false sinon.
   */
  @Override
  public boolean update(Tournee t) {
    try {
      PreparedStatement req  = this.getCo().prepareStatement("UPDATE tournees " +
              "SET horaireDebut = ?, horaireFin = ?, estimationDuree = ?, note = ?, valide = ?, " +
              "idVehicule = ?, idProducteur = ? WHERE idTournee = ?");
      req.setString(1, t.getHoraireDebut());
      req.setString(2, t.getHoraireFin());
      req.setString(3, t.getEstimationDuree());
      req.setString(4, t.getNote());
      req.setBoolean(5, t.isValide());
      req.setInt(6, t.getVehicule().getIdVehicule());
      req.setInt(7, t.getProducteur().getIdProducteur());
      req.setInt(8, t.getIdTournee());
      // L'exécution de la requête
      req.execute();
      return true;
    } catch (SQLException e) { // En cas d'échec de la requête
      return false;
    }
  }

  /**
   * Suppression d'une tournée dans la base
   *
   * @param id L'identifiant de la tournée à supprimer.
   * @return True si la tournée a été supprimée, false sinon.
   */
  @Override
  public boolean delete(int id) {
    try {
      PreparedStatement req = this.getCo().prepareStatement("DELETE FROM tournees WHERE idTournee = ?");
      req.setInt(1, id);
      // Si l'entrée a été supprimée, on retourne true
      return req.executeUpdate() == 1;
      // Sinon, on retourne false
    } catch (SQLException e) {
      return false;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Recherche de tournées dans la base avec les attributs renseignés de l'objet métier.
   *
   * @param criteres Les critères de recherche de tournées.
   * @return Une collection d'objets qui correspond aux critères mis en paramètre.
   */
  @Override
  public ArrayList<Tournee> find(HashMap<Tournee.Champs, String> criteres) {
    PreparedStatement req;
    try {
      // On fait une requête avec les critères de recherche
      req = this.getCo().prepareStatement("SELECT * FROM tournees WHERE 1=1 " +
              criteresPourWHERE(criteres));
      // On récupère le résultat
      ResultSet rs = req.executeQuery();
      // On les stockera dans un ArrayList de commandes
      ArrayList<Tournee> tournees = new ArrayList<Tournee>();
      // On aura besoin de créer les objets Vehicule et Producteur
      DAOProducteur daoP = new DAOProducteur();
      DAOVehicule daoV = new DAOVehicule();
      Vehicule vehicule;
      Producteur producteur;
      while (rs.next()) {
        // Tant qu'il y a des lignes dans le résultat
        vehicule = daoV.findById(Integer.parseInt(rs.getString("idVehicule")));
        producteur = daoP.findById(Integer.parseInt(rs.getString("idProducteur")));

        tournees.add(new Tournee(rs.getInt("idTournee"), rs.getString("horaireDebut"),
                rs.getString("horaireFin"), rs.getString("estimationDuree"),
                rs.getString("note"), rs.getBoolean("valide"),
                producteur, vehicule));
      }
      return tournees;
    } catch (Exception e) {
      // On renvoie un ArrayList vide si la requête n'a pas pu être effectuée correctement.
      return new ArrayList<Tournee>();
    }
  }

  /**
   * Recherche d'une tournée à partir de sa clé primaire
   *
   * @param id L'identifiant de la tournée.
   * @return L'objet Tournee contenant les informations de la ligne.
   * Renvoie null si la tournée n'a pas été trouvée.
   */
  @Override
  public Tournee findById(int id) {
    // On réutilise la méthode find avec comme seul critère l'identifiant
    HashMap<Tournee.Champs, String> criteres = new HashMap<Tournee.Champs, String>();
    criteres.put(Tournee.Champs.idTournee, String.valueOf(id));
    ArrayList<Tournee> resultatRecherche = find(criteres);
    if(resultatRecherche.isEmpty()){
      return null;
    }
    return resultatRecherche.get(0);
  }
}