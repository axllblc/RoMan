package fr.roman.dao;

import fr.roman.modeles.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* DAO pour la classe Adresse.
*/
public class DAOAdresse extends DAO<Adresse, Adresse.Champs> {

  /**
   * Constructeur de la classe.
   */
  public DAOAdresse() throws Exception {
    super();
  }

  /**
   * Méthode servant à ajouter une adresse dans la base de données.
   *
   * @param a L'adresse (sans identifiant)
   * @return L'objet adresse avec son identifiant.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public Adresse insert(Adresse a) throws Exception {
    if (a.getCoordonneesGPS() == null) {
      // Une adresse a au minimum des coordonnées GPS
      throw new Exception(new Throwable("Absence de coordonnées"));
    }
    String sql = "INSERT INTO adresses "
            + "(coordonneesGPS, libelle, numeroVoie, complementNumero, voie, complementAdresse,"
            + " codePostal, ville) VALUES (ST_GeomFromText(?, 4326),?,?,?,?,?,?,?)";
    try (PreparedStatement req = this.getCo().prepareStatement(sql,
            PreparedStatement.RETURN_GENERATED_KEYS)) {

      req.setString(1, "Point(" + a.getCoordonneesGPS()[0] + " "
              + a.getCoordonneesGPS()[1] + ")");
      req.setString(2, a.getLibelle());
      req.setInt(3, a.getNumeroVoie());
      req.setString(4, a.getComplementNumero());
      req.setString(5, a.getVoie());
      req.setString(6, a.getComplementAdresse());
      req.setInt(7, a.getCodePostal());
      req.setString(8, a.getVille());
      // L'exécution de la requête
      req.execute();
      // Récupération de la clé primaire
      ResultSet rs = req.getGeneratedKeys();
      if (rs.next()) {
        // Si l'ajout a eu lieu, on retourne l'objet utilisateur avec son identifiant
        return new Adresse(rs.getInt(1), a.getCoordonneesGPS(), a.getLibelle(),
                a.getNumeroVoie(), a.getComplementNumero(), a.getVoie(), a.getComplementAdresse(),
                a.getCodePostal(), a.getVille());
      }
      throw new Exception(new Throwable("Erreur dans l'insertion de l'Adresse"));
    }
  }

  /**
   * Mise à jour d'une adresse de la base.
   *
   * @param a Un objet Adresse.
   * @return True si l'adresse a été modifiée, false sinon.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public boolean update(Adresse a) throws Exception {
    if (a.getCoordonneesGPS() == null) {
      // Une adresse a au minimum des coordonnées GPS
      throw new Exception(new Throwable("Absence de coordonnées"));
    }
    String sql = "UPDATE adresses SET coordonneesGPS = ST_GeomFromText(?, 4326), libelle = ?, "
                + "numeroVoie = ?, complementNumero = ?, voie = ?, complementAdresse = ?, "
                + "codePostal = ?, ville = ? WHERE idAdresse = ?";
    try (PreparedStatement req  = this.getCo().prepareStatement(sql)) {
      req.setString(1, "Point(" + a.getCoordonneesGPS()[0] + " " + a.getCoordonneesGPS()[1] + ")");
      req.setString(2, a.getLibelle());
      req.setInt(3, a.getNumeroVoie());
      req.setString(4, a.getComplementNumero());
      req.setString(5, a.getVoie());
      req.setString(6, a.getComplementAdresse());
      req.setInt(7, a.getCodePostal());
      req.setString(8, a.getVille());
      req.setInt(9, a.getIdAdresse());
      // L'exécution de la requête
      return (req.executeUpdate() > 0);
    }
  }

  /**
   * Suppression d'une adresse dans la base.
   *
   * @param id L'identifiant de l'adresse dans la base.
   * @return Vrai si la ligne a été supprimée, false sinon.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public boolean delete(int id) throws SQLException {
    String sql = "DELETE FROM adresses WHERE idAdresse = ?";
    try (PreparedStatement req = this.getCo().prepareStatement(sql)) {
      req.setInt(1, id);
      // Si l'entrée a été supprimée, on retourne true
      return req.executeUpdate() == 1;
      // Sinon, on retourne false
    }
  }

  /**
   * Recherche d'adresses dans la base avec les attributs renseignés en paramètre..
   *
   * @param criteres Les critères de recherche des adresses.
   * @return Une collection d'objets qui correspond aux critères mis en paramètre.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public ArrayList<Adresse> find(HashMap<Adresse.Champs, String> criteres) throws SQLException {
    // On fait une requête avec les critères de recherche
    String sql = "SELECT idAdresse, ST_X(coordonneesGPS) AS 'coX',"
            + "ST_Y(coordonneesGPS) AS 'coY', libelle, numeroVoie, complementNumero, voie, "
            + "complementAdresse, codePostal, ville FROM adresses WHERE 1=1 "
            + criteresPourWHERE(criteres);
    try (PreparedStatement req = this.getCo().prepareStatement(sql)) {
      // On récupère le résultat
      ResultSet rs = req.executeQuery();
      // On les stockera dans un ArrayList de commandes
      ArrayList<Adresse> adresses = new ArrayList<>();
      while (rs.next()) {
        // Tant qu'il y a des lignes dans le résultat
        adresses.add(new Adresse(rs.getInt("idAdresse"),
                new double[] { rs.getDouble("coX"), rs.getDouble("coY") },
                rs.getString("libelle"), rs.getInt("numeroVoie"),
                rs.getString("complementNumero"), rs.getString("voie"),
                rs.getString("complementAdresse"), rs.getInt("codePostal"),
                rs.getString("ville")));
      }
      return adresses;
    }
  }

  /**
   * Recherche d'une adresse à partir de sa clé primaire.
   *
   * @param id L'identifiant de l'adresse.
   * @return L'objet Adresse contenant les informations de la ligne.
   * Renvoie null si l'adresse n'a pas été trouvée.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public Adresse findById(int id) throws SQLException {
    // On réutilise la méthode find avec comme seul critère l'identifiant
    HashMap<Adresse.Champs, String> criteres = new HashMap<>();
    criteres.put(Adresse.Champs.idAdresse, String.valueOf(id));
    ArrayList<Adresse> resultatRecherche = find(criteres);
    if (resultatRecherche.isEmpty()) {
      return null;
    }
    return resultatRecherche.get(0);
  }
}