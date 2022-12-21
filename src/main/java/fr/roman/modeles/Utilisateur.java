package fr.roman.modeles;

import java.util.Arrays;
import java.util.Objects;

/**
* Représente l'utilisateur de l'application.
*/
public class Utilisateur extends Modele {

  private int idUtilisateur;
  private String nomUtilisateur;
  private String mdp;
  private byte[] sel;
  private String nom;
  private String prenom;
  private String email;
  private Role role;

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

  /**
   * Le constructeur par défaut de la classe Utilisateur.
   */
  public Utilisateur() {
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

  public enum Champs {email, mdp, nom, nomUtilisateur, prenom, role, idUtilisateur};

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Utilisateur that = (Utilisateur) o;
    return Objects.equals(nomUtilisateur, that.nomUtilisateur) && Objects.equals(mdp, that.mdp) && Arrays.equals(sel, that.sel) && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(email, that.email) && role == that.role;
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(nomUtilisateur, mdp, nom, prenom, email, role);
    result = 31 * result + Arrays.hashCode(sel);
    return result;
  }

  @Override
  public String toString() {
    return "Utilisateur{" +
            "idUtilisateur=" + idUtilisateur +
            ", nomUtilisateur='" + nomUtilisateur + '\'' +
            ", mdp='" + mdp + '\'' +
            ", nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            ", email='" + email + '\'' +
            ", role=" + role +
            '}';
  }
}