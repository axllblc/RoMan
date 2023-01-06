package fr.roman.modeles;

import java.util.Arrays;
import java.util.Objects;

/**
* Représente l'utilisateur de l'application.
*/
public class Utilisateur extends Modele {
  private final int idUtilisateur;
  private String nomUtilisateur;
  private String mdp;
  private byte[] sel;
  private String nom;
  private String prenom;
  private String email;
  private Role role;

  /**
   * Constructeur sans paramètre de la classe {@link Utilisateur}.
   */
  public Utilisateur() {
    idUtilisateur = 0;
  }

  /**
   * Constructeur de la classe Utilisateur.
   *
   * @param idUtilisateur L'identifiant de l'utilisateur dans la base de donnée.
   * @param nomUtilisateur Le nom d'utilisateur (pour se connecter à l'espace dédié de l'application)
   * @param mdp Le mot de passe de l'utilisateur.
   * @param sel Le sel utilisé pour chiffrer le mot de passe de l'utilisateur.
   * @param nom Le nom de famille de l'utilisateur.
   * @param prenom Le prénom de l'utilisateur.
   * @param email L'adresse mail de l'utilisateur.
   * @param role Le rôle de l'utilisateur.
   */
  public Utilisateur(int idUtilisateur, String nomUtilisateur, String mdp, byte[] sel,
                     String nom, String prenom, String email, Role role) {
    this.idUtilisateur = idUtilisateur;
    this.nomUtilisateur = nomUtilisateur;
    this.mdp = mdp;
    this.sel = sel;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.role = role;
  }

  @Override
  public int getId() {
    return idUtilisateur;
  }

  public int getIdUtilisateur() {
    return idUtilisateur;
  }

  public String getNomUtilisateur() {
    return nomUtilisateur;
  }

  public void setNomUtilisateur(String nomUtilisateur) {
    this.nomUtilisateur = nomUtilisateur;
  }

  public String getMdp() {
    return mdp;
  }

  public void setMdp(String mdp) {
    this.mdp = mdp;
  }

  public byte[] getSel() {
    return sel;
  }

  public void setSel(byte[] sel) {
    this.sel = sel;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Utilisateur that = (Utilisateur) o;
    return
        Objects.equals(nomUtilisateur, that.nomUtilisateur)
        && Objects.equals(mdp, that.mdp)
        && Arrays.equals(sel, that.sel)
        && Objects.equals(nom, that.nom)
        && Objects.equals(prenom, that.prenom)
        && Objects.equals(email, that.email)
        && role == that.role;
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(nomUtilisateur, mdp, nom, prenom, email, role);
    result = 31 * result + Arrays.hashCode(sel);
    return result;
  }

  @Override
  public String toString() {
    return "Utilisateur{"
        + "idUtilisateur=" + idUtilisateur
        + ", nomUtilisateur='" + nomUtilisateur + '\''
        + ", mdp='" + mdp + '\''
        + ", sel=" + Arrays.toString(sel)
        + ", nom='" + nom + '\''
        + ", prenom='" + prenom + '\''
        + ", email='" + email + '\''
        + ", role=" + role
        + '}';
  }

  /**
   * Liste des champs de la table {@code Utilisateurs} dans la base de données.
   */
  public enum Champs implements ChampsModele {
    idUtilisateur(true, false, false, false, false),
    nomUtilisateur(false, false, true, false, false),
    mdp(false, true, true, false, false),
    sel(false, false, false, false, false),
    nom,
    prenom,
    email,
    role(false, false, false, false, false);

    private final boolean modifProd;
    private final boolean modifAdmin;
    private final boolean id;
    private final boolean idExt;
    private final boolean nullable;

    /**
     * Constructeur pour désigner des propriétés du champ
     *
     * @param id True si il s'agit de l'identifiant, false sinon
     * @param modifProd True si le champ est modifiable par un producteur, false sinon
     * @param modifAdmin True si le champ est modifiable par un administrateur, false sinon
     * @param idExt True s'il s'agit d'un identifiant qui correspond
     *              à une clé étrangère dans la BDD, false sinon
     * @param nullable True si le champ peut être null, false sinon
     */
    Champs(boolean id, boolean modifProd, boolean modifAdmin, boolean idExt, boolean nullable) {
      this.id = id;
      this.modifProd = modifProd;
      this.modifAdmin = modifAdmin;
      this.idExt = idExt;
      this.nullable = nullable;
    }
    /**
     * Par défaut, tous les utilisateurs peuvent modifier le champ,
     * ce n'est pas un id et il est nullable
     */
    Champs(){
      modifProd = true;
      modifAdmin = true;
      id = false;
      idExt = false;
      nullable = true;
    }

    @Override
    public boolean isModifProd() {
      return this.modifProd;
    }

    @Override
    public boolean isModifAdmin() {
      return this.modifAdmin;
    }

    @Override
    public boolean isId() {
      return this.id;
    }

    @Override
    public boolean isIdExt() {
      return this.idExt;
    }

    @Override
    public boolean isNullable() {
      return false;
    }
  }
}