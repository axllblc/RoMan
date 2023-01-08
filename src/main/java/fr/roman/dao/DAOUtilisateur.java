package fr.roman.dao;

import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.modeles.Utilisateur;

import java.sql.*;
import java.util.*;



/**
 * DAO pour la classe Utilisateur.
 */

public class DAOUtilisateur extends DAO<Utilisateur, Utilisateur.Champs> {

  /**
   * Constructeur de la classe.
   */
  public DAOUtilisateur() throws Exception {
    super();
  }

  /**
   * Ajout d'un utilisateur qui est un administrateur
   *
   * @param u L'utilisateur administrateur à ajouter à la base.
   * @return Un objet Utilisateur avec son identifiant.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public Utilisateur insert(Utilisateur u) throws Exception {
    // On s'assure que l'utilisateur à ajouter a un nom d'utilisateur (nouveau),
    // un mot de passe et que si c'est un producteur on est bien en train d'ajouter son compte
    // utilisateur dans le contexte d'une transaction qui se passe dans la méthode #insert
    if (u.getNomUtilisateur() == null){
      throw new Exception("Nom d'utilisateur non renseigné");
    }
    if (u.getMdp() == null){
      throw new Exception("Mot de passe non renseigné");
    }
    if (findByNomUtilisateur(u.getNomUtilisateur()) != null){
      throw new Exception("Nom d'utilisateur déjà renseigné");
    }
    if ((u.getRole() == Role.PRODUCTEUR
                && !(Thread.currentThread().getStackTrace()[2].getMethodName().equals("insert")))) {
      throw new Exception("Ajout de compte utilisateur pour un producteur sans ajouter un objet producteur avant");
    }
    // La requête
    String sql = "INSERT INTO utilisateurs (nomUtilisateur, mdp, sel, nom, prenom, email) "
            + "VALUES (?,?,?,?,?,?)";
    try (PreparedStatement req = this.getCo().prepareStatement(sql,
            PreparedStatement.RETURN_GENERATED_KEYS)) {
      // L'ajout des valeurs
      req.setString(1, u.getNomUtilisateur());
      req.setBytes(2, Base64.getDecoder().decode(u.getMdp()));
      req.setBytes(3, u.getSel());
      req.setString(4, u.getNom());
      req.setString(5, u.getPrenom());
      req.setString(6, u.getEmail());
      // L'exécution de la requête
      req.execute();
      // Récupération de la clé primaire
      ResultSet rs = req.getGeneratedKeys();
      if (rs.next()) {
        // Si l'ajout a eu lieu, on retourne l'objet utilisateur avec son identifiant
        return new Utilisateur(rs.getInt(1), u.getNomUtilisateur(), u.getMdp(),
                u.getSel(), u.getNom(), u.getPrenom(), u.getEmail(),
                getRole(rs.getInt(1)));
      }
      // En cas d'échec de l'ajout
      throw new Exception(new Throwable("Echec de l'insertion de l'utilisateur"));
    }
  }

  /**
   * Ajout d'un utilisateur producteur dans la base. On réalise une transaction pour assurer la
   *  Cohérence entre l'ajout d'un producteur et du compte utilisateur associé.
   *
   * @param p Le producteur à ajouter à la base, qui contient entre autre un objet Utilisateur.
   * @return L'objet métier Producteur inséré avec son compte utilisateur en attribut
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  public Producteur insert(Producteur p) throws Exception {
    if (p.getUtilisateur() == null || p.getAdresse() == null) {
      // Si on n'a pas associé le producteur à un utilisateur à ajouter,
      // ou qu'il n'a pas d'adresse, on annule la création de compte
      throw new Exception("Adresse et/ou utilisateur non renseignés");
    }
    // On vérifie que le Siret n'existe pas déjà dans la base
    LinkedHashMap<Producteur.Champs, String> critereSiret = new LinkedHashMap<>();
    critereSiret.put(Producteur.Champs.siret, p.getSiret());
    DAOProducteur daoProducteur = new DAOProducteur();
    if (!daoProducteur.find(critereSiret).isEmpty()) {
      throw new Exception("Siret déjà renseignée");
    }
    // La requête (deuxième) après l'ajout de l'utilisateur
    String sql = "INSERT INTO producteurs (siret, nomEtablissement, tel, idAdresse, idUtilisateur) "
            + "VALUES (?,?,?,?,?)";
    try (PreparedStatement reqProducteur = this.getCo()
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
      Utilisateur u = p.getUtilisateur();

      // On désactive l'autocommit pour réaliser une transaction
      this.getCo().setAutoCommit(false);
      // Pour réaliser la transaction, on commence par faire la requête d'ajout de l'utilisateur
      u = this.insert(u);
      // on ajoute l'utilisateur (avec maintenant un identifiant) au producteur (qui va être ajouté)
      u.setRole(Role.PRODUCTEUR); // On ajoute un producteur
      p.setUtilisateur(u);
      // L'ajout des valeurs pour la requête d'ajout dans la table "Producteur"
      reqProducteur.setString(1, p.getSiret());
      reqProducteur.setString(2, p.getNomEtablissement());
      reqProducteur.setString(3, p.getTel());
      reqProducteur.setObject(4, p.getAdresse() != null ? p.getAdresse().getIdAdresse() : null);
      reqProducteur.setInt(5, p.getUtilisateur().getIdUtilisateur());
      // L'exécution de la requête
      reqProducteur.execute();
      // Récupération de la clé primaire
      ResultSet rs = reqProducteur.getGeneratedKeys();
      if (!rs.next()) { // Si l'ajout n'a pas eu lieu, on renvoie une exception
        throw new SQLException();
      }
      p = new Producteur(rs.getInt(1), p.getSiret(),
              p.getNomEtablissement(), p.getTel(), p.getAdresse(), p.getUtilisateur());
      // Toutes les requêtes se sont bien passées,
      // on peut commiter et repasser en mode "auto-commit"
      this.getCo().setAutoCommit(true);
      return p;
    }
  }

  /**
   * Mise à jour d'un utilisateur dans la base.
   *
   * @param u L'utilisateur avec les champs à mettre à jour.
   * @return True si l'utilisateur a été modifié, false sinon.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public boolean update(Utilisateur u) throws Exception {
    // On s'assure que l'utilisateur à ajouter a un nom d'utilisateur (nouveau),
    // un mot de passe et que si c'est un producteur on est bien en train d'ajouter son compte
    // utilisateur dans le contexte d'une transaction qui se passe dans la méthode #insert
    if (u.getNomUtilisateur() == null){
      throw new Exception("Nom d'utilisateur non renseigné");
    }
    if (u.getMdp() == null){
      throw new Exception("Mot de passe non renseigné");
    }

    // On vérifie que si on modifie le nom d'utilisateur, il n'existe pas déjà dans la base
    Utilisateur rechNomUti = findByNomUtilisateur(u.getNomUtilisateur());
    if (rechNomUti != null && rechNomUti.getIdUtilisateur() != u.getIdUtilisateur()){
      throw new Exception("Nom d'utilisateur déjà renseigné");
    }
    String sql = "UPDATE utilisateurs "
            + "SET nomUtilisateur = ?, mdp = ?, sel = ?, nom = ?, prenom = ?, email = ? "
            + "WHERE idUtilisateur = ?";
    try (PreparedStatement req  = this.getCo().prepareStatement(sql)) {
      req.setString(1, u.getNomUtilisateur());
      req.setBytes(2, Base64.getDecoder().decode(u.getMdp()));
      req.setBytes(3, u.getSel());
      req.setString(4, u.getNom());
      req.setString(5, u.getPrenom());
      req.setString(6, u.getEmail());
      req.setInt(7, u.getIdUtilisateur());
      // L'exécution de la requête
      return req.executeUpdate() > 0;
    }
  }

  /**
   * Suppression d'un utilisateur dans la base
   *
   * @param id L'identifiant de l'utilisateur à supprimer.
   * @return True si l'utilisateur a été supprimé, false sinon.
   * @throws SQLException Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public boolean delete(int id) throws SQLException {
    if (id == 1) {
      // Si on cherche a supprimer le super-administrateur (a pour identifiant 1), on ne peut pas.
      return false;
    }
    String sql = "DELETE FROM utilisateurs WHERE idUtilisateur = ?";
    try (PreparedStatement req = this.getCo().prepareStatement(sql)) {
      req.setInt(1, id);
      // Si l'entrée a été supprimée, on retourne true
      return req.executeUpdate() == 1;
      // Sinon, on retourne false
    }
  }

  /**
   * Recherche de données dans une table de la base avec les attributs renseignés de l'utilisateur.
   *
   * @param criteres Un objet HashMap où la clé est le nom du critère
   *        (un champ de la classe et table utilisateur) et la valeur est celle du critère.
   * @return Une collection d'objets qui correspond aux critères mis en paramètre.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public ArrayList<Utilisateur> find(LinkedHashMap<Utilisateur.Champs, String> criteres) throws Exception {
    // On fait une requête avec les critères de recherche
    String sql = "SELECT * FROM utilisateurs WHERE 1=1 " + criteresPourWHERE(criteres);
    try (PreparedStatement req = this.getCo().prepareStatement(sql)) {
      int noCritere = 1;
      for (String critere : criteres.values()) {
        req.setString(noCritere, critere);
        noCritere++;
      }
      // On récupère le résultat
      ResultSet rs = req.executeQuery();
      // On les stockera dans un ArrayList d'utilisateurs
      ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
      while (rs.next()) {
        // Tant qu'il y a des lignes dans le résultat
        // Note : on utilise Base64 pour convertir les bits de la base de donnée en chaine de caractère
        utilisateurs.add(new Utilisateur(rs.getInt("idUtilisateur"), rs.getString("nomUtilisateur"),
                Base64.getEncoder().encodeToString(rs.getBytes("mdp")), rs.getBytes("sel"),
                rs.getString("nom"), rs.getString("prenom"),
                rs.getString("email"), getRole(rs.getInt("idUtilisateur"))));
      }
      return utilisateurs;
    }
  }

  /**
   * Permet de trouver le rôle qu'a un utilisateur de l'application.
   * Se détermine par la présence ou non de son identifiant dans la table producteur
   *  (Si ce n'est pas un producteur, c'est un administrateur).
   *
   * @param idUtilisateur L'identifiant de l'utilisateur à vérifier
   * @return Le rôle de l'utilisateur
   * @throws Exception Si la requête n'a pas pu avoir lieu, on renvoie une exception
   */
  private Role getRole(int idUtilisateur) throws Exception {
    if (idUtilisateur == 1) {
      // Le root ou super-administrateur a l'identifiant 1
      return Role.ROOT;
    }
    // Pour retrouver on recherche la présence de l'identifiant dans la table producteur
    LinkedHashMap<Utilisateur.Champs, String> criteres = new LinkedHashMap<>();
    criteres.put(Utilisateur.Champs.idUtilisateur, String.valueOf(idUtilisateur));
    // On fait une requête avec les critères de recherche
    String sql = "SELECT idUtilisateur FROM producteurs WHERE 1=1 " + criteresPourWHERE(criteres);
    try (PreparedStatement req = this.getCo().prepareStatement(sql)) {
      int noCritere = 1;
      for (String critere : criteres.values()) {
        req.setString(noCritere, critere);
        noCritere++;
      }
      // On récupère le résultat
      ResultSet rs = req.executeQuery();
      if (rs.next()) {
        return Role.PRODUCTEUR;
      } else {
        return Role.ADMINISTRATEUR;
      }
    }
  }

  /**
   * Recherche d'un utilisateur à partir de sa clé primaire.
   *
   * @param id L'identifiant de l'utilisateur.
   * @return L'utilisateur trouvé. Renvoie null s'il n'a pas été trouvée.
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  @Override
  public Utilisateur findById(int id) throws Exception {
    // On réutilise la méthode find avec comme seul critère l'identifiant
    LinkedHashMap<Utilisateur.Champs, String> criteres = new LinkedHashMap<>();
    criteres.put(Utilisateur.Champs.idUtilisateur, String.valueOf(id));
    ArrayList<Utilisateur> resultatRecherche = find(criteres);
    if (resultatRecherche.isEmpty()) {
      return null;
    }
    return resultatRecherche.get(0);
  }

  /**
   * Recherche la présence d'un utilisateur dans la base.
   *
   * @param nomUtilisateur Le nom d'utilisateur recherché
   * @return Un objet Utilisateur contenant les informations de l'utilisateur trouvé, null sinon
   * @throws Exception Si la requête n'a pas pu avoir lieu.
   */
  public Utilisateur findByNomUtilisateur(String nomUtilisateur) throws Exception {
    // On réutilise la méthode find avec comme seul critère le nom d'utilisateur
    LinkedHashMap<Utilisateur.Champs, String> criteres = new LinkedHashMap<>();
    criteres.put(Utilisateur.Champs.nomUtilisateur, String.valueOf(nomUtilisateur));
    // On récupère le résultat
    ArrayList<Utilisateur> resultatRecherche = find(criteres);
    if (resultatRecherche.isEmpty()) {
      // Si l'utilisateur n'a pas été trouvé avec ce nom d'utilisateur, on renvoie null
      return null;
    }
    return resultatRecherche.get(0);
  }
}