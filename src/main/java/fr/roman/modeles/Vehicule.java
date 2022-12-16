package fr.roman.modeles;

/**
 * Représente le véhicule d'un producteur.
 */
public class Vehicule extends Modele {

  private int idVehicule;
  private String immatriculation;
  private int poidsMax;
  private String libelle;
  private Producteur producteur;

  /**
   * Constructeur de la classe Vehicule.
   *
   * @param idVehicule L'identifiant du véhicule
   * @param immatriculation L'immatriculation du véhicule
   * @param poidsMax Le poids maximal de chargement du véhicule (en kg)
   * @param libelle Le libellé du véhicule.
   * @param producteur Le producteur propriétaire du véhicule.
   */
  public Vehicule(int idVehicule, String immatriculation, int poidsMax,
                  String libelle, Producteur producteur) {
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
   * @param poidsMax en kg
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

  public enum Champs {immatriculation, libelle, poidsMax, icProducteur, idVehicule}

  @Override
  public String toString() {
    return "Vehicule{" +
            "idVehicule=" + idVehicule +
            ", immatriculation='" + immatriculation + '\'' +
            ", poidsMax=" + poidsMax +
            ", libelle='" + libelle + '\'' +
            ", producteur=" + producteur.toString() +
            '}';
  }
}