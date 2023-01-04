package fr.roman.dao;

import fr.roman.modeles.*;

import java.sql.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

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
   * @return L'objet Tournee ajouté, avec son identifiant.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public Tournee insert(Tournee t) throws Exception {
    // La tournée est nécessairement associée à un véhicule et un producteur
    if (t.getVehicule() == null || t.getProducteur() == null) {
      throw new Exception(new Throwable("Vehicule et/ou Producteur manquant"));
    }
    String sql = "INSERT INTO tournees (horaireDebut, horaireFin, estimationDuree, "
            + "note, valide, idVehicule, idProducteur) VALUES (?,?,?,?,?,?,?)";
    try (PreparedStatement req = this.getCo()
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
      // L'ajout des valeurs

      // Pour les objets "Calendar" ou "Duration"
      req.setTime(1, null);
      req.setTime(2, null);
      req.setTime(3, null);
      if (t.getHoraireDebut() != null) {
        req.setTimestamp(1, new Timestamp(t.getHoraireDebut().getTime().getTime()));
      }
      if (t.getHoraireFin() != null) {
        req.setTimestamp(2, new Timestamp(t.getHoraireFin().getTime().getTime()));
      }
      if (t.getEstimationDuree() != null) {
        req.setTime(3, Time.valueOf(LocalTime
                .ofSecondOfDay(t.getEstimationDuree().toSeconds())));
      }

      req.setString(4, t.getNote());
      req.setBoolean(5, t.isValide());
      req.setObject(6, t.getVehicule() != null ? t.getVehicule().getIdVehicule() : null);
      req.setObject(7, t.getProducteur() != null ? t.getProducteur().getIdProducteur() : null);
      // L'exécution de la requête
      req.execute();
      // Récupération de la clé primaire
      ResultSet rs = req.getGeneratedKeys();
      if (rs.next()) {
        // Si l'ajout a eu lieu, on retourne l'objet utilisateur avec son identifiant
        return new Tournee(rs.getInt(1), t.getHoraireDebut(), t.getHoraireFin(),
                t.getEstimationDuree(), t.getNote(), t.isValide(), t.getProducteur(),
                t.getVehicule());
      }
      // En cas d'échec de l'ajout
      throw new Exception(new Throwable("Erreur dans l'ajout d'une Tournée"));
    }
  }

  /**
   * Mise à jour d'une tournée dans la base.
   *
   * @param t Un objet métier.
   * @return True si la tournée a été modifiée, false sinon.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public boolean update(Tournee t) throws Exception {
    // La tournée est nécessairement associée à un véhicule et un producteur
    if (t.getVehicule() == null || t.getProducteur() == null) {
      throw new Exception(new Throwable("Vehicule et/ou Producteur manquant"));
    }
    String sql = "UPDATE tournees SET horaireDebut = ?, horaireFin = ?, estimationDuree = ?, "
            + "note = ?, valide = ?, idVehicule = ?, idProducteur = ? WHERE idTournee = ?";
    try (PreparedStatement req  = this.getCo().prepareStatement(sql)) {
      // Pour les objets "Calendar" ou "Duration"
      req.setTime(1, null);
      req.setTime(2, null);
      req.setTime(3, null);
      if (t.getHoraireDebut() != null) {
        req.setTimestamp(1, new Timestamp(t.getHoraireDebut().getTime().getTime()));
      }
      if (t.getHoraireFin() != null) {
        req.setTimestamp(2, new Timestamp(t.getHoraireFin().getTime().getTime()));
      }
      if (t.getEstimationDuree() != null) {
        req.setTime(3, Time.valueOf(LocalTime.ofSecondOfDay(t.getEstimationDuree().toSeconds())));
      }
      req.setString(4, t.getNote());
      req.setBoolean(5, t.isValide());
      req.setObject(6, t.getVehicule() != null ? t.getVehicule().getIdVehicule() : null);
      req.setObject(7, t.getProducteur() != null ? t.getProducteur().getIdProducteur() : null);
      req.setInt(8, t.getIdTournee());
      // L'exécution de la requête
      return (req.executeUpdate() > 0);
    }
  }

  /**
   * Suppression d'une tournée dans la base.
   *
   * @param id L'identifiant de la tournée à supprimer.
   * @return True si la tournée a été supprimée, false sinon.
   * @throws SQLException Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public boolean delete(int id) throws SQLException {
    String sql = "DELETE FROM tournees WHERE idTournee = ?";
    try (PreparedStatement req = this.getCo().prepareStatement(sql)) {
      req.setInt(1, id);
      // Si l'entrée a été supprimée, on retourne true
      return req.executeUpdate() == 1;
      // Sinon, on retourne false
    }
  }

  /**
   * Recherche de tournées dans la base avec les attributs renseignés de l'objet métier.
   *
   * @param criteres Les critères de recherche de tournées.
   * @return Une collection d'objets qui correspond aux critères mis en paramètre.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public ArrayList<Tournee> find(LinkedHashMap<Tournee.Champs, String> criteres) throws Exception {
    String sql = "SELECT * FROM tournees WHERE 1=1 " + criteresPourWHERE(criteres);
    // On fait une requête avec les critères de recherche
    try (PreparedStatement req = this.getCo().prepareStatement(sql)) {
      int noCritere = 1;
      for (String critere : criteres.values()) {
        req.setString(noCritere, critere);
        noCritere++;
      }
      // On récupère le résultat
      ResultSet rs = req.executeQuery();
      // On les stockera dans un ArrayList de commandes
      ArrayList<Tournee> tournees = new ArrayList<>();
      // On aura besoin de créer les objets Vehicule et Producteur
      DAOProducteur daoP = new DAOProducteur();
      DAOVehicule daoV = new DAOVehicule();
      Vehicule vehicule;
      Producteur producteur;
      while (rs.next()) {
        // Tant qu'il y a des lignes dans le résultat
        vehicule = daoV.findById(rs.getInt("idVehicule"));
        producteur = daoP.findById(rs.getInt("idProducteur"));

        // Pour les objets "Calendar" ou "Duration"
        Calendar horaireDebut = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        if (rs.getTimestamp("horaireDebut") != null) {
          horaireDebut.setTime(rs.getTimestamp("horaireDebut"));
        }
        Calendar horaireFin = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        if (rs.getTimestamp("horaireFin") != null) {
          horaireFin.setTime(rs.getTimestamp("horaireFin"));
        }
        Duration estimationDuree = Duration.ZERO;
        if (rs.getTime("estimationDuree") != null) {
          estimationDuree = Duration.ofNanos(rs.getTime("estimationDuree")
                  .toLocalTime().toNanoOfDay());
        }

        tournees.add(new Tournee(rs.getInt("idTournee"), horaireDebut,
                horaireFin, estimationDuree, rs.getString("note"),
                rs.getBoolean("valide"), producteur, vehicule));
      }
      return tournees;
    }
  }

  /**
   * Recherche d'une tournée à partir de sa clé primaire.
   *
   * @param id L'identifiant de la tournée.
   * @return L'objet Tournee contenant les informations de la ligne.
   * Renvoie null si la tournée n'a pas été trouvée.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public Tournee findById(int id) throws Exception {
    // On réutilise la méthode find avec comme seul critère l'identifiant
    LinkedHashMap<Tournee.Champs, String> criteres = new LinkedHashMap<>();
    criteres.put(Tournee.Champs.idTournee, String.valueOf(id));
    ArrayList<Tournee> resultatRecherche = find(criteres);
    if (resultatRecherche.isEmpty()) {
      return null;
    }
    return resultatRecherche.get(0);
  }
}