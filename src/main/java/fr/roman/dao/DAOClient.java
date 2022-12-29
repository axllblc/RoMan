package fr.roman.dao;

import fr.roman.modeles.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
* DAO pour la classe Client.
*/
public class DAOClient extends DAO<Client, Client.Champs> {


  /**
   * Constructeur.
   */
  public DAOClient() throws Exception {
    super();
  }

  /**
   * Ajout d'un client dans la table.
   *
   * @param c Le client à ajouter.
   * @return L'identifiant du client ajouté. -1 en cas d'échec.
   */
  @Override
  public Client insert(Client c) {
    try {
      // Un client a forcément un nom et une adresse
      if(c.getNom() == null || c.getAdresse() == null){
        return null;
      }
      // La requête
      PreparedStatement req = this.getCo().prepareStatement("INSERT INTO clients " +
                      "(nom, tel, email, siret, particulier, idAdresse) " +
                      "VALUES (?,?,?,?,?,?)",
              PreparedStatement.RETURN_GENERATED_KEYS);
      // L'ajout des valeurs
      req.setString(1, c.getNom());
      req.setString(2, c.getTel());
      req.setString(3, c.getEmail());
      req.setString(4, c.getSiret());
      req.setBoolean(5, c.isParticulier());
      req.setInt(6, c.getAdresse().getIdAdresse());
      // L'exécution de la requête
      req.execute();
      // Récupération de la clé primaire
      ResultSet rs = req.getGeneratedKeys();
      if(rs.next()){
        // Si l'ajout a eu lieu, on retourne l'objet utilisateur avec son identifiant
        return new Client(rs.getInt(1), c.getNom(), c.getTel(),
                c.getEmail(), c.getSiret(), c.isParticulier(), c.getAdresse());
      }
      // En cas d'échec de l'ajout, on ne renvoie rien
      return null;
    } catch (Exception e) { // En cas d'erreur de la requête on ne renvoie rien
      return null;
    }
  }

  /**
   * Mise à jour des informations d'un client dans la base.
   *
   * @param c Le client à modifier.
   * @return true si le client a été modifié, false sinon.
   */
  @Override
  public boolean update(Client c) {
    try {
      PreparedStatement req  = this.getCo().prepareStatement("UPDATE clients " +
              "SET nom = ?, tel = ?, email = ?, siret = ?, particulier = ?, idAdresse = ? " +
              "WHERE idClient = ?");
      // L'ajout des valeurs
      req.setString(1, c.getNom());
      req.setString(2, c.getTel());
      req.setString(3, c.getEmail());
      req.setString(4, c.getSiret());
      req.setBoolean(5, c.isParticulier());
      req.setInt(6, c.getAdresse().getIdAdresse());
      req.setInt(7, c.getIdClient());
      // L'exécution de la requête
      req.execute();
      return true;
    } catch (SQLException e) { // En cas d'échec de la requête
      return false;
    }
  }

  /**
   * Suppression d'un client de la base
   *
   * @param id L'identifiant du client à supprimer.
   * @return true si la ligne a été supprimée, false sinon.
   */
  @Override
  public boolean delete(int id) {
    try {
      PreparedStatement req = this.getCo().prepareStatement("DELETE FROM clients WHERE idClient = ?");
      req.setInt(1, id);
      if (req.executeUpdate() == 1) {
        // Si l'entrée a été supprimée, on retourne true
        return true;
      }
      // Sinon, on retourne false
      return false;
    } catch (SQLException e) {
      return false;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Recherche de clients à partir de plusieurs critères.
   *
   * @param criteres Les critères de recherche des clients.
   * @return Une collection d'objets qui correspond aux critères mis en paramètre.
   */
  @Override
  public ArrayList<Client> find(HashMap<Client.Champs, String> criteres) {
    PreparedStatement req;
    try {
      // On fait une requête avec les critères de recherche
      req = this.getCo().prepareStatement("SELECT * FROM clients WHERE 1=1 " +
              criteresPourWHERE(criteres));
      // On récupère le résultat
      ResultSet rs = req.executeQuery();
      // On les stockera dans un ArrayList de commandes
      ArrayList<Client> commandes = new ArrayList<Client>();
      // On aura besoin de créer l'objet Adresse
      DAOAdresse daoA = new DAOAdresse();
      Adresse adresse;
      while (rs.next()) {
        // Tant qu'il y a des lignes dans le résultat
        adresse = daoA.findById(Integer.parseInt(rs.getString("idAdresse")));

        commandes.add(new Client(rs.getInt("idClient"), rs.getString("nom"),
                rs.getString("tel"), rs.getString("email"), rs.getString("siret"),
                rs.getBoolean("particulier"), adresse));
      }
      return commandes;
    } catch (Exception e) {
      // On renvoie un ArrayList vide si la requête n'a pas pu être effectuée correctement.
      return new ArrayList<Client>();
    }
  }

  /**
   * Recherche d'un client à partir de sa clé primaire
   *
   * @param id L'identifiant du client.
   * @return L'objet client contenant les informations de la ligne.
   * Renvoie null si le client n'a pas été trouvée.
   */
  @Override
  public Client findById(int id) {
    // On réutilise la méthode find avec comme seul critère l'identifiant
    HashMap<Client.Champs, String> criteres = new HashMap<Client.Champs, String>();
    criteres.put(Client.Champs.idClient, String.valueOf(id));
    ArrayList<Client> resultatRecherche = find(criteres);
    if(resultatRecherche.isEmpty()){
      return null;
    }
    return resultatRecherche.get(0);
  }
}