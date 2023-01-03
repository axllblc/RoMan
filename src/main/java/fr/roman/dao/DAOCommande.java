package fr.roman.dao;

import fr.roman.modeles.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

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
   * @return Un objet Commande avec son identifiant, null s'il n'a pas pu être ajouté.
   */
  @Override
  public Commande insert(Commande c) {
    // La commande est nécessairement associée à un producteur et un client
    if (c.getClient() == null || c.getProducteur() == null) {
      return null;
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

      req.setInt(9, c.getProducteur().getIdProducteur());
      req.setInt(10, c.getTournee().getIdTournee());
      req.setInt(11, c.getClient().getIdClient());
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
      // En cas d'échec de l'ajout, on ne renvoie rien
      return null;
    } catch (Exception e) { // En cas d'échec de la requête, on ne renvoie rien
      return null;
    }
  }

  /**
   * Mise à jour d'une commande dans la base.
   *
   * @param c Un objet Commande.
   * @return True si la commande a été modifiée, false sinon.
   */
  @Override
  public boolean update(Commande c) {
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

      req.setInt(9, c.getProducteur().getIdProducteur());
      req.setInt(10, c.getTournee().getIdTournee());
      req.setInt(11, c.getClient().getIdClient());
      req.setInt(12, c.getIdCommande());
      // L'exécution de la requête
      req.execute();
      return true;
    } catch (SQLException e) { // En cas d'échec de la requête
      return false;
    }
  }

  /**
   * Suppression d'une commande dans la base.
   *
   * @param id L'identifiant de la commande à supprimer.
   * @return True si la commande a été supprimée, false sinon.
   */
  @Override
  public boolean delete(int id) {
    String sql = "DELETE FROM commandes WHERE idCommande = ?";
    try (PreparedStatement req = this.getCo().prepareStatement(sql)) {
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
   * Recherche de commandes dans la base avec les attributs renseignés de l'objet.
   *
   * @param criteres Les critères de recherche de commandes.
   * @return Une collection d'objets Commande qui correspond aux critères mis en paramètre.
   */
  @Override
  public ArrayList<Commande> find(HashMap<Commande.Champs, String> criteres) {
    // On fait une requête avec les critères de recherche
    String sql = "SELECT * FROM commandes WHERE 1=1 " + criteresPourWHERE(criteres);
    try (PreparedStatement req = this.getCo().prepareStatement(sql)) {
      // On récupère le résultat
      ResultSet rs = req.executeQuery();
      // On les stockera dans un ArrayList de commandes
      ArrayList<Commande> commandes = new ArrayList<Commande>();
      // On aura besoin de créer les objets Client, Producteur et Tournee
      DAOClient daoC = new DAOClient();
      DAOProducteur daoP = new DAOProducteur();
      DAOTournee daoT = new DAOTournee();
      Client client;
      Producteur producteur;
      Tournee tournee;
      while (rs.next()) {
        // Tant qu'il y a des lignes dans le résultat
        client = daoC.findById(Integer.parseInt(rs.getString("idClient")));
        producteur = daoP.findById(Integer.parseInt(rs.getString("idProducteur")));
        tournee = daoT.findById(Integer.parseInt(rs.getString("idTournee")));

        // Pour les objets "Calendar"
        Calendar horaireDebut = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        if (rs.getTimestamp("horaireDebut") != null) {
          horaireDebut.setTime(rs.getTimestamp("horaireDebut"));
        }
        Calendar horaireFin = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        if (rs.getTimestamp("horaireFin") != null) {
          horaireFin.setTime(rs.getTimestamp("horaireFin"));
        }
        Calendar dateInitiale = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        if (rs.getTimestamp("dateInitiale") != null) {
          dateInitiale.setTime(rs.getTimestamp("dateInitiale"));
        }
        Calendar dateLivraison = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        if (rs.getTimestamp("dateLivraison") != null) {
          dateLivraison.setTime(rs.getTimestamp("dateLivraison"));
        }


        commandes.add(new Commande(rs.getInt("idCommande"), rs.getString("libelle"),
                rs.getInt("poids"), horaireDebut, horaireFin,
                rs.getString("note"), rs.getBoolean("defautLivraison"),
                dateInitiale, dateLivraison, producteur, client, tournee));
      }
      return commandes;
    } catch (Exception e) {
      // On renvoie un ArrayList vide si la requête n'a pas pu être effectuée correctement.
      return new ArrayList<>();
    }
  }

  /**
   * Recherche d'une commande dans la base à partir de sa clé primaire.
   *
   * @param id L'identifiant de la commande.
   * @return L'objet Commande contenant les informations de la ligne trouvée.
   * Renvoie null si la commande n'a pas été trouvée.
   */
  @Override
  public Commande findById(int id) {
    // On réutilise la méthode find avec comme seul critère l'identifiant
    HashMap<Commande.Champs, String> criteres = new HashMap<>();
    criteres.put(Commande.Champs.idCommande, String.valueOf(id));
    ArrayList<Commande> resultatRecherche = find(criteres);
    if (resultatRecherche.isEmpty()) {
      return null;
    }
    return resultatRecherche.get(0);
  }
}