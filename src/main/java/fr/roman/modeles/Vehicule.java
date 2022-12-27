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

  /**
   * Le constructeur par défaut de la classe Vehicule.
   */
  public Vehicule() {
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
    return poidsMax == vehicule.poidsMax && Objects.equals(immatriculation, vehicule.immatriculation) && Objects.equals(libelle, vehicule.libelle) && Objects.equals(producteur, vehicule.producteur);
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
        + ", producteur=" + producteur.toString()
        + '}';
  }
}