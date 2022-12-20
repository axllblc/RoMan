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
   * @return L'identifiant de la ligne ajoutée.
   */
  @Override
  public Adresse insert(Adresse a) {
    try {
      if(a.getCoordonneesGPS() == null){
        // Une adresse a au minimum des coordonnées GPS
        return null;
      }
      PreparedStatement req = this.getCo().prepareStatement("INSERT INTO adresses " +
              "(coordonneesGPS, libelle, numeroVoie, complementNumero, voie, complementAdresse," +
              " codePostal, ville) VALUES (ST_GeomFromText(?, 4326),?,?,?,?,?,?,?)");
      req.setString(1, "Point(" + a.getCoordonneesGPS()[0] + " " + a.getCoordonneesGPS()[1] + ")");
      req.setString(2, a.getLibelle());
      req.setInt(3, a.getNumeroVoie());
      req.setString(4, a.getComplementNumero());
      req.setString(5, a.getVoie());
      req.setString(6, a.getComplementAdresse());
      req.setInt(7, a.getCodePostal());
      req.setString(8, a.getVille());
      req.execute();
      return a;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Mise à jour d'une adresse de la base.
   *
   * @param a Un objet Adresse.
   * @return True si l'adresse a été modifiée, false sinon.
   */
  @Override
  public boolean update(Adresse a) {
    try {
      PreparedStatement req  = this.getCo().prepareStatement("UPDATE adresses " +
              "SET coordonneesGPS = ST_GeomFromText(?, 4326), libelle = ?, numeroVoie = ?, complementNumero = ?, " +
              "voie = ?, complementAdresse = ?, codePostal = ?, ville = ? " +
              "WHERE idAdresse = ?");
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
      req.execute();
      return true;
    } catch (SQLException e) { // En cas d'échec de la requête : on renvoie false
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Suppression d'une adresse dans la base.
   *
   * @param id L'identifiant de l'adresse dans la base.
   * @return Vrai si la ligne a été supprimée, false sinon.
   */
  @Override
  public boolean delete(int id) {
    try {
      PreparedStatement req = this.getCo().prepareStatement("DELETE FROM adresses WHERE idAdresse = ?");
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
   * Recherche d'adresses dans la base avec les attributs renseignés en paramètre..
   *
   * @param criteres Les critères de recherche des adresses.
   * @return Une collection d'objets qui correspond aux critères mis en paramètre.
   */
  @Override
  public ArrayList<Adresse> find(HashMap<Adresse.Champs, String> criteres) {
    PreparedStatement req;
    try {
      // On fait une requête avec les critères de recherche
      req = this.getCo().prepareStatement("SELECT idAdresse, ST_X(coordonneesGPS) AS 'coX'," +
              "ST_Y(coordonneesGPS) AS 'coY', libelle, numeroVoie, complementNumero, voie, " +
              "complementAdresse, codePostal, ville FROM adresses WHERE 1=1 " +
              criteresPourWHERE(criteres));
      // On récupère le résultat
      ResultSet rs = req.executeQuery();

      // On les stockera dans un ArrayList de commandes
      ArrayList<Adresse> adresses = new ArrayList<Adresse>();
      while (rs.next()) {
        // Tant qu'il y a des lignes dans le résultat
        adresses.add( new Adresse(rs.getInt("idAdresse"),
                new double[] { rs.getDouble("coX"), rs.getDouble("coY") },
                rs.getString("libelle"), rs.getInt("numeroVoie"),
                rs.getString("complementNumero"), rs.getString("voie"),
                rs.getString("complementAdresse"), rs.getInt("codePostal"),
                rs.getString("ville")));
      }
      return adresses;
    } catch (Exception e) {
      // On renvoie un ArrayList vide si la requête n'a pas pu être effectuée correctement.
      return new ArrayList<Adresse>();
    }
  }

  /**
   * Recherche d'une adresse à partir de sa clé primaire
   *
   * @param id L'identifiant de l'adresse.
   * @return L'objet Adresse contenant les informations de la ligne.
   * Renvoie null si l'adresse n'a pas été trouvée.
   */
  @Override
  public Adresse findById(int id) {
    // On réutilise la méthode find avec comme seul critère l'identifiant
    HashMap<Adresse.Champs, String> criteres = new HashMap<Adresse.Champs, String>();
    criteres.put(Adresse.Champs.idAdresse, String.valueOf(id));
    ArrayList<Adresse> resultatRecherche = find(criteres);
    if(resultatRecherche.isEmpty()){
      return null;
    }
    return resultatRecherche.get(0);
  }
}