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

  @Override
  public int getId() {
    return idClient;
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
    return
        Objects.equals(siret, client.siret)
        && particulier == client.particulier
        && Objects.equals(nom, client.nom)
        && Objects.equals(tel, client.tel)
        && Objects.equals(email, client.email)
        && Objects.equals(adresse, client.adresse);
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
        + ", adresse=" + adresse
        + '}';
  }

  /**
   * Liste des champs de la table {@code Clients} dans la base de données.
   */
  public enum Champs implements ChampsModele {
    idClient(true, false, false, false, false),
    nom(false, false, true, false, false),
    email(false, false, true, false, true),
    siret(false, false, true, false, true),
    tel(false, false, true, false, true),
    particulier(false, false, true, false, false),
    idAdresse(true, false, true, true, false);

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
      return this.nullable;
    }
  }
}