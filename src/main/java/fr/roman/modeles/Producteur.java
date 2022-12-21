package fr.roman.modeles;

import java.util.Objects;

/**
* Représente un producteur.
*/
public class Producteur extends Modele {

  private int idProducteur;
  private String siret;
  private String nomEtablissement;
  private String tel;
  private Adresse adresse;
  private Utilisateur utilisateur;

  /**
   * Constructeur de la classe producteur.
   *
   * @param idProducteur L'identifiant du producteur dans la base.
   * @param siret Le SIRET du producteur.
   * @param nomEtablissement Le nom de l'établissement du producteur.
   * @param tel Le numéro de téléphone du producteur.
   * @param adresse L'adresse du producteur.
   * @param utilisateur L'utilisateur associé au producteur.
   */
  public Producteur(int idProducteur, String siret, String nomEtablissement, String tel,
                    Adresse adresse, Utilisateur utilisateur) {
    this.idProducteur = idProducteur;
    this.siret = siret;
    this.nomEtablissement = nomEtablissement;
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

  public String getSiret() {
    return siret;
  }

  public void setSiret(String siret) {
    this.siret = siret;
  }

  public String getNomEtablissement() {
    return nomEtablissement;
  }

  public void setNomEtablissement(String nomEtablissement) {
    this.nomEtablissement = nomEtablissement;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Producteur that = (Producteur) o;
    return Objects.equals(siret, that.siret) && Objects.equals(nomEtablissement, that.nomEtablissement) && Objects.equals(tel, that.tel) && Objects.equals(adresse, that.adresse) && Objects.equals(utilisateur, that.utilisateur);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idProducteur, siret, nomEtablissement, tel, adresse, utilisateur);
  }

  @Override
  public String toString() {
    return "Producteur{"
        + "idProducteur=" + idProducteur
        + ", siret='" + siret + '\''
        + ", nomEtablissement='" + nomEtablissement + '\''
        + ", tel='" + tel + '\''
        + ", adresse=" + adresse
        + ", utilisateur=" + utilisateur.toString()
        + '}';
  }
}