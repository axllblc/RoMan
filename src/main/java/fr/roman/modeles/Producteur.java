package fr.roman.modeles;

import java.util.Objects;

/**
* Représente un producteur.
*/
public class Producteur extends Modele {
  private final int idProducteur;
  private String siret;
  private String nomEtablissement;
  private String tel;
  private Adresse adresse;
  private Utilisateur utilisateur;

  /**
   * Constructeur sans paramètre de la classe {@link Producteur}.
   */
  public Producteur() {
    idProducteur = 0;
  }

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

  @Override
  public int getId() {
    return idProducteur;
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
    return
        Objects.equals(siret, that.siret)
        && Objects.equals(nomEtablissement, that.nomEtablissement)
        && Objects.equals(tel, that.tel)
        && Objects.equals(adresse, that.adresse)
        && Objects.equals(utilisateur, that.utilisateur);
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
        + ", utilisateur=" + utilisateur
        + '}';
  }

  /**
   * Liste des champs de la table {@code Producteurs} dans la base de données.
   */
  public enum Champs implements ChampsModele {
    idProducteur(true, false, false, false, false),
    siret(false, false, true, false, false),
    nomEtablissement(false, false, true, false, false),
    tel,
    idAdresse(false, false, true, true, false),
    idUtilisateur(false, false, true, true, false);

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