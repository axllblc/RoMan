package fr.roman.dao;

import fr.roman.modeles.Adresse;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.modeles.Utilisateur;
import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.util.BitSet.valueOf;

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
      if(u.getNomUtilisateur() == null || u.getMdp() == null
              || findByNomUtilisateur(u.getNomUtilisateur()) != null
              || u.getRole() != Role.ADMINISTRATEUR){
        return null;
      }

      // La requête
      PreparedStatement req = this.getCo().prepareStatement("INSERT INTO utilisateurs " +
              "(nomUtilisateur, mdp, nom, prenom, email) VALUES (?,?,?,?,?)",
              PreparedStatement.RETURN_GENERATED_KEYS);
      // L'ajout des valeurs
      req.setString(1, u.getNomUtilisateur());
      req.setString(2, new String(chiffrerMDP(u.getMdp()), StandardCharsets.UTF_8));
      req.setString(3, u.getNom());
      req.setString(4, u.getPrenom());
      req.setString(5, u.getEmail());
      // L'exécution de la requête
      req.execute();
      // Récupération de la clé primaire
      ResultSet rs = req.getGeneratedKeys();
      if(rs.next()){
        // Si l'ajout a eu lieu, on retourne l'objet utilisateur avec son identifiant
        return new Utilisateur(rs.getInt(1), u.getNomUtilisateur(),
                "", u.getNom(), u.getPrenom(),
                u.getEmail(), getRole(rs.getInt(1)));
      }
      // En cas d'échec de l'ajout, on ne renvoie rien
      return null;
    } catch (Exception e) { // En cas d'échec de la requête on ne renvoie rien
      return null;
    }
  }

  /**
   * Ajout d'un utilisateur producteur dans la base.
   *
   * @param p Le producteur à ajouter à la base, qui contient entre autre un objet Utilisateur.
   * @return
   */
  public Producteur insert(Producteur p){

    if(p.getUtilisateur() == null){
      // Si on n'a pas associé le producteur à un utilisateur à ajouter,
      // on annule la création de compte
      return null;
    }

    // TODO: 12/12/2022 Dans le cas où l'utilisateur ajouté est un producteur il est nécessaire de
    //  faire une transaction car l'ajout du producteur doit se faire après l'ajout de l'utilisateur.
    //  \Sinon, en cas de coupure entre les deux ajouts,le producteur risque d'être considéré
    //  comme un administrateur.
    return new Producteur();
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
              "SET nomUtilisateur = ?, mdp = ?, nom = ?, prenom = ?, email = ? " +
              "WHERE idUtilisateur = ?");
      req.setString(1, u.getNomUtilisateur());
      req.setString(2, u.getMdp());
      req.setString(3, u.getNom());
      req.setString(4, u.getPrenom());
      req.setString(5, u.getEmail());
      req.setInt(6, u.getIdUtilisateur());
      // L'exécution de la requête
      ResultSet rs = req.executeQuery();
      if (rs.next()) {
        return true;
      }
      else { // Si la modification n'a pas aboutie : il n'y a pas l'utilisateur...
        return false;
      }
    } catch (SQLException e) { // En cas d'échec de la requête
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
   * @param criteres Un objet HashMap<C, String> où la clé est le nom du critère
   *    * (un champ de la classe et table utilisateur) et la valeur est celle du critère.
   * @return Une collection d'objets qui correspond aux critères mis en paramètre.
   */
  @Override
  public ArrayList<Utilisateur> find(HashMap<Utilisateur.Champs, String> criteres) {
    PreparedStatement req;
    try {
      // On faut une requête avec les critères de recherche
      req = this.getCo().prepareStatement("SELECT * FROM utilisateurs WHERE 1=1 " +
                      criteresPourWHERE(criteres));
      // On récupère le résultat
      ResultSet rs = req.executeQuery();
      // On les stockera dans un ArrayList d'utilisateurs
      ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
      while (rs.next()) {
        // Tant qu'il y a des lignes dans le résultat
        utilisateurs.add(new Utilisateur(rs.getInt("idUtilisateur"), rs.getString("nomUtilisateur"),
                rs.getString("mdp"), rs.getString("nom"), rs.getString("prenom"),
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
    // On faut une requête avec les critères de recherche
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
   * L'algorithme choisi est "PBKDF2WithHmacSHA1". Le salage est sur 16 octets et
   * le chiffrage est itéré 10 000 fois. Le mot de passe est chiffré sur 128 bits.
   *
   * @param mdp Le mot de passe renseigné par l'utilisateur.
   * @return Le mot de passe chiffré.
   */
  private byte[] chiffrerMDP(@NotNull String mdp) throws NoSuchAlgorithmException, InvalidKeySpecException {
    SecureRandom random = new SecureRandom(); // On utilise un générateur d'octets.
    // On utilise un salage sur 16 octets
    byte[] salt = "FHPCUUhfjçNVIYPEH23435G3JKEG53BKgkjbtGH3V34HktkbIfghVTB6".getBytes();
    // On paramètre la clé de chiffrement selon les modalités décrites en documentation
    KeySpec spec = new PBEKeySpec(mdp.toCharArray(), salt, 10000, 128);
    // On récupère l'algorithme de chiffrement choisi
    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    // On procède au chiffrement du mot de passe et on le retourne
    return factory.generateSecret(spec).getEncoded();
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
        if (Arrays.equals( chiffrerMDP(mdp) , u.getMdp().getBytes() )){
          // Et si le mot de passe est correct, on retourne l'objet Utilisateur
          return u;
        }
      }
      //Sinon on ne renvoie rien
      return null;
    } catch (Exception e) {
      // En cas d'erreur pour le processus d'authentification on ne renvoie rien
      return null;
    }
  }
}