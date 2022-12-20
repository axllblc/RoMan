package fr.roman.dao;

import fr.roman.modeles.Adresse;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
* DAO pour la classe Producteur.
*/
public class DAOProducteur extends DAO<Producteur, Producteur.Champs> {

  /**
   * Constructeur.
   */
  public DAOProducteur() throws Exception {
    super();
  }

  /**
   * Entrée d'un producteur dans la table (et par extension un compte Utilisateur).
   *
   * @param p Un objet Producteur, qui doit contenir un objet Utilisateur.
   * @return Un objet Producteur avec son identifiant, null s'il n'a pas pu être ajouté.
   */
  public Producteur insert(Producteur p) {
    try {
      DAOUtilisateur daoU = new DAOUtilisateur();
      return daoU.insert(p);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Mise à jour des informations d'un producteur dans la base.
   *
   * @param p Un objet Producteur.
   * @return True si le producteur a été modifié, false sinon.
   */
  @Override
  public boolean update(Producteur p) {
    try {
      PreparedStatement req  = this.getCo().prepareStatement("UPDATE producteurs " +
              "SET siret = ?, nomEtablissement = ?, tel = ?, idAdresse = ? " +
              "WHERE idProducteur = ?");
      req.setString(1, p.getSiret());
      req.setString(2, p.getNomEtablissement());
      req.setString(3, p.getTel());
      req.setInt(4, p.getAdresse().getIdAdresse());
      req.setInt(5, p.getIdProducteur());
      // L'exécution de la requête
      req.execute();
      return true;
    } catch (SQLException e) { // En cas d'échec de la requête
      return false;
    }
  }

  /**
   * Suppression d'un producteur dans la base (on supprime aussi le compte utilisateur associé)
   *
   * @param id L'identifiant du producteur à supprimer.
   * @return True si le producteur a été supprimé, false sinon.
   */
  @Override
  public boolean delete(int id) {
    try {
      // Comme on a une suppression en cascade, il suffit de supprimer
      // le compte utilisateur associé au producteur
      DAOUtilisateur daoU = new DAOUtilisateur();
      return daoU.delete(this.findById(id).getUtilisateur().getIdUtilisateur());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Recherche de producteurs dans la base avec les attributs renseignés de l'objet métier.
   *
   * @param criteres Les critères de recherche de producteurs.
   * @return Une collection de Producteur qui correspond aux critères mis en paramètre.
   */
  @Override
  public ArrayList<Producteur> find(HashMap<Producteur.Champs, String> criteres) {
    PreparedStatement req;
    try {
      // On fait une requête avec les critères de recherche
      req = this.getCo().prepareStatement("SELECT * FROM producteurs WHERE 1=1 " +
              criteresPourWHERE(criteres));
      // On récupère le résultat
      ResultSet rs = req.executeQuery();
      // On les stockera dans un ArrayList d'utilisateurs
      ArrayList<Producteur> producteurs = new ArrayList<Producteur>();
      // On aura besoin de créer les objets Adresse et Utilisateur pour chaque producteur trouvé
      DAOUtilisateur daoU = new DAOUtilisateur();
      DAOAdresse daoA = new DAOAdresse();
      Utilisateur utilisateur;
      Adresse adresse;
      while (rs.next()) {
        // Tant qu'il y a des lignes dans le résultat
        utilisateur = daoU.findById(Integer.parseInt(rs.getString("idUtilisateur")));
        adresse = daoA.findById(Integer.parseInt(rs.getString("idAdresse")));

        producteurs.add(new Producteur(rs.getInt("idProducteur"), rs.getString("siret"),
                rs.getString("nomEtablissement"), rs.getString("tel"), adresse, utilisateur));
      }
      return producteurs;
    } catch (Exception e) {
      e.printStackTrace();
      // On renvoie un ArrayList vide si la requête n'a pas pu être effectuée correctement.
      return new ArrayList<Producteur>();
    }
  }

  /**
   * Recherche d'un producteur à partir de sa clé primaire
   *
   * @param id L'identifiant du producteur.
   * @return L'objet Producteur contenant les informations de la ligne.
   * Renvoie null si la ligne n'a pas été trouvée.
   */
  @Override
  public Producteur findById(int id) {
    // On réutilise la méthode find avec comme seul critère l'identifiant
    HashMap<Producteur.Champs, String> criteres = new HashMap<Producteur.Champs, String>();
    criteres.put(Producteur.Champs.idProducteur, String.valueOf(id));
    ArrayList<Producteur> resultatRecherche = find(criteres);
    if(resultatRecherche.isEmpty()){
      return null;
    }
    return resultatRecherche.get(0);
  }
}