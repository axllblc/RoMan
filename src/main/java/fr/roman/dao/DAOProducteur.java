package fr.roman.dao;

import fr.roman.modeles.Adresse;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;

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
   * Entrée d'un producteur dans la table (et par extension un compte Utilisateur associé).
   *
   * @param p Un objet Producteur, qui doit contenir un objet Utilisateur.
   * @return Un objet Producteur avec son identifiant
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  public Producteur insert(Producteur p) throws Exception {
    DAOUtilisateur daoU = new DAOUtilisateur();
    return daoU.insert(p);
  }

  /**
   * Mise à jour des informations d'un producteur dans la base.
   *
   * @param p Un objet Producteur.
   * @return True si le producteur a été modifié, false sinon.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public boolean update(Producteur p) throws Exception {
    if (p.getUtilisateur() == null || p.getAdresse() == null) {
      // Si on n'a pas associé le producteur à un utilisateur,
      // ou qu'il n'a pas d'adresse, on annule la modification
      throw new Exception("Adresse et/ou utilisateur non renseignés");
    }
    // On vérifie que le Siret (si modifié) n'existe pas déjà dans la base
    LinkedHashMap<Producteur.Champs, String> critereSiret = new LinkedHashMap<>();
    critereSiret.put(Producteur.Champs.siret, p.getSiret());
    ArrayList<Producteur> resRech = find(critereSiret);
    if (!find(critereSiret).isEmpty() && resRech.get(0).getIdProducteur() != p.getIdProducteur()) {
      throw new Exception("Siret déjà renseigné");
    }
    String sql = "UPDATE producteurs SET siret = ?, nomEtablissement = ?, tel = ?, idAdresse = ? "
            + "WHERE idProducteur = ?";
    try(PreparedStatement req  = this.getCo().prepareStatement(sql)) {
      req.setString(1, p.getSiret());
      req.setString(2, p.getNomEtablissement());
      req.setString(3, p.getTel());
      req.setObject(4, p.getAdresse() != null ? p.getAdresse().getIdAdresse() : null);
      req.setInt(5, p.getIdProducteur());
      // L'exécution de la requête
      return (req.executeUpdate() > 0);
    }
  }

  /**
   * Suppression d'un producteur dans la base (on supprime aussi le compte utilisateur associé).
   *
   * @param id L'identifiant du producteur à supprimer.
   * @return True si le producteur a été supprimé, false sinon.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public boolean delete(int id) throws Exception {
    // Comme on a une suppression en cascade, il suffit de supprimer
    // le compte utilisateur associé au producteur
    DAOUtilisateur daoU = new DAOUtilisateur();
    return daoU.delete(this.findById(id).getUtilisateur().getIdUtilisateur());
  }

  /**
   * Recherche de producteurs dans la base avec les attributs renseignés de l'objet métier.
   *
   * @param criteres Les critères de recherche de producteurs.
   * @return Une collection de Producteur qui correspond aux critères mis en paramètre.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public ArrayList<Producteur> find(LinkedHashMap<Producteur.Champs, String> criteres) throws Exception {
    // On fait une requête avec les critères de recherche
    String sql = "SELECT * FROM producteurs WHERE 1=1 " + criteresPourWHERE(criteres);
    try(PreparedStatement req = this.getCo().prepareStatement(sql)) {
      int noCritere = 1;
      for (String critere : criteres.values()) {
        req.setString(noCritere, critere);
        noCritere++;
      }
      // On récupère le résultat
      ResultSet rs = req.executeQuery();
      // On les stockera dans un ArrayList d'utilisateurs
      ArrayList<Producteur> producteurs = new ArrayList<>();
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
    }
  }

  /**
   * Recherche d'un producteur à partir de sa clé primaire.
   *
   * @param id L'identifiant du producteur.
   * @return L'objet Producteur contenant les informations de la ligne.
   * Renvoie null si la ligne n'a pas été trouvée.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public Producteur findById(int id) throws Exception {
    // On réutilise la méthode find avec comme seul critère l'identifiant
    LinkedHashMap<Producteur.Champs, String> criteres = new LinkedHashMap<>();
    criteres.put(Producteur.Champs.idProducteur, String.valueOf(id));
    ArrayList<Producteur> resultatRecherche = find(criteres);
    if (resultatRecherche.isEmpty()) {
      return null;
    }
    return resultatRecherche.get(0);
  }
}