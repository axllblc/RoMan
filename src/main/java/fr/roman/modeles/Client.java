package fr.roman.modeles;

import java.util.Objects;

/**
* Représente un client de producteurs.
*/
public class Client extends Modele {
  private final int idClient;
  private String nom;
  private String tel;
  private String email;
  private String siret;
  private boolean particulier;
  private Adresse adresse;

  /**
   * Constructeur sans paramètre de la classe {@link Client}.
   */
  public Client() {
    idClient = 0;
  }

  /**
   * Constructeur de la classe Client.
   *
   * @param idClient L'identifiant du client dans la base.
   * @param nom Le nom du client.
   * @param tel Le numéro de téléphone du client.
   * @param email L'adresse mail du client.
   * @param siret Le SIRET du Client s'il est particulier
   * @param particulier Vrai si le client est particulier, faux sinon
   * @param adresse L'adresse du client.
   */
  public Client(int idClient, String nom, String tel, String email, String siret,
                boolean particulier, Adresse adresse) {
    this.idClient = idClient;
    this.nom = nom;
    this.tel = tel;
    this.email = email;
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

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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
    return siret == client.siret && particulier == client.particulier && Objects.equals(nom, client.nom) && Objects.equals(tel, client.tel) && Objects.equals(email, client.email) && Objects.equals(adresse, client.adresse);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nom, tel, email, siret, particulier, adresse);
  }

  @Override
  public String toString() {
    return "Client{"
        + "idClient=" + idClient
        + ", nom='" + nom + '\''
        + ", tel='" + tel + '\''
        + ", mail='" + email + '\''
        + ", siret=" + siret
        + ", particulier=" + particulier
        + ", adresse=" + adresse.toString()
        + '}';
  }
}