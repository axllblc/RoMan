package fr.roman.modeles;

import java.util.Objects;

/**
* Représente un client de producteurs.
*/
public class Client extends Modele {

  private int idClient;
  private String nom;
  private String prenom;
  private String tel;
  private String mail;
  private String siret;
  private boolean particulier;
  private Adresse adresse;

  /**
   * Constructeur par défaut de la classe Client.
   */
  public Client() {
  }

  /**
   * Constructeur de la classe Client.
   *
   * @param idClient L'identifiant du client dans la base.
   * @param nom Le nom du client.
   * @param prenom Le prénom du client.
   * @param tel Le numéro de téléphone du client.
   * @param mail L'adresse mail du client.
   * @param siret Le SIRET du Client s'il est particulier
   * @param particulier Vrai si le client est particulier, faux sinon
   * @param adresse L'adresse du client.
   */
  public Client(int idClient, String nom, String prenom, String tel, String mail,
                String siret, boolean particulier, Adresse adresse) {
    this.idClient = idClient;
    this.nom = nom;
    this.prenom = prenom;
    this.tel = tel;
    this.mail = mail;
    this.siret = siret;
    this.particulier = particulier;
    this.adresse = adresse;
  }

  public int getIdClient() {
    return idClient;
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

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public String getSiret() {
    return siret;
  }

  public void setSiret(String siret) {
    this.siret = siret;
  }

  public boolean isParticulier() {
    return particulier;
  }

  public void setParticulier(boolean particulier) {
    this.particulier = particulier;
  }

  public Adresse getAdresse() {
    return adresse;
  }

  public void setAdresse(Adresse adresse) {
    this.adresse = adresse;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Client client = (Client) o;
    return siret == client.siret && particulier == client.particulier && Objects.equals(nom, client.nom) && Objects.equals(prenom, client.prenom) && Objects.equals(tel, client.tel) && Objects.equals(mail, client.mail) && Objects.equals(adresse, client.adresse);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nom, prenom, tel, mail, siret, particulier, adresse);
  }

  @Override
  public String toString() {
    return "Client{"
        + "idClient=" + idClient
        + ", nom='" + nom + '\''
        + ", prenom='" + prenom + '\''
        + ", tel='" + tel + '\''
        + ", mail='" + mail + '\''
        + ", siret=" + siret
        + ", particulier=" + particulier
        + ", adresse=" + adresse.toString()
        + '}';
  }
}