package fr.roman.dao;

import fr.roman.modeles.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

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
   * @return L'objet Client de ce qui a été ajouté.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public Client insert(Client c) throws Exception {
    // Un client a forcément un nom et une adresse
    if (c.getNom() == null || c.getAdresse() == null) {
      throw new Exception(new Throwable("Nom et/ou adresse manquant"));
    }
    String sql = "INSERT INTO clients (nom, tel, email, siret, particulier, idAdresse) "
            + "VALUES (?,?,?,?,?,?)";
    try (PreparedStatement req = this.getCo()
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
      // L'ajout des valeurs
      req.setString(1, c.getNom());
      req.setString(2, c.getTel());
      req.setString(3, c.getEmail());
      req.setString(4, c.getSiret());
      req.setBoolean(5, c.isParticulier());
      req.setObject(6, c.getAdresse() != null ? c.getAdresse().getIdAdresse() : null);
      // L'exécution de la requête
      req.execute();
      // Récupération de la clé primaire
      ResultSet rs = req.getGeneratedKeys();
      if (rs.next()) {
        // Si l'ajout a eu lieu, on retourne l'objet utilisateur avec son identifiant
        return new Client(rs.getInt(1), c.getNom(), c.getTel(),
                c.getEmail(), c.getSiret(), c.isParticulier(), c.getAdresse());
      }
      // En cas d'échec de l'ajout, on ne renvoie rien
      throw new Exception(new Throwable("Erreur dans l'insertion du client dans la base"));
    }
  }

  /**
   * Mise à jour des informations d'un client dans la base.
   *
   * @param c Le client à modifier.
   * @return true si le client a été modifié, false sinon.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public boolean update(Client c) throws Exception {
    // Un client a forcément un nom et une adresse
    if (c.getNom() == null || c.getAdresse() == null) {
      throw new Exception(new Throwable("Nom et/ou adresse manquant"));
    }
    String sql = "UPDATE clients "
            + "SET nom = ?, tel = ?, email = ?, siret = ?, particulier = ?, idAdresse = ? "
            + "WHERE idClient = ?";
    try (PreparedStatement req  = this.getCo().prepareStatement(sql)) {
      // L'ajout des valeurs
      req.setString(1, c.getNom());
      req.setString(2, c.getTel());
      req.setString(3, c.getEmail());
      req.setString(4, c.getSiret());
      req.setBoolean(5, c.isParticulier());

      req.setObject(6, c.getAdresse() != null ? c.getAdresse().getIdAdresse() : null);
      req.setInt(7, c.getIdClient());
      // L'exécution de la requête
      return req.executeUpdate() > 0;
    }
  }

  /**
   * Suppression d'un client de la base.
   *
   * @param id L'identifiant du client à supprimer.
   * @return true si la ligne a été supprimée, false sinon.
   * @throws SQLException Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public boolean delete(int id) throws SQLException {
    String sql = "DELETE FROM clients WHERE idClient = ?";
    try (PreparedStatement req = this.getCo().prepareStatement(sql)) {
      req.setInt(1, id);
      return req.executeUpdate() == 1; // Si l'entrée a été supprimée, on retourne true
    }
  }

  /**
   * Recherche de clients à partir de plusieurs critères.
   *
   * @param criteres Les critères de recherche des clients.
   * @return Une collection d'objets qui correspond aux critères mis en paramètre.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public ArrayList<Client> find(LinkedHashMap<Client.Champs, String> criteres) throws Exception {
    // On fait une requête avec les critères de recherche
    String sql = "SELECT * FROM clients WHERE 1=1 " + criteresPourWHERE(criteres);
    try (PreparedStatement req = this.getCo().prepareStatement(sql)) {
      int noCritere = 1;
      for (String critere : criteres.values()) {
        req.setString(noCritere, critere);
        noCritere++;
      }
      // On récupère le résultat
      ResultSet rs = req.executeQuery();
      // On les stockera dans un ArrayList de commandes
      ArrayList<Client> commandes = new ArrayList<>();
      // On aura besoin de créer l'objet Adresse
      DAOAdresse daoA = new DAOAdresse();
      Adresse adresse;
      while (rs.next()) {
        // Tant qu'il y a des lignes dans le résultat
        adresse = daoA.findById(rs.getInt("idAdresse"));

        commandes.add(new Client(rs.getInt("idClient"), rs.getString("nom"),
                rs.getString("tel"), rs.getString("email"), rs.getString("siret"),
                rs.getBoolean("particulier"), adresse));
      }
      return commandes;
    }
  }

  /**
   * Recherche d'un client à partir de sa clé primaire
   *
   * @param id L'identifiant du client.
   * @return L'objet client contenant les informations de la ligne.
   * Renvoie null si le client n'a pas été trouvée.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public Client findById(int id) throws Exception {
    // On réutilise la méthode find avec comme seul critère l'identifiant
    LinkedHashMap<Client.Champs, String> criteres = new LinkedHashMap<>();
    criteres.put(Client.Champs.idClient, String.valueOf(id));
    ArrayList<Client> resultatRecherche = find(criteres);
    if (resultatRecherche.isEmpty()) {
      return null;
    }
    return resultatRecherche.get(0);
  }
}