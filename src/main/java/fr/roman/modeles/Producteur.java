package fr.roman.modeles;

/**
* Représente un producteur.
*/
public class Producteur extends Modele {

  private int idProducteur;
  private int siret;
  private String tel;
  private Adresse adresse;
  private Utilisateur utilisateur;

  /**
   * Constructeur de la classe producteur.
   *
   * @param idProducteur L'identifiant du producteur dans la base.
   * @param siret Le SIRET du producteur.
   * @param tel Le numéro de téléphone du producteur.
   * @param adresse L'adresse du producteur.
   * @param utilisateur L'utilisateur associé au producteur.
   */
  public Producteur(int idProducteur, int siret, String tel, Adresse adresse, Utilisateur utilisateur) {
    this.idProducteur = idProducteur;
    this.siret = siret;
    this.tel = tel;
    this.adresse = adresse;
    this.utilisateur = utilisateur;
  }

  /**
   * Le constructeur par défaut de la classe Producteur.
   */
  public Producteur() {
  }

  public int getIdProducteur() {
    return idProducteur;
  }

  public int getSiret() {
    return siret;
  }

  public void setSiret(int siret) {
    this.siret = siret;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public Adresse getAdresse() {
    return adresse;
  }

  public void setAdresse(Adresse adresse) {
    this.adresse = adresse;
  }

  public Utilisateur getUtilisateur() {
    return utilisateur;
  }

  public void setUtilisateur(Utilisateur utilisateur) {
    this.utilisateur = utilisateur;
  }
}