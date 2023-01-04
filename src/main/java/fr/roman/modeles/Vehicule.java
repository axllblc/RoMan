package fr.roman.modeles;

import java.util.Objects;

/**
 * Représente le véhicule d'un producteur.
 */
public class Vehicule extends Modele {
  private final int idVehicule;
  private String immatriculation;
  private int poidsMax;
  private String libelle;
  private Producteur producteur;

  /**
   * Constructeur sans paramètre de la classe {@link Vehicule}.
   */
  public Vehicule() {
    idVehicule = 0;
  }

  /**
   * Constructeur de la classe Vehicule.
   *
   * @param idVehicule L'identifiant du véhicule
   * @param immatriculation L'immatriculation du véhicule
   * @param poidsMax Le poids maximal de chargement du véhicule (en kg)
   * @param libelle Le libellé du véhicule.
   * @param producteur Le producteur propriétaire du véhicule.
   */
  public Vehicule(int idVehicule, String immatriculation, int poidsMax, String libelle,
                  Producteur producteur) {
    this.idVehicule = idVehicule;
    this.immatriculation = immatriculation;
    this.poidsMax = poidsMax;
    this.libelle = libelle;
    this.producteur = producteur;
  }

  @Override
  public int getId() {
    return idVehicule;
  }

  public int getIdVehicule() {
    return idVehicule;
  }

  public String getImmatriculation() {
    return immatriculation;
  }

  public void setImmatriculation(String immatriculation) {
    this.immatriculation = immatriculation;
  }

  public int getPoidsMax() {
    return poidsMax;
  }

  /**
   * Définir le poids maximum de l'ensemble des commandes admissible dans le véhicule.
   *
   * @param poidsMax Poids maximum, exprimé en kg
   */
  public void setPoidsMax(int poidsMax) {
    this.poidsMax = poidsMax;
  }

  public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

  public Producteur getProducteur() {
    return producteur;
  }

  public void setProducteur(Producteur producteur) {
    this.producteur = producteur;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vehicule vehicule = (Vehicule) o;
    return
        poidsMax == vehicule.poidsMax
        && Objects.equals(immatriculation, vehicule.immatriculation)
        && Objects.equals(libelle, vehicule.libelle)
        && Objects.equals(producteur, vehicule.producteur);
  }

  @Override
  public int hashCode() {
    return Objects.hash(immatriculation, poidsMax, libelle, producteur);
  }

  @Override
  public String toString() {
    return "Vehicule{"
        + "idVehicule=" + idVehicule
        + ", immatriculation='" + immatriculation + '\''
        + ", poidsMax=" + poidsMax
        + ", libelle='" + libelle + '\''
        + ", producteur=" + producteur
        + '}';
  }

  /**
   * Liste des champs de la table {@code Vehicules} dans la base de données.
   */
  public enum Champs implements ChampsModele {
    idVehicule(true, false, false, false, false),
    immatriculation(false, true, true, false, false),
    poidsMax(false, true, true, false, false),
    libelle,
    idProducteur(false, false, true, true, false);
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