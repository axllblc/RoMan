package fr.roman.dao;

import fr.roman.modeles.*;
import java.sql.*;
import java.util.*;

/**
 * DAO pour la classe Commande.
 */
public class DAOCommande extends DAO<Commande, Commande.Champs> {

  /**
   * Constructeur.
   */
  public DAOCommande() throws Exception {
    super();
  }

  /**
   * Entrée d'une commande dans la table.
   *
   * @param c Un objet Commande.
   * @return Un objet Commande avec son identifiant.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public Commande insert(Commande c) throws Exception {
    // La commande est nécessairement associée à un producteur et un client
    if (c.getClient() == null || c.getProducteur() == null) {
      throw new Exception(new Throwable("Client et/ou producteur manquant"));
    }
    String sql = "INSERT INTO commandes "
            + "(libelle, poids, horaireDebut, horaireFin, note, defautLivraison, "
            + "dateInitiale, dateLivraison, idProducteur, idTournee, idClient) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    try (PreparedStatement req = this.getCo().prepareStatement(sql,
            PreparedStatement.RETURN_GENERATED_KEYS)) {
      // L'ajout des valeurs
      req.setString(1, c.getLibelle());
      req.setDouble(2, c.getPoids());

      // Pour les objets "Calendar"
      req.setTime(3, null);
      req.setTime(4, null);
      if (c.getHoraireDebut() != null) {
        req.setTimestamp(3, new Timestamp(c.getHoraireDebut().getTime().getTime()));
      }
      if (c.getHoraireFin() != null) {
        req.setTimestamp(4, new Timestamp(c.getHoraireFin().getTime().getTime()));
      }

      req.setString(5, c.getNote());
      req.setBoolean(6, c.isDefautLivraison());

      // Pour les objets "Calendar"
      req.setTime(7, null);
      req.setTime(8, null);
      if (c.getDateInitiale() != null) {
        req.setTimestamp(7, new Timestamp(c.getDateInitiale().getTime().getTime()));
      }
      if (c.getDateLivraison() != null) {
        req.setTimestamp(8, new Timestamp(c.getDateLivraison().getTime().getTime()));
      }

      req.setObject(9, c.getProducteur() != null ? c.getProducteur().getIdProducteur() : null);
      req.setObject(10, c.getTournee() != null ? c.getTournee().getIdTournee() : null);
      req.setObject(11, c.getClient() != null ? c.getClient().getIdClient() : null);
      // L'exécution de la requête
      req.execute();
      // Récupération de la clé primaire
      ResultSet rs = req.getGeneratedKeys();
      if (rs.next()) {
        // Si l'ajout a eu lieu, on retourne l'objet utilisateur avec son identifiant
        return new Commande(rs.getInt(1), c.getLibelle(), c.getPoids(),
                c.getHoraireDebut(), c.getHoraireFin(), c.getNote(), c.isDefautLivraison(),
                c.getDateInitiale(), c.getDateLivraison(), c.getProducteur(), c.getClient(),
                c.getTournee());
      }
      // En cas d'échec de l'ajout
      throw new Exception(new Throwable("Erreur dans l'insertion de la commande"));
    }
  }

  /**
   * Mise à jour d'une commande dans la base.
   *
   * @param c Un objet Commande.
   * @return True si la commande a été modifiée, false sinon.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public boolean update(Commande c) throws Exception {
    // La commande est nécessairement associée à un producteur et un client
    if (c.getClient() == null || c.getProducteur() == null) {
      throw new Exception(new Throwable("Client et/ou producteur manquant"));
    }
    String sql = "UPDATE commandes SET libelle = ?, poids = ?, horaireDebut = ?, horaireFin = ?, "
            + "note = ?, defautLivraison = ?, dateInitiale = ?, dateLivraison = ?,"
            + "idProducteur = ?, idTournee = ?, idClient = ? WHERE idCommande = ?";
    try (PreparedStatement req  = this.getCo().prepareStatement(sql)) {
      req.setString(1, c.getLibelle());
      req.setDouble(2, c.getPoids());
      // Pour les objets "Calendar"
      req.setTime(3, null);
      req.setTime(4, null);
      if (c.getHoraireDebut() != null) {
        req.setTimestamp(3, new Timestamp(c.getHoraireDebut().getTime().getTime()));
      }
      if (c.getHoraireFin() != null) {
        req.setTimestamp(4, new Timestamp(c.getHoraireFin().getTime().getTime()));
      }

      req.setString(5, c.getNote());
      req.setBoolean(6, c.isDefautLivraison());

      // Pour les objets "Calendar"
      req.setTime(7, null);
      req.setTime(8, null);
      if (c.getDateInitiale() != null) {
        req.setTimestamp(7, new Timestamp(c.getDateInitiale().getTime().getTime()));
      }
      if (c.getDateLivraison() != null) {
        req.setTimestamp(8, new Timestamp(c.getDateLivraison().getTime().getTime()));
      }

      req.setObject(9, c.getProducteur() != null ? c.getProducteur().getIdProducteur() : null);
      req.setObject(10, c.getTournee() != null ? c.getTournee().getIdTournee() : null);
      req.setObject(11, c.getClient() != null ? c.getClient().getIdClient() : null);

      req.setInt(12, c.getIdCommande());
      // L'exécution de la requête

      return (req.executeUpdate() > 0);
    }
  }

  /**
   * Suppression d'une commande dans la base.
   *
   * @param id L'identifiant de la commande à supprimer.
   * @return True si la commande a été supprimée, false sinon.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public boolean delete(int id) throws SQLException {
    String sql = "DELETE FROM commandes WHERE idCommande = ?";
    try (PreparedStatement req = this.getCo().prepareStatement(sql)) {
      req.setInt(1, id);
      // Si l'entrée a été supprimée, on retourne true
      return req.executeUpdate() == 1;
      // Sinon, on retourne false
    }
  }

  /**
   * Suppression de plusieurs commandes dans la base.
   *
   * @param commandes Commandes à supprimer
   * @return {@code true} si les commandes ont été supprimées {@code false} sinon.
   * @throws SQLException Si la requête n'a pas pu avoir lieu
   */
  public boolean deleteAll(List<Commande> commandes) throws SQLException {
    boolean statut = true;

    try {
      // Début d'une transaction
      getCo().setAutoCommit(false);
      for (Commande commande : commandes) {
        statut = statut && delete(commande.getId());
      }
      // Si toutes les commandes ont été supprimées, la transaction est validée (commit), sinon
      // elle est annulée (rollback)
      if (statut) {
        getCo().commit();
      } else {
        getCo().rollback();
      }
    } catch (SQLException e) {
      // En cas de problème, la transaction est annulée
      getCo().rollback();
      throw e;
    } finally {
      getCo().setAutoCommit(true);
    }

    return statut;
  }

  /**
   * Recherche de commandes dans la base avec les attributs renseignés de l'objet.
   *
   * @param criteres Les critères de recherche de commandes.
   * @return Une collection d'objets Commande qui correspond aux critères mis en paramètre.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public ArrayList<Commande> find(LinkedHashMap<Commande.Champs, String> criteres) throws Exception {
    // On fait une requête avec les critères de recherche
    String sql = "SELECT * FROM commandes WHERE 1=1 " + criteresPourWHERE(criteres);
    try (PreparedStatement req = this.getCo().prepareStatement(sql)) {
      int noCritere = 1;
      for (String critere : criteres.values()) {
        req.setString(noCritere, critere);
        noCritere++;
      }
      // On récupère le résultat
      ResultSet rs = req.executeQuery();
      // On les stockera dans un ArrayList de commandes
      ArrayList<Commande> commandes = new ArrayList<>();
      // On aura besoin de créer les objets Client, Producteur et Tournee
      DAOClient daoC = new DAOClient();
      DAOProducteur daoP = new DAOProducteur();
      DAOTournee daoT = new DAOTournee();
      Client client;
      Producteur producteur;
      Tournee tournee;
      while (rs.next()) {
        // Tant qu'il y a des lignes dans le résultat
        client = daoC.findById(rs.getInt("idClient"));
        producteur = daoP.findById(rs.getInt("idProducteur"));
        tournee = daoT.findById(rs.getInt("idTournee"));

        // Pour les objets "Calendar"
        Calendar horaireDebut = null;
        if (rs.getTimestamp("horaireDebut") != null) {
          horaireDebut = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
          horaireDebut.setTime(rs.getTimestamp("horaireDebut"));
        }
        Calendar horaireFin = null;
        if (rs.getTimestamp("horaireFin") != null) {
          horaireFin = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
          horaireFin.setTime(rs.getTimestamp("horaireFin"));
        }
        Calendar dateInitiale = null;
        if (rs.getTimestamp("dateInitiale") != null) {
          dateInitiale = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
          dateInitiale.setTime(rs.getTimestamp("dateInitiale"));
        }
        Calendar dateLivraison = null;
        if (rs.getTimestamp("dateLivraison") != null) {
          dateLivraison = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
          dateLivraison.setTime(rs.getTimestamp("dateLivraison"));
        }


        commandes.add(new Commande(rs.getInt("idCommande"), rs.getString("libelle"),
                rs.getInt("poids"), horaireDebut, horaireFin,
                rs.getString("note"), rs.getBoolean("defautLivraison"),
                dateInitiale, dateLivraison, producteur, client, tournee));
      }
      return commandes;
    }
  }

  /**
   * Recherche d'une commande dans la base à partir de sa clé primaire.
   *
   * @param id L'identifiant de la commande.
   * @return L'objet Commande contenant les informations de la ligne trouvée.
   * Renvoie null si la commande n'a pas été trouvée.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public Commande findById(int id) throws Exception {
    // On réutilise la méthode find avec comme seul critère l'identifiant
    LinkedHashMap<Commande.Champs, String> criteres = new LinkedHashMap<>();
    criteres.put(Commande.Champs.idCommande, String.valueOf(id));
    ArrayList<Commande> resultatRecherche = find(criteres);
    if (resultatRecherche.isEmpty()) {
      return null;
    }
    return resultatRecherche.get(0);
  }
}