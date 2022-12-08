package fr.roman.modeles;

/**
* Représente l'utilisateur de l'application.
*/
public class Utilisateur extends Modele {

  private int idUtilisateur;
  private String nomUtilisateur;
  private String mdp;
  private String nom;
  private String prenom;
  private String mail;
  private Role role;

  /**
   * Constructeur de la classe Utilisateur.
   *
   * @param idUtilisateur L'identifiant de l'utilisateur dans la base de donnée.
   * @param nomUtilisateur Le nom d'utilisateur (pour se connecter à l'espace dédié de l'application)
   * @param mdp Le mot de passe de l'utilisateur.
   * @param nom Le nom de famille de l'utilisateur.
   * @param prenom Le prénom de l'utilisateur.
   * @param mail L'adresse mail de l'utilisateur.
   * @param role Le rôle de l'utilisateur.
   */
  public Utilisateur(int idUtilisateur, String nomUtilisateur, String mdp,String nom,
                     String prenom, String mail, Role role) {
    this.idUtilisateur = idUtilisateur;
    this.nomUtilisateur = nomUtilisateur;
    this.mdp = mdp;
    this.nom = nom;
    this.prenom = prenom;
    this.mail = mail;
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

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}