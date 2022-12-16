package fr.roman.dao;

import fr.roman.modeles.Commande;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.modeles.Utilisateur;
import javafx.util.Pair;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.*;



/**
 * DAO pour la classe Utilisateur.
 */

public class DAOUtilisateur extends DAO<Utilisateur, Utilisateur.Champs> {

  /**
   * Constructeur de la classe.
   *
   * @param co L'objet Connection pour le lien avec la base.
   */
  public DAOUtilisateur(Connection co) {
    super(co);
  }

  /**
   * Ajout d'un utilisateur qui est un administrateur
   *
   * @param u L'utilisateur administrateur à ajouter à la base.
   * @return Un objet Utilisateur avec son identifiant, null s'il n'a pas pu être ajouté.
   */
  @Override
  public Utilisateur insert(Utilisateur u) {
    try {
      // On s'assure que l'utilisateur à ajouter a un nom d'utilisateur (nouveau),
      // un mot de passe et que si c'est un producteur on est bien en train d'ajouter son compte
      // utilisateur dans le contexte d'une transaction qui se passe dans la méthode #insert
      if(u.getNomUtilisateur() == null || u.getMdp() == null
              || findByNomUtilisateur(u.getNomUtilisateur()) != null
              || (u.getRole() == Role.PRODUCTEUR
                  && !(Thread.currentThread().getStackTrace()[2].getMethodName().equals("insert")))){
        return null;
      }
      // La requête
      PreparedStatement req = this.getCo().prepareStatement("INSERT INTO utilisateurs " +
              "(nomUtilisateur, mdp, sel, nom, prenom, email) VALUES (?,?,?,?,?,?)",
              PreparedStatement.RETURN_GENERATED_KEYS);
      // L'ajout des valeurs
      req.setString(1, u.getNomUtilisateur());
      Pair<byte[], byte[]> chiffrement = chiffrerMDP(u.getMdp(), genererSel());
      req.setBytes(2,chiffrement.getKey()); // la clé est le mdp chiffré
      req.setBytes(3,chiffrement.getValue()); // la valeur est le sel
      req.setString(4, u.getNom());
      req.setString(5, u.getPrenom());
      req.setString(6, u.getEmail());
      // L'exécution de la requête
      req.execute();
      // Récupération de la clé primaire
      ResultSet rs = req.getGeneratedKeys();
      if(rs.next()){
        // Si l'ajout a eu lieu, on retourne l'objet utilisateur avec son identifiant
        return new Utilisateur(rs.getInt(1), u.getNomUtilisateur(),
                new String(chiffrement.getKey(), StandardCharsets.UTF_8), chiffrement.getValue(),
                u.getNom(), u.getPrenom(), u.getEmail(), getRole(rs.getInt(1)));
      }
      // En cas d'échec de l'ajout, on ne renvoie rien
      return null;
    } catch (Exception e) { // En cas d'échec de la requête, on ne renvoie rien
      return null;
    }
  }

  /**
   * Ajout d'un utilisateur producteur dans la base. On réalise une transaction pour assurer la
   *  Cohérence entre l'ajout d'un producteur et du compte utilisateur associé.
   *
   * @param p Le producteur à ajouter à la base, qui contient entre autre un objet Utilisateur.
   * @return
   */
  public Producteur insert(Producteur p){

    if(p.getUtilisateur() == null || p.getAdresse() == null){
      // Si on n'a pas associé le producteur à un utilisateur à ajouter,
      // ou qu'il n'a pas d'adresse, on annule la création de compte
      return null;
    }

    // On enregistre un point de sauvegarde où le producteur et son compte n'est pas ajouté
    Savepoint pointSauvegarde;
    try {
      pointSauvegarde = this.getCo().setSavepoint();
      this.getCo().setAutoCommit(false);
    } catch (SQLException e) {
      // Si on n'arrive pas à joindre la base pour définir un point de sauvegarde
      return null;
    }

    try {
      Utilisateur u = p.getUtilisateur();
      // Pour réaliser la transaction, on commence par faire la requête d'ajout de l'utilisateur
      u = this.insert(u);
      if (u == null){// Si cela n'a pas été possible, on lance une SQLException
        throw new SQLException();
      }
      // on ajoute l'utilisateur (avec maintenant un identifiant) au producteur (qui va être ajouté)
      p.setUtilisateur(u);
      // La requête
      PreparedStatement reqProducteur = this.getCo().prepareStatement("INSERT INTO producteurs " +
              "(siret, nomEtablissement, tel, idAdresse, idUtilisateur) " +
              "VALUES (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
      // L'ajout des valeurs
      reqProducteur.setString(1, p.getSiret());
      reqProducteur.setString(2, p.getNomEtablissement());
      reqProducteur.setString(3, p.getTel());
      reqProducteur.setInt(4, p.getAdresse().getIdAdresse());
      reqProducteur.setInt(5, p.getUtilisateur().getIdUtilisateur());

      // L'exécution de la requête
      reqProducteur.execute();
      // Récupération de la clé primaire
      ResultSet rs = reqProducteur.getGeneratedKeys();
      if(!rs.next()){ // Si l'ajout n'a pas eu lieu, on renvoie une exception
        throw new SQLException();
      }
      p = new Producteur(rs.getInt(1), p.getSiret(),
              p.getNomEtablissement(), p.getTel(), p.getAdresse(), p.getUtilisateur());
      // La transaction s'est bien passée, on peut libérer le point de sauvegarde
      this.getCo().releaseSavepoint(pointSauvegarde);
      this.getCo().setAutoCommit(true);
      return p;
    } catch (SQLException e) {
      e.printStackTrace();
      try {
        // En cas de problème, on revient à notre point de sauvegarde
        this.getCo().rollback(pointSauvegarde);
        return null;
      } catch (SQLException ex) {

        return null;
      }
    }
  }

  /**
   * Mise à jour d'un utilisateur dans la base.
   *
   * @param u L'utilisateur avec les champs à mettre à jour.
   * @return True si l'utilisateur a été modifié, false sinon.
   */
  @Override
  public boolean update(Utilisateur u) {
    try {
      PreparedStatement req  = this.getCo().prepareStatement("UPDATE utilisateurs " +
              "SET nomUtilisateur = ?, mdp = ?, sel = ?, nom = ?, prenom = ?, email = ? " +
              "WHERE idUtilisateur = ?");
      req.setString(1, u.getNomUtilisateur());
      req.setBytes(2, Base64.getDecoder().decode(u.getMdp()));
      req.setBytes(3, u.getSel());
      req.setString(4, u.getNom());
      req.setString(5, u.getPrenom());
      req.setString(6, u.getEmail());
      req.setInt(7, u.getIdUtilisateur());
      // L'exécution de la requête
      req.execute();
      return true;
    } catch (SQLException e) { // En cas d'échec de la requête : on renvoie false
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Suppression d'un utilisateur dans la base
   *
   * @param id L'identifiant de l'utilisateur à supprimer.
   * @return True si l'utilisateur a été supprimé, false sinon.
   */
  @Override
  public boolean delete(int id) {
    try {
      if(id == 1){
        // Si on cherche a supprimer le super-administrateur (a pour identifiant 1), on ne peut pas.
        return false;
      }
      PreparedStatement req = this.getCo().prepareStatement("DELETE FROM utilisateurs WHERE idUtilisateur = ?");
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
   * Recherche de données dans une table de la base avec les attributs renseignés de l'utilisateur.
   *
   * @param criteres Un objet HashMap où la clé est le nom du critère
   *        (un champ de la classe et table utilisateur) et la valeur est celle du critère.
   * @return Une collection d'objets qui correspond aux critères mis en paramètre.
   */
  @Override
  public ArrayList<Utilisateur> find(HashMap<Utilisateur.Champs, String> criteres) {
    PreparedStatement req;
    try {
      // On fait une requête avec les critères de recherche
      req = this.getCo().prepareStatement("SELECT * FROM utilisateurs WHERE 1=1 " +
                      criteresPourWHERE(criteres));
      // On récupère le résultat
      ResultSet rs = req.executeQuery();
      // On les stockera dans un ArrayList d'utilisateurs
      ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
      while (rs.next()) {
        // Tant qu'il y a des lignes dans le résultat
        // Note : on utilise Base64 pour convertir les bits de la base de donnée en chaine de caractère
        utilisateurs.add(new Utilisateur(rs.getInt("idUtilisateur"), rs.getString("nomUtilisateur"),
                Base64.getEncoder().encodeToString(rs.getBytes("mdp")), rs.getBytes("sel"), rs.getString("nom"), rs.getString("prenom"),
                rs.getString("email"), getRole(rs.getInt("idUtilisateur"))));
      }
      return utilisateurs;
    } catch (Exception e) {
      // On renvoie un ArrayList vide si la requête n'a pas pu être effectuée.
      return new ArrayList<Utilisateur>();
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
    if(idUtilisateur == 1){
      // Le root ou super-administrateur a l'identifiant 1
      return Role.ROOT;
    }
    // Pour retrouver on recherche la présence de l'identifiant dans la table producteur
    HashMap<Utilisateur.Champs, String> criteres = new HashMap<Utilisateur.Champs, String>();
    criteres.put(Utilisateur.Champs.idUtilisateur, String.valueOf(idUtilisateur));
    PreparedStatement req;
    // On fait une requête avec les critères de recherche
    req = this.getCo().prepareStatement("SELECT idUtilisateur FROM producteurs WHERE 1=1 " +
            criteresPourWHERE(criteres));
    // On récupère le résultat
    ResultSet rs = req.executeQuery();
    if (rs.next()) {
      return Role.PRODUCTEUR;
    }
    else{
      return Role.ADMINISTRATEUR;
    }
  }

  /**
   * Recherche d'un utilisateur à partir de sa clé primaire
   *
   * @param id L'identifiant de l'utilisateur.
   * @return L'utilisateur trouvé. Renvoie null s'il n'a pas été trouvée.
   */
  @Override
  public Utilisateur findById(int id) {
    // On réutilise la méthode find avec comme seul critère l'identifiant
    HashMap<Utilisateur.Champs, String> criteres = new HashMap<Utilisateur.Champs, String>();
    criteres.put(Utilisateur.Champs.idUtilisateur, String.valueOf(id));
    ArrayList<Utilisateur> resultatRecherche = find(criteres);
    if(resultatRecherche.isEmpty()){
      return null;
    }
    return resultatRecherche.get(0);
  }

  /**
   * Recherche la présence d'un utilisateur dans la base
   * @param nomUtilisateur Le nom d'utilisateur recherché
   * @return Un objet Utilisateur contenant les informations de l'utilisateur trouvé, null sinon
   */
  public Utilisateur findByNomUtilisateur(String nomUtilisateur) {
    // On réutilise la méthode find avec comme seul critère le nom d'utilisateur
    HashMap<Utilisateur.Champs, String> criteres = new HashMap<Utilisateur.Champs, String>();
    criteres.put(Utilisateur.Champs.nomUtilisateur, String.valueOf(nomUtilisateur));
    // On récupère le résultat
    ArrayList<Utilisateur> resultatRecherche = find(criteres);
    if(resultatRecherche.isEmpty()){
      // Si l'utilisateur n'a pas été trouvé avec ce nom d'utilisateur, on renvoie null
      return null;
    }
    return resultatRecherche.get(0);
  }

  /**
   * Cette méthode sert à chiffrer le mot de passe pour être stocké dans la base.
   * L'algorithme choisi est "PBKDF2WithHmacSHA1".
   * Le salage (en paramètre) et le mot de passe (chiffré) ont une entropie de 248 bits (31 octets).
   * On itère 10 000 fois le chiffrement
   *
   * @param mdp Le mot de passe renseigné par l'utilisateur.
   * @return Un objet Pair avec le mot de passe en première position et le sel en deuxième position
   */
  private Pair<byte[], byte[]> chiffrerMDP(String mdp, byte[] sel) throws NoSuchAlgorithmException, InvalidKeySpecException {
    // On paramètre la clé de chiffrement selon les modalités décrites en documentation
    KeySpec spec = new PBEKeySpec(mdp.toCharArray(), sel, 10000, 248);
    // On récupère l'algorithme de chiffrement choisi
    SecretKeyFactory algo = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    // On procède au chiffrement du mot de passe et on le retourne avec le sel utilisé
    return new Pair<>(algo.generateSecret(spec).getEncoded(), sel);
  }

  /**
   * Méthode servant à générer un sel ayant une entropie de 32 octets.
   * @return Un sel sous forme de tableau de bits.
   */
  private static byte[] genererSel() {
    SecureRandom random = new SecureRandom(); // On utilise un générateur d'octets.
    // On génère le sel sur 31 octets
    return random.generateSeed(31);
  }

  /**
     *
     * @param nomUtilisateur Le nom d'utilisateur
     * @param mdp Le mot de passe renseigné (non chiffré)
     * @return Un objet utilisateur correspondant au nom d'utilisateur.
     *         Renvoie null si le nom d'utilisateur et/ou le mot de passe est incorrect.
     */
  public Utilisateur authentification(String nomUtilisateur, String mdp) {
    try {
      Utilisateur u = findByNomUtilisateur(nomUtilisateur);
      if (u != null){ // Si le nom d'utilisateur existe
        // Note : on utilise Base64 pour convertir le mot de passe (chaine de caractère) en tableau de bits
        if (Arrays.equals( chiffrerMDP(mdp, u.getSel()).getKey() , Base64.getDecoder().decode(u.getMdp()))){
          // Et si le mot de passe est correct, on retourne l'objet Utilisateur
          return u;
        }
      }
      //Sinon on ne renvoie rien
      return null;
    } catch (Exception e) {
      // En cas d'erreur pour le processus d'authentification, on ne renvoie rien
      return null;
    }
  }
}